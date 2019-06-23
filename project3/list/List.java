//List Class by Anna Blendermann
//Honor Pledge: I have not received any help on this assignment
package list;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.Comparator;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;

//LIST CLASS***************************************************************
//List class creates and manipulates a singly linked list
//utilizes inner classes for iterator and comparator
public class List<T extends Comparable<T>> implements Iterable<T> 
{
	private Node<T> head;

	//INNER NODE CLASS******************************************************
	//Node class creates and initializes a new node with data
	// you may ADD TO this inner class, but not CHANGE what's already here!
	private static class Node<D extends Comparable<D>> 
	{
		private D data;
		private Node<D> next;

		public Node(D data) 
		{
			this.data = data;
			next = null;
		}
	}

	//LIST CONSTRUCTOR******************************************************
	public List() 
	{
		head = null;
	}

	//COPY CONSTRUCTOR******************************************************
	public List(List<T> otherList) 
	{
		//new list but use the same node references
		List<T> newList = new List<T>();
		Node<T> current1 = otherList.head;
		Node<T> current2 = newList.head;

		while(current1 != null)
		{
			current2.next = new Node<T>(current1.data);
			current1 = current1.next;
			current2 = current2.next;
		}
		newList = otherList;
	}

	//SORTED ORDER INSERT METHOD*******************************************
	//Case 1 - empty list or greater first node, add newElt to head
	//Case 2 - list contains elements, add newElt between other elements
	public void sortedOrderInsert(T newElt) 
	{
		if (newElt == null || size() < 0) //invalid list or param
			throw new NoSuchElementException();
		else //newElt data is present and list is valid
		{
			Node<T> newElement = new Node<T>(newElt);
			Node<T> current = head;

			if (head == null || head.data.compareTo(newElt) > 0)
			{
				newElement.next = head;
				head = newElement; 
			}
			else //add element between other elements
			{				
				while (current.next != null && current.next.data.compareTo(newElt) <= 0)
					current = current.next;

				newElement.next = current.next;
				current.next = newElement;
			}
		}
	} //end of sorted insert method

	//SIZE METHOD***************************************************************
	//returns the size of the current linked list
	public int size() 
	{
		Node<T> current = head;
		int listsize = 0;
		while (current != null)
		{
			current = current.next;
			listsize += 1;
		}
		return listsize;
	}

	//GET ELEMENT AT INDEX METHOD***********************************************
	//Case 1 - method asks for first node, returns head.data
	//Case 2 - target index greater than zero, traverses and finds node
	public T getElementAtIndex(int index) throws IndexOutOfBoundsException 
	{
		Node<T> current = head;
		int currentIndex = 0;

		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		else if(index == 0)
			return head.data;
		else
		{
			while (current.next != null && currentIndex < index)
			{
				current = current.next;
				currentIndex++;
			}
			return current.data;		
		}
	}

	//CONTAINS METHOD***********************************************************
	//checks if target element exists in current list, null if not
	public T contains(T element) 
	{		
		Node<T> current = head;
		if (element != null)
		{
			while (current != null)
			{
				if (current.data.compareTo(element) == 0)
					return current.data;
				current = current.next;
			}
		}
		return null;
	}

	//TO STRING METHOD***********************************************************
	//returns toString() of elements of the current list
	public String toString() 
	{
		Node<T> current = head;
		String result = "";
		while (current != null)
		{
			result += current.data + " ";
			current = current.next;
		}
		return result.trim();
	}

	//INDEX OF METHOD**************************************************************
	//finds index of target element in the current list
	//returns -1 if target element is null or doesn't exist
	public int indexOf(T element)  
	{
		Node<T> current = head;
		int currentIndex = 0;
		if (element != null && contains(element) != null)
		{
			while (current != null)
			{
				if(current.data.compareTo(element) == 0)
					return currentIndex;
				current = current.next;
				currentIndex++;
			}
		}
		return -1;
	} //end of IndexOf method()

	//LAST INDEX OF METHOD*********************************************************
	//finds most recent list index containing the target element
	//returns -1 if target element is null or doesn't exist
	public int lastIndexOf(T element)  
	{
		Node<T> current = head;
		int currentIndex = 0, targetIndex = -1;

		if(element != null && contains(element) != null)
		{
			while (current != null)
			{
				if(current.data.compareTo(element) == 0)
					targetIndex = currentIndex; //set last index
				current = current.next;
				currentIndex++;
			}
		}
		return targetIndex;
	} //end of lastIndex() method

	//REMOVE ELEMENT METHOD*******************************************************
	//Case true - target element found and removed from list 
	//Case false - list is empty or target element doesn't exist
	public boolean removeElt(T element) 
	{
		Node<T> prev = head, current = head.next;

		if (head.data.compareTo(element) == 0)
		{
			head = head.next; //removes first element
			return true;
		} //return true and you're done

		while (current != null && current.data.compareTo(element) != 0) 
		{
			prev = current;
			current = current.next;
		}
		if (current != null) //target element not null
		{			
			prev.next = current.next;
			return true;
		}
		return false;
	} //end of removeElt() method


