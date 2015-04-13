import java.util.ArrayList;
import java.util.Iterator;


public class Village implements Iterable<Edge> {
	String name;
	ArrayList<Edge> neighbors;
	
	Village(String n) {
		this.name = n;
		neighbors = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e) {
		neighbors.add(e);
	}
	
	public void displayEdges() {
		for(Edge e : neighbors)
			System.out.println(e.getName());
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Iterator<Edge> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
