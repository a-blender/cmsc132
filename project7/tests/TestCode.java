package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

// This class contains a helper method used in the public tests, and example
// graphs that the tests call methods on.

import graph.Graph;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collection;

public class TestCode {

  // utility method /////////////////////////////////////////////////////

  // In various tests we have to check the contents of a Collection returned
  // by a method, but we can't create a Collection that has the expected
  // values and use the equals() method to compare against the Collection,
  // because we don't even know what kind of Collection the methods will
  // return.  This method takes a Collection to check, and an array with the
  // expected values.  It constructs an ArrayList with the array's values
  // (the expected correct values)- of course an ArrayList is one type of
  // Collection.  Then it uses the Collection containsAll() method to
  // compare the parameter Collection against the ArrayList.  If we have two
  // collections A and B, and A contains all of the elements of B, and B
  // contains all of the elements of A, then it must be the case that they
  // must have all the same elements, and only the same elements.
  public static <T> boolean checkCollection(Collection<T> collection,
                                            T[] array) {
    ArrayList<T> expectedResults= new ArrayList<T>();

    for (T elt : array)
      expectedResults.add(elt);

    return collection.containsAll(expectedResults) &&
           expectedResults.containsAll(collection);
  }

  // example graphs /////////////////////////////////////////////////////

  // several comparator classes

  public static class StringComparator implements Comparator<String> {

    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
    }

  }

  public static class CharacterComparator implements Comparator<Character> {

    public int compare(Character c1, Character c2) {
      return c1.compareTo(c2);
    }

  }

  public static class IntegerComparator implements Comparator<Integer> {

    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }

  }

  // multiple tests can use these objects of the comparator types above
  static IntegerComparator intComparator= new IntegerComparator();
  static CharacterComparator charComparator= new CharacterComparator();
  static StringComparator strComparator= new StringComparator();

  // This method creates a graph, instantiated with Strings, with only
  // vertices, and no edges.
  public static Graph<String> exampleGraph1() {
    Graph<String> graph= new Graph<String>(strComparator);
    // several Australian marsupials
    String[] vertices= new String[]{"koala", "kangaroo", "quokka", "numbat"};

    for (String vertex : vertices)
      graph.addVertex(vertex);

    return graph;
  }

  // This method creates a simple graph, instantiated with Integers, with
  // all edge weights of 1.
  public static Graph<Integer> exampleGraph2() {
    Graph<Integer> graph= new Graph<Integer>(intComparator);
    int[] vertices= new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 31};
    int i;

    // add vertices to the graph
    for (int vertex : vertices)
      graph.addVertex(vertex);

    // add some edges to the graph; the vertex corresponding to every
    // element in the array vertices has an edge to the next one, except for
    // the last one
    for (i= 0; i < vertices.length - 1; i++)
      graph.addEdgeBetweenVertices(vertices[i], vertices[i + 1], 1);

    return graph;
  }

  // This method creates a graph, instantiated with Strings, with all edge
  // weights of 1.
  public static Graph<String> exampleGraph3() {
    Graph<String> graph= new Graph<String>(strComparator);
    String[][] vertices= new String[][]{{"aardvark", "beagle", "cat", "donkey",
                                         "elephant"},
                                        {"flamingo", "gorilla", "hyena",
                                         "iguana"},
                                        {"jaguar", "koala", "lemur"},
                                        {"manatee", "numbat"},
                                        {"ocelot"}};
    int i, j, k;

    // add vertices to the graph
    for (i= 0; i < vertices.length; i++)
      for (j= 0; j < vertices[i].length; j++)
        graph.addVertex(vertices[i][j]);

    // add some edges to the graph
    for (i= 0; i < vertices.length - 1; i++)
      for (j= 0; j < vertices[i].length; j++)
        for (k= 0; k < vertices[i + 1].length; k++)
          graph.addEdgeBetweenVertices(vertices[i][j], vertices[i + 1][k], 1);

    return graph;
  }

  // This method creates a graph, instantiated with Integers, with all edge
  // weights of 1, where every vertex has an edge to every other vertex.
  public static Graph<Integer> exampleGraph4() {
    Graph<Integer> graph= new Graph<Integer>(intComparator);
    int i, j;

    // add vertices to the graph
    for (i= 0; i <= 7; i++)
      graph.addVertex(i);

    // add some edges to the graph
    for (i= 0; i <= 7; i++)
      for (j= 0; j <= 7; j++)
        if (i != j)
          graph.addEdgeBetweenVertices(i, j, 1);

    return graph;
  }

  // This method creates a graph, instantiated with Characters, with
  // different edge weights, and various edges between vertices.
  public static Graph<Character> exampleGraph5() {
    Graph<Character> graph= new Graph<Character>(charComparator);
    char[] edgeSource= new char[]{'a', 'a', 'a', 'a', 'b', 'c', 'c', 'd', 'e',
                                  'e', 'e', 'e', 'f', 'f', 'g', 'g', 'h', 'i',
                                  'i', 'i', 'k'};
    char[] edgeDest= new char[]  {'b', 'c', 'g', 'i', 'a', 'b', 'g', 'c', 'a',
                                  'd', 'f', 'h', 'h', 'i', 'd', 'h', 'g', 'd',
                                  'e', 'j', 'j'};
    int[] costs= new int[]{2, 8, 14, 17, 5, 4, 21, 10, 7, 20, 12, 11, 3, 12,
                           16, 1, 6, 13, 15, 9, 18};
    char ch;
    int i;

    // add vertices to the graph
    for (ch= 'a'; ch <= 'k'; ch++)
      graph.addVertex(ch);

    // add some edges to the graph
    for (i= 0; i < edgeSource.length - 1; i++)
      graph.addEdgeBetweenVertices(edgeSource[i], edgeDest[i], costs[i]);

    return graph;
  }

}
