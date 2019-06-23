//EMPTY TREE CLASS by Anna Blendermann
package tree;
import java.util.List;

//EMPTY TREE CLASS********************************************************
//EmptyTree class is an empty polymorphic binary tree, that can
//only be created once and carries null tree references
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> 
{
	//private constructor to enforce only being called once
	private static EmptyTree singleton = new EmptyTree();

	private EmptyTree() {} //nothing to create

	//GET INSTANCE METHOD*************************************************
	//returns private, static instance of singleton tree
	public static EmptyTree getInstance() {
		return singleton;
	}

	//ADD KEY WITH VALUE METHOD*******************************************
	//adds non empty tree with references to singleton tree
	public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) {		
		return new NonEmptyTree<K, V>(keyToAdd, valueToAdd, this, this);
	}

	//SIZE METHOD*********************************************************
	//empty tree is completely empty so returns zero
	public int size() {
		return 0;
	}

	//LOOKUP METHOD*******************************************************
	//empty tree contains no data so method returns null
	public V lookup(K lookUpKey) {
		return null; 
	}

	//PATH FROM ROOT METHOD***********************************************
	//empty tree contains no root so returns nothing
	public void pathFromRoot(K key, List<K> list) {}

	//HAVE SAME KEYS METHOD***********************************************
	//empty tree contains no data so returns false
	public boolean haveSameKeys(Tree<K, V> otherTree) {
		if (this == otherTree)
			return true;
		else return false;
	}

	//NUM ELEMENTS AT LEVEL METHOD****************************************
	//empty tree contains no elements/levels so returns zero
	public int numElementsAtLevel(int level) {
		return 0;
	}

	//EMPTY TREE EXCEPTION METHOD*****************************************
	//empty tree has no data so instead of max, throws exception
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	//EMPTY TREE EXCEPTION METHOD*****************************************
	//empty tree has no data so instead of min, throws exception
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	//DELETE KEY AND VALUE METHOD*****************************************
	//search reached end of binary tree so returns empty tree
	public Tree<K, V> deleteKeyAndValue(K keyToDelete) {
		return singleton; 
	}

	//TO STRING METHOD****************************************************
	//empty tree contains no data so returns empty string
	public String toString() {
		return "";
	}

} //end of singleton class
