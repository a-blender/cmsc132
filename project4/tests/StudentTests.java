//STUDENT TESTS by Anna Blendermann
package tests;
import java.util.List;
import recursiveMethods.RecursiveMethods;
import org.junit.*;

import static org.junit.Assert.*;

//STUDENT TESTS****************************************************************
public class StudentTests 
{
	//tests replaceRange() with character list
	@Test public void testPublic1() 
	{
		List<Character> list = studentList1();

		RecursiveMethods.replaceRange(list, 2, 6, 'X');
		assertEquals("c v X X X X X k r l a u s t a d t",
				TestCode.listToStr(list));	
	}

	//tests replaceRange() with removing just one element
	@Test public void testPublic2() 
	{
		List<Integer> list = studentList2();

		RecursiveMethods.replaceRange(list, 6, 6, 50);
		assertEquals("2 7 1 9 5 6 50 1 8 4 5",
				TestCode.listToStr(list));
	}

	//tests replaceRange() with sublist starting from index 0
	@Test public void testPublic3() 
	{
		List<String> list = studentList3();

		RecursiveMethods.replaceRange(list, 0, 4, "GOLD");
		assertEquals("GOLD GOLD GOLD GOLD GOLD green dress shape word",
				TestCode.listToStr(list));
	}

	//tests replaceRange() with sublist ending at list.size()
	@Test public void testPublic4() 
	{
		List<String> list = studentList3();

		RecursiveMethods.replaceRange(list, 4, 8, "GOLD");
		assertEquals("dog cake fly born GOLD GOLD GOLD GOLD GOLD",
				TestCode.listToStr(list));	
	}

	//test replaceRange() for abnormal cases
	@Test public void testPublic5() 
	{
		List<Integer> list = studentList2();

		RecursiveMethods.replaceRange(list, -2, 6, 50);
		assertEquals("2 7 1 9 5 6 3 1 8 4 5", 
				TestCode.listToStr(list));

		RecursiveMethods.replaceRange(list, 5, 20, 50);
		assertEquals("2 7 1 9 5 6 3 1 8 4 5", 
				TestCode.listToStr(list));

		RecursiveMethods.replaceRange(list, 7, 4, 50);
		assertEquals("2 7 1 9 5 6 3 1 8 4 5", 
				TestCode.listToStr(list));
	}

	//tests areReversed() for a normal list of integers
	@Test public void testPublic6() 
	{
		List<Integer> list1 = studentList2();
		List<Integer> list2= TestCode.makeList(new Integer[] 
				{5, 4, 8, 1, 3, 6, 5, 9, 1, 7, 2});	
		assertTrue(RecursiveMethods.areReversed(list1, list2));
	}

	//tests areReversed for a reversed array of strings
	@Test public void testPublic7() 
	{
		List<String> list1 = TestCode.makeList(new String[] 
				{"your", "small", "cat", "is", "adorable"});	
		List<String> list2 = TestCode.makeList(new String[] 
				{"adorable", "is", "cat", "small", "your"});

		assertTrue(RecursiveMethods.areReversed(list1, list2));
	}

	//tests areReversed() for small switches in numbers
	@Test public void testPublic8() 
	{
		List<Integer> list1 = TestCode.makeList(new Integer[] 
				{5, 4, 3, 2, 2, 7, 9, 1, 2});
		List<Integer> list2 = TestCode.makeList(new Integer[] 
				{2, 1, 9, 7, 2, 2, 3, 5, 4}); //5 and 4 are switched

		assertFalse(RecursiveMethods.areReversed(list1, list2));
	}

	//tests areReversed() for the "single element lists" case
	@Test public void testPublic9() 
	{
		List<Integer> list1 = TestCode.makeList(new Integer[] {6});
		List<Integer> list2 = TestCode.makeList(new Integer[] {6});
		assertTrue(RecursiveMethods.areReversed(list1, list2));

		List<Integer> list3 = TestCode.makeList(new Integer[] {12});
		List<Integer> list4 = TestCode.makeList(new Integer[] {15});
		assertFalse(RecursiveMethods.areReversed(list3, list4));
	}

	//tests areReversed() for the "both lists are null" case
	@Test public void testPublic10() 
	{
		List<Integer> list1 = TestCode.makeList(new Integer[] {}); 
		List<Integer> list2 = TestCode.makeList(new Integer[] {}); 
		assertTrue(RecursiveMethods.areReversed(list1, list2));
	}

	//tests postOfMaxElt() with three different lists
	@Test public void testPublic11() 
	{
		List<Character> list1 = studentList1();
		List<Integer> list2 = studentList2();
		List<String> list3 = studentList3();

		assertEquals(5, RecursiveMethods.posOfMaxElt(list1));
		assertEquals(3, RecursiveMethods.posOfMaxElt(list2));
		assertEquals(8, RecursiveMethods.posOfMaxElt(list3));
	}

	//tests posOfMaxElt() with list with one/two elements
	@Test public void testPublic12() 
	{
		List<Integer> list1 = TestCode.makeList(new Integer[] {12}); 
		assertEquals(0, RecursiveMethods.posOfMaxElt(list1));

		List<String> list2 = TestCode.makeList(new String[] {"dog"}); 
		assertEquals(0, RecursiveMethods.posOfMaxElt(list2));
	}

