package codes.abdullah.rit;

import java.util.ArrayList;
import java.util.List;

class RArrayBase extends RObject {

	Class<?> type;
	String name;
	List<Object> items = new ArrayList<>();

	public void add(String v) {
		Object o = Utils.parseAsLiteral(v, type);
		items.add(o);
	}

	public Object get(int i) {
		return items.get(i);
	}

	public int size() {
		return items.size();
	}

	@Override
	public String toString() {
		return getClass() + " [type=" + type + ", name=" + name + ", items=" + items + "]";

	}

	public String build(int tabs) {
		if (name == null || !name.matches(Utils.NAMING_CONVENTION_PATTERN))
			throw new IllegalArgumentException("invalid name: " + name);
		return Utils.buildArray(type, name, items, tabs);
	}

	public String buildJsp(int tab, RClass c) {
		if (name == null || !name.matches(Utils.NAMING_CONVENTION_PATTERN))
			throw new IllegalArgumentException("invalid name: " + name);
		return Utils.buildJspArray(type, name, c, tab);
	}
	
	public String buildJs(int tabs) {
		if (name == null || !name.matches(Utils.NAMING_CONVENTION_PATTERN))
			throw new IllegalArgumentException("invalid name: " + name);
		return Utils.buildJsArray(type, name, items, tabs);
	}
	
	public String buildProperties(int tabs) {
		if (name == null || !name.matches(Utils.NAMING_CONVENTION_PATTERN))
			throw new IllegalArgumentException("invalid name: " + name);
		return Utils.buildPropertiesArray(type, name, items, tabs);
	}

}
