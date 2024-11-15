package codes.abdullah.rit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

//TODO >= 3rd depth class
//TODO throw exceptions on invalid names (my-name)
//TODO prevent import java.lang.*
//TODO s
class RClass extends RObject {
	private List<RField> fields = new ArrayList<RField>();
	private String modifiers;
	private RClass parent;
	private String name;
	private List<RClass> classes = new ArrayList<>();
	private List<RPair> pairs = new ArrayList<>();
	private List<RArray> arrays = new ArrayList<>();
	private List<RString> strings = new ArrayList<>();
	private List<RStringArray> stringArrays = new ArrayList<>();
	private List<RPath> paths = new ArrayList<>();
	private List<RKey> keys = new ArrayList<>();
	private Set<RImport> imports = new HashSet<>();
	private static final List<String> primitiveTypes = Arrays
			.asList("boolean,byte,short,char,int,long,float,double".split("[,]"));

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifiers() {
		return modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	public Set<RImport> getImports() {
		for (RClass c : classes) {
			imports.addAll(c.getImports());
		}

		for (RArray a : arrays) {
			imports.addAll(Utils.fetchImports(a.items).stream().map(s -> new RImport(s)).collect(Collectors.toSet()));
		}

		for (RPair p : pairs) {
			imports.add(new RImport(p.getType().getName()));
		}

		for (RString s : strings) {
			imports.add(new RImport(s.getType().getName()));
		}

		for (RPair p : pairs) {
			imports.add(new RImport(p.getType().getName()));
		}

		imports.addAll(imports);

		Set<RImport> imports2 = imports.stream().filter(i -> !i.getPackage().startsWith("java.lang."))
				.filter(i2 -> !primitiveTypes.contains(i2.getPackage())).collect(Collectors.toSet());

		imports.clear();
		imports.addAll(imports2);
		return imports;
	}

	public List<RField> getFields() {
		return fields;
	}

	public void setParent(RClass parent) {
		this.parent = parent;
	}

	public RRoot getR() {
		if (this.getClass().equals(RRoot.class))
			return (RRoot) this;
		if (parent.getClass().equals(RRoot.class))
			return (RRoot) parent;
		return parent.getR();
	}

	public RClass getParent() {
		return parent;
	}

	public void setImports(Set<RImport> imports) {
		this.imports = imports;
	}

	public List<RClass> getClasses() {
		return classes;
	}

	public List<RPair> getPairs() {
		return pairs;
	}

	public List<RArray> getArrays() {
		return arrays;
	}

	public List<RString> getStrings() {
		return strings;
	}

	public List<RKey> getKeys() {
		return keys;
	}

	public List<RStringArray> getStringArrays() {
		return stringArrays;
	}

	public List<RPath> getPaths() {
		return paths;
	}

	@Override
	public String toString() {

		String s = build(this, 0);

		return s;
//		return name;
	}

	String build(RClass c, int tab) {
		Random rand = new Random();
		try {

			String t = Utils.tabit(tab);

			String s = String.format("%s%s class %s {\n%s%%s\n%s}\n", t, c.getModifiers(), c.getName(), t, t);
			String x = "";

//			R r = c.getR();
			for (RClass c2 : c.getClasses()) {

				boolean isstatic = c2.getModifiers().contains("static");

				if (isstatic) {
					if (!c2.getParent().getClass().equals(RRoot.class)
							&& !c2.getParent().getModifiers().contains("static")) {
						String fmt = "The member type %s cannot be declared static; static types can only be declared in static or top level types";
						throw new IllegalArgumentException(String.format(fmt, c2.getName()));
					}

				}

				RField rf = new RField();
				rf.setModifiers(c2.getModifiers());

				String name = (c2.getName() + Long.toHexString(System.currentTimeMillis() + (rand.nextInt())))
						.toLowerCase();
				rf.setName(name);
				rf.setType(c2.getName());
				rf.setValue(String.format("new %s();", c2.getName()));
				c.getFields().add(rf);

				c2.setParent(c);

				x += build(c2, tab + 1);
			}

			String f = "";
			for (RField rf : c.getFields()) {
				f += rf.build(tab + 1);
			}

			x = f + x;

			for (RArray a : c.getArrays()) {
				x += a.build(tab + 1);
			}

			for (RStringArray a : c.getStringArrays()) {
				x += a.build(tab + 1);
			}

			for (RString str : c.getStrings()) {
				x += str.build(tab + 1);
			}

			for (RPair str : c.getPairs()) {
				x += str.build(tab + 1);
			}

			for (RPath d : c.getPaths()) {
				x += d.build(tab + 1);
			}

			for (RKey d : c.getKeys()) {
				x += d.build(tab + 1);
			}

			s = String.format(s, x);
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	String buildRjsp(RClass c, int tab) {
		String t = Utils.tabit(tab);
		String s = String.format("%spublic class %s {\n%s%%s\n%s}\n", t, c.getName().equals("R") ? "Rjsp" : c.getName(),
				t, t);

		String x = "";

		for (RClass c2 : c.getClasses()) {
			x += buildRjsp(c2, tab + 1);
		}

		

		for (RString str : c.getStrings()) {
			x += str.buildJsp(tab + 1, c);
		}

		for (RKey k : c.getKeys()) {
			x += k.buildJsp(tab + 1, c);
		}
		for (RPair p : c.getPairs()) {
			x += p.buildJsp(tab + 1, c);
		}

		for (RPath p : c.getPaths()) {
			x += p.buildJsp(tab + 1, c);
		}

		for (RArray a : c.getArrays()) {
			x += a.buildJsp(tab + 1, c);
		}

		for (RStringArray a : c.getStringArrays()) {
			x += a.buildJsp(tab + 1, c);
		}

		s = String.format(s, x);
		return s;
	}

	String buildRjs(RClass c, int tab) {

		String s = String.format("//============= %s\n%%s", c.getName());

		String x = "";

		for (RClass c2 : c.getClasses()) {
			x += buildRjs(c2, tab + 1);
		}

		for (RString str : c.getStrings()) {
			x += str.buildJs(tab + 1, c.getName());
		}

		for (RKey k : c.getKeys()) {
			x += k.buildJs(tab + 1, c.getName());
		}
		for (RPair p : c.getPairs()) {
			x += p.buildJs(tab + 1, c.getName());
		}

		for (RPath p : c.getPaths()) {
			x += p.buildJs(tab + 1, c.getName());
		}

		for (RArray a : c.getArrays()) {
			x += a.buildJs(tab + 1);
		}

		for (RStringArray a : c.getStringArrays()) {
			x += a.buildJs(tab + 1);
		}

		s = String.format(s, x);
		return s;
	}

	String buildProperties(RClass c, int tab) {

		String s = String.format("#============= %s\n%%s", c.getName());

		String x = "";

		for (RClass c2 : c.getClasses()) {
			x += buildProperties(c2, tab + 1);
		}

		for (RString str : c.getStrings()) {
			x += str.buildProperties(tab + 1);
		}

		for (RKey k : c.getKeys()) {
			x += k.buildProperties(tab + 1);
		}
		for (RPair p : c.getPairs()) {
			x += p.buildProperties(tab + 1);
		}

		for (RPath p : c.getPaths()) {
			x += p.buildProperties(tab + 1);
		}

		for (RArray a : c.getArrays()) {
			x += a.buildProperties(tab + 1);
		}

		for (RStringArray a : c.getStringArrays()) {
			x += a.buildProperties(tab + 1);
		}

		s = String.format(s, x);
		return s;
	}

}
