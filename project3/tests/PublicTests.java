package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import list.List;
import java.util.Iterator;
import java.util.Comparator;
import org.junit.*;
import static org.junit.Assert.*;

/* Some sample lists are created below, that various tests can use.  The two
 * comparators right below can also be used by multiple tests.  Note that
 * the methods that construct and return the sample lists call a helper
 * method makeList().
 */

public class PublicTests {

  // Adds a few elements to a list and checks its size.
  @Test public void testPublic1() {
    List<Integer> list= exampleList1();

    assertEquals(5, list.size());
  }

  // Adds a few elements to a list and ensures that the elements are stored
  // in sorted order.
  @Test public void testPublic2() {
    List<Integer> list= exampleList1();

    assertEquals("1 2 3 4 5", list.toString());
  }

  // Adds several elements to a list, including some that are duplicates, so
  // tests that lists properly store duplicate elements.
  @Test public void testPublic3() {
    List<Integer> list= new List<Integer>();

    list.sortedOrderInsert(2);
    list.sortedOrderInsert(9);
    list.sortedOrderInsert(9);
    list.sortedOrderInsert(6);
    list.sortedOrderInsert(3);
    list.sortedOrderInsert(2);
    list.sortedOrderInsert(7);

    assertEquals("2 2 3 6 7 9 9", list.toString());
  } 

  // Tests some situations for contains().
  @Test public void testPublic4() {
    List<Integer> list= exampleList2();

    assertNotNull(list.contains(2));
    assertNotNull(list.contains(9));
    assertNotNull(list.contains(7));
    assertNotNull(list.contains(3));
  }

  // Tests getElementAtIndex() by calling it on several elements in a list.
  @Test public void testPublic5() {
    List<Integer> list= exampleList2();

    assertEquals(2, (int) list.getElementAtIndex(0));
    assertEquals(7, (int) list.getElementAtIndex(4));
    assertEquals(9, (int) list.getElementAtIndex(6));
    assertEquals(3, (int) list.getElementAtIndex(1));
  }

  // Tests calling getElementAtIndex() with an invalid index that is larger
  // than the size of the list.
  @Test(expected= IndexOutOfBoundsException.class) public void testPublic6() {
    List<Integer> list= exampleList1();

    list.getElementAtIndex(100);
  }

  // Tests clear() by verifying that a list is empty after it's called.
  @Test public void testPublic7() {
    List<Integer> list= exampleList2();

    list.clear();

    assertEquals("", list.toString());
    assertEquals(0, list.size());
  }

  // Tests some situations for lastIndexOf().
  @Test public void testPublic8() {
    List<Integer> list= new List<Integer>();

    list.sortedOrderInsert(3);
    list.sortedOrderInsert(9);
    list.sortedOrderInsert(9);
    list.sortedOrderInsert(9);
    list.sortedOrderInsert(6);
    list.sortedOrderInsert(2);
    list.sortedOrderInsert(3);
    list.sortedOrderInsert(3);
    list.sortedOrderInsert(7);
    list.sortedOrderInsert(9);

    assertEquals(0, list.lastIndexOf(2));
    assertEquals(3, list.lastIndexOf(3));
    assertEquals(9, list.lastIndexOf(9));
  }

  // Tests some situations for removeElt().
  @Test public void testPublic9() {
    List<Integer> list= exampleList2();

    list.removeElt(9);
    assertEquals("2 3 4 6 7 8", list.toString());

    list.removeElt(6);
    assertEquals("2 3 4 7 8", list.toString());
  }

  // Tests some situations for removeElementWithIndex().
  @Test public void testPublic10() {
    List<Integer> list= exampleList2();

    list.removeElementWithIndex(5);
    list.removeElementWithIndex(3);
    list.removeElementWithIndex(1);

    assertEquals("2 4 7 9", list.toString());
  }

  // Tests some situations for subList().
  @Test public void testPublic11() {
    List<Integer> list= exampleList2();
    List<Integer> partOfList= new List<Integer>();

    partOfList= list.subList(0, 1);
    assertEquals("2 3", partOfList.toString());

    partOfList= list.subList(2, 4);
    assertEquals("4 6 7", partOfList.toString());
  }

  // Tests some special cases of behavior for subList().
  @Test public void testPublic12() {
    List<Integer> list= exampleList2();
    List<Integer> partOfList= new List<Integer>();

    partOfList= list.subList(0, 0);
    assertEquals("2", partOfList.toString());

    partOfList= list.subList(6, 6);
    assertEquals("9", partOfList.toString());

    partOfList= list.subList(0, 6);
    assertEquals("2 3 4 6 7 8 9", partOfList.toString());
  }

  // Tests whether the iterator properly iterates over a list and returns
  // the right values.
  @Test public void testPublic13() {
    List<Integer> list= exampleList2();
    Iterator<Integer> it;
    String s= "";

    it= list.iterator();
    while (it.hasNext()) {
      s= s + it.next();
      if (it.hasNext())
        s += " ";
    }

    assertEquals("2 3 4 6 7 8 9", s.toString());
  }

  // Tests the iterator remove() method.
  @Test public void testPublic14() {
    List<Integer> list= exampleList1();
    Iterator<Integer> it;
    
    it= list.iterator();
    while (it.hasNext()) {
      it.next();  // the return value is just thrown away
      it.remove();
    }
    assertEquals(0, list.size());
  }

  // Tests the length comparator.
  @Test public void testPublic15() {
    List<Integer> list1= exampleList1();
    List<Integer> list2= exampleList2();
    List<Integer> emptyList1= new List<Integer>();
    List<Integer> emptyList2= new List<Integer>();
    Comparator<List<Integer>> comparator= list1.lengthComparator();

    assertTrue(comparator.compare(list1, list2) < 0);
    assertTrue(comparator.compare(list2, list1) > 0);

    assertTrue(comparator.compare(emptyList1, list2) < 0);
    assertTrue(comparator.compare(list2, emptyList1) > 0);

    assertEquals(0, comparator.compare(list1, list1));
    assertEquals(0, comparator.compare(emptyList1, emptyList2));
  }

  // private utility methods ////////////////////////////////////////////

  // Adds all elements of an array of any type (as long as the type is one
  // that implements the Comparable interface) to a List of the same type
  // and returns it, for use in creating lists for testing the methods.
  public static <T extends Comparable<T>> List<T> makeList(T[] arr) {
    List<T> list= new List<T>();

    if (arr != null)
      for (T elt : arr)
        list.sortedOrderInsert(elt);

    return list;
  }

  // Creates and returns a list of Integers with just a few elements.
  private List<Integer> exampleList1() {
    return makeList(new Integer[]{1, 2, 3, 4, 5});
  }

  // Creates and returns a list of Integers with a few more elements.
  private List<Integer> exampleList2() {
    return makeList(new Integer[]{2, 9, 6, 3, 7, 4, 8});
  }

}
