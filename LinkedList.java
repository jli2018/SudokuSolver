import java.util.NoSuchElementException;
import java.util.Iterator;
import java.lang.Iterable;

/**
 * LinkedList class. 
 *
 * @author 		Jessica Li
 * @version 	11/29/15 (1.0)
 */
public class LinkedList<E> implements Stack<E>, Queue<E>, Iterable<E>
{
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;

	/** 
	 * Default constructor, creates an empty list. 
	 * Sets head and tail to null, size to 0. 
	 */
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}

	/** 
	 * Constructor, take in one ListNode. 
	 * Sets both head and tail to ListNode, sets size to 1. 
	 *
	 * @param h 	the ListNode to be added to the list
	 */
	public LinkedList( ListNode<E> h )
	{
		if ( h == null )
			throw new IllegalArgumentException( "The parameter must not be null. " );
		head = h;
		tail = h;
		size = 1;
	}

	/** 
	 * Copy constructor. 
	 * If other is null, throws exception. If other is empty, creates empty list by setting head, tail to null and size to 0. 
	 * Otherwise, sets head to the first element in other (makes a copy using ListNode copy constructor). 
	 * Creates a new ListNode node for actions in for loop. Sets size to the size of other. 
	 * If size equals 1, sets tail to head, and the for loop will not execute. For loop traverses through list for the length of other, 
	 * starting at 1 because the head is already set. Sets node's next to the element at index i in other. Sets node to the next ListNode. 
	 * Continues until list is the same as other. Sets tail to node if index is size-1. 
	 * 
	 * @param other 	the list to be copied
	 */
	public LinkedList( LinkedList<E> other )
	{
		if ( other == null )
			throw new IllegalArgumentException( "The parameter must not be null. " );
		if ( other.isEmpty() )
		{
			head = null;
			tail = null;
			size = 0;
		}
		else
		{
			head = new ListNode( other.get(0) );			//copy ListNode
			ListNode node = head; 
			size = other.size(); 
			if ( size == 1 )
				tail = head; 

			for ( int i = 1; i < size; i++ )
			{
				node.setNext( new ListNode( other.get(i) ) );
				node = node.getNext(); 
				//if i is the last element in the list
				if ( i == size-1 )
				{
					tail = node; 
				}
			}
		}
	}

	/** 
	 * Returns size of list. 
	 *
	 * @return 		size of list
	 */
	public int size()
	{
		return size;
	}

	/** 
	 * Removes all items from the LinkedList. 
	 * Sets both head and tail to null, and sets size to 0. The previous elements of the list will be taken care of by 
	 * the Java garbage collector. 
	 */
	public void clear()
	{
		head = null; 
		tail = null; 
		size = 0; 
	}

	/**
	 * Adds a value to the end of the LinkedList. Calls addLast() to do so. 
	 * Returns boolean because it is a good practice for the future, when we might need to use boolean for sets. 
	 *
	 * @param e 	the value to be added to the LinkedList
	 * @return 		true if successfully added
	 */
	public boolean add( E e )
	{
		addLast( e );
		return true;
	}

	/**
	 * Adds to the specified index. 
	 * If index is 0 or size, this method calls addFirst() or addLast(). 
	 * Otherwise, a new ListNode is created with the value e, called node. The method traverses through the list, 
	 * setting ListNode ln to the next listnode. Since ln starts at head outside of the loop, the loop ends at index - 1. 
	 * Then node's next is set to ln's next, and ln's next is set to node. 
	 * Size is incremented. 
	 *
	 * @param index 	the location where the new ListNode is to be added
	 * @param e 		the value of the ListNode to be added
	 */ 
	public void add( int index, E e )
	{
		if ( index > size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than or equal to size and greater than or equal to 0. ");
		if ( index == 0 )
			addFirst( e );
		if ( index == size )
			addLast( e ); 

		else
		{
			ListNode<E> node = new ListNode<E>( e );
			ListNode<E> ln = head; 
			for ( int i = 0; i < index-1; i++)
			{
				ln = ln.getNext(); 
			}
			node.setNext( ln.getNext() );
			ln.setNext( node );
		}

		size++;
	}

	/**
	 * Adds object to the front of the list. 
	 * Creates a ListNode called node using item. If the list is empty, sets both head and tail to node. 
	 * Otherwise, sets node's next to head and sets head to node. Increments size.
	 *
	 * @param item 		the value to be added to the list
	 */
	public void addFirst(E item)
	{
		ListNode<E> node = new ListNode<E>( item );
		if ( head == null )
		{
			head = node;
			tail = node;
		}
		else
		{
			node.setNext( head );
			head = node;
		}

		size++;
	}

	/**
	 * Adds object to the end of the list. 
	 * Creates a ListNode called node using item. If the list is empty, sets both head and tail to node. 
	 * Otherwise, sets tail's next to node and sets tail to node. Increments size.
	 *
	 * @param item 		the value to be added to the list
	 */
	public void addLast( E item )
	{
		ListNode<E> node = new ListNode<E>( item );
		if ( head == null )
		{
			head = node;
			tail = node;
		}
		else
		{
			tail.setNext( node );
			tail = node;
		}

		size++;
	}

	/**
	 * Removes item o and returns whether or not the item was removed. 
	 * If the list is empty (size equals 0), returns false, as there is nothing to remove. 
	 * Creates new ListNode node and sets to head. 
	 * If head equals o, removes node by setting head to the next ListNode and decrements size. Returns true. 
	 * Otherwise, loop traverses through list. Loop ends at size-1 because node starts at head and getNext() is being called
	 * inside the loop. If the item of the ListNode after node equals o, removes the ListNode. Sets tail to node if removing
	 * the last value of the list. Decrements size and returns true.
	 * Otherwise, sets node to the next ListNode. 
	 * If for loop finishes without finding a match, method returns false - nothing successfully removed. 
	 *
	 * @param o 	the value to be removed
	 * @return 		true if o is successfully removed, otherwise false
	 */
	public boolean remove( E o )
	{
		if ( size == 0 )
			return false;

		ListNode<E> node = head;

		//takes care of the case when head equals o
		if ( node.getItem().equals( o ) )
		{
			head = head.getNext();
			size--;
			return true;
		}

		for ( int i = 0; i < size-1; i++)
		{
			if ( node.getNext().getItem().equals( o ) )
			{
				node.setNext( node.getNext().getNext() );
				//for when you are removing the last item and need to reset tail
				//will be size-2 because i will be the listnode before the last listnode
				if ( i == size-2 ) 
					tail = node; 
				size--;
				return true;
			}
			node = node.getNext();
		}

		return false;
	}

	/**
	 * Removes and returns item at specified index. 
	 * If the list is empty, throws NoSuchElementException. 
	 * If index is 0, calls removeFirst(). 
	 * Otherwise, creates ListNode toReturn to store the item to be reomved. Creates ListNode node and sets to head. 
	 * For loop traverses through list until index - 1 (because node starts from head outside of loop) and sets 
	 * node to its next. Once the loop is finished, node will equal the ListNode right before the one to be removed. 
	 * Sets toReturn to node's next, or the ListNode to be removed. Sets node's next to the ListNode after the one 
	 * to be removed. If index is equal to size, meaning the method is removing the last item in the list, tail is 
	 * set to node. 
	 * size is decremented and the method returns toReturn. 
	 * 
	 * @param index 	the index of the item to be removed
	 * @return 			the removed item
	 */
	public E remove( int index )
	{
		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than or equal to 0. ");
		if ( size == 0 )
			throw new NoSuchElementException( "The list is empty, therefore the element cannot be removed. "); 
		if ( index == 0 )
			return removeFirst(); 
		
		ListNode toReturn; 
		ListNode node = head;
		for ( int i = 0; i < index-1; i++ )
		{
			node = node.getNext();
		}
		toReturn = node.getNext(); 
		node.setNext( node.getNext().getNext() );
		if ( index == size-1 )
			tail = node;
		size--;
		return (E) toReturn.getItem(); 
	}

	/**
	 * Removes and returns the first item in the list. 
	 * If the list is empty, throws NoSuchElementException. 
	 * Creates ListNode toReturn to store the item to be removed, sets to head. 
	 * Sets head to head's next. Decrements size. Returns toReturn. 
	 * 
	 * @return 		the last item in the list, which is being removed
	 */
	public E removeFirst()
	{
		if ( size == 0 )
			throw new NoSuchElementException( "The list is empty, therefore the first element cannot be removed. "); 
		ListNode toReturn = head;
		head = head.getNext(); 
		size--;
		return (E) toReturn.getItem();
	}

	/**
	 * Removes and returns the last item in the list. 
	 * Calls remove() with index size. 
	 * Does not throw an exception because remove will do already do so. 
	 * I did not write individual code for this method because it would be almost identical to the code 
	 * in the remove method. 
	 * 
	 * @return 		the last item in the list, which is being removed
	 */
	public E removeLast()
	{
		//size-1 because index starts at 0
		return remove( size-1 );
	}

	/**
	 * Adds object to stack -- meaning it adds to the end of the list. Therefore, calls addLast(). 
	 *
	 * @param item 		the value to be added to the stack/list. 
	 */
	public void push(E item)
	{
		addLast( item );
	}

	/**
	 * Removes object from stack, or removes from end of list. 
	 * Calls removeLast().
	 *
	 * @return 		the removed last item of the stack/list
	 */
	public E pop()
	{
		return removeLast(); 
	}

	/**
	 * Adds object to queue -- meaning it adds to the front of the list. Therefore, calls addFirst(). 
	 *
	 * @param item 		the value to be added to the stack/list. 
	 */
	public void offer(E item)
	{
		addFirst( item );
	}

	/**
	 * Removes object from queue, or removes object from end of list. 
	 * Calls removeLast(). 
	 *
	 * @return 		the removed first item of the queue/list
	 */
	public E poll()
	{
		return removeLast(); 
	}

	/**
	 * Returns, but does not remove, the tail item. 
	 * Calls get() with size-1 to get the first item. 
	 *
	 * @return 		the head item of the list
	 */
	public E peek()
	{
		//size-1 because index starts at 0
		return get( size-1 ); 
	}

	/**
	 * Returns item at a specified index. 
	 * Creates ListNode node and sets to head. For loop traverses through list until index, setting node to the next ListNode
	 * each time. Returns node. 
	 *
	 * @param index 	the index of the value to be returned
	 * @return 			the object at the index
	 */
	public E get( int index )
	{
		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than or equal to 0. ");

		ListNode<E> node = head; 
		for ( int i = 0; i < index; i++)
		{
			node = node.getNext(); 
		}
		return (E) node.getItem();
	}

	/** 
	 * Replaces object at specified location. 
	 * Creates new ListNode node with value o. Creates ListNode ln and sets to head - to be used for traversing list. 
	 * Creates ListNode store to store the value being replaced. For loop traverses through array, setting ln to the next
	 * ListNode until index-1. store is set to ln.getNext(), the value being replaced. node's next is set to two values 
	 * after ln - the value right after the value to be replaced. ln's next is set to node. store is returned. 
	 * 
	 * @param index 	the index of the element to be replaced
	 * @param o 		the value to be placed at index
	 * @return 			the value previously at index that is now replaced by o
	 */
	public E set(int index, E o)
	{
		//Can I remove and then add? Or would this be bad for runtime?
		if ( index >= size || index < 0 )
			throw new IndexOutOfBoundsException( "The index must be less than size and greater than or equal to 0. ");

		ListNode<E> node = new ListNode<E>( o ); 
		ListNode<E> ln = head; 
		ListNode<E> store;
		//index-1 because I need the object before, not at, the location
		for ( int i = 0; i < index-1; i++)
		{
			ln = ln.getNext(); 
		}
		store = ln.getNext(); 
		node.setNext( ln.getNext().getNext() );
		ln.setNext( node );

		return (E) store.getItem();
	}


	/**
	 * Returns whether or not list contains specified object. 
	 * Calls indexOf(). If indexOf() returns a number other than -1, returns true - list does contain this value. 
	 * If list returns -1, returns false - list does not contain this value. 
	 * 
	 * @param o 	the value to be found and matched
	 * @return 		true if matching object is found, false if otherwise
	 */
	public boolean contains( E o )
	{
		if ( indexOf(o) != -1 )
			return true;
		return false; 
	}

	/**
	 * Returns index of the first instance of specified object
	 * Creates new ListNode node, called head. For loop traverses through list, testing if node's item is equal to o. 
	 * If yes, then returns index i. Otherwise, sets node to the next ListNode. 
	 * Returns -1 if for loop ends, meaning o does not exist in the list. 
	 * 
	 * @param o 	the value to be found and matched
	 * @return 		index of the value o; -1 if not found
	 */
	public int indexOf(E o)
	{
		ListNode<E> node = head;
		for ( int i = 0; i < size; i++ )
		{
			if ( node.getItem().equals(o) )
				return i;
			node = node.getNext(); 
		}
		return -1; 
	}

	/**
	 * Returns whether or not the list is empty. 
	 * If size equals 0, returns true. Otherwise, returns false. 
	 *
	 * @return 	true is array is empty, false if not
	 */
	public boolean isEmpty()
	{
		if ( size == 0 )
			return true;
		return false; 
	}
	
	/** 
	 * iterator method, returns a new LinkedListIterator created with head. 
	 *
	 * @return 		LinkedListIterator created with head
	 */
	public Iterator<E> iterator()
	{
		return new LinkedListIterator<E>( head );
	}



	/** 
	 * Returns a String representation of the LinkedList. 
	 * If list is empty, returns empty String.
	 * Creates an empty String and ListNode ln, sets ln to head. Adds first ListNode to toReturn outside of for loop. 
	 * For loop sets ln to next and adds to toReturn. Ends at size-1 because the first ListNode was added outside. 
	 * Returns toReturn. 
	 *
	 * @return 		string representation of the list
	 */
	public String toString()
	{
		if ( size == 0 )
			return "";
		String toReturn = "";
		ListNode<E> ln = head;
		//if i added the first toString() here, i would not need the last iteration of the loop
		toReturn += ln.toString();
		for ( int i = 0; i < size-1; i++ )
		{
			ln = ln.getNext();
			toReturn += " –––> " + ln.toString();

		}
		return toReturn;
	}

}

/*

singly - with head pointer and tail pointer
doubly - points forward and backward

*/