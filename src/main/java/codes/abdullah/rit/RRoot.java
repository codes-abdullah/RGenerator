package codes.abdullah.rit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

class RRoot extends RClass {
	private String R_class_outputDir, Rjsp_class_outputDir, Rjs_script_outputDir, properties_strings_outputDir,
			R_class_package, Rjsp_class_package,
			properties_strings_output_encoding = StandardCharsets.ISO_8859_1.toString();
	private boolean R_class_generate, Rjsp_class_generate, Rjs_script_generate, properties_strings_generate;

	// =======================
	// =======================
	// =======================
	// =======================

	/* join modes for resource-paths tag */
	public static final String JOIN_NAME = "JOIN_NAME";
	public static final String JOIN_EXT_NAME = "JOIN_EXT_NAME";
	public static final String JOIN_DIR_NAME = "JOIN_DIR_NAME";
	public static final String JOIN_EXT_DIR_NAME = "JOIN_EXT_DIR_NAME";

	// =======================
	// =======================
	public static final String TAG_R = "R";
	public static final String TAG_INCLUDE_STRINGS = "include-strings";
	public static final String TAG_PAIR = "pair";
	public static final String TAG_KEY = "key";
	public static final String TAG_PATH = "path";
	public static final String TAG_ARRAY = "array";
	public static final String TAG_IMPORT = "import";
	public static final String TAG_ITEM = "item";
	public static final String TAG_STRINGARRAY = "string-array";
	public static final String TAG_CLASS = "class";
	public static final String TAG_RESOURCE_PATHS = "resource-paths";
	public static final String ATT_KEY = "key";
	public static final String ATT_TYPE = "type";
	public static final String ATT_FILE = "file";
	public static final String ATT_NAME = "name";
	public static final String ATTR_MODIFIERS = "modifiers";
	public static final String ATTR_ROOT_DIR = "root-dir";
	public static final String ATTR_EXTENSIONS = "extensions";
	public static final String ATTR_JOIN_MODE = "join-mode";
	public static final String ATTR_RELATIVE_PATHS = "relative-paths";
	public static final String ATTR_MAX_DEPTH = "max-depth";
	public static final String ATTR_MATCHER_PATTERN = "matcher-pattern";
	// =======================
	// =======================
	// =======================
	// =======================
	public static final String ATTR_R_CLASS_GENERATE = "R-class-generate";
	public static final String ATTR_RJSP_CLASS_GENERATE = "Rjsp-class-generate";
	public static final String ATTR_RJS_SCRIPT_GENERATE = "Rjs-script-generate";
	// =======================
	public static final String ATTR_R_CLASS_OUTPUTDIR = "R-class-outputDir";
	// =======================
	public static final String ATTR_R_CLASS_PACKAGE = "R-class-package";
	public static final String ATTR_RJSP_CLASS_PACKAGE = "Rjsp-class-package";
	// =======================
	public static final String ATTR_RJSP_CLASS_OUTPUTDIR = "Rjsp-class-outputDir";
	public static final String ATTR_RJS_SCRIPT_OUTPUTDIR = "Rjs-script-outputDir";
	// =======================
	public static final String ATTR_PROPERTIES_STRINGS_GENERATE = "properties-strings-generate";
	public static final String ATTR_PROPERTIES_STRINGS_OUTPUTDIR = "properties-strings-outputDir";
	public static final String ATTR_PROPERTIES_STRINGS_OUTPUT_ENCODING = "properties-strings-output-encoding";
	public static final String ATTR_PROPERTIES_STRINGS_INPUT_ENCODING = "properties-strings-input-encoding";
	// =======================

	// =======================
	// =======================
	// =======================
	public static final String TAG_STRING = "string";

	public static final String TAG_RESOURCES = "resources";

	public RRoot() {
		setName("R");
	}

	public String getR_class_package() {
		return R_class_package;
	}

	public void setR_class_package(String r_class_package) {
		R_class_package = r_class_package;
	}

	public String getRjsp_class_package() {
		return Rjsp_class_package;
	}

	public void setRjsp_class_package(String rjsp_class_package) {
		Rjsp_class_package = rjsp_class_package;
	}

	public String getR_class_outputDir() {
		return R_class_outputDir;
	}

