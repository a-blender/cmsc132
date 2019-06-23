package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.List;
import recursiveMethods.RecursiveMethods;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests one case of replaceRange().
  @Test public void testPublic1() {
    List<Character> list = sampleList1();
    
    RecursiveMethods.replaceRange(list, 4, 8, 'X');
    assertEquals("p o s t X X X X X l a u r e a t e",
                 TestCode.listToStr(list));
  }

  // Tests one case of areReversed().
  @Test public void testPublic2() {
    List<Integer> list1= sampleList2();
    List<Integer> list2= TestCode.makeList(new Integer[]{5, 6, 7, 5, 4, 3,
                                                         2, 5, 9, 1, 8});

    assertTrue(RecursiveMethods.areReversed(list1, list2));
  }

  // Tests one case of posOfMaxElt().
  @Test public void testPublic3() {
    assertEquals(11, RecursiveMethods.posOfMaxElt(sampleList1()));
  }

  // Tests one case of changeAllFromTo().
  @Test public void testPublic4() {
    List<Integer> result= RecursiveMethods.changeAllFromTo(sampleList2(),
                                                           9, 0);

    assertEquals("8 1 0 5 2 3 4 5 7 6 5", TestCode.listToStr(result));
  }

  // private utility methods ////////////////////////////////////////////

  // Returns a sample list containing Characters.
  private static List<Character> sampleList1() {
    return TestCode.makeList(new Character[]{'p', 'o', 's', 't', 'b', 'a',
                                             'c', 'c', 'a', 'l', 'a', 'u',
                                             'r', 'e', 'a', 't', 'e'});
  }

  // Returns a sample list containing Integers.
  private static List<Integer> sampleList2() {
    return TestCode.makeList(new Integer[]{8, 1, 9, 5, 2, 3, 4, 5, 7, 6, 5});
  }

}
