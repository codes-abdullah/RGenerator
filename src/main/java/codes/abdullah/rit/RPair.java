package codes.abdullah.rit;

class RPair extends RPairBase {

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(String v) {
		this.value = Utils.parseAsLiteral(v, type);
	}

	public String build(int tab) {
		if (key == null || !key.matches(Utils.NAMING_CONVENTION_PATTERN)) {
			throw new IllegalArgumentException("invalid key: " + key);
		}
//		return Utils.build(String.class, key.toString().toUpperCase(), value, tab);
		return super.build(tab);
	}

}
