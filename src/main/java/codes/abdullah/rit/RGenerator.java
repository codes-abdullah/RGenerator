package codes.abdullah.rit;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RGenerator {

	public static void generate(String R_xml_path) throws Exception {
		SAXParserFactory f = SAXParserFactory.newInstance();
		SAXParser p = f.newSAXParser();
		RHandler h = new RHandler(R_xml_path);
		File R_xml_file = Utils.resolve(".", R_xml_path, R_xml_path);
		p.parse(R_xml_file, h);
		h.getR().build();
	}

}
