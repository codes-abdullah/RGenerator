package codes.abdullah.rit;

class RImport extends RPairBase {

	public RImport(String _pkg) {
		value = _pkg;
	}

	public RImport() {
		// TODO Auto-generated constructor stub
	}

	public void setPackage(String v) {
		value = v;
	}

	@Override
	public String toString() {
		return getClass() + " [package=" + value + "]";
	}

	public String getPackage() {
		return value.toString();
	}

}
