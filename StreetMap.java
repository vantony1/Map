/*Name: Victor Nikhil Antony
CSC 172
NetID: vantony
ClassID: 134
Assignment # HW10
TR 4:50-6:05
TA Name: Sifan Ye
I did not collaboarated with Matthew Sundsberg on this project

Class Written By: Victor antony */


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


//StreetMap Method that has the main method that runs the program
//and mapMaker method that reads a file to appropriately make a map
public class StreetMap {
	// Run it all
	public static void main(String[] args) {
		// Store command arguments as a list so can remove them to simplify processing
		ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));

		// Initialize some "global" things
		MapBoundsAndGraph graphHolder;
		Navigator navigator = new Navigator();
		boolean displaying = false;
		String name;
		List<Node> directionsPath = null;

		// Tell the user the program started
		System.out.println("Processing...");

		// If given any arguments, read the first and make a graph
		if(args.length >= 1){
			// Make a map from the first argument
			try{
				graphHolder = mapMaker(new BufferedReader(new FileReader(arguments.get(0))));
				name = arguments.get(0);
			} catch(Exception ex){
				System.out.println("Error: Invalid or missing map file.");
				return;
			}
			arguments.remove(0);
		} else {
			System.out.println("No graph file specified.");
			return;
		}

		// If displaying
		if(arguments.contains("--show")){
			displaying = true;
			arguments.remove("--show");
		}

		// If doing directions
		if(arguments.contains("--directions")){
			// Try to get starting points
			String start;
			String end;
			int directionsIndex = arguments.indexOf("--directions");
			arguments.remove(directionsIndex);
			if(arguments.size() - directionsIndex == 2){
				start = arguments.get(directionsIndex);
				arguments.remove(directionsIndex);
				end = arguments.get(directionsIndex);
				arguments.remove(directionsIndex);
			} else {
				System.out.println("Invalid arguments given.");
				return;
			}

			// Get the path from A to B
			directionsPath = navigator.navigate(graphHolder.graph, graphHolder.graph.findVertex(end), graphHolder.graph.findVertex(start));

			// Print out the path
			System.out.println("Path from " + start + " to " + end + ": ");
			for(Node step : directionsPath){
				System.out.print(step.getName() + ", ");
			}
			System.out.println("\b\b");
		}

		// Check for more arguments left over (shouldn't have)
		if(arguments.size() > 0){
			System.out.println("Too many arguments.");
			return;
		}

		// Display the graph
		if(displaying){
			// Make a jFrame for it all
			JFrame frame = new JFrame("Map display: " + name);

			// Make a parent container so maintaining aspect ratios is easier
			JPanel container = new JPanel(new GridBagLayout());

			// Make a new instance of the canvas
			MapDisplay canvas = new MapDisplay(graphHolder, directionsPath);
			container.add(canvas);
			frame.add(container);

			// Further setup the frame sizing and show the frame
			frame.setSize(600, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setMinimumSize(new Dimension(300, 300));
			frame.setVisible(true);
		}
	}

	//mapMaker method that reads a file to appropriately make a map
	public static MapBoundsAndGraph mapMaker(BufferedReader bufferedReader) throws IOException {
		Graph graph = new Graph();
		String line;
		double latitude;
		double longitude;
		double minLat = 0;
		double maxLat = 90;
		double minLong = -180;
		double maxLong = 180;

		// Runs while there are more lines in the file to be read
		line = bufferedReader.readLine();
		while(line != null) {
			// Split up the string (safely)
			String[] lineContents = line.split("\\s");
			if(lineContents.length != 4){
				throw new IOException("Invalid number of line arguments.");
			}

			// Process if adding an intersection
			if(lineContents[0].equals("i")){
				// Gets location from the proper spot
				latitude = Double.parseDouble(lineContents[2]);
				longitude = Double.parseDouble(lineContents[3]);

				// Update map bounds
				if(latitude < maxLat){maxLat = latitude;}
				if(latitude > minLat){minLat = latitude;}
				if(longitude < maxLong){maxLong = longitude;}
				if(longitude > minLong){minLong = longitude;}

				// Creates vertex with the name and location and continue
				graph.addVertex(new Node(lineContents[1], longitude, latitude));
			}

			// Process if adding edge
			if(lineContents[0].equals("r")){
				// Gets the two endpoints by name
				Node intersection1 = graph.findVertex(lineContents[2]);
				Node intersection2 = graph.findVertex(lineContents[3]);

				// Create a new edge around the two intersections
				graph.addEdge(new Edge(lineContents[1], intersection1, intersection2));
			}

			// Load the next line
			line = bufferedReader.readLine();
		}
		return new MapBoundsAndGraph(graph, minLat, maxLat, minLong, maxLong);
	}


}
