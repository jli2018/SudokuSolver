/**
 * Queue class
 *
 * @author 		Jessica Li
 * @version 	11/29/15 (1.0)
 */
public interface Queue<E>
{
	//FIFO - first in first out, like a lunch line
	
	public void offer( E item );
	public E poll();
	public E peek();
	public boolean isEmpty();

}