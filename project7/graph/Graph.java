//GRAPH by Anna Blendermann
//Pledge: I have not received help on this assignment
package graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
import java.util.HashMap;

//GRAPH CLASS************************************************************
//implemented using nested hashmaps aka (source, (dest, cost))
//implements add and remove operations for both vertices and edges
public class Graph<V> 
{
	//graph represented by two nested hashmaps
	private HashMap<V, HashMap<V, Integer>> graph;
	private Comparator<V> comp;

	public Graph(Comparator<V> comparator) 
	{
		if (comparator == null)
			throw new NullPointerException();
		else
		{
			graph = new HashMap<V, HashMap<V, Integer>>();
			comp = comparator;
		}

	} //end of graph constructor

	public Graph(Graph<V> otherGraph) 
	{
		graph = new HashMap<V, HashMap<V, Integer>>();
		for (V key : otherGraph.getVertices()) 
			addVertex(key);

		for (V key2 : graph.keySet()) 
		{
			for (V key3 : otherGraph.getNeighbors(key2)) 
			{
				this.addEdgeBetweenVertices(key2, key3, 
						otherGraph.getEdgeCost(key2, key3));
			}
		}
	}

	//ADD VERTEX METHOD**************************************************
	//adds new vertex with no edges to the graph
	public boolean addVertex(V vertex) 
	{
		if (vertex == null)
			throw new NullPointerException();

		if (!graph.containsKey(vertex)) //add vertex
		{
			graph.put(vertex, new HashMap<V, Integer>());
			return true; //vertex added
		}
		else return false; //vertex not added
	}

	//IS VERTEX METHOD***************************************************
	//checks whether or not a vertex exists within the graph
	public boolean isVertex(V vertex) 
	{
		if (vertex == null)
			throw new NullPointerException();

		if (graph.containsKey(vertex))
			return true;
		else return false;
	}

	//GET VERTICES METHOD************************************************
	//returns a collection of graph vertices
	public Collection<V> getVertices() 
	{
		if (graph == null)
			throw new NullPointerException();
		else return graph.keySet();
	}

	//REMOVE VERTEX METHOD***********************************************
	//removes vertex and corresponding edges from the graph
	public boolean removeVertex(V vertex) 
	{
		if (vertex == null)
			throw new NullPointerException();

		if (graph.containsKey(vertex))
		{
			//remove vertex and outgoing edges
			graph.remove(vertex, graph.get(vertex)); 

			for (V key : graph.keySet()) //remove incoming edges
			{
				for (V edge : graph.get(key).keySet())
				{
					if(comp.compare(edge, vertex) == 0)
						graph.get(key).remove(edge);
				}
			}
			return true; //removed successfully
		}
		else return false;
	} //end of removeVertex method


	//ADD EDGE BETWEEN VERTICES******************************************
	//adds edge between two vertices of the graph
	//replaces weight of current edge if already exists
	public boolean addEdgeBetweenVertices(V source, V dest, int cost) 
	{
		if (source == null || dest == null)
			throw new NullPointerException();

		if (graph.containsKey(source) && graph.containsKey(dest)
				&& cost >= 0) //vertices exist in graph
		{
			if (graph.get(source).containsKey(dest)) //edge exists
			{
				graph.get(source).remove(dest); //remove old weight
				graph.get(source).put(dest, cost); //add new weight
				return true; //edge weight changed
			}
			else //edge doesn't already exist
			{
				graph.get(source).put(dest, cost); //add new edge
				return true; //new edge added between vertices
			}
		}
		else return false;
	}

	//GET EDGE COST METHOD***********************************************
	//gets weight of the edge between two vertices
	public int getEdgeCost(V source, V dest) 
	{
		if (source == null || dest == null)
			throw new NullPointerException();

		if (graph.containsKey(source) && graph.containsKey(dest)
				&& graph.get(source).containsKey(dest))
			return graph.get(source).get(dest);
		else return -1;
	}

	//REMOVE EDGE BETWEEN VERTICES***************************************
	//removes directed edge between two vertices
	public boolean removeEdgeBetweenVertices(V source, V dest) 
	{
		if (source == null || dest == null)
			throw new NullPointerException();

		if (graph.containsKey(source) && graph.containsKey(dest))
		{
			graph.get(source).remove(dest);
			return true; //removed successfully
		}
		else return false;
	}

	//GET NEIGHBORS METHOD***********************************************
	//returns collection of outgoing edges from a vertex
	public Collection<V> getNeighbors(V vertex) 
	{
		if (vertex == null)
			throw new NullPointerException();

		if (graph.containsKey(vertex))
			return graph.get(vertex).keySet();
		else return new ArrayList<V>();
	}

	//GET PREDECESSORS METHOD********************************************
	//returns collection of incoming edges to a vertex
	public Collection<V> getPredecessors(V vertex) 
	{
		ArrayList<V> list = new ArrayList<V>();
		if (vertex == null)
			throw new NullPointerException();

		if (graph.containsKey(vertex))
		{
			for (V source : graph.keySet())
			{
				for (V edge : graph.get(source).keySet())
				{
					if(comp.compare(vertex, edge) == 0)
						list.add(source); //add predecessor
				}	
			}
		}
		return list; //return predecessor list
	}


