/*Name: Victor Nikhil Antony
CSC 172
NetID: vantony
ClassID: 134
Assignment # HW10
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaboarated with Matthew Sundsberg on this project
 */


//Edge class that defines an edge and implements Comparable
public class Edge implements Comparable<Edge> {
	
	//Declares foundational variables that are to be defined for every edge
	public String name;
	public double distance;
	public Node start, end;
	
	//Constructor for Edge which takes in name, and start and end vertices.
	public Edge(String name, Node start, Node end) {
		//Sets name and start and end as per input, calculates distance between points and sets it to distance
		//and adds start and end as each others' neighbours
		this.name = name;
		this.start = start;
		this.end = end;
		
		this.distance = findDistance(start, end);
		
		this.start.addNeighbour(end);
		this.end.addNeighbour(start);
	}
	
	
	//if two edges are the same, returns 0 otherwise return -1
	//determines equality with same originating from same vertices (start and end don't matter) 
	//and having same distance
	@Override
	public int compareTo(Edge other) {

		if((this.start == other.start && this.end == other.end)|| (this.start == other.end && this.end == other.start)) {
			if(this.distance == other.distance) {
			return 0;
			}
		}
		return -1;
	}
	
	//Finds distance between two vertices using haversine formula to take into consideration the earth's curvature.
	public double findDistance(Node a, Node b) {
		
		double start_pos_lat = Math.toRadians(a.getLatitude());
		double end_pos_lat = Math.toRadians(b.getLatitude());
		
		double HaversineLatitude = Math.toRadians((b.getLatitude() - a.getLatitude()));
		double HaversineLongitude = Math.toRadians((b.getLongitude() - a.getLongitude()));
		
		
		double haverine = Math.pow(Math.sin(HaversineLatitude / 2), 2) + Math.cos(start_pos_lat) * Math.cos(end_pos_lat) * Math.pow(Math.sin(HaversineLongitude / 2), 2);
		
		double distance = 2*Math.atan2(Math.sqrt(haverine), Math.sqrt(1-haverine));
		
		return 6371* distance;
	}
	

	//Standard computer generated getters and setters for all variables;
	public String getName() {
		return name;
	}

	public double getDistance() {
		return distance;
	}

	public Node getStart() {
		return start;
	}

	public Node getEnd() {
		return end;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public void setEnd(Node end) {
		this.end = end;
	}
	
	

}
