package tree;

import java.util.List;

public interface Tree<K extends Comparable<K>, V> {
  public NonEmptyTree<K, V> addKeyWithValue(K keyToAdd, V valueToAdd);
  public int size();
  public V lookup(K lookUpKey);
  public void pathFromRoot(K key, List<K> list);
  public boolean haveSameKeys(Tree<K, V> otherTree);
  public int numElementsAtLevel(int level);
  public K max() throws EmptyTreeException;
  public K min() throws EmptyTreeException;
  public Tree<K, V> deleteKeyAndValue(K keyToDelete);
}
