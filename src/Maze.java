import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Maze {
	
	private Transit transit;
	private Color color;
	private Scanner sc;
	private static Maze maze;
	private String villages;
	private String lines;
	
	public Maze(String file) throws FileNotFoundException {
		transit = null;
		color = null;
		readFile(file);
		
	}
	
	public void readFile(String file) throws FileNotFoundException {
		sc = new Scanner(new File(file));
		String firstLine = sc.nextLine();
		String [] breakFirstLine = firstLine.split(" ");
		villages = breakFirstLine[0];
		lines = breakFirstLine[1];
		System.out.println("villages: " + villages + "\nlines: " + lines);
		String line = "";
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			System.out.println(line);
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
