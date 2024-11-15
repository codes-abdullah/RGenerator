package codes.abdullah.rit;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import codes.abdullah.project.structure.ProjectStructure;

public class RGeneratorTest {

	@Test
	public void test() throws Exception {

		String rootDir, file, relativeTo;
		File expected, actual;
		rootDir = new File(".").getCanonicalPath();
		// case#1: ${key}
		// case#2: ${key}/file
		// case#3: base/base/file
		// case#4: base/file
		// case#5: ../file
		// case#6: ./file
		// case#7: file
		// case#8: both absolute, but different locations
		// e.g /absolute/path/TO/file and /absolute/path/to/file
		// ========== case#1
		rootDir = new File(".").getCanonicalPath();
		relativeTo = "";
		file = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY;
		expected = new File(ProjectStructure.getDefault().resolve(file));
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#2
		rootDir = new File(".").getCanonicalPath();
		relativeTo = "";
		file = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY + "/file";
		expected = new File(ProjectStructure.getDefault().resolve(file));
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#3
		rootDir = new File(".").getCanonicalPath();
		relativeTo = "";
		file = rootDir + "/path/to/file";
		expected = new File(rootDir, "/path/to/file");
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#4
		rootDir = new File(".").getCanonicalPath();
		relativeTo = "";
		file = "path/to/file";
		expected = new File(rootDir, file);
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#5
		rootDir = new File(".").getCanonicalPath();
		relativeTo = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY + "/rit";
		file = "..";
		expected = new File(rootDir, "src/test/resources/");
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#6
		rootDir = new File(".").getCanonicalPath();
		relativeTo = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY + "/rit/";
		file = ".";
		expected = new File(rootDir, "src/test/resources/rit");
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#7
		rootDir = new File(".").getCanonicalPath();
		relativeTo = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY + "/rit/";
		file = "file.txt";
		expected = new File(ProjectStructure.getDefault().resolve(relativeTo), "file.txt");
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());
		// ========== case#8
		file = "${project.build.sourceDirectory}/file.txt";
		expected = new File(ProjectStructure.getDefault().resolve(file));
		actual = Utils.resolve(rootDir, relativeTo, file);
		assertEquals(expected.toString(), actual.toString());

		// ==========
		// ==========
		// ==========
		// ==========
		// ==========

		String path = ProjectStructure.PROPERTY_PROJECT_BUILD_TEST_RESOURCE_DIRECTORY + "/rit/R.xml";
//		path = "src/main/resources/gtest.xml";
		RGenerator.generate(path);
	}
}
