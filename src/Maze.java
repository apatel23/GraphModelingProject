import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
	
	private Transit transit;
	private Transit last_transit;
	private Color color;
	private Color last_color;
	private Scanner sc;
	private static Maze maze;
	private int villages;
	private int lines;
	private ArrayList<Village> vertices;
	private Graph g;
	private Village start;
	private Village finish;
	private Boolean found;
	private Boolean begin;
	
	// CONSTRUCTOR
	public Maze(String file) throws FileNotFoundException {
		transit = Transit.START;
		last_transit = null;
		color = Color.START;
		last_color = null;
		vertices = new ArrayList<Village>();
		start = null;
		finish = null;
		found = false;
		begin = true;
		g = new Graph();
		readFile(file);
		System.out.println("end reading file");
		setPoints(); // set start/finish points
		iterate();	
	}
	
	
	// Parse the input file
	public void readFile(String file) throws FileNotFoundException {
		sc = new Scanner(new File(file));
		String firstLine = sc.nextLine();
		String [] breakFirstLine = firstLine.split(" ");
		villages = Integer.parseInt(breakFirstLine[0]);
		lines = Integer.parseInt(breakFirstLine[1]);
		System.out.println("villages: " + villages + "\nlines: " + lines);
		String line = ""; // current line
		while(sc.hasNextLine()) { 
			line = sc.nextLine();
			System.out.println(line);
			String[] breaks = line.split(" ");
			String city1 = breaks[0];
			String city2 = breaks[1];
			String col = breaks[2];
			color = color(col);
			String route = breaks[3];
			transit = transit(route);
			Village a = new Village(city1);
			Village b = new Village(city2);
			
			Edge e = new Edge(a, b, transit, color);
			a.addEdge(e);
			b.addEdge(e);
			g.addEdge(e);
			
			vertices.add(a);
			last_transit = transit;
			last_color = color;
		}
	}
	
	// Display each vertex's neighbors
	public void neighhbors(Village vertex) {
		Iterable<Edge> neighbors = g.getNeighbors(vertex);
		System.out.println("------------");
		System.out.println("vertex: " + vertex.getName());
		for(Edge e : neighbors) {
			System.out.println("edge: " + e.getName());
			System.out.println("transit: " + e.getTransit() + " color: " + e.getColor());
		}
	}
	
	public Transit transit(String trans) {
		switch(trans) {
			case "H":
				return Transit.HORSE;
			case "C":
				return Transit.CABLE;
			case "T":
				return Transit.TROLLEY;
			case "B":
				return Transit.BUS;
			default:
				return null;
		}
	}
	
	public Color color(String col) {
		switch(col) {
		case "R":
			return Color.RED;
		case "G":
			return Color.GREEN;
		case "B":
			return Color.BLUE;
		default:
			return null;
		}
	}
	
	public void setPoints() {
		start = vertices.get(0);
		finish = vertices.get(vertices.size()-1);
		System.out.println("start: " + start.getName());
		System.out.println("finish: " + finish.getName());
	}

	public void iterate() {
		System.out.println("--------Iteration------------------");
		// start iteration
		if(start.neighbors == null) {
			System.out.println("Solution: " + start.getName());
			return;
		}
		Village currentV = start;
		Village lastV = currentV;
		// iterate until the destination is found
		while(!found) {
			System.out.println("current: " + currentV.getName());
			// look at neighbors
			if(currentV.neighbors.size() != 0) {
				for(Edge e : currentV.neighbors) {
					System.out.println("e: " + e.getName()) ;
					if(!e.discovered) {
						transit = e.getTransit();
						color = e.getColor();
						if(transit == last_transit || color == last_color || 
								begin == true) {
							e.discovered = true; // acceptable transfer
							currentV = e.getA(); // go to next village
						}
					}
				}	
				// update the current Village	
				last_transit = transit;
				last_color = color;
			} else {
				currentV = lastV; // back up
				transit = last_transit;
				color = last_color;
			}	
			if(currentV == finish) found = true; // done
		}	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		if(args.length < 1) {
			System.out.println("No args");
			return;
		}
		System.out.println("File: " + args[0]);
		maze = new Maze(args[0]);
		for(int i = 0; i < maze.vertices.size(); i++) {
			maze.neighhbors(maze.vertices.get(i));
		}	
	}
}
