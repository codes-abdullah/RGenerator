package codes.abdullah.rit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class RHandler extends DefaultHandler {
	private StringBuilder sb = new StringBuilder();
	private RRoot r;
	private RClass scope;// currentClass, currentParent;
	private RArray currentArray;
	private File r_xml_file;
	private String root_dir, relative_to;

	public RHandler(String r_xml_path) throws IOException {
		root_dir = new File(".").getCanonicalPath();
		r_xml_file = Utils.resolve(root_dir, r_xml_path, r_xml_path);
		relative_to = new File(r_xml_file.toString()).getCanonicalFile().getParent();
		URL url = r_xml_file.toURI().toURL();
		if (url == null) {
			throw new NoSuchFileException(r_xml_path);
		}
	}

	public RRoot getR() {
		return r;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		sb.append(ch, start, length);
//		System.out.println(new String(ch, start, length));
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		r = new RRoot();

//		System.out.println("currentClass: R");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		super.startElement(uri, localName, qName, attrs);

		try {
			log("start: " + qName);

			RPair rpair = new RPair();
			RPath rpath = new RPath();
			RKey rkey = new RKey();
			RString rstr = new RString();
			RStringArray rstrarr = new RStringArray();
			RArray rarr = new RArray();
			RClass rclass = new RClass();

			String modifiers = "";
			String key = getAttr(attrs, RRoot.ATT_KEY, null);
			String type = getAttr(attrs, RRoot.ATT_TYPE, null);
			String name = getAttr(attrs, RRoot.ATT_NAME, null);

			switch (qName) {
			case RRoot.TAG_PAIR:
				rpair.setType(type == null ? String.class : Utils.forName(type));
				rpair.setKey(key);
				getScope().getPairs().add(rpair);
				break;
			case RRoot.TAG_KEY:
				rkey.setType(type == null ? String.class : Utils.forName(type));
				rkey.setKey(key);
				getScope().getKeys().add(rkey);
				break;
			case RRoot.TAG_STRING:
				rstr.setName(name);
				getScope().getStrings().add(rstr);
				break;
			case RRoot.TAG_STRINGARRAY:
				this.currentArray = rstrarr;
				rstrarr.setName(name);
				getScope().getStringArrays().add(rstrarr);
				break;
			case RRoot.TAG_ARRAY:
				rarr.setType(type == null ? String.class : Utils.forName(type));
				this.currentArray = rarr;
				rarr.setName(name);
				getScope().getArrays().add(rarr);
				break;
			case RRoot.TAG_ITEM:
				// nothing todo
				break;
			case RRoot.TAG_CLASS:
				modifiers = getAttr(attrs, RRoot.ATTR_MODIFIERS, "");
				rclass.setName(name);

				rclass.setModifiers(modifiers);
				getScope().getClasses().add(rclass);
				rclass.setParent(scope);
				setScope(rclass);
//				currentClass = rclass;
				break;
			case RRoot.TAG_RESOURCE_PATHS:
				String rootDirAttr = getAttr(attrs, RRoot.ATTR_ROOT_DIR, "/");
				File rootDir = Utils.resolve(root_dir, relative_to, getAttr(attrs, RRoot.ATTR_ROOT_DIR, "/"));
				if (!rootDir.exists()) {
					
					Utils.resolve(root_dir, relative_to, getAttr(attrs, RRoot.ATTR_ROOT_DIR, "/"));
					throw new NoSuchFileException(rootDir.toString());
				}
//				rootDir = r_xml_path + rootDir;

				String matcherPattern = getAttr(attrs, RRoot.ATTR_MATCHER_PATTERN, "glob:**");
				if (!matcherPattern.startsWith("glob:") && !matcherPattern.startsWith("regex:"))
					throw new IllegalArgumentException(String
							.format("invalid pattern, must start with (glob:) or (regex:), found %s", matcherPattern));
				String joinMode = getAttr(attrs, RRoot.ATTR_JOIN_MODE, RRoot.JOIN_NAME);
				boolean relativePath = getAttr(attrs, RRoot.ATTR_RELATIVE_PATHS, false);
				int maxDepth = getAttr(attrs, RRoot.ATTR_MAX_DEPTH, Integer.MAX_VALUE);

				// ===============
				Set<Path> rps = new HashSet<Path>();
				PathMatcher pm = FileSystems.getDefault().getPathMatcher(matcherPattern);

//				String rootDirUnrelative = Utils.unrelative(rootDir);
				Files.walkFileTree(rootDir.toPath(), new HashSet<>(), maxDepth, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (pm.matches(file)) {
//System.out.println("--->"+file);
//							int i = file.toString().indexOf(rootDirUnrelative);
//							if (i < 0) {
//								throw new RuntimeException(String.format(
//										"can't find in path: \n(%s), \nroot(%s), \npattern(%s)",
//										file, Paths.get(rootDir), matcherPattern));
//							}
//							rps.add(Paths.get(file.toString().substring(i)));
							rps.add(file);
						}
						return FileVisitResult.CONTINUE;
					}
				});

				for (Path p : rps) {
					String parent = p.getParent().getFileName().toString();
					String filename = Utils.getFileNameOnly(p.getFileName().toString());
					String fileext = Utils.getFileExtensionOnly(p.getFileName().toString());
					RPath p2 = new RPath();
					String pp = p.toString();
					String prefix = "";
					if (relativePath) {
						prefix = !(pp.charAt(0) == '/' || pp.charAt(0) == '\\') ? "/" : "";
						prefix += ".";
					}
					String x = Paths.get(prefix + p.toString()).toString();
					p2.setPath(x);

					switch (joinMode) {
					case RRoot.JOIN_NAME:
						p2.setName(filename);
						break;
					case RRoot.JOIN_EXT_NAME:
						p2.setName(fileext + "_" + filename);
						break;
					case RRoot.JOIN_DIR_NAME:
						p2.setName(parent + "_" + filename);
						break;
					case RRoot.JOIN_EXT_DIR_NAME:
						p2.setName(fileext + "_" + parent + "_" + filename);
						break;
					default:
						throw new UnsupportedOperationException(String.format("unsupported joinMode: %s", joinMode));
					}
					getScope().getPaths().add(p2);
				}
				break;
			case RRoot.TAG_PATH:
				rpath.setName(name);
				getScope().getPaths().add(rpath);
				break;
			case RRoot.TAG_INCLUDE_STRINGS:
				String file = attrs.getValue(RRoot.ATT_FILE);
				if (file == null || file.trim().isEmpty())
					throw new IllegalArgumentException(String.format("The tag %s attr(%s) must have valid file path",
							RRoot.TAG_INCLUDE_STRINGS, RRoot.ATT_FILE));
				File f = Utils.resolve(root_dir, relative_to, file);
				if (!f.exists())
					throw new NoSuchFileException(f.toString());
				SAXParserFactory fac = SAXParserFactory.newInstance();
				SAXParser parser = fac.newSAXParser();
				IncludeStringsHandler ish = new IncludeStringsHandler(getScope());
				parser.parse(f.toURI().toString(), ish);
				break;
			case RRoot.TAG_R:
				String R_package = getAttr(attrs, RRoot.ATTR_R_CLASS_PACKAGE, "");
				String Rjsp_package = getAttr(attrs, RRoot.ATTR_RJSP_CLASS_PACKAGE, "");
				String R_outputDir = getAttr(attrs, RRoot.ATTR_R_CLASS_OUTPUTDIR, "");
				String Rjsp_outputDir = getAttr(attrs, RRoot.ATTR_RJSP_CLASS_OUTPUTDIR, "");
				String Rjs_outputDir = getAttr(attrs, RRoot.ATTR_RJS_SCRIPT_OUTPUTDIR, "");
				String properties_outputDir = getAttr(attrs, RRoot.ATTR_PROPERTIES_STRINGS_OUTPUTDIR, "");
				String properties_strings_output_encoding = getAttr(attrs,
						RRoot.ATTR_PROPERTIES_STRINGS_OUTPUT_ENCODING, "iso-8859-1");

				if (R_outputDir != null)
					R_outputDir = Utils.resolve(root_dir, relative_to, R_outputDir).toString();
				if (Rjsp_outputDir != null)
					Rjsp_outputDir= Utils.resolve(root_dir, relative_to, Rjsp_outputDir).toString();
				if (Rjs_outputDir != null)
					Rjs_outputDir = Utils.resolve(root_dir, relative_to, Rjs_outputDir).toString();
				if (properties_outputDir != null)
					properties_outputDir = Utils.resolve(root_dir, relative_to, properties_outputDir).toString();

				boolean R_generate = Utils.parseBooleanOrDefault(attrs.getValue(RRoot.ATTR_R_CLASS_GENERATE), false);
				boolean Rjsp_generate = Utils.parseBooleanOrDefault(attrs.getValue(RRoot.ATTR_RJSP_CLASS_GENERATE),
						false);
				boolean Rjs_generate = Utils.parseBooleanOrDefault(attrs.getValue(RRoot.ATTR_RJS_SCRIPT_GENERATE),
						false);
				boolean properties_generate = Utils
						.parseBooleanOrDefault(attrs.getValue(RRoot.ATTR_PROPERTIES_STRINGS_GENERATE), false);

				r.setR_class_package(R_package);
				r.setRjsp_class_package(Rjsp_package);

				r.setR_class_outputDir(R_outputDir);
				r.setRjsp_class_outputDir(Rjsp_outputDir);
				r.setRjs_script_outputDir(Rjs_outputDir);

				r.setR_class_generate(R_generate);
				r.setRjsp_class_generate(Rjsp_generate);
				r.setRjs_script_generate(Rjs_generate);

				r.setProperties_strings_generate(properties_generate);
				r.setProperties_strings_outputDir(properties_outputDir);
				r.setProperties_strings_output_encoding(properties_strings_output_encoding);

				modifiers = getAttr(attrs, RRoot.ATTR_MODIFIERS, "");
				r.setModifiers(modifiers);

				setScope(r);

				break;
			case RRoot.TAG_IMPORT:
				// add below
				break;
			default:
				throw new UnsupportedOperationException(String.format("localName: %s, qName: %s", localName, qName));
			}
		} catch (ClassNotFoundException | ParserConfigurationException | IOException e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);

		RPair rpair = new RPair();
		RKey rkey = new RKey();
		RString rstr = new RString();
		RPath rpath = new RPath();
		RImport rimport = new RImport();

		String v = sb.toString().trim();
		log("end: " + qName + ", value: " + v);
		switch (qName) {
		case RRoot.TAG_PAIR:
			rpair = last(getScope().getPairs());
			rpair.setValue(v);
			break;
		case RRoot.TAG_KEY:
			rkey = last(getScope().getKeys());
			rkey.setValue(v);
			break;
		case RRoot.TAG_STRING:
			rstr = last(getScope().getStrings());
			rstr.setValue(v);
			break;
		case RRoot.TAG_ARRAY:
			// nothing todo
			break;
		case RRoot.TAG_STRINGARRAY:
			// nothing todo
			break;
		case RRoot.TAG_ITEM:
			currentArray.add(v);
			break;
		case RRoot.TAG_CLASS:

			RClass parent = getScope().getParent();

			setScope(parent);
//			currentParent = currentClass.getParent(); 
//			currentClass = currentClass.getParent();
			break;
		case RRoot.TAG_PATH:
			rpath = last(getScope().getPaths());
			rpath.setPath(v);
			break;
		case RRoot.TAG_R:
			// do nothing
			break;
		case RRoot.TAG_INCLUDE_STRINGS:
			// do nothing here, will add above
			break;
		case RRoot.TAG_IMPORT:
			rimport.setPackage(v);
			r.getImports().add(rimport);
			break;
		case RRoot.TAG_RESOURCE_PATHS:
			// do nothing here, will add above
			break;
		default:
			throw new UnsupportedOperationException(
					String.format("localName: %s, qName: %s, value: %s", localName, qName, v));
		}
		sb.delete(0, sb.length());
	}

	private static <T> T last(List<T> l) {
		return l.get(l.size() - 1);
	}

	private static void log(String o) {
//		System.out.println(o);
	}

	public static String getAttr(Attributes attrs, String attrName, String defaultValue) {
		String v = attrs.getValue(attrName);
		return v == null ? defaultValue : v;
	}

	public static int getAttr(Attributes attrs, String attrName, int defaultValue) {
		String v = attrs.getValue(attrName);
		return v == null ? defaultValue : Integer.parseInt(v);
	}

	public static float getAttr(Attributes attrs, String attrName, float defaultValue) {
		String v = attrs.getValue(attrName);
		return v == null ? defaultValue : Float.parseFloat(v);
	}

	public static double getAttr(Attributes attrs, String attrName, double defaultValue) {
		String v = attrs.getValue(attrName);
		return v == null ? defaultValue : Double.parseDouble(v);
	}

	public static boolean getAttr(Attributes attrs, String attrName, boolean defaultValue) {
		String v = attrs.getValue(attrName);
		return v == null ? defaultValue : Boolean.parseBoolean(v);
	}

	public static String[] getAttr(Attributes attrs, String attrName, String[] defaultValue, String delim) {
		String v = attrs.getValue(attrName);
		if (v != null)
			return v.split("[" + delim + "]");
		return defaultValue;
	}

	public RClass getScope() {
		return scope;
	}

	public void setScope(RClass c) {
		this.scope = c;
	}

}
