import java.util.LinkedList;

public class Plant {
	
	private String name;
	private Plant parent;
	private LinkedList<Plant> children;
	private int generation;
	
	public Plant(Plant parent, String name) {
		this.name = name;
		this.parent = parent;
		if (parent==null) { generation = 0; }
		else { generation = parent.getGeneration() + 1; }
		this.children = new LinkedList<Plant>();
	}

	public String getName() {
		return name;
	}

	public Plant getParent() {
		return parent;
	}

	public Plant[] getChildren() {
		return (Plant[]) children.toArray();
	}
	
	public Plant getChild(int i) {
		return children.get(i);
	}
	
	public void addChild(Plant child) {
		this.children.add(child);
	}
	
	public int getNumberOfChildren() {
		return children.size();
	}

	public int getGeneration() {
		return generation;
	}

}
