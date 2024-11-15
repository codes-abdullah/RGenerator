package codes.abdullah.rit;

class RKey extends RPair {

	@Override
	public void setValue(String v) {
		super.setValue(v);
		if (key == null)
			setKey(v);
	}

}
