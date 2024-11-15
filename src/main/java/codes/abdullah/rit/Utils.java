package codes.abdullah.rit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import codes.abdullah.project.structure.ProjectStructure;

class Utils {
	public static final String NAMING_CONVENTION_PATTERN = "^[a-zA-Z_][a-zA-Z_0-9]*$";

	public static Object parseAsLiteral(String v, Class<?> type) {
		try {
			if (type == null)
				return v;
			if (type.equals(boolean.class) || type.equals(Boolean.class)) {
				return Boolean.parseBoolean(v);
			} else if (type.equals(byte.class) || type.equals(Byte.class)) {
				return Byte.parseByte(v);
			} else if (type.equals(short.class) || type.equals(Short.class)) {
				return Short.parseShort(v);
			} else if (type.equals(char.class) || type.equals(Character.class)) {
				return v.charAt(0);
			} else if (type.equals(int.class) || type.equals(Integer.class)) {
				return Integer.parseInt(v);
			} else if (type.equals(long.class) || type.equals(Long.class)) {
				return Long.parseLong(v);
			} else if (type.equals(float.class) || type.equals(Float.class)) {
				return Float.parseFloat(v);
			} else if (type.equals(double.class) || type.equals(Double.class)) {
				return Double.parseDouble(v);
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("src(%s), type(%s)", v, type), e);
		}
		return v;
	}

	public static boolean parseBooleanOrDefault(String v, boolean default_) {
		try {
			return Boolean.parseBoolean(v);
		} catch (Exception e) {
			return default_;
		}
	}

	public static String[] literalPrefixAndSuffix_java(Class<?> type) {
		if (type == null || type.equals(String.class) || type.equals(char.class) || type.equals(Character.class))
			return new String[] { "\"", "\"" };
		else if (type.equals(char.class) || type.equals(Character.class)) {
			return new String[] { "'", "'" };
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			return new String[] { "", "L" };
		} else if (type.equals(float.class) || type.equals(Float.class)) {
			return new String[] { "", "F" };
		} else if (type.equals(double.class) || type.equals(Double.class)) {
			return new String[] { "", "D" };
		}
		return new String[] { "", "" };
	}

	public static String[] literalPrefixAndSuffix_javascript(Class<?> type) {
		if (type == null || type.equals(String.class) || type.equals(char.class) || type.equals(Character.class))
			return new String[] { "\"", "\"" };

		return new String[] { "", "" };
	}

	public static String tabit(int n) {
		String t = "";
		for (int i = 0; i < n; i++) {
			t += "\t";
		}
		return t;
	}

