import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graph {
	// Alternatively, use a Multimap:
    // http://google-collections.googlecode.com/
	// svn/trunk/javadoc/com/google/common/collect/Multimap.html
	
    public Map<Village, List<Edge>> edges = new HashMap<Village, List<Edge>>();

    public void addEdge(Edge edge) {
        List<Edge> srcNeighbors = this.edges.get(edge.getA());
        if (srcNeighbors == null) {
            this.edges.put(edge.getA(),
                srcNeighbors = new ArrayList<Edge>()
            );
        }
        System.out.println("add: " + edge.getName());
        srcNeighbors.add(edge);
    }
 
    public Iterable<Edge> getNeighbors(Village vertex) {
        List<Edge> neighbors = this.edges.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(neighbors);
        }
    }

}