	//tests posOfMaxElt() for max as last element
	@Test public void testPublic13() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] 
				{2, 5, 4, 2, 7, 10, 16});
		assertEquals(6, RecursiveMethods.posOfMaxElt(list));
	}

	//tests posOfMaxElt() for multiple max elements
	@Test public void testPublic14() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] 
				{2, 5, 4, 7, 7, 7, 3});
		assertEquals(5, RecursiveMethods.posOfMaxElt(list));
	}

	//tests posOfMaxElt() for invalid parameters, empty list
	@Test public void testPublic15() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] {});
		assertEquals(-1, RecursiveMethods.posOfMaxElt(list));
	}

	//tests changeAllFromTo() for normal integer list
	@Test public void testPublic16() 
	{
		List<Integer> list = studentList2();

		List<Integer> result1 = RecursiveMethods.changeAllFromTo(list, 1, 10);
		assertEquals("2 7 10 9 5 6 3 10 8 4 5", TestCode.listToStr(result1));

		List<Integer> result2 = RecursiveMethods.changeAllFromTo(result1, 10, 20);
		assertEquals("2 7 20 9 5 6 3 20 8 4 5", TestCode.listToStr(result2));
	}

	//tests changeAllFromTo() for normal string list 
	@Test public void testPublic17() 
	{
		List<String> list = studentList3();

		List<String> list1 = RecursiveMethods.changeAllFromTo(list, "cake", "X");
		List<String> list2 = RecursiveMethods.changeAllFromTo(list1, "dress", "X");
		List<String> list3 = RecursiveMethods.changeAllFromTo(list2, "word", "X");

		assertEquals("dog X fly born mate green X shape X", TestCode.listToStr(list3));
	}

	//tests changeAllFromTo() for oldElt that doesn't exist
	@Test public void testPublic18() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] 
				{2, 10, 15, 20, 32, 24, 20});
		List<Integer> result = RecursiveMethods.changeAllFromTo(list, 21, 50);
		assertEquals("2 10 15 20 32 24 20", TestCode.listToStr(result));
	}

	//tests changeAllFromTo() for when oldElt and newElt are equal
	@Test public void testPublic19() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] 
				{2, 6, 12, 8, 12, 13, 10});
		List<Integer> result = RecursiveMethods.changeAllFromTo(list, 12, 12);
		assertEquals("2 6 12 8 12 13 10", TestCode.listToStr(result));
	}

	//tests changeAllFromTo() for empty passed list
	@Test public void testPublic20() 
	{
		List<Integer> list = TestCode.makeList(new Integer[] {});
		List<Integer> result = RecursiveMethods.changeAllFromTo(list, 2, 3);
		assertEquals("", TestCode.listToStr(result));
	}

	//tests replaceRange() and areReversed() for null lists
	@Test(expected= NullPointerException.class) public void testPublic21() 
	{
		List<Character> list = studentList1();
		RecursiveMethods.replaceRange(null, 0, 5, "CHEESE");

		RecursiveMethods.areReversed(list, null);
		RecursiveMethods.areReversed(null, list);   
	}

	//tests posOfMaxElt() and changeAllFromTo() for null lists
	@Test(expected= NullPointerException.class) public void testPublic22() 
	{	    
		RecursiveMethods.posOfMaxElt(null);
		RecursiveMethods.changeAllFromTo(null, 2, 8); 
	}

	//additional test for unexpected cases (see below)
	@Test public void testPublic23() 
	{	    
		List<Integer> list = studentList2();
		List<Integer> list2 = TestCode.makeList(new Integer[] 
				{-5, 4, 8, -1, 3, 6, -5, 9, -1, 7, 2});

		List<Integer> result1 = RecursiveMethods.changeAllFromTo(list2, -1, 1);
		assertEquals("-5 4 8 1 3 6 -5 9 1 7 2", TestCode.listToStr(result1));

		List<Integer> result2 = RecursiveMethods.changeAllFromTo(result1, -5, 5);
		assertEquals("5 4 8 1 3 6 5 9 1 7 2", TestCode.listToStr(result2));

		assertTrue(RecursiveMethods.areReversed(list, result2));	
	}

	//PRIVATE UTILITY METHODS***************************************************
	// Returns a sample list containing Characters
	private static List<Character> studentList1() {
		return TestCode.makeList(new Character[] {'c', 'v', 'p', 'f', 'g', 'w',
				'n', 'k', 'r', 'l', 'a', 'u', 's', 't', 'a', 'd', 't'});
		//"c v p f g w n k r l a u s t a d t"
	}

	// Returns a sample list containing Integers
	private static List<Integer> studentList2() {
		return TestCode.makeList(new Integer[] {2, 7, 1, 9, 5, 6, 3, 1, 8, 4, 5});
		//"2 7 1 9 5 6 3 1 8 4 5"
	}

	// Returns a sample list containing Strings
	private static List<String> studentList3() {
		return TestCode.makeList(new String[] {"dog", "cake", "fly", "born", 
				"mate", "green", "dress", "shape", "word"});
		//"dog cake fly born mate green dress shape word"
	}
}
