import java.util.HashMap;


//Node class that defines a vertex and implements Comparable
public class Node implements Comparable<Node> {
	
	//Declares foundational variables that are to be defined for every vertex
	public String name;
	public double distance ,longitude, latitude;
	public boolean visited;
	public Node closest;
	
	//HashMap that holds all neighbouring vertices with vertex name as key and the vertex itself as value
	public HashMap<String, Node> neighbour;
	
	
	//Constructor for Node which takes in name, and location.
	public Node(String name, double longitude, double latitude) {
		//Sets name and location as per input and visited as false and initializes neighbours hashmap
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		visited = false; 
		neighbour =	new HashMap<String, Node>();
	}
	
	//returns 0 if the two Node are the same; else returns -1
	//Determines two vertices to be equal if both are in the same location
	@Override
	public int compareTo(Node other) {
		
		if(other.getLatitude() == this.getLatitude() && other.longitude == this.longitude) {
			return 0;
		}
		
		return -1;
	}
	
	//addNeighbour that adds vertex to the neighbours hashmap to keep track of adjacent vertices
	public boolean addNeighbour(Node neighbour) {
		
		if(!(this.neighbour.containsValue(neighbour))) {
			this.neighbour.put(neighbour.getName(), neighbour);
			return true;
		}
		return false;
		
	}

	
	//Standard computer generated getters and setters for all variables;
	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Node getClosest() {
		return closest;
	}

	public HashMap<String, Node> getNeighbour() {
		return neighbour;
	}

	public void setClosest(Node closest) {
		this.closest = closest;
	}

	public void setNeighbour(HashMap<String, Node> neighbour) {
		this.neighbour = neighbour;
	}
	
	

	
	
	
	
}
