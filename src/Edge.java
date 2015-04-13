
public class Edge {
	String name;
	Transit transit;
	Color color;
	Village a;
	Village b;
	Boolean discovered;
	
	Edge(Village first, Village second, Transit trans, Color col) {
		name = first.getName() + " to " + second.getName();
		this.a = first;
		this.b = second;
		this.transit = trans;
		this.color = col;
		discovered = false;
	}
	
	public String getName() {
		return name;
	}

	public Transit getTransit() {
		return transit;
	}

	public Color getColor() {
		return color;
	}

	public Village getA() {
		return a;
	}

	public Village getB() {
		return b;
	}
	
	
}
