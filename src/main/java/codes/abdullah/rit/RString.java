package codes.abdullah.rit;

class RString extends RPairBase {

	public RString() {
		type = String.class;
	}

	public Class<?> getType() {
		return String.class;
	}

	public void setName(String name) {
		this.key = name;
	}

	public String getName() {
		return key;
	}

	public void setValue(String v) {
		this.value = v;
	}

	public String getValue() {
		return value.toString();
	}

	@Override
	public String build(int tab) {
		if (key == null || !key.matches(Utils.NAMING_CONVENTION_PATTERN)) {
			throw new IllegalArgumentException("invalid name: " + key);
		}
		return super.build(tab);
	}

	@Override
	public String toString() {
		return getClass() + " [name=" + key + ", value=" + value + "]";
	}
}
