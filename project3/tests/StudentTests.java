//STUDENT TESTS by Anna Blendermann
package tests;
import list.List;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTests 
{
	//tests size() by adding three elements and checking the size
	@Test public void testPublic1() 
	{
		List<Integer> list = new List<Integer>();
		list.sortedOrderInsert(1);
		list.sortedOrderInsert(3);
		list.sortedOrderInsert(5);

		assertEquals(3, list.size());
	}

	//tests sortedOrderInsert() by adding five different elements
	//and checking the toString() output
	@Test public void testPublic2() 
	{
		List<Integer> list = new List<Integer>();
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(7);
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(12);
		list.sortedOrderInsert(8);
		list.sortedOrderInsert(15);

		assertEquals("2 4 7 8 12 15", list.toString());
	}

	//tests sortedOrderinsert() by adding groups of consecutive
	//elements and checking the toString() output
	@Test public void testPublic3() 
	{
		List<Integer> list = new List<Integer>();
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(4);
		assertEquals("2 2 4", list.toString());

		List<Integer> list2 = new List<Integer>();
		list2.sortedOrderInsert(5);
		list2.sortedOrderInsert(3);
		list2.sortedOrderInsert(5);
		assertEquals("3 5 5", list2.toString());

		List<Integer> list3 = new List<Integer>();
		list3.sortedOrderInsert(4);
		list3.sortedOrderInsert(4);
		list3.sortedOrderInsert(1);
		assertEquals("1 4 4", list3.toString());
	}

	//tests getElementAtIndex() with list of five elements
	@Test public void testPublic4() 
	{
		List<Integer> list = new List<Integer>();
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(1);
		list.sortedOrderInsert(7);
		list.sortedOrderInsert(2);

		assertEquals("1 2 2 4 7", list.toString());
		assertEquals(1, (int) list.getElementAtIndex(0));
		assertEquals(2, (int) list.getElementAtIndex(2));
		assertEquals(7, (int) list.getElementAtIndex(4));
	}

	//tests contains() with integers and negative/bounds elements
	@Test public void testPublic5() 
	{
		List<Integer> list = new List<Integer>();
		list.sortedOrderInsert(5);
		list.sortedOrderInsert(6);
		list.sortedOrderInsert(7);

		assertNotNull(list.contains(5));
		assertNotNull(list.contains(6));

		assertNull(list.contains(-2));
		assertNull(list.contains(10));
	}

	//tests IndexOf() with list of five different elements
	@Test public void testPublic6() 
	{
		List<Integer> list= new List<Integer>();

		list.sortedOrderInsert(1);
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(3);
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(5);

		assertEquals(0, list.indexOf(1));
		assertEquals(2, list.indexOf(3));
		assertEquals(4, list.indexOf(5));
		assertEquals(-1, list.indexOf(9));
	}

	//tests lastIndexOf() with groups of consecutive elements
	@Test public void testPublic7() 
	{
		List<Integer> list= new List<Integer>();

		list.sortedOrderInsert(2);
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(3);
		list.sortedOrderInsert(3);
		list.sortedOrderInsert(3);

		assertEquals(1, list.lastIndexOf(2));
		assertEquals(4, list.lastIndexOf(3));
		assertEquals(-1, list.lastIndexOf(10));
	}

	//tests removeElt() by removing elements from list
	@Test public void testPublic8() 
	{
		List<Integer> list= new List<Integer>();
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(6);
		list.sortedOrderInsert(7);		

		list.removeElt(2);
		assertEquals("4 6 7", list.toString());
		list.removeElt(6);
		assertEquals("4 7", list.toString());
	}

	//tests getElementAtIndex() by removing indexes
	@Test public void testPublic9() 
	{
		List<Integer> list= new List<Integer>();
		list.sortedOrderInsert(1);
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(5);
		list.sortedOrderInsert(8);
		list.sortedOrderInsert(9);

		list.removeElementWithIndex(0);
		assertEquals("2 5 8 9", list.toString());
		list.removeElementWithIndex(3);
		assertEquals("2 5 8", list.toString());
		list.removeElementWithIndex(1);
		assertEquals("2 8", list.toString());
	}

	//tests getElementAtIndex() with two invalid indexes
	@Test(expected= IndexOutOfBoundsException.class) 
	public void testPublic10() 
	{
		List<Integer> list= new List<Integer>();
		list.sortedOrderInsert(1);
		list.sortedOrderInsert(2);
		list.sortedOrderInsert(3);

		list.removeElementWithIndex(-2);
		list.removeElementWithIndex(4); 
	}

	//tests regular situations for subList() method
	@Test public void testPublic11() 
	{
		List<Integer> list= new List<Integer>();
		List<Integer> newList = new List<Integer>();
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(5);
		list.sortedOrderInsert(6);

		newList = list.subList(0, 1);
		assertEquals("4 5", newList.toString());
		newList = list.subList(1, 2);
		assertEquals("5 6", newList.toString());
	}

	//tests subList() for one element sublists
	@Test public void testPublic12() 
	{
		List<Integer> list= new List<Integer>();
		List<Integer> newList= new List<Integer>();
		list.sortedOrderInsert(4);
		list.sortedOrderInsert(5);
		list.sortedOrderInsert(6);

		newList = list.subList(0, 0);
		assertEquals("4", newList.toString()); 
		newList = list.subList(2, 2);
		assertEquals("6", newList.toString()); 
	}

	//tests subList() for negative and too-large indexes
	@Test(expected= IndexOutOfBoundsException.class) 
	public void testPublic13() 
	{
		List<Integer> list= new List<Integer>();
		List<Integer> newList= new List<Integer>();
		list.sortedOrderInsert(7);
		list.sortedOrderInsert(8);

		newList = list.subList(1, 3);
		assertEquals(new IndexOutOfBoundsException(), newList.toString()); 
		newList = list.subList(-2, 7);
		assertEquals(new IndexOutOfBoundsException(), newList.toString());
	}
} //end of studentTests
