package codes.abdullah.rit;

class RStringArray extends RArray {

	public RStringArray() {
		super.setType(String.class);
	}

	@Override
	public Class<?> getType() {
		return String.class;
	}

	@Override
	public void setType(Class<?> type) {
		if (type != String.class)
			throw new UnsupportedOperationException();
		super.setType(type);
	}

}
