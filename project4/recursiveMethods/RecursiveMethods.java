//RECURSIVE METHODS by Anna Blendermann
//Honor Pledge: I pledge that I have not received help on this assignment
package recursiveMethods;
import java.util.ArrayList;
import java.util.List;

//RECURSIVE METHODS*************************************************************
//four recursive methods to perform simple operations on lists
public class RecursiveMethods 
{
	//REPLACE RANGE METHOD******************************************************
	//replaces an entire sublist with multiple repetitions of one element
	public static <T> void replaceRange(List<T> list, 
			int from, int to, T elt) 
	{
		if(list == null)
			throw new NullPointerException();
		else if(from <= to && from >= 0 && to <= list.size())
		{
			list.set(from, elt);
			replaceRange(list, from+1, to, elt);
		}
	}

	//ARE REVERSED METHOD*******************************************************
	//checks if two lists are the same, but reversed
	public static <T> boolean areReversed(List<T> list1, List<T> list2) 
	{
		if(list1 == null || list2 == null)
			throw new NullPointerException();
		else if(list1.size() == 0 && list2.size() == 0)
			return true;
		else if(list1.size() == list2.size())
			return helpReverse(list1, list2, 0, list2.size()-1);
		else return false;
	}

	public static <T> boolean helpReverse(List<T> list1, List<T> list2,
			int first, int last)
	{		
		if (first >= list1.size() && last < 0)
			return true;
		else return list1.get(first).equals(list2.get(last)) ? 
				helpReverse(list1, list2, first+1, last-1) : false;
	}

	//MAX ELEMENT METHOD********************************************************
	//finds and returns the most recent index of the max element
	public static <T extends Comparable<T>> int posOfMaxElt(List <T> list) 
	{
		if(list == null)
			throw new NullPointerException();
		else if(list.size() == 1)
			return 0;
		else if(list.size() > 1)
			return helpFindMax(list, 0, 0);
		else return -1;
	}

	public static <T extends Comparable<T>> int helpFindMax(List<T> list, 
			int max, int index)
	{		
		if(index >= list.size()) //base case
			return max;
		else
		{
			if (list.get(index).compareTo(list.get(max)) >= 0)
				max = index;	
			return helpFindMax(list, max, index+1);
		}
	}

	//CHANGE ALL METHOD*********************************************************
	//changes all instances of one element in a list to another element
	public static <T> List<T> changeAllFromTo(List<T> list, T oldElt, T newElt) 
	{
		ArrayList<T> newList = new ArrayList<T>();

		if(list == null)
			throw new NullPointerException();
		else if(oldElt == newElt || list.size() == 0)
			return list; //list has not been changed
		else return helpChangeList(list, newList, oldElt, newElt, 0);
	}

	public static <T> List<T> helpChangeList(List<T> list, ArrayList<T> newList, 
			T oldElt, T newElt, int index)
	{	
		if(index >= list.size()) //base case
			return newList;
		else
		{
			if(list.get(index).equals(oldElt))
				newList.add(newElt);
			else newList.add(list.get(index));
			return helpChangeList(list, newList, oldElt, newElt, index+1);
		}
	} 

} //end of RecursiveMethods class
