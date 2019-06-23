//STUDENT TESTS by Anna Blendermann
package tests;
import java.util.ArrayList;

import org.junit.*;

import sun.reflect.generics.tree.Tree;
import tree.EmptyTree;
import tree.EmptyTreeException;
import tree.NonEmptyTree;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class StudentTests 
{
	//tests addKeyWithValue() by adding sequential keys
	@Test public void testPublic1() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		System.out.println("EXAMPLE TREE 1: " + tree1.toString());

		tree1.addKeyWithValue("crazy", 500);
		tree1.addKeyWithValue("lioness", 450);
		tree1.addKeyWithValue("brave", 620);

		assertEquals(500, (int) tree1.lookup("crazy"));
		assertEquals(620, (int) tree1.lookup("brave"));
	}

	//tests addKeyWithValue() by replacing key with another value
	@Test public void testPublic2() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		assertEquals(2, (int) tree1.lookup("hamster"));
		assertEquals(7, (int) tree1.lookup("koala"));

		tree1.addKeyWithValue("hamster", 12);
		assertEquals(12, (int) tree1.lookup("hamster"));

		tree1.addKeyWithValue("koala", 25);
		assertEquals(25, (int) tree1.lookup("koala"));
	}

	//tests size() on empty tree and smaller example tree
	@Test public void testPublic3() 
	{
		assertEquals(0, EmptyTree.getInstance().size());

		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		tree1.addKeyWithValue("goose", 332);
		assertEquals(10, (int) tree1.size());
	}

	//tests size() by adding two tree2 elements and updating size
	@Test public void testPublic4() 
	{
		tree.Tree<Character, Integer> tree2 = TestCode.exampleTree2();
		System.out.println("EXAMPLE TREE 2: " + tree2.toString());

		assertEquals(13, (int) tree2.size()); //original size

		tree2.addKeyWithValue('R', 246);
		tree2.addKeyWithValue('K', 273);
		assertEquals(15, (int) tree2.size()); //new size
	}

	//tests lookup() by searching for root and other elements
	@Test public void testPublic5() 
	{
		tree.Tree<Character, Integer> tree2 = TestCode.exampleTree2();

		assertEquals(10, (int) tree2.lookup('a')); //search root
		assertEquals(8, (int) tree2.lookup('u')); //last element

		assertEquals(12, (int) tree2.lookup('e'));
		assertEquals(7, (int) tree2.lookup('j'));
		assertEquals(3, (int) tree2.lookup('s'));
	}

	//tests lookup() on empty tree and invalid values
	@Test public void testPublic6() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();

		assertNull(EmptyTree.getInstance().lookup("coffee"));
		assertNull(EmptyTree.getInstance().lookup("AbagCdog"));

		assertNull(tree1.lookup("breakup"));
		assertNull(tree1.lookup("sandwhich"));
	}

	//tests pathFromRoot() with small tree of elements
	@Test public void testPublic7() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();

		tree1.pathFromRoot("dog", list1);
		assertEquals("hamster frog dog", TestCode.listToStr(list1));

		tree1.pathFromRoot("koala", list2);
		assertEquals("hamster llama koala", TestCode.listToStr(list2));
	}

	//tests pathFromRoot() with few more small paths
	@Test public void testPublic8() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();

		tree1.pathFromRoot("elephant", list1);
		assertEquals("hamster frog dog elephant", TestCode.listToStr(list1));

		tree1.pathFromRoot("iguana", list2);
		assertEquals("hamster llama koala iguana", TestCode.listToStr(list2));
	}

	//tests pathFromRoot() with a two element binary tree
	@Test public void testPublic9() 
	{
		tree.Tree<Integer, String> tree= EmptyTree.getInstance();
		ArrayList<Integer> list = new ArrayList<Integer>();

		tree = tree.addKeyWithValue(16, "caramel");
		tree = tree.addKeyWithValue(20, "butterscotch");

		tree.pathFromRoot(20, list);
		assertEquals("16 20", TestCode.listToStr(list));
	}

	//tests pathFromRoot() with empty trees and invalid params
	@Test public void testPublic10() 
	{
		tree.Tree<Integer, String> tree= EmptyTree.getInstance();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		tree.pathFromRoot(42, list1);
		tree.pathFromRoot(-50, list2);

		assertEquals("", TestCode.listToStr(list1));
		assertEquals("", TestCode.listToStr(list2));
	}

	//tests haveSameKeys() for two identical trees
	@Test public void testPublic11() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		tree.Tree<String, Integer> tree2 = TestCode.exampleTree1();

		assertTrue(tree1.haveSameKeys(tree2));
		assertTrue(tree2.haveSameKeys(tree1));
	}

	//tests haveSameKeys() for trees of different sizes
	@Test public void testPublic12() 
	{
		tree.Tree<Integer, String> tree1 = EmptyTree.getInstance();
		tree1 = tree1.addKeyWithValue(1, "Jake");
		tree1 = tree1.addKeyWithValue(2, "Thomas");

		tree.Tree<Integer, String> tree2 = EmptyTree.getInstance();
		tree2 = tree2.addKeyWithValue(3, "Bryan");
		tree2 = tree2.addKeyWithValue(4, "Timothy");
		tree2 = tree2.addKeyWithValue(5, "Michael");

		assertFalse(tree1.haveSameKeys(tree2));
	}

	//tests haveSameKeys() for trees with unordered elements
	@Test public void testPublic13() 
	{
		tree.Tree<Integer, String> tree1 = EmptyTree.getInstance();
		tree1 = tree1.addKeyWithValue(2, "sameKey");
		tree1 = tree1.addKeyWithValue(5, "sameKey");
		tree1 = tree1.addKeyWithValue(4, "sameKey");

		tree.Tree<Integer, String> tree2 = EmptyTree.getInstance();
		tree2 = tree2.addKeyWithValue(5, "sameKey");
		tree2 = tree2.addKeyWithValue(2, "sameKey");
		tree2 = tree2.addKeyWithValue(4, "sameKey");

		assertTrue(tree1.haveSameKeys(tree2));
	}

	//tests numElementsAtLevel() for ExampleTree1 elements
	@Test public void testPublic14() 
	{
		//SOLUTIONS TO THIS TEST
		//levels 1,5,6 - one element
		//level 2 - two elements
		//levels 3,4 - four elements

		tree.Tree<Character, Integer> tree = TestCode.exampleTree2();

		assertEquals(1, tree.numElementsAtLevel(1));
		assertEquals(2, tree.numElementsAtLevel(2));
		assertEquals(4, tree.numElementsAtLevel(3));

		assertEquals(4, tree.numElementsAtLevel(4));
		assertEquals(1, tree.numElementsAtLevel(5));
		assertEquals(1, tree.numElementsAtLevel(6));
	}

	//tests numElementsAtLevel() for tree with three elements
	@Test public void testPublic15() 
	{
		tree.Tree<Integer, String> tree = EmptyTree.getInstance();
		tree = tree.addKeyWithValue(25, "cathedral");
		tree = tree.addKeyWithValue(21, "chandelier");
		tree = tree.addKeyWithValue(26, "garden");

		assertEquals(1, tree.numElementsAtLevel(1));
		assertEquals(2, tree.numElementsAtLevel(2));
		assertEquals(0, tree.numElementsAtLevel(4));
	}

	//tests numElementsAtLevel() for empty tree and invalid param

	@Test public void testPublic16() 
	{
		tree.Tree<Integer, String> tree = EmptyTree.getInstance();

		assertEquals(0, tree.numElementsAtLevel(0));
		assertEquals(0, tree.numElementsAtLevel(-2));
		assertEquals(0, tree.numElementsAtLevel(56));
	}

	//tests max() on both exampleTree1 and exampleTree2
	@Test public void testPublic17() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		tree.Tree<Character, Integer> tree2 = TestCode.exampleTree2();

		try {
			assertEquals("rhinoceros", tree1.max());
		} catch (EmptyTreeException ete) {} //test fails

		try {
			assertEquals('u', (char) tree2.max());
		} catch (EmptyTreeException ete) {} //test fails 
	}

	//tests max() for tree with just two elements
	@Test public void testPublic18() 
	{
		tree.Tree<String, String> tree = EmptyTree.getInstance();
		tree = tree.addKeyWithValue("purple", "dog");
		tree = tree.addKeyWithValue("sweet", "cakes");

		try {
			assertEquals("sweet", tree.max());
		} catch (EmptyTreeException ete) {} //test fails
	}

	//tests max() on both exampleTree1 and exampleTree2
	@Test public void testPublic19() 
	{
		tree.Tree<String, Integer> tree1 = TestCode.exampleTree1();
		tree.Tree<Character, Integer> tree2 = TestCode.exampleTree2();

		try {
			assertEquals("crocodile", tree1.min());
		} catch (EmptyTreeException ete) {} //test fails

		try {
			assertEquals('a', (char) tree2.min());
		} catch (EmptyTreeException ete) {} //test fails 
	}

	//tests min() on three-element tree with duplicate values
	@Test public void testPublic20() 
	{
		tree.Tree<Character, String> tree = EmptyTree.getInstance();
		tree = tree.addKeyWithValue('K', "violet");
		tree = tree.addKeyWithValue('S', "violet");
		tree = tree.addKeyWithValue('B', "violet");

		try {
			assertEquals('B', (char) tree.min());
		} catch (EmptyTreeException ete) {} //test fails
	}

	//tests deleteKeyAndValue() with tree with two elements
	@Test public void testPublic21() 
	{
		tree.Tree<Integer, Integer> tree = EmptyTree.getInstance();
		tree = tree.addKeyWithValue(10, 2);
		tree = tree.addKeyWithValue(12, 3);
		
		tree = tree.deleteKeyAndValue(12); //valid value
		tree = tree.deleteKeyAndValue(24); //invalid

		assertEquals("10/2", tree.toString());
	}

	//tests deleteKeyAndValue() by deleting from string tree
	@Test public void testPublic22() 
	{
		tree.Tree<String, Integer> tree = TestCode.exampleTree1();
	
		tree = tree.deleteKeyAndValue("crocodile");
		tree = tree.deleteKeyAndValue("dog");
		tree = tree.deleteKeyAndValue("hamster");
		tree = tree.deleteKeyAndValue("koala");
		tree = tree.deleteKeyAndValue("rhinoceros");

		assertEquals("elephant/8 frog/4 iguana/6 llama/5", tree.toString());
	}
	
	//tests deleteKeyAndValue() by deleting from char tree
	@Test public void testPublic23() 
	{
		tree.Tree<Character, Integer> tree = TestCode.exampleTree2();

		tree = tree.deleteKeyAndValue('m');
		tree = tree.deleteKeyAndValue('s');
		tree = tree.deleteKeyAndValue('j');
		tree = tree.deleteKeyAndValue('a');
		tree = tree.deleteKeyAndValue('i');
		
		assertEquals("c/4 d/13 e/12 g/9 n/6 o/5 t/11 u/8", tree.toString());
	}
	
	//tests deleteKeyAndValue by adding and then removing element
	@Test public void testPublic24() 
	{
		tree.Tree<Character, Integer> tree = TestCode.exampleTree2();
		
		tree = tree.addKeyWithValue('K', 200);
		tree = tree.deleteKeyAndValue('K');
		
		assertEquals("a/10 c/4 d/13 e/12 g/9 i/2 j/7 m/1 n/6 o/5 s/3"
				+ " t/11 u/8", tree.toString());
	}

} //end of studentTests class
