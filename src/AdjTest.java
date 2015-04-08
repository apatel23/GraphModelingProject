import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AdjTest {

	static Maze m;
	Node node_A;
	Node node_B;
	Node node_C;
	Node node_D;
	Node node_E;
	Node node_F;
	Node node_G;
	Node node_I;
	Node node_J;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		m = new Maze("input.txt");
	}

	@Before
	public void setUp() throws Exception {
		 node_A = new Node("A");
		 node_B = new Node("B");
		 node_C = new Node("C");
		 node_D = new Node("D");
		 node_E = new Node("E");
		 node_F = new Node("F");
		 node_G = new Node("G");
		 node_I = new Node("I");
		 node_J = new Node("J");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testA() {
		 node_A.adjList = m.findAdj(node_A);
		 System.out.println("node_A.adjList.size(): " + node_A.adjList.size());
		Assert.assertTrue(node_A.adjList.size() == 1);
		Assert.assertTrue(node_A.adjList.contains(node_B));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testB() {
		 m.findAdj(node_B);
		Assert.assertTrue(node_B.adjList.size() == 4);
		Assert.assertTrue(node_B.adjList.contains(node_A));
		Assert.assertTrue(node_B.adjList.contains(node_C));
		Assert.assertTrue(node_B.adjList.contains(node_F));
		Assert.assertTrue(node_B.adjList.contains(node_E));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testC() {
		 m.findAdj(node_C);
		Assert.assertTrue(node_C.adjList.size() == 3);
		Assert.assertTrue(node_C.adjList.contains(node_B));
		Assert.assertTrue(node_C.adjList.contains(node_D));
		Assert.assertTrue(node_C.adjList.contains(node_G));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testD() {
		 m.findAdj(node_D);
		Assert.assertTrue(node_D.adjList.size() == 4);
		Assert.assertTrue(node_D.adjList.contains(node_C));
		Assert.assertTrue(node_D.adjList.contains(node_G));
		Assert.assertTrue(node_D.adjList.contains(node_I));
		Assert.assertTrue(node_D.adjList.contains(node_J));
	}

}
