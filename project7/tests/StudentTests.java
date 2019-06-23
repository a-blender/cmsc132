//STUDENT TESTS by Anna Blendermann
package tests;
import java.util.Collection;
import java.util.Set;

import graph.Graph;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTests 
{
	// write your student tests in this class
	//create a toString() for your reference

	//prints out the TestCode graphs for reference
	@Test public void testPublic1() 
	{
		Graph<String> graph1 = TestCode.exampleGraph1();
		Graph<Integer> graph2 = TestCode.exampleGraph2();
		Graph<String> graph3 = TestCode.exampleGraph3();
		Graph<Integer> graph4 = TestCode.exampleGraph4();
		Graph<Character> graph5 = TestCode.exampleGraph5();

		System.out.println("******************" + "\n");
		System.out.println("GRAPH 1: " + graph1.toString());
		System.out.println("\nGRAPH 2: " + graph2.toString());
		System.out.println("\nGRAPH 3: " + graph3.toString());
		System.out.println("\nGRAPH 4: " + graph4.toString());
		System.out.println("\nGRAPH 5: " + graph5.toString());
		System.out.println("\n" + "******************");
	}

	//tests addVertex for adding two valid vertices
	@Test public void testPublic2() 
	{
		Graph<String> graph = TestCode.exampleGraph1();
		assertTrue(graph.addVertex("josh groban"));
		assertTrue(graph.isVertex("josh groban"));
	}

	//tests addVertex for vertex already present
	@Test public void testPublic3() 
	{
		Graph<String> graph = TestCode.exampleGraph1();
		assertFalse(graph.addVertex("kangaroo"));
		assertTrue(graph.addVertex("wombat"));
		assertTrue(graph.isVertex("wombat"));
	}

	//tests addVertex for null parameters
	@Test(expected=NullPointerException.class)
	public void testPublic4() 
	{
		Graph<String> graph = TestCode.exampleGraph1();
		graph.addVertex(null);
	}

	//tests isVertex for valid string and integer graphs
	@Test public void testPublic5() 
	{
		Graph<String> graph1 = TestCode.exampleGraph1();
		Graph<Integer> graph2 = TestCode.exampleGraph2();

		assertTrue(graph1.isVertex("quokka")); 
		assertTrue(graph2.isVertex(5));
		assertTrue(graph2.isVertex(17));
	}

	//tests isVertex for invalid strings, ints, chars
	@Test public void testPublic6() 
	{
		Graph<String> graph1 = TestCode.exampleGraph1();
		Graph<String> graph3 = TestCode.exampleGraph1();

		assertFalse(graph1.isVertex("brother bear")); 
		assertTrue(graph1.isVertex("koala"));
		assertFalse(graph1.isVertex("puppy")); 
	}

	//tests isVertex for null parameters
	@Test(expected=NullPointerException.class)
	public void testPublic7() 
	{
		Graph<Character> graph = TestCode.exampleGraph5();
		graph.addVertex(null);
	}

	//tests getVertices for graph with ten integers 
	@Test public void testPublic8() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		Collection<Integer> keyset = graph.getVertices();

		assertEquals(10, keyset.size());
		assertTrue(graph.addVertex(250)); 
		assertEquals(11, keyset.size());
	}

	//tests getVertices for graph with zero elements
	@Test public void testPublic9() 
	{
		Graph<String> graph = new Graph<String>(TestCode.strComparator);
		Collection<String> keyset = graph.getVertices();
		assertEquals(0, keyset.size());
	}

	//tests getVertices for null parameters
	@Test(expected=NullPointerException.class)
	public void testPublic10() 
	{
		Graph<Integer> graph = null;
		graph.getVertices(); //should throw exception
	}

	//test removeVertex from graph with no edges
	@Test public void testPublic11() 
	{
		Graph<String> graph = TestCode.exampleGraph1();

		assertTrue(graph.removeVertex("kangaroo"));		
		assertEquals("[ koala numbat quokka ]", graph.vertexString());

		assertTrue(graph.removeVertex("numbat"));
		assertEquals("[ koala quokka ]", graph.vertexString());
	}

	//test removeVertex with graph with in/out edges
	@Test public void testPublic12() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		System.out.println("\nTEST 12 BEFOREHAND: " + graph.toString());

		assertTrue(graph.removeVertex(7)); //remove 7
		System.out.println("TEST 12 AFTERWARD: " + graph.toString());
		assertEquals(9, graph.getVertices().size());
	}

	//second test of removeVertex for graph with in/out edges
	@Test public void testPublic13() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		System.out.println("\nTEST 13 BEFOREHAND: " + graph.toString());

		assertTrue(graph.removeVertex(13));
		assertTrue(graph.removeVertex(19));
		assertTrue(graph.removeVertex(31));

		System.out.println("TEST 13 AFTERWARD: " + graph.toString());
		assertEquals(7, graph.getVertices().size());
	}

	//test removeVertex with vertex that doesn't exist
	@Test public void testPublic14() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();

		assertTrue(graph.removeVertex(3));
		assertFalse(graph.removeVertex(100));
	}

	//test removeVertex with null parameters
	@Test(expected=NullPointerException.class)
	public void testPublic15() 
	{
		Graph<String> graph = TestCode.exampleGraph3();
		graph.removeVertex(null);

		Graph<String> graph2 = null;
		graph2.removeVertex("dreadlocks");
	}

	//test addEdgeBetweenVertices for adding edges
	@Test public void testPublic16() 
	{
		Graph<String> graph = TestCode.exampleGraph1();
		System.out.println("\nTEST 16 BEFOREHAND: " + graph.toString());

		assertTrue(graph.addEdgeBetweenVertices("koala", "numbat", 2));
		assertTrue(graph.addEdgeBetweenVertices("numbat", "quokka", 2));
		assertTrue(graph.addEdgeBetweenVertices("kangaroo", "numbat", 2));
		assertTrue(graph.addEdgeBetweenVertices("quokka", "koala", 2));

		System.out.println("TEST 16 AFTERWARD: " + graph.toString());
		assertEquals(4, graph.getVertices().size());
	}

	//second test addEdgeBetweenVertices for adding edges
	@Test public void testPublic17() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		System.out.println("\nTEST 17 BEFOREHAND: " + graph.toString());

		assertTrue(graph.addEdgeBetweenVertices(17, 5, 1));
		assertTrue(graph.addEdgeBetweenVertices(2, 13, 1));
		assertFalse(graph.addEdgeBetweenVertices(3, 12, 1));

		System.out.println("TEST 17 AFTERWARD: " + graph.toString());
		assertEquals(10, graph.getVertices().size());
	}

	//test addEdgeBetweenVertices for already existing edges
	@Test public void testPublic18() 
	{
		Graph<String> graph = TestCode.exampleGraph3();
		System.out.println("\nTEST 18 BEFOREHAND: " + graph.toString());

		assertTrue(graph.addEdgeBetweenVertices("koala", "manatee", 24));
		assertTrue(graph.addEdgeBetweenVertices("beagle", "flamingo", 24));
		assertTrue(graph.addEdgeBetweenVertices("ocelot", "elephant", 50));

		System.out.println("TEST 18 AFTERWARD: " + graph.toString());
		assertEquals(15, graph.getVertices().size());
	}

	//test addEdgeBetweenVertices for invalid cost values
	@Test public void testPublic19() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();

		assertFalse(graph.addEdgeBetweenVertices(100, 125, 10));
		assertFalse(graph.addEdgeBetweenVertices(5, 72, 10));
		assertFalse(graph.addEdgeBetweenVertices(13, 19, -20));
	}

	//test addEdgeBetweenVertices for null params
	@Test(expected=NullPointerException.class)
	public void testPublic20() 
	{
		Graph<String> graph = null;
		assertFalse(graph.addEdgeBetweenVertices("dog", "fly", 3));
	}

	//test getEdgeCost for some sample edges
	@Test public void testPublic21() 
	{
		Graph<Character> graph = TestCode.exampleGraph5();

		assertEquals(21, graph.getEdgeCost('c', 'g'));
		assertEquals(14, graph.getEdgeCost('a', 'g'));

		assertTrue(graph.addEdgeBetweenVertices('h', 'g', 12));
		assertEquals(12, graph.getEdgeCost('h', 'g'));
	}

	//test getEdgeCost for invalid parameters
	@Test public void testPublic22() 
	{
		Graph<Character> graph = TestCode.exampleGraph5();

		assertEquals(-1, graph.getEdgeCost('k', 'b')); //no edge
		assertEquals(-1, graph.getEdgeCost('1', 'p'));
		assertEquals(-1, graph.getEdgeCost('z', 'y'));
	}

	//test getEdgeCost for null source or destination
	@Test(expected=NullPointerException.class)
	public void testPublic23() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();

		graph.getEdgeCost(null, 75);
		graph.getEdgeCost(80, null);
	}

	//tests removeEdgeBetweenVertices by removing some edges
	@Test public void testPublic24() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		graph.removeEdgeBetweenVertices(3, 5);
		graph.removeEdgeBetweenVertices(5, 7);
		graph.removeEdgeBetweenVertices(7, 11);

		assertEquals(-1, graph.getEdgeCost(3, 5));
		assertEquals(-1, graph.getEdgeCost(7, 11));
	}

	//tests removeEdgeBetweenVertices by removing more
	@Test public void testPublic25() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		graph.removeEdgeBetweenVertices(17, 19);
		graph.removeEdgeBetweenVertices(19, 23);

		assertEquals(-1, graph.getEdgeCost(17, 19));
		assertEquals(-1, graph.getEdgeCost(19, 23));
	}

	//tests removeVertex by removing nonexistent data
	@Test public void testPublic26() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		graph.removeVertex(31);

		assertFalse(graph.removeEdgeBetweenVertices(31, -2));
		assertFalse(graph.removeEdgeBetweenVertices(23, 31));
	}

	//tests getNeighbors for the integer graph
	@Test public void testPublic27() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		assertEquals("[19]", graph.getNeighbors(17).toString());
	}

	//tests getNeighbors for the animals graph
	@Test public void testPublic28() 
	{
		Graph<String> graph = TestCode.exampleGraph3();
		assertEquals("[lemur, koala, jaguar]", 
				graph.getNeighbors("gorilla").toString());
	}

	//tests getPredecessors for the integers graph
	@Test public void testPublic29() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		assertEquals("[13]", graph.getPredecessors(17).toString());
	}

	//tests getPredecessors for the animals graph
	@Test public void testPublic30() 
	{
		Graph<String> graph = TestCode.exampleGraph3();
		assertEquals("[donkey, beagle, cat, aardvark, elephant]",
				graph.getPredecessors("gorilla").toString());
	}

	//tests contractEdgeBetweenVertices for second graph
	@Test public void testPublic31() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		System.out.println("TEST 31 BEFOREHAND: " + graph.toString());
		
		assertTrue(graph.contractEdgeBetweenVertices(13, 17));
		System.out.println("TEST 31 AFTERWARD: " + graph.toString());
	}
	
	//tests contractEdgeBetweenVertices for second graph2
	@Test public void testPublic32() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		System.out.println("TEST 32 BEFOREHAND: " + graph.toString());

		assertTrue(graph.contractEdgeBetweenVertices(3, 5));
		System.out.println("TEST 32 AFTERWARD: " + graph.toString());
	}
	
	//tests contractEdgeBetweenVertices for nonexistent data
	@Test public void testPublic33() 
	{
		Graph<Integer> graph = TestCode.exampleGraph2();
		assertFalse(graph.contractEdgeBetweenVertices(50, 51));
	}

} //end of StudentTests class
