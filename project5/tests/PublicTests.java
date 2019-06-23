package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import tree.Tree;
import tree.EmptyTree;
import tree.EmptyTreeException;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class PublicTests {

	// Tests calling lookup() on several of the elements of a small tree, and 
	// on elements that are not in the tree.
	@Test public void testPublic1() {
		Tree<String, Integer> tree= TestCode.exampleTree1();

		assertEquals(3, (int) tree.lookup("dog"));
		assertEquals(2, (int) tree.lookup("hamster"));
		assertEquals(7, (int) tree.lookup("koala"));
		assertEquals(10, (int) tree.lookup("rhinoceros"));

		assertNull(tree.lookup("quokka"));
		assertNull(tree.lookup("dogs"));
		assertNull(tree.lookup("rhino"));
	}

	// Tests calling lookup() on the root and on all of the leaves of a small
	// example tree.
	@Test public void testPublic2() {
		Tree<String, Integer> tree= TestCode.exampleTree1();

		assertEquals(2, (int) tree.lookup("hamster"));
		assertEquals(9, (int) tree.lookup("crocodile"));
		assertEquals(8, (int) tree.lookup("elephant"));
		assertEquals(6, (int) tree.lookup("iguana"));
	}

	// Tests calling size() on an empty tree and on a small example tree.
	@Test public void testPublic3() {
		assertEquals(0, EmptyTree.getInstance().size());
		assertEquals(9, TestCode.exampleTree1().size());
	}

	// Tests calling toString() on an example tree.
	@Test public void testPublic4() {
		assertEquals("crocodile/9 dog/3 elephant/8 frog/4 hamster/2 " +
				"iguana/6 koala/7 llama/5 rhinoceros/10",
				TestCode.exampleTree1().toString());
	}

	// Tests that addKeyWithValue() modifies its current object tree.
	@Test public void testPublic5() {
		Tree<Character, Integer> tree1= TestCode.exampleTree2();
		Tree<Character, Integer> tree2= tree1.addKeyWithValue('z', 14);

		assertEquals(14, (int) tree1.lookup('z'));
		assertEquals(14, (int) tree2.lookup('z'));
		assertEquals(tree1.toString(), tree2.toString());
	}

	// Tests that addKeyWithValue() replaces the value associated with a key
	// if the key is already present in the tree.
	@Test public void testPublic6() {
		Tree<String, Integer> tree= TestCode.exampleTree1();

		tree.addKeyWithValue("llama", 1);

		assertEquals(1, (int) tree.lookup("llama"));
	}

	// Tests pathFromRoot() on a tree with only one element
	@Test public void testPublic7() {
		Tree<Integer, String> tree= EmptyTree.getInstance();
		ArrayList<Integer> list= new ArrayList<Integer>();

		tree= tree.addKeyWithValue(15, "llama");

		tree.pathFromRoot(15, list);
		assertEquals("15", TestCode.listToStr(list));
	}

	// Tests pathFromRoot() on various elements other than the root of a tree.
	@Test public void testPublic8() {
		Tree<Character, Integer> tree= TestCode.exampleTree2();
		ArrayList<Character> list1= new ArrayList<Character>();
		ArrayList<Character> list2= new ArrayList<Character>();
		ArrayList<Character> list3= new ArrayList<Character>();

		tree.pathFromRoot('i', list1);
		assertEquals("m i", TestCode.listToStr(list1));

		tree.pathFromRoot('o', list2);
		assertEquals("m s o", TestCode.listToStr(list2));

		tree.pathFromRoot('d', list3);
		assertEquals("m i c g e d", TestCode.listToStr(list3));
	}

	// Tests haveSameKeys() with two empty trees.
	@Test public void testPublic9() {
		Tree<Byte, Boolean> tree= EmptyTree.getInstance();
		Tree<Byte, Boolean> tree2= EmptyTree.getInstance();

		assertTrue(tree.haveSameKeys(tree2));
		assertTrue(tree2.haveSameKeys(tree));
	}

	// Tests haveSameKeys() with an empty tree and a nonempty tree
	@Test public void testPublic10() {
		Tree<String, Integer> tree= EmptyTree.getInstance();
		Tree<String, Integer> tree2= TestCode.exampleTree1();

		assertFalse(tree.haveSameKeys(tree2));
		assertFalse(tree2.haveSameKeys(tree));
	}

	// Tests haveSameKeys() with two nonempty trees that have the same keys.
	@Test public void testPublic11() {
		Tree<String, Integer> tree= TestCode.exampleTree1();
		Tree<String, Integer> tree2= TestCode.exampleTree1();

		assertTrue(tree.haveSameKeys(tree2));
		assertTrue(tree2.haveSameKeys(tree));
	}

	// Tests calling max() on one element of a tree.
	@Test public void testPublic12() {
		Tree<Character, Integer> tree= TestCode.exampleTree2();

		try {
			assertEquals('u', (char) tree.max());
		} catch (EmptyTreeException ete) {
			// if an exception is thrown the test will fail
		}
	}

	// Tests calling max() on an empty tree, which should throw an
	// EmptyTreeException.
	@Test public void testPublic13() {
		try {
			EmptyTree.getInstance().max();
			fail();  // if the exception is not thrown, the test should fail
		} catch (EmptyTreeException ete) {
			// if we get here the test should pass
		}
	}

	// Tests calling min() on one element of a tree.
	@Test public void testPublic14() {
		Tree<Character, Integer> tree= TestCode.exampleTree2();

		try {
			assertEquals('a', (char) tree.min());
		} catch (EmptyTreeException ete) {
			// if an exception is thrown the test will fail
		}
	}

	// Tests calling deleteKeyAndValue() on a few elements of a tree,
	// including the root.
	@Test public void testPublic15() {
		Tree<Character, Integer> tree= TestCode.exampleTree2();

		tree= tree.deleteKeyAndValue('i');
		tree= tree.deleteKeyAndValue('s');
		tree= tree.deleteKeyAndValue('e');
		tree= tree.deleteKeyAndValue('u');
		tree= tree.deleteKeyAndValue('m');
	}

}
