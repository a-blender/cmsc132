package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TestCode {

  // Adds all elements of an array of any type to an ArrayList of the same
  // type and returns it, for use in creating lists for testing the methods.
  public static <T> List<T> makeList(T[] arr) {
    ArrayList<T> list= new ArrayList<T>();

    if (arr != null)
      for (T elt : arr)
        list.add(elt);

    return list;
  }

  // Converts a list to a string, for use in testing the methods.
  public static <T> String listToStr(List<T> list) {
    Iterator<T> iter;
    String s= "";

    if (list != null) {
      iter= list.iterator();

      while (iter.hasNext()) {
        s += iter.next();
        if (iter.hasNext())
          s += " ";
      }
    }

    return s;
  }

}
