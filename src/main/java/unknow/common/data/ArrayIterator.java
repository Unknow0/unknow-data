package unknow.common.data;

import java.util.Iterator;

/**
 * Iterator over an array
 * 
 * @author unknow
 * @param <T> array content type
 */
public class ArrayIterator<T> implements Iterator<T> {
	private T[] array;
	private int start;
	private int end;

	/**
	 * create new ArrayIterator
	 * 
	 * @param array array to iterate on
	 */
	public ArrayIterator(T[] array) {
		this(array, 0, array.length);
	}

	/**
	 * create new ArrayIterator
	 * 
	 * @param array array to iterate on
	 * @param start start of the iteration (inclusive)
	 * @param end   end of iteration (exclusive)
	 */
	public ArrayIterator(T[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean hasNext() {
		return start < end;
	}

	@Override
	public T next() {
		return array[start++];
	}
}
