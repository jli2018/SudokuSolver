import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException; 

/**
 * LinkedListIterator class
 *
 * @author		Jessica Li
 * @version 	11/29/15 (1.0)
 */
public class LinkedListIterator<E> implements Iterator<E>
{
	//curr is the next
	private ListNode<E> curr; 

	/**
	 * Constructor, takes in head. Sets curr to head.
	 *
	 * @param head 		what curr is set to
	 */
	public LinkedListIterator( ListNode<E> head )
	{
		curr = head;
	}

	/**
	 * Returns whether or not curr is null. 
	 *
	 * @return 		true if curr is not null, false if curr is null
	 */
	public boolean hasNext() 
	{
		return curr != null; 
	}

	/**
	 * If curr is null, throws exception. Otherwise, stores curr's item value, sets curr to the next ListNode, and returns the stored value. 
	 *
	 * @return 		curr's item value
	 */
	public E next()
	{
		if ( !hasNext() )
		{
			throw new NoSuchElementException( "No elements left to iterate." );
		}
		E value = curr.getItem(); 
		curr = curr.getNext();
		return value;

	}
}