	public void setR_class_outputDir(String r_class_outputDir) {
		R_class_outputDir = r_class_outputDir;
	}

	public String getRjsp_class_outputDir() {
		return Rjsp_class_outputDir;
	}

	public void setRjsp_class_outputDir(String rjsp_class_outputDir) {
		Rjsp_class_outputDir = rjsp_class_outputDir;
	}

	public String getRjs_script_outputDir() {
		return Rjs_script_outputDir;
	}

	public void setRjs_script_outputDir(String rjs_script_outputDir) {
		Rjs_script_outputDir = rjs_script_outputDir;
	}

	public String getProperties_strings_outputDir() {
		return properties_strings_outputDir;
	}

	public void setProperties_strings_outputDir(String properties_strings_outputDir) {
		this.properties_strings_outputDir = properties_strings_outputDir;
	}

	public boolean isR_class_generate() {
		return R_class_generate;
	}

	public void setR_class_generate(boolean r_class_generate) {
		R_class_generate = r_class_generate;
	}

	public boolean isRjsp_class_generate() {
		return Rjsp_class_generate;
	}

	public void setRjsp_class_generate(boolean rjsp_class_generate) {
		Rjsp_class_generate = rjsp_class_generate;
	}

	public boolean isRjs_script_generate() {
		return Rjs_script_generate;
	}

	public void setRjs_script_generate(boolean rjs_script_generate) {
		Rjs_script_generate = rjs_script_generate;
	}

	public boolean isProperties_strings_generate() {
		return properties_strings_generate;
	}

	public void setProperties_strings_generate(boolean properties_strings_generate) {
		this.properties_strings_generate = properties_strings_generate;
	}

	public String getProperties_strings_output_encoding() {
		return properties_strings_output_encoding;
	}

	public void setProperties_strings_output_encoding(String properties_strings_output_encoding) {
		this.properties_strings_output_encoding = properties_strings_output_encoding;
	}

	public void build() throws IOException {

		String fmt = "Illegal modifier for the class R; only public, abstract & final are permitted, found: %s";
		String mm = getModifiers();
		String[] keywords = { "public", "final", "abstract" };
		Arrays.sort(keywords);
		for (String m : mm.split("[\\s]+")) {

			if (Arrays.binarySearch(keywords, m) < 0 && !mm.isEmpty()) {
				throw new RuntimeException(String.format(fmt, m));
			}
		}

		String r = build(this, 0);
		String rjsp = buildRjsp(this, 0);
		String rjs = buildRjs(this, 0);
		String properties_strings = buildProperties(this, 0);

		String R_imports = "";
		for (RImport rimport : getImports()) {
			R_imports += String.format("import %s;\n", rimport.getPackage());
		}

		if (!R_class_package.isEmpty())
			r = String.format("package %s;%n\n%s\n%s", R_class_package, R_imports, r);

		String Rjsp_imports = R_imports;

		if (!R_class_package.isEmpty()) {
			Rjsp_imports += "\nimport " + Rjsp_class_package + ".R;";
		}

		if (!Rjsp_class_package.isEmpty())
			rjsp = String.format("package %s;%n\n%s\n%s", Rjsp_class_package, Rjsp_imports, rjsp);

		File r_outputf = new File(R_class_outputDir);
		File rjsp_outputf = new File(Rjsp_class_outputDir);
		File rjs_outputf = new File(Rjs_script_outputDir);
		File properties_outputf = new File(properties_strings_outputDir);

		if (R_class_generate) {
			Utils.mkdirs(r_outputf);
			Files.write(Paths.get(r_outputf.toString(), "R.java"), r.getBytes());
		}

		if (Rjsp_class_generate) {
			Utils.mkdirs(rjsp_outputf);
			Files.write(Paths.get(r_outputf.toString(), "Rjsp.java"), rjsp.getBytes());
		}

		if (Rjs_script_generate) {
			Utils.mkdirs(rjs_outputf);
			Files.write(Paths.get(rjs_outputf.toString(), "rjs.js"), rjs.getBytes());
		}

		if (properties_strings_generate) {
			Utils.mkdirs(properties_outputf);
			Files.write(Paths.get(properties_outputf.toString(), "strings.properties"),
					properties_strings.getBytes(properties_strings_output_encoding));
		}

		System.out.println();
		System.out.println("RGenerator: done");

	}

}