	//CONTRACT EDGE BETWEEN VERTICES*************************************
	//case 1: vertex1 or vertex2 are not present - return false
	//case 2: vertices are present but no edge - return false
	// case 3: combine vertex1 and vertex 2 - return true
	public boolean contractEdgeBetweenVertices(V vertex1, V vertex2) 
	{
		if (vertex1 == null || vertex2 == null)
			throw new NullPointerException();

		if (!graph.containsKey(vertex1) || !graph.containsKey(vertex2)
				|| comp.compare(vertex1, vertex2) == 0)
			return false; //case 1

		if (getEdgeCost(vertex1, vertex2) == -1
				&& getEdgeCost(vertex2, vertex1) == -1)
			return false; //case 2

		else //case 3 - vertices can be combined
		{
			//SELECT HELPER METHOD BASED UPON VERTEX WITH LESSER DATA
			if (comp.compare(vertex1, vertex2) < 0) //vertex1 is less
			{				
				HashMap<V, Integer> map1 = new HashMap<V, Integer>();
				for (V dest : getNeighbors(vertex2)) //save data
					map1.put(dest, graph.get(vertex2).get(dest));

				HashMap<V, Integer> map2 = new HashMap<V, Integer>();
				for (V source : getPredecessors(vertex2)) //save data
					map2.put(source, graph.get(source).get(vertex2));

				removeVertex(vertex2); //remove vertex2

				if (combineOutgoingEdges(vertex1, map1) &&
						combineIncomingEdges(vertex1, map2))
					return true; //vertices successfully combined
			}
			else //vertex 2 is less or equal
			{
				HashMap<V, Integer> map1 = new HashMap<V, Integer>();
				for (V dest : getNeighbors(vertex1)) //save data
					map1.put(dest, graph.get(vertex1).get(dest));

				HashMap<V, Integer> map2 = new HashMap<V, Integer>();
				for (V source : getPredecessors(vertex1)) //save data
					map2.put(source, graph.get(vertex1).get(source));

				removeVertex(vertex1); //remove vertex1

				if (combineOutgoingEdges(vertex2, map1))
				{
					if (combineIncomingEdges(vertex2, map2))
						return true; //vertices successfully combined
				}
			}
		}
		return false; //default false return
	} //end of contractEdgeBetweenVertices method


	//COMBINE OUTGOING EDGES*********************************************
	//traverse neighbors and add them to newVertex 
	//case 1: edge already exists, so compare/replace
	//case 2: edge doesn't exist, so add to newVertex
	public boolean combineOutgoingEdges(V newVertex, HashMap<V, Integer> neighs)
	{
		for (V dest : neighs.keySet())
		{
			if (graph.get(newVertex).containsKey(dest)) //ERROR HERE
			{
				//compare and leave/replace vertex
				int oldValue = graph.get(newVertex).get(dest);
				int newValue = neighs.get(dest);

				if (oldValue < newValue)
					graph.get(newVertex).replace(dest, oldValue, newValue);
			}
			else addEdgeBetweenVertices(newVertex, dest, neighs.get(dest));
			neighs.remove(dest); //remove extra edge
		}
		if (neighs.isEmpty())
			return true; //all outgoing edges start from newVertex
		else return false; //extra outgoing edges exist
	}


	//COMBINE INCOMING EDGES*********************************************
	//traverse predecessors and add them to newVertex 
	//case 1: edge already exists, so compare/replace
	//case 2: edge doesn't exist, so add to newVertex
	public boolean combineIncomingEdges(V newVertex, HashMap<V, Integer> preds)
	{
		for (V source : preds.keySet())
		{
			if (graph.get(source).containsKey(newVertex))
			{
				//compare and leave/replace vertex
				int oldValue = graph.get(source).get(newVertex);
				int newValue = preds.get(source);

				if (oldValue < newValue)
					graph.get(source).replace(newVertex, oldValue, newValue);
			}
			else addEdgeBetweenVertices(source, newVertex, preds.get(source));
			preds.remove(source); //remove extra edge
		}
		if (preds.isEmpty())
			return true; //all outgoing edges start from newVertex
		else return false; //extra outgoing edges exist
	}


	//VERTEX STRING METHOD***********************************************
	public String vertexString()
	{
		String result = "[ ";
		for(V vertex : graph.keySet())
			result += vertex + " ";

		return result + "]";
	} //end of vertexString() method

	//TO STRING METHOD***************************************************
	@Override
	public String toString()
	{
		String result = "";
		for (V vertex : graph.keySet())
		{
			result += "\n[" + vertex + ", ";
			for (V edge : graph.get(vertex).keySet())
			{
				result += "(" + edge + ", " + 
						graph.get(vertex).get(edge) + ")";
			}
			result += "] "; //add extra bracket
		}
		return result;
	} //end of toString() method

} //end of Graph class