	//REMOVE ELEMENT WITH INDEX METHOD**********************************************
	//removes element from list with the target index
	//throws IndexOutOfBoundsException if index is negative/too large
	public void removeElementWithIndex(int index) throws IndexOutOfBoundsException 
	{
		Node<T> prev = head, current = head.next;
		int currentIndex = 1;

		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		else //index is within range
		{
			if (index == 0)
				head = head.next; //removes first element

			while (current != null && currentIndex<index) 
			{
				prev = current;
				current = current.next;
				currentIndex++;
			}
			if (current != null) //target element not null
				prev.next = current.next;
		}
	} //end of removeWithIndex() method

	//CLEAR METHOD***************************************************************
	//clear() resets the current linked list to empty
	public void clear() 
	{
		head = null; //resets list to empty
	}

	//SUBLIST METHOD************************************************************
	//creates a sublist of the current list, without modifying current
	//throws IndexOutOfBoundsException for negative/large indexes
	public List<T> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException 
	{
		List<T> newList = new List<T>();
		Node<T> current1 = head;
		Node<T> current2 = newList.head;
		int currentIndex = 0;

		if (fromIndex < 0 || toIndex > size()-1 || fromIndex > toIndex)
			throw new IndexOutOfBoundsException();
		else //both index positions are valid
		{
			while (current1 != null && currentIndex < fromIndex) 
			{
				current1 = current1.next;
				currentIndex++;
			} //current has now reached fromIndex

			while (current1 != null && currentIndex <= toIndex)
			{
				if (newList.head == null) //new list is empty
				{
					newList.head = new Node<T>(current1.data);
					current2 = newList.head;
				}
				else //new list already has nodes 
				{ 
					current2.next = new Node<T>(current1.data); //add node
					current2 = current2.next; 
				}

				current1 = current1.next;
				currentIndex++;
			} //current has now reached toIndex
		}
		return newList;
	} //end of subList method


	//ITERATOR METHOD***********************************************************
	//creates and returns new myIterator() object
	public Iterator<T> iterator() 
	{
		return new myIterator(this);
	}

	//MyITERATOR INNER CLASS****************************************************
	//inner class iterates through or removes elements from current list
	//uses hasNext(), next(), and remove() non-static methods
	public class myIterator implements Iterator<T>
	{
		private Node<T> current;
		private T currentData = null; //data to return from next()

		public myIterator(List<T> newList) 
		{
			current = head;
		}

		//hasNext() method checks if current list has next element
		public boolean hasNext() 
		{			
			if (size() > 0 && current != null) 
				return true;
			else return false;
		}        

		//Case 1 - hasNext() returns true, method returns next element
		//Case 2 - hasNext() is false, method returns null
		//throws NoSuchElementException if current list is empty
		public T next() throws NoSuchElementException
		{
			if (size() == 0)
				throw new NoSuchElementException();

			else if (hasNext()) //list has more nodes
			{
				currentData = current.data;
				current = current.next;
			}
			return currentData;
		}

		//remove() removes elements from the current list
		public void remove() throws IllegalStateException
		{
			if (size() > 0)
			{
				if(currentData != null)
					removeElt(currentData);
			}
			else
				throw new IllegalStateException();		
		}
	} //end of myIterator inner class


	//LENGTH COMPARATOR METHOD**************************************************
	//lengthComparator() creates new compareLength() inner class
	public Comparator<List<T>> lengthComparator() 
	{
		return new compareLength();
	}

	//COMPARE LENGTH INNER CLASS************************************************
	//compareLength() inner class implements Comparator interface
	//compare() compares two linked lists depending on length only
	public class compareLength implements Comparator<List<T>> 
	{
		//compare() returns 1, -1, or 0 based on length comparison
		public int compare(List<T> list1, List<T> list2) 
		{
			if (list1.size() > list2.size())
				return 1;
			else if (list1.size() < list2.size())
				return -1;
			else return 0;
		}
	}

	//ORDER COMPARATOR**********************************************************
	//orderComparator() creates new compareOrder() inner class
	public Comparator<List<T>> orderComparator() 
	{
		return new compareOrder();
	}

	//COMPARE ORDER INNER CLASS*************************************************
	//compareOrder() inner class implements Comparator interface
	//compare() compares two linked lists based on alphabetic order
	public class compareOrder implements Comparator<List<T>> 
	{
		//compare() returns 1, -1, or 0 based on alphabetic order
		public int compare(List<T> list1, List<T> list2) 
		{
			myIterator itr1 = new myIterator(list1);
			myIterator itr2 = new myIterator(list2);
			int status = 0;

			while (itr1.hasNext() && itr2.hasNext())
			{
				if (itr1.next().compareTo(itr2.next()) == 1)
					status = 1;
				else if (itr1.next().compareTo(itr2.next()) == -1)
					status = -1;
			}
			return status;
		}
	}
} //end of List class
