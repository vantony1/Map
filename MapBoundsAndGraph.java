/*Name: Victor Nikhil Antony
CSC 172
NetID: vantony
ClassID: 134
Assignment # HW10
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaboarated with Matthew Sundsberg on this project

Class Written By: Matthew Sundsberg
 */


// Easy holder for a graph and its dimensions without messing too much with Victor's code
class MapBoundsAndGraph {
	String name;
	double minLat;
	double maxLat;
	double minLong;
	double maxLong;
	Graph graph;
	public MapBoundsAndGraph(Graph graph, double minLat, double maxLat, double minLong, double maxLong){
		this.graph = graph;
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLong = minLong;
		this.maxLong = maxLong;
	}
}