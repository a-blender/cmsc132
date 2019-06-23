package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.ArrayList;
import graph.Graph;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests that getVertex() returns true for vertices that do exist, and
  // false for vertices that don't exist, when called on a graph with no
  // vertices, and on one with only a few vertices but no edges.  Also
  // checks that getVertices() returns a Collection of the right size.
  @Test public void testPublic1() {
    // currently an empty graph
    Graph<String> graph= new Graph<String>(TestCode.strComparator);
    String[] vertices= new String[]{"koala", "kangaroo", "quokka", "numbat"};

    assertFalse(graph.isVertex("koala"));  // no vertices have been added yet
    assertFalse(graph.isVertex("platypus"));  // Australian, but not a marsupial
    assertEquals(0, graph.getVertices().size());

    // now use a graph with some vertices
    graph= TestCode.exampleGraph1();

    // check results
    for (String vertex : vertices)
      assertTrue(graph.isVertex(vertex));
    assertEquals(4, graph.getVertices().size());
  }

  // Tests the return value of addVertex().
  @Test public void testPublic2() {
    Graph<Character> graph= new Graph<Character>(TestCode.charComparator);

    assertTrue(graph.addVertex('l'));
    assertFalse(graph.addVertex('l'));
    assertTrue(graph.addVertex('a'));
    assertTrue(graph.addVertex('m'));
    assertFalse(graph.addVertex('a'));
  }

  // Tests calling getEdgeCost() when called on a graph with a few vertices
  // and some edges.  Of course this also tests addEdgeBetweenVertices().
  @Test public void testPublic3() {
    Graph<String> graph= TestCode.exampleGraph1();

    // now add some edges
    graph.addEdgeBetweenVertices("quokka", "kangaroo", 1);
    graph.addEdgeBetweenVertices("kangaroo", "koala", 1);

    assertEquals(1, graph.getEdgeCost("quokka", "kangaroo"));
    assertEquals(1, graph.getEdgeCost("kangaroo", "koala"));
    assertEquals(-1, graph.getEdgeCost("moose", "gerbil"));
    // the next two test vertices that are in the graph, but they have no
    // edge between them
    assertEquals(-1, graph.getEdgeCost("koala", "quokka"));
    assertEquals(-1, graph.getEdgeCost("numbat", "koala"));
  }

  // Tests calling getEdgeCost() on nonexistent vertices.
  @Test public void testPublic4() {
    Graph<String> graph= TestCode.exampleGraph3();

    assertEquals(-1, graph.getEdgeCost("cat", "llama"));
    assertEquals(-1, graph.getEdgeCost("llama", "koala"));
    assertEquals(-1, graph.getEdgeCost("llama", "frog"));
  }

  // Tests one case of calling getNeighbors().
  @Test public void testPublic5() {
    Graph<String> graph= TestCode.exampleGraph3();
    String[] verticesToCheck= new String[]{"flamingo", "gorilla", "hyena",
                                           "iguana"};

    for (String vertex : verticesToCheck)
      assertTrue(TestCode.checkCollection(graph.getNeighbors(vertex),
                                          new String[]{"jaguar", "koala",
                                                       "lemur"}));
  }

  // Tests one case of calling getPredecessors().
  @Test public void testPublic6() {
    Graph<String> graph= TestCode.exampleGraph3();
    String[] verticesToCheck= new String[]{"flamingo", "gorilla", "hyena",
                                           "iguana"};

    for (String vertex : verticesToCheck)
      assertTrue(TestCode.checkCollection(graph.getPredecessors(vertex),
                                          new String[]{"cat", "aardvark",
                                                       "donkey", "elephant",
                                                       "beagle"}));
  }

  // Tests calling removeEdgeBetweenVertices() to remove some edges from a
  // graph.
  @Test public void testPublic7() {
    Graph<Integer> graph= TestCode.exampleGraph2();
    int[] vertices= new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 31};
    int[] expectedResults= new int[]{1, -1, 1, 1, -1, -1, 1, 1, -1};
    int i;

    graph.removeEdgeBetweenVertices(3, 5);
    graph.removeEdgeBetweenVertices(11, 13);
    graph.removeEdgeBetweenVertices(13, 17);
    graph.removeEdgeBetweenVertices(23, 31);

    for (i= 0; i < vertices.length - 1; i++)
      assertEquals(expectedResults[i],
                   graph.getEdgeCost(vertices[i], vertices[i + 1]));
  }

  // Tests calling removeVertices() to remove some vertices from a graph.
  @Test public void testPublic8() {
    Graph<Integer> graph= TestCode.exampleGraph4();

    graph.removeVertex(2);
    graph.removeVertex(4);

    assertFalse(graph.isVertex(2));
    assertFalse(graph.isVertex(4));
    assertEquals(6, graph.getVertices().size());
    assertTrue(TestCode.checkCollection(graph.getVertices(),
                                        new Integer[]{0, 1, 3, 5, 6, 7}));
  }

  // Tests whether removeVertex() correctly removes incoming edges when it
  // removes vertices from a graph, using getNeighbors().
  @Test public void testPublic9() {
    Graph<String> graph= TestCode.exampleGraph3();
    String[] verticesToCheck= new String[]{"aardvark", "beagle", "cat",
                                           "donkey", "elephant"};
    String[] expectedResults= new String[]{"flamingo", "gorilla", "iguana"};

    graph.removeVertex("hyena");

    for (String vertexToCheck : verticesToCheck)
      assertTrue(TestCode.checkCollection(graph.getNeighbors(vertexToCheck),
                                          expectedResults));
  }

  // Tests the basic operation of the copy constructor.
  @Test public void testPublic10() {
    Graph<Integer> graph1= TestCode.exampleGraph4();
    Graph<Integer> graph2= new Graph<Integer>(graph1);
    Integer[] expectedVertices= new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
    int i;

    // test that graph2 has the same vertices as graph1
    assertTrue(TestCode.checkCollection(graph1.getVertices(),
                                        expectedVertices));
    assertTrue(TestCode.checkCollection(graph2.getVertices(),
                                        expectedVertices));

    // test that one of the vertices in graph2 (vertex 4) has the same
    // neighbors as the corresponding vertex in graph1 (all other vertices,
    // but not itself)
    for (i= 0; i <= 7; i++)
      assertEquals((i != 4 ? 1 : -1), graph2.getEdgeCost(4, i));

    // test that one of the vertices in graph2 (vertex 4) has the same
    // predecessors as the corresponding vertex in graph1 (all other
    // vertices, but not itself)
    for (i= 0; i <= 7; i++)
      assertEquals((i != 4 ? 1 : -1), graph2.getEdgeCost(i, 4));
  }

}
