package codes.abdullah.rit;

import java.util.Objects;

public class RField extends RPairBase {
	private String modifiers, type2;
	

	public String getModifiers() {
		return modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	public String getType() {
		return type2;
	}

	public void setType(String type2) {
		this.type2 = type2;
	}

	public String getName() {
		return key;
	}

	public void setName(String name) {
		if (name == null || !name.matches(Utils.NAMING_CONVENTION_PATTERN)) {
			throw new IllegalArgumentException("invalid field name: " + name);
		}
		this.key = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(modifiers, type2);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RField other = (RField) obj;
		return Objects.equals(modifiers, other.modifiers) && Objects.equals(type2, other.type2);
	}

	@Override
	public String build(int tab) {
		String t  = Utils.tabit(tab);
		return String.format("%s%s %s %s = %s\n", t, modifiers, type2, key, value);
	}
	

}
