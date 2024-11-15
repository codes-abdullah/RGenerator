package codes.abdullah.rit;

import java.nio.file.Path;
import java.nio.file.Paths;

class RPath extends RPairBase {

	public RPath() {
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

	public String getPath() {
		return value.toString();
	}

	public void setPath(String path) {
		this.value = path.replaceAll("[\\\\]", "/");
	}

	public RPath toAbsolute() {
		Path p = Paths.get(value.toString()).toAbsolutePath();
		RPath p2 = new RPath();
		p2.setPath(p.toString());
		return p2;
	}

	@Override
	public String toString() {
		return getClass() + " [name=" + key + ", path=" + value + "]";
	}

	public String build(int tab) {
		return Utils.build(String.class, key, value, tab);
	}

}
