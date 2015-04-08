import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Maze {
	
	private Transit transit;
	private Color color;
	private Scanner sc;
	private static Maze maze;
	private int villages;
	private int lines;
	private ArrayList<String> vertices;
	private ArrayList<Character> adjList;
	
	// CONSTRUCTOR
	public Maze(String file) throws FileNotFoundException {
		transit = null;
		color = null;
		vertices = new ArrayList<String>();
		adjList = new ArrayList<Character>();
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

			System.out.println("city1: " + city1 + " city2: " + city2);
		}
	}


	public static void main(String[] args) throws FileNotFoundException {
		if(args.length < 1) {
			System.out.println("No args");
			return;
		}
		System.out.println("File: " + args[0]);
		maze = new Maze(args[0]);

	}

}
