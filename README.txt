/*Name: Victor Nikhil Antony
CSC 172
NetID: vantony
ClassID: 134
Assignment # HW10
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaboarated with Matthew Sundsberg on this project
 */
NOTE: Distance is shown in kilometers because most of the world uses kms and it make more sense! 

Team Members: Victor Antony (Graph Representation & other Algorithms), Matthew Sundsberg (UI and FileReading)

List of files included in the submission: Edge.java, Graph.java, Vertex.java, MapBoundsAndGraph.java MapDisplay.java Navigator.java StreetMapper.java 

Significant obstacles overcome: Aspect Ratio and Large Map processing speeds

How we structured the project: First, we tackled the issue of representing the data provided in form of graph; In order to do this we made classes to represent Vertex, Edge and Graph. Then, we tackled the problem of finding the route between two given vertices by implementing Djikstra's algorithm provided through psuedocode on Wikipedia. then, we tested this part of the code by implementing a reading method that took in data from the .txt files and translated it into graphs. Then, we used java’s swing to draw the map and took into consideration the aspect ration -- we also used java’s swing to color the path with a different color.

How we tackled issues due to larger maps: We used a priority queue for Djikstra to speed up the process and made djikstra stop once the destination was found preventing it from running through the whole graph for no reason. We also had to take into consideration the aspect ratio changes between different sized maps.

Detailed Description: 

Classes Used: Vertex, Edge, Graph, Navigator, StreetMapper MapBoundsAndGraph and MapDisplay

Vertex: 
          Public Members: String name; double distance ,longitude, latitude; boolean visited; Vertex                                          closest; HashMap<String, Vertex> neighbour
          Methods: Constructor - public Vertex(String name, double longitude, double latitude),                                      CompareTo -- to implement comparable, public boolean addNeighbour(Vertex  
                          neighbour) ++ getters and setters for all member

Edge:
        Public Members: String name; double distance; Vertex start, end;
        Methods: Constructor - public Edge(String name, Vertex start, Vertex end), compareTo(Edge other). double findDistance(Vertex a, Vertex b), ++ getters and setters for all member

Graph:
         Public Members: HashMap<String, Vertex> vertices; HashMap<String, Edge> edges;
         Methods: Constructor - Graph(String name), Graph(String name, ArrayList<Vertex>             
                         vertices,  ArrayList<Edge> edges), public boolean addVertex(Vertex 
                         vertex), public boolean addEdge(Edge edge), public Vertex findVertex(String 
                         name)

Navigator:
          Public Members: Vertex current, closest; LinkedList<Vertex> path; PriorityQueue<Vertex> 
                                      queue; double distance;
          Methods: public LinkedList<Vertex> navigate(Graph graph, Vertex origin, Vertex  
                          destination), public void djikstra(Graph graph, Vertex origin, Vertex 
                          destination), public double findDistance(Vertex a, Vertex b), public class  
                          Minimum implements Comparator<Vertex>
MapDisplay
	Public Members: none
	Methods: Constructor, paintComponent, scaleToOne (helper), getPreferedSize (Aspect Ratio Management) 
How program works: The main method exists in StreetMapper where it reads data from the file and constructs a graph using classes Vertex, Edge, and Graph; Then, dependent on the --show and --directions requests takes further actions. if directions are requested, uses Navigator class to use Djikstra's algorithm to provide the shortest route to the destination. If show is requested, a Swing JFrame is made with a Gridbag container that allows the canvas to keep a constant aspect ratio, and the canvas added inside of it. The canvas is of the set aspect ratio of the map, so that the endpoints of the edges can be normalized to 0-1 and then multiplied by the width or height safely. 


Runtime Analysis

K ⇒ constant

Class Edge
          Method: findDistance -- O(k)

Class Graph 
          Method: addVertex -- O(k)
                        addEdge -- O(k)
                        findVertex -- O(k)
Class Vertex 
          Method: addNeighbour -- O(k)

Class Navigator 
          Method: Djikstra -- O(|E| log |V|)
                        Navigate -- O(|E| log |V|)
                        findDistance -- O(k)

Class StreetMap
	Method: mapMaker -- O(file length)
	Method: main -- O(file length)

Class MapDisplay
	paintComponent -- O(|E|)
	scaleToOne -- O(1)
	getPreferedSize -- O(1)



