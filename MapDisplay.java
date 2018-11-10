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


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;

public class MapDisplay extends JPanel {
	// Overall data
	private int windowWidth;
	private int windowHeight;
	final private HashMap<String, Edge> edges;
	final private List<Node> path;
	final private double minLat;
	final private double latHeight;
	final private double minLong;
	final private double longWidth;
	final private double aspectRatio; // Is width/height

	public MapDisplay(MapBoundsAndGraph graphToDisplay, List<Node> path){
		// Pass through all of the information received from the constructor arguments
		edges = graphToDisplay.graph.edges;
		this.path = path;
		minLat = graphToDisplay.minLat;
		latHeight = graphToDisplay.maxLat - minLat;
		minLong = graphToDisplay.minLong;
		longWidth = graphToDisplay.maxLong - minLong;

		// This took forever to figure out
		aspectRatio = 1.0 * longWidth / latHeight;

		// Tell the user a display is made
		System.out.println("Map display finished.");

		// Initialize dimensions
		windowWidth = getWidth();
		windowHeight = getHeight();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Update dimensions
		windowWidth = getWidth();
		windowHeight = getHeight();

		// Line styles
		g.setColor(Color.BLACK);

		// For each edge in the graph, draw a line from the proper coordinates to the other proper coordinates (this may make resizing slow af).
		edges.forEach((name, edge) -> {
			// Scale the coordinates to 1x1
			double[] start = scaleToOne(edge.getStart());
			double[] end = scaleToOne(edge.getEnd());

			// And draw a line in the proper place
			g.drawLine((int)(windowWidth * start[0]),(int)(windowHeight * start[1]),(int)(windowWidth * end[0]),(int)(windowHeight * end[1]));
		});

		// Draw the specific path
		if(path != null){
			// Make the paths red
			g.setColor(Color.RED);

			// For the entire path draw the line in red
			for(int i = 0; i < path.size() - 1; i++){
				double[] start = scaleToOne(path.get(i));
				double[] end = scaleToOne(path.get(i + 1));
				g.drawLine((int)(windowWidth * start[0]),(int)(windowHeight * start[1]),(int)(windowWidth * end[0]),(int)(windowHeight * end[1]));
			}
		}
	}


	// Scale the latitude and longitude to a factor of one (to multiply by width or height)
	private double[] scaleToOne(Node node){
		double latitude = (node.latitude - minLat) / latHeight;
		double longitude = (node.longitude - minLong) / longWidth;
		return new double[]{1 - longitude, latitude}; // Subtracting from one since flipped coordinates system somehow
	}

	// Code to maintain aspect ratio: https://stackoverflow.com/a/27545074/3196151
	@Override
	public Dimension getPreferredSize() {
		Dimension container = this.getParent().getSize();
		Dimension preferred = new Dimension();


		// If taller than wide (if taller than the map)
		if(1.0 * container.width / container.height < aspectRatio){
			// Make the width full sized
			preferred.width = container.width;

			// Scale the height to match the width
			preferred.height = (int)(preferred.width / aspectRatio);
		} else {
			// If wider than tall
			// Make the height the height
			preferred.height = container.height;

			// Scale the width to match the height
			preferred.width = (int)(preferred.height * aspectRatio);

		}
		return preferred;
	}
}
