//NON EMPTY TREE CLASS by Anna Blendermann
//Pledge: I have not received any help on this project
package tree;
import java.util.List;

//NON EMPTY TREE CLASS***************************************************
//NonEmptyTree a polymorphic binary tree containing generic 
//keys and values, and manipulates data using methods shown below
@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> 
{
	private K key;
	private V value;
	public Tree<K, V> left, right;

	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right)
	{
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	//ADD KEY WITH VALUE METHOD*******************************************
	//recursively adds key with value to current binary tree
	//replaces duplicate value or adds value at empty tree location
	public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd) 
	{
		if (keyToAdd.compareTo(this.key) == 0)
			this.value = valueToAdd;

		else if (keyToAdd.compareTo(this.key) > 0)
			right = this.right.addKeyWithValue(keyToAdd, valueToAdd);

		else if (keyToAdd.compareTo(this.key) < 0)
			left = this.left.addKeyWithValue(keyToAdd, valueToAdd);
		return this;		
	}

	//SIZE METHOD*********************************************************
	//recursively computes tree size with pre-order traversal
	public int size() 
	{
		return left.size() + 1 + right.size();
	}

	//LOOKUP METHOD*******************************************************
	//searches for target key until found or empty tree reached
	public V lookup(K lookUpKey) 
	{				
		if (lookUpKey.compareTo(this.key) > 0)
			return this.right.lookup(lookUpKey);

		else if (lookUpKey.compareTo(this.key) < 0)
			return this.left.lookup(lookUpKey);

		return this.value; //search found key
	}

	//PATH FROM ROOT METHOD**********************************************
	//gets depth-first path from root to target key (adds to list)
	public void pathFromRoot(K key, List<K> list) 
	{	
		if (key.compareTo(this.key) > 0)
		{
			list.add(this.key); //add key to list
			this.right.pathFromRoot(key, list); //move sub-left
		}
		else if (key.compareTo(this.key) < 0)
		{
			list.add(this.key); //add key to list
			this.left.pathFromRoot(key, list); //move sub-right
		}
		else if (key.compareTo(this.key) == 0)
			list.add(this.key); //add last key to path
	}

	//HAVE SAME KEYS METHOD**********************************************
	//checks if two binary trees have the same contents
	public boolean haveSameKeys(Tree<K, V> otherTree) 
	{
		if (this.size() != otherTree.size()) //check size 
			return false;
		if (otherTree.lookup(this.key) == null)
			return false;
		else
		{
			this.left.haveSameKeys(otherTree);
			this.right.haveSameKeys(otherTree);	
		}
		return true; //trees have the same contents	
	}

	//NUM ELEMENTS AT LEVEL METHOD***************************************
	//returns number of binary tree elements at the target level
	public int numElementsAtLevel(int level) 
	{
		if (level == 1) return 1; //return root
		else if (level != 1) //decrementing level
		{
			return this.left.numElementsAtLevel(level-1) +
					this.right.numElementsAtLevel(level-1);	
		}
		else return 0; //tree level not valid
	}

	//MAX METHOD*********************************************************
	//returns the key with the largest value
	public K max() throws EmptyTreeException 
	{
		try //attempt to find max key
		{
			K maxKey = this.right.max();
			return maxKey;
		}
		catch (EmptyTreeException e) { 
			return this.key; }
	}

	//MIN METHOD*********************************************************
	//returns the key with the smallest value
	public K min() throws EmptyTreeException 
	{
		try //attempt to find min key 
		{
			K minKey = this.left.min();
			return minKey;
		}
		catch (EmptyTreeException e) { 
			return this.key; }
	}

	//DELETE KEY AND VALUE METHOD****************************************
	//deletes key and value from target location keyToDelete
	//deletes leaf nodes, and replaces from subtree for interior nodes
	public Tree<K, V> deleteKeyAndValue(K keyToDelete)
	{
		if (keyToDelete.compareTo(this.key) > 0)
			right = right.deleteKeyAndValue(keyToDelete);

		else if (keyToDelete.compareTo(this.key) < 0) 
			left = left.deleteKeyAndValue(keyToDelete); 
		else
		{
			try {
				this.value = this.lookup(right.min()); //find smallest right sub-tree
				this.key = right.min(); //replace target key with smallest value
				right = right.deleteKeyAndValue(right.min()); //delete the duplicate
			}
			catch (EmptyTreeException e) { //target is leaf 
				return this.left; //return entire left-subtree
			}
		}
		return this;
	}

	//TO STRING METHOD***************************************************
	//returns string of elements in the polymorphic binary tree
	public String toString() 
	{
		String result = "", leftStr, rightStr;

		leftStr = left.toString();
		rightStr =  right.toString();
		result = leftStr + (leftStr.equals("") ? "" : " ") + this.key + "/" 
				+ this.value + (rightStr.equals("") ? "" : " ") + rightStr;
		return result;
	}

} //end of NonEmptyTree class
