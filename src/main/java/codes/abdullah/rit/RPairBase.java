package codes.abdullah.rit;

import java.util.Objects;

class RPairBase extends RObject {

	Class<?> type;
	String key;
	Object value;

	@Override
	public String toString() {
		return getClass() + " [type=" + type + ", key=" + key + ", value=" + value + "]";
	}

	public String build(int tab) {
		return Utils.build(type, key, value, tab);
	}
	
	public String buildJsp(int tab, RClass c) {
		return Utils.buildJsp(type, key, c, tab);
	}
	
	public String buildJs(int tab, String className) {
		return Utils.buildJs(type, key, value, tab);
	}

	public String buildProperties(int tab) {
		return Utils.buildProperties(type, key, value, tab);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(key, type, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RPairBase other = (RPairBase) obj;
		return Objects.equals(key, other.key) && Objects.equals(type, other.type) && Objects.equals(value, other.value);
	}

}
