/**
 * Stack class
 *
 * @author 		Jessica Li
 * @version 	11/29/15 (1.0)
 */
public interface Stack<E>
{
	//overides Linked list if a linked list is initialized as a stack - limits to just the methods below 
	//FILO - first in last out, like a stack of paper

	public void push( E item );
	public E pop();
	public E peek();
	public boolean isEmpty();
	
}