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
	private ArrayList<String> vertices;
	private Graph g;
	
	// CONSTRUCTOR
	public Maze(String file) throws FileNotFoundException {
		transit = null;
		last_transit = null;
		color = null;
		last_color = null;
		vertices = new ArrayList<String>();
		g = new Graph();
		readFile(file);
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
			if(!vertices.contains(city1)) vertices.add(city1);
			if(!vertices.contains(city2)) vertices.add(city2);
			if(last_transit == transit || last_transit == null)
				g.addRoute(city1, city2);
			else if(last_color == color || last_color == null)
				g.addRoute(city1, city2);
			g.addEdge(city1, city2);
			g.addEdge(city2, city1);
			System.out.println("city1: " + city1 + " city2: " + city2);
			
			
			last_transit = transit;
			last_color = color;
		}
	}
	
	// Display each vertex's neighbors
	public void neighhbors(String vertex) {
		Iterable<String> neighbors = g.getNeighbors(vertex);
		System.out.println("vertex: " + vertex);
		for(String s : neighbors) {
			System.out.println("neighbor: " + s);
		}
	}
	
	// Display each vertex's routes
	public void routes(String vertex) {
			Iterable<String> routes = g.getRoute(vertex);
			System.out.println("vertex: " + vertex);
			for(String s : routes) {
				System.out.println("route: " + s);
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
		for(int i = 0; i < maze.vertices.size(); i++) {
			maze.routes(maze.vertices.get(i));
		}
		
	}

}
