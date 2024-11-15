package codes.abdullah.rit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class IncludeStringsHandler extends DefaultHandler {
	private StringBuilder sb = new StringBuilder();

	RArray currentArray;

	private RClass currentClass;

	public IncludeStringsHandler(RClass currentClass) {
		this.currentClass = currentClass;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
//		sb.append(ch, start, length);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
		super.startElement(uri, localName, qName, attr);
		String name = attr.getValue(RRoot.ATT_NAME);

		RString rstr = new RString();
		RStringArray rstrarr = new RStringArray();
		RArray rarr = new RArray();

		String type = null;

		try {
			switch (qName) {
			case RRoot.TAG_RESOURCES:
				// nothing
				break;
			case RRoot.TAG_STRING:
				name = attr.getValue(RRoot.ATT_NAME);
				rstr.setName(name);
				rstr.setValue(name);
				currentClass.getStrings().add(rstr);
				break;
			case RRoot.TAG_STRINGARRAY:
				name = attr.getValue(RRoot.ATT_NAME);
				this.currentArray = rstrarr;
				rstrarr.setName(name);
				currentClass.getStringArrays().add(rstrarr);
				break;
			case RRoot.TAG_ARRAY:
				name = attr.getValue(RRoot.ATT_NAME);
				type = attr.getValue(RRoot.ATT_TYPE);
				rarr.setType(type == null ? String.class : Class.forName(type));
				this.currentArray = rarr;
				rarr.setName(name);
				currentClass.getArrays().add(rarr);
				break;
			case RRoot.TAG_ITEM:
				// nothing todo
				break;
			default:
				throw new UnsupportedOperationException(qName);
			}
		} catch (Exception e) {
			throw new SAXException(e);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
//		RString rstr = new RString();
		String v = sb.toString().trim();
		switch (qName) {
		case RRoot.TAG_RESOURCES:
			// nothing
			break;
		case RRoot.TAG_STRING:
//			rstr = last(currentClass.getStrings());
//			rstr.setValue(v);
			break;
		case RRoot.TAG_ARRAY:
			// nothing todo
		case RRoot.TAG_STRINGARRAY:
			// nothing todo
			break;
		case RRoot.TAG_ITEM:
			currentArray.add(v);
			break;
		default:
			throw new UnsupportedOperationException();
		}
		sb.delete(0, sb.length());
	}
//
//	private static <T> T last(List<T> l) {
//		return l.get(l.size() - 1);
//	}

}
