/**
 * ListNode class
 *
 * @author 		Jessica Li
 * @version 	11/29/30 (1.0)
 */
public class ListNode<E>
{
	private E item;
	private ListNode<E> next;

	/**
	 * Constructor, takes in value for item. Sets next to null.
	 *
	 * @param it 	the value item is set to
	 */
	public ListNode( E it )
	{
		item = it; 
		//should i set next to null?
		next = null;
	}

	/**
	 * Constructor, takes in values for item and next. 
	 *
	 * @param it 		value for item
	 * @param nxt 		value for next
	 */
	public ListNode( E it, ListNode<E> nxt )
	{
		item = it; 
		next = nxt; 
	}

	/**
	 * Copy Constructor (for LinkedList copy constructor??)
	 *
	 * @param node 		the ListNode being copied
	 */
	public ListNode( ListNode<E> node )
	{
		item = node.getItem();
		next = node.getNext(); 
	}

	/**
	 * Accessor for item.
	 *
	 * @return 		item
	 */
	public E getItem()
	{
		return item;
	}

	/**
	 * Acessor for next. 
	 *
	 * @return 		next
	 */
	public ListNode getNext()
	{
		return next;
	}

	/**
	 * Modifier for item. 
	 *
	 * @param e 	the new item
	 */
	public void setItem( E e )
	{
		item = e; 
	}

	/**
	 * Modifier for next.
	 *
	 * @param ln 	the new next
	 */
	public void setNext( ListNode<E> ln )
	{
		next = ln; 
	}

	/**
	 * Returns String representation of ListNode. Calls toString() on item. 
	 *
	 * @return 		item's toString
	 */
	public String toString()
	{
		return item.toString(); 
	}

}