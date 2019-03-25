import java.util.ArrayList;
import java.util.HashMap;


//Graph class that defines a Graph 
public class Graph {
	//HashMaps that holds all vertices and edges with their names as key and the vertex/edge itself as value
	public HashMap<String, Node> vertices;
	public HashMap<String, Edge> edges;
	
	
	//Constructor for Edge which takes in a name 
	public Graph() {
		// Initializes HashMaps tracking vertices and edges in the graph
		this.vertices = new HashMap<String, Node>();
		this.edges = new HashMap<String, Edge>();
	}
	
	//Constructor for Edge which takes in a name and HashMaps for vertices and edges
	public Graph(String name, ArrayList<Node> vertices, ArrayList<Edge> edges) {
		//Sets vertices and edges as per input
		this.vertices = new HashMap<String, Node>();
		this.edges = new HashMap<String, Edge>();
	}
	
	//Method that adds a node to the graph if the graph doesn't contain the same node already and returns true if sucess
	public boolean addVertex(Node node) {
		
		if(!(this.vertices.containsKey(node.getName()))) {
			//sets the node's name as key and the node as the value
			this.vertices.put(node.getName(), node);
			return true;
		}
		return false;
		 
	}

	// Method that adds a edge to the graph if the graph doesn't contain the same vertex already and returns true if success
	public boolean addEdge(Edge edge) {
		if(!this.edges.containsValue(edge)) {
			//sets the edge's name as key and the edge as the value
			this.edges.put(edge.getName(), edge);
			
			//adds the edges vertices to the graph
			this.addVertex(edge.getStart());
			this.addVertex(edge.getEnd());
	
			return true;
			
		} else {
			
			return false;
		}
		
	}
	
	//method that returns vertex with given name in the graph
	public Node findVertex(String name) {
		
		if(this.vertices.containsKey(name)) {
			return this.vertices.get(name);
		}
		
		return null;
		
	}
	
	
}
