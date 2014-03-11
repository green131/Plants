import java.util.Scanner;
import java.util.LinkedList;

public class TrackPlants {
	
	private static Scanner sc;
	private static LinkedList<Plant> allPlants;
	private static int maxGen = 0;
	
	private static Plant selectParent() {
		while(true) {
			for(int i=0; i<allPlants.size(); i++) {
				Plant p = allPlants.get(i);
				if(i==0) {
					System.out.println("Plant "+p.getName()+", "
							+ "your first plant:");
				} else {
					System.out.println("Plant "+p.getName()+", "
							+ "child of "+p.getParent().getName()+":");
				}
				if (sc.nextLine().equals("y")) { 
					return allPlants.get(i);
				}
			}
			return null;
		}
	}
	
	private static String createName() {
		System.out.println("Name your new plant: ");
		return sc.nextLine();
	}
	
	private static void addNewPlant() {
		System.out.println("Select your parent: (y/n)");
		Plant parent = selectParent();
		if (parent!=null) {
			Plant t = new Plant(parent, createName());
			allPlants.add(t);
			parent.addChild(t);
			if(maxGen < t.getGeneration()) { maxGen = t.getGeneration(); }
			System.out.println("Plant added!");
		} else {
			System.out.println("No parent chosen, WHOOPS!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void printChildren(Object[] gens, Plant t) {
		for (int i=0; i<t.getNumberOfChildren(); i++) {
			((LinkedList<Plant>) gens[t.getChild(i).getGeneration()]).add(t.getChild(i));
			printChildren(gens, t.getChild(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void printTree() {
		Object[] gens = new Object[maxGen+1];
		for (int i=0; i<gens.length; i++) { gens[i] = new LinkedList<Object>(); }
		Plant t = allPlants.getFirst();
		((LinkedList<Plant>) gens[t.getGeneration()]).add(t);
		printChildren(gens, t);
		for (int i=0; i<gens.length; i++) {
			System.out.println("Generation "+i+":");
			for (int j=0; j<((LinkedList<Plant>) gens[i]).size(); j++) {
				System.out.println("- "+((LinkedList<Plant>) gens[i]).get(j).getName());
			}
		}
	}
	
	public static void main(String[] args) {
		allPlants = new LinkedList<Plant>();
		sc = new Scanner(System.in);
		System.out.print("Name your first plant: ");
		String name = sc.nextLine();
		allPlants.add(new Plant(null, name));
		System.out.println("New plant " + name + " created!"
				+ "\nAdd children now? (y/n) ");
		if (sc.nextLine().equals("y")) { addNewPlant(); }
		while (true) {
			System.out.println("Enter c to create a new plant"
					+ "\nEnter o to view plant generations");
			switch (sc.nextLine()) {
				case "c":	addNewPlant();
							break;
				case "o":	printTree();
							break;
			}
		}		
	}

}