	public static boolean isEssintialType(Class<?> type) {
		if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			return true;
		} else if (type.equals(byte.class) || type.equals(Byte.class)) {
			return true;
		} else if (type.equals(short.class) || type.equals(Short.class)) {
			return true;
		} else if (type.equals(char.class) || type.equals(Character.class)) {
			return true;
		} else if (type.equals(int.class) || type.equals(Integer.class)) {
			return true;
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			return true;
		} else if (type.equals(float.class) || type.equals(Float.class)) {
			return true;
		} else if (type.equals(double.class) || type.equals(Double.class)) {
			return true;
		} else if (type.equals(String.class))
			return true;
		return false;
	}

	public static String resolveKey(String key) {
//		return key.replaceAll("[- ]+", "_");
		String k2 = key.toString().replaceAll("[-()\\[\\]{}!@#$%^&*~`+='\"/| ]", "_");
		k2 = k2.replaceAll("[_]{2}", "_").replaceAll("^[_]|[_]$", "");
		return k2.toUpperCase();
	}

	public static String build(Class<?> type, String key, Object value, int tab) {
		String t = Utils.tabit(tab);
		String[] ps = literalPrefixAndSuffix_java(type);
		if (!Utils.isEssintialType(type))
			throw new UnsupportedOperationException(type.toString());
		return String.format("%spublic static final %s %s = %s%s%s;\n", t, type.getSimpleName(),
				Utils.resolveKey(key).toUpperCase(), ps[0], value, ps[1]);
	}

	public static String buildJsp(Class<?> type, String key, RClass c, int tab) {
		String t = Utils.tabit(tab);
		String k2 = resolveKey(key);
		String t2 = t + t;

		String classNameChain = getClassNameChain(c);
		return String.format("%spublic %s get%s(){\n%sreturn %s.%s;\n%s}\n", t, type.getSimpleName(), k2, t2,
				classNameChain, k2, t2);
	}

	public static String buildJs(Class<?> type, String key, Object value, int tab) {
		String[] ps = literalPrefixAndSuffix_javascript(type);
		if (!Utils.isEssintialType(type))
			throw new UnsupportedOperationException(type.toString());
		String k2 = resolveKey(key).toUpperCase();
		return String.format("const %s = %s%s%s;\n", k2, ps[0], value, ps[1]);
	}

	public static String buildProperties(Class<?> type, String key, Object value, int tab) {
		if (!Utils.isEssintialType(type))
			throw new UnsupportedOperationException(type.toString());
		return String.format("%s=%s\n", key, value);
	}

	public static Set<String> fetchImports(List<Object> l) {
		return l.stream().map(i -> i.getClass().getName()).collect(Collectors.toSet());
	}

	public static String buildArray(Class<?> type, String name, List<Object> items, int tab) {
		String t = Utils.tabit(tab);
		StringJoiner sj = new StringJoiner(", //\n");
		String n2 = resolveKey(name).toUpperCase();
		for (Object item : items) {
			String s = item.toString().replaceAll("\\\\,", ",");
			String[] ps = literalPrefixAndSuffix_java(type);
			sj.add(String.format("%s%s%s%s", t, ps[0], s, ps[1]));
		}
		return String.format("%spublic static final %s[] %s = new %s[]{//\n%s\n};\n", t, type.getSimpleName(), n2,
				type.getSimpleName(), sj);
	}

	public static String buildJspArray(Class<?> type, String key, RClass c, int tab) {
		String t = Utils.tabit(tab);
		String k2 = resolveKey(key).toUpperCase();
		String t2 = t + t;
		String classNameChain = getClassNameChain(c);
		return String.format("%spublic %s[] get%s(){\n%sreturn %s.%s;\n%s}\n", t, type.getSimpleName(), k2, t2,
				classNameChain, k2, t2);
	}

	public static String buildJsArray(Class<?> type, String name, List<Object> items, int tab) {
		String t = Utils.tabit(tab);
		StringJoiner sj = new StringJoiner(", //\n");
		String n2 = resolveKey(name).toUpperCase();
		for (Object item : items) {
			String[] ps = literalPrefixAndSuffix_javascript(type);
			sj.add(String.format("%s%s%s%s", t, ps[0], item, ps[1]));
		}
		return String.format("const %s = [%s];\n", n2, sj);
	}

	public static String buildPropertiesArray(Class<?> type, String name, List<Object> items, int tab) {
		StringJoiner sj = new StringJoiner(",");
		for (Object item : items) {
			int i = -1;
			String s = item.toString();
			while ((i = s.indexOf(',', i + 1)) >= 0) {
				if (i - 1 >= 0 && s.charAt(i - 1) == '\\')
					continue;
				throw new IllegalArgumentException(
						"invalid properties array item, contains non-escaped delimiter, please use (\\) to escape delimiter (,): "
								+ item);
			}
			sj.add(String.format("%s", item));
		}
		return String.format("%s={%s}\n", name, sj);
	}

	public static String getClassNameChain(RClass c) {
		String classNameChain = c.getName();
		RClass parent = null;
		RClass child = c;
		while ((parent = child.getParent()) != null) {
			classNameChain = parent.getName() + "." + classNameChain;
			child = parent;
			parent = null;
		}
		return classNameChain;
	}

	public static Class<?> forName(String cls) throws ClassNotFoundException {
		String[] p = "boolean,byte,short,char,int,long,float,double".split("[,]");
		Class<?>[] w = new Class[] { boolean.class, byte.class, short.class, char.class, int.class, long.class,
				float.class, double.class };

		for (int i = 0; i < p.length; i++) {
			if (cls.equals(p[i]))
				return w[i];
		}

		return Class.forName(cls);
	}

	public static Class<?> findWrapperClass(String cls) throws ClassNotFoundException {
		String[] p = "boolean,byte,short,char,int,long,float,double".split("[,]");
		String[] w = "Boolean,Byte,Short,Character,Integer,Long,Float,Double".split("[,]");

		for (int i = 0; i < p.length; i++) {
			if (cls.equals(p[i]))
				return Class.forName(String.format("java.lang.%s", w[i]));
		}

		return Class.forName(cls);
	}

