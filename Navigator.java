/*Name: Victor Nikhil Antony
CSC 172
NetID: vantony
ClassID: 134
Assignment # HW10
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaboarated with Matthew Sundsberg on this project

Class Written By: Victor Antony
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


//Navigator class that basically is set to find the shortest path between two vertices
public class Navigator {

	//Declares variables that are needed in methods defined in this class
	Node current, closest;
	LinkedList<Node> path = new LinkedList<Node>();
	PriorityQueue<Node> queue = new PriorityQueue<Node>(30, new Minimum());
	double distance;


	// navigate method that takes in a graph and the origin and destination
	public LinkedList<Node> navigate(Graph graph, Node origin, Node destination) {

		//Calls djikstra method to find the shortest path
		djikstra(graph, origin, destination);

		//Gets the distance from origin to destination
		this.distance = Math.abs(destination.getDistance());

		//initiates path and sets current vertex as destination
		path = new LinkedList<Node>();

		current = destination;

		// Basically backtracks from destination to origin taking the shortest path
		//adds each vertex into path
		while(!(current.equals(origin))){

			path.add(current);
			closest = current.closest;
			current = closest;

		}

		// Adds origin into the path
		path.add(current);

		// Prints out the distance and the path
		System.out.println("Distance to Destination: " + distance + " kms");
		return path;
	}

	//djikstra method to find the shortest path
	public void djikstra(Graph graph, Node origin, Node destination) {

		//sets all vertices distance to -1 and visited as false;
		for(Node v : graph.vertices.values()){
			v.setDistance(-1);
			v.setVisited(false);
		}

		//sets origins distance as 0 and visited as true
		origin.setDistance(0);
		origin.setVisited(true);

		//initiates a priority Queue with comparator that returns the vertex with the smallest distance value
		queue = new PriorityQueue<Node>(30, new Minimum());


		//adds all of origins' neighbours' to the que and sets origin as their closest vertex
		for(Node v : origin.neighbour.values()){
			queue.add(v);
			v.closest = origin;
		}

		//Runs while queue is not empty or destination has not been encountered
		while(!queue.isEmpty() || current.equals(destination)){

			//polls queue to set current to the closest vertex and sets its visited to true
			current = queue.poll();
			current.setVisited(true);

			//runs through all of currents' neighbours 
			for(Node v : current.neighbour.values()){

				//if v has not been visited
				if(!v.visited){

					//adds v to queue if its not already there
					if(!queue.contains(v)){
						queue.offer(v);
					}
					//finds the distance between v and current
					double length = findDistance(v, current);

					//if distance from origin to v is shorter than previously set distance
					//changes distance and sets closest to current
					if(v.distance == -1){
						v.distance = length + current.distance;
						v.closest = current;
					} else if(v.distance > length + current.distance){
						v.distance = length + current.distance;
						v.closest = current;
					}

				}

			}

		}

	}

	//finds distance between two points using haversine formula
	public double findDistance(Node a, Node b) {

		double start_pos_lat = Math.toRadians(a.getLatitude());
		double end_pos_lat = Math.toRadians(b.getLatitude());
		
		double HaversineLatitude = Math.toRadians((b.getLatitude() - a.getLatitude()));
		double HaversineLongitude = Math.toRadians((b.getLongitude() - a.getLongitude()));
		
		
		double haverine = Math.pow(Math.sin(HaversineLatitude / 2), 2) + Math.cos(start_pos_lat) * Math.cos(end_pos_lat) * Math.pow(Math.sin(HaversineLongitude / 2), 2);
		
		double distance = 2*Math.atan2(Math.sqrt(haverine), Math.sqrt(1-haverine));
		
		return 6371* distance;
	}


	//Comparator to set PriorityQueue's priority inversely i.e. smaller is higher priority based on distance values
	public class Minimum implements Comparator<Node> {

		@Override
		public int compare(Node a, Node b) {


			if(a.getDistance() > b.getDistance()){
				return 1;
			}

			if(a.getDistance() < b.getDistance()){
				return -1;
			}

			return 0;
		}
	}

}
