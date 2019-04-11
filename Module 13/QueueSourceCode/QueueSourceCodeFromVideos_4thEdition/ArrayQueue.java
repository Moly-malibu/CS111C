/** * A class that implements the ADT queue by using an expandable circular array * with one unused location. *  * @author Frank M. Carrano * @version 2.0 */public class ArrayQueue<T> implements QueueInterface<T>, java.io.Serializable {	private T[] queue; // circular array of queue entries and one unused location	private int frontIndex;	private int backIndex;	private static final int DEFAULT_INITIAL_CAPACITY = 50;	public ArrayQueue() {		this(DEFAULT_INITIAL_CAPACITY);	}	public ArrayQueue(int initialCapacity) {		queue = (T[]) new Object[initialCapacity + 1];		frontIndex = 0;		backIndex = initialCapacity; // same as queue.length-1	} 	public void enqueue(T newEntry) {		if (isArrayFull()) 			doubleArray(); 		backIndex = (backIndex + 1) % queue.length; // first we find the new backIndex		// we use the % to account for when we need to "wrap around" to the beginning				queue[backIndex] = newEntry; 	}	public T getFront() {		T front = null;		if (!isEmpty())			front = queue[frontIndex];		return front;	}	public T dequeue() {		T front = null;		if (!isEmpty()) {			front = queue[frontIndex];			queue[frontIndex] = null;			frontIndex = (frontIndex + 1) % queue.length;		} 		return front;	} 	public boolean isEmpty() {		return frontIndex == ((backIndex + 1) % queue.length);	} 	public void clear() {		if (!isEmpty()) { // deallocates only the used portion			for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length) {				queue[index] = null;			} 			queue[backIndex] = null;		} 		frontIndex = 0;		backIndex = queue.length - 1;	} 	private boolean isArrayFull() {		return frontIndex == ((backIndex + 2) % queue.length);	} 	private void doubleArray() {		T[] oldQueue = queue; // oldQueue and queue are aliases		int oldSize = oldQueue.length;		queue = (T[]) new Object[2 * oldSize]; // make queue refer to a new, empty, bigger array		for (int index = 0; index < oldSize - 1; index++) {			queue[index] = oldQueue[frontIndex];			frontIndex = (frontIndex + 1) % oldSize;		} 		frontIndex = 0;		backIndex = oldSize - 2;	} } 