//	public static String getPackage(String outputDir) {
//		outputDir = outputDir.replace('\\', '/');
//		String maven_main = "src/main/java";
//		String maven_test = "src/test/java";
//
//		int i = outputDir.indexOf(maven_main);
//		if (i >= 0) {
//			outputDir = outputDir.substring(i);
//		} else {
//			i = outputDir.indexOf(maven_test);
//			if (i >= 0)
//				outputDir = outputDir.substring(i);
//		}
//
//		if (!outputDir.startsWith(maven_main) && !outputDir.startsWith(maven_test)) {
//			throw new IllegalArgumentException(
//					"invalid outputDir, must start with " + maven_main + " for maven projects, found: " + outputDir);
//		}
//		String pkg = outputDir.substring(maven_main.length());
//		if (pkg.length() > 0) {
//			if (pkg.charAt(0) == '/')
//				pkg = pkg.substring(1);
//			return pkg.replace('/', '.');
//		}
//		return "";
//	}

	public static String getFileNameOnly(String n) {
		if (n.trim().isEmpty())
			throw new IllegalArgumentException("n is empty");

		int index = n.lastIndexOf('.');
		// name
		// .name
		if (index == -1 || index == 0)
			return n;
		// name.ext
		// .name.ext
		return n.substring(0, index);
	}

	public static String getFileExtensionOnly(String n) {
		if (n.trim().isEmpty())
			throw new IllegalArgumentException("n is empty");

		int index = n.lastIndexOf('.');
		// name
		// .name
		if (index == -1 || index == 0)
			return "";
		// name.ext
		// .name.ext
		return n.substring(index + 1, n.length());
	}

	public static void mkdirs(File f) throws IOException {
		if(f.toString().startsWith("${"))
			System.out.println("found");
		if (!f.exists() && !f.mkdirs()) {
			throw new IOException("can't mkdir: " + f);
		}
	}

	public static Path resolvePathProperties(String path) throws IOException {
		int i = path.length();
		StringBuilder sb = new StringBuilder(path);
		while ((i = sb.lastIndexOf("${", i)) != -1) {
			int end = path.indexOf('}', i + 2);
			String key = path.substring(i + 2, end);
			Path p2 = ProjectStructure.getDefault().get(key);
			sb.replace(i, end + 1, p2.toString());
			i = end;
		}
		return Paths.get(sb.toString());
	}

	// case#1: ${key}
	// case#2: ${key}/file
	// case#3: base/base/file
	// case#4: base/file
	// case#5: ../file
	// case#6: ./file
	// case#7: file
	// case#8: both absolute, but different locations
	// e.g /absolute/path/TO/file and /absolute/path/to/file
	/**
	 * @param rootDir    e.g: C:\dev\eclipse\jse\default\workspace\RGenerator
	 * @param relativeTo e.g:
	 *                   C:\dev\eclipse\jse\default\workspace\RGenerator\src\test\resources\rit\R.xml
	 * @param file       e.g: any case
	 */
	public static File resolve(String rootDir, String relativeTo, String file) {
		try {
			// =============
			// ensure separators are system dependent
			// =============
			rootDir = new File(rootDir).getCanonicalFile().toString();
			if (ProjectStructure.getDefault().containsKey(relativeTo))
				relativeTo = new File(ProjectStructure.getDefault().resolve(relativeTo)).getCanonicalFile()
						.toString();
			relativeTo = new File(relativeTo).getCanonicalFile().toString();
			file = new File(file).toString();
			// =============
			// case#1
			// case#2
			// =============
			if (ProjectStructure.getDefault().containsKey(file))
				file = new File(ProjectStructure.getDefault().resolve(file)).toString();
			// =============
			// case#5
			// case#6
			// case#7
			// =============
			if (isRelative(file)) {
				return resolveRelative(relativeTo, file);
			}
			// =============
			// case#3
			// =============
			if (file.startsWith(rootDir))
				return new File(file).getCanonicalFile();
			// =============
			// case#8
			// =============
			{
				File b = new File(rootDir);
				File f = new File(rootDir);
				if (b.isAbsolute() && f.isAbsolute())
					return f.getCanonicalFile();
			}
			// =============
			// case#4
			// =============
			return new File(rootDir, file).getCanonicalFile();
		} catch (Exception e) {
			throw new RuntimeException(
					String.format("\nrootDir: %s\nrelativeTo: %s\nfile: %s\n", rootDir, relativeTo, file), e);
		}
	}

	private static File resolveRelative(String base, String relative) throws IOException {
		try {
//			File bf = new File(base);
//			if(bf.isAbsolute())
//				return new File(bf, relative);
			return new File(base, relative.toString()).getCanonicalFile();
		} catch (Exception e) {
			throw new RuntimeException(String.format("\nbase: %s,\nrelative: %s", base, relative), e);
		}
	}
//
//	private static File resolveRelative(String base, File relative) throws IOException {
//		try {
//			return new File(base, relative.toString()).getCanonicalFile();
//		} catch (Exception e) {
//			throw new RuntimeException(String.format("\nbase: %s,\nrelative: %s", base, relative), e);
//		}
//	}
//
//	private static File resolveRelative(File base, String relative) throws IOException {
//		try {
//			return new File(base, relative.toString()).getCanonicalFile();
//		} catch (Exception e) {
//			throw new RuntimeException(String.format("\nbase: %s,\nrelative: %s", base, relative), e);
//		}
//	}
//
//	private static File resolveRelative(File base, File relative) throws IOException {
//		try {
//			return new File(base, relative.toString()).getCanonicalFile();
//		} catch (Exception e) {
//			throw new RuntimeException(String.format("\nbase: %s,\nrelative: %s", base, relative), e);
//		}
//	}

	public static boolean isRelative(String path) {
		return !new File(path).isAbsolute();
	}

	public static String unrelative(String rootDir) {
		if (rootDir.startsWith("../"))
			return rootDir.substring(3);
		else if (rootDir.startsWith("./"))
			return rootDir.substring(2);
		return rootDir;
	}

//	public static RClass findR(RClass cls) {
//		if(cls.getName().equals("R"))
//		return cls;
//		for(RClass c2 : cls.getClasses()) {
//			System.out.println("R found");
//			return findR(c2.getParent());
//		}
////		throw new IllegalArgumentException("can't find R");
//		return null;
//	}
}
