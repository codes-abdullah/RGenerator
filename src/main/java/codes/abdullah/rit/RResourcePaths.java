package codes.abdullah.rit;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class RResourcePaths extends RArray {
	public RResourcePaths() {
		super.setType(RPath.class);
	}

	@Override
	public Class<?> getType() {
		return RPath.class;
	}

	@Override
	public void setType(Class<?> type) {
		if (type != String.class)
			throw new UnsupportedOperationException();
		super.setType(type);
	}
	
	@Override
	public void add(String v) {
		RPath p = new RPath();
		p.setPath(v);
		p = p.toAbsolute();
		if(!Files.exists(Paths.get(p.getPath())))
			new RuntimeException(new NoSuchFileException(p.toString()));
		super.add(v);
	}
}
