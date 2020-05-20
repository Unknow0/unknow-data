package unknow.common.data;

import java.util.Arrays;

/**
 * an set backed by a int[]
 * 
 * @author unknow
 */
public class IntArraySet implements IntCollection {
	private int[] data;
	private int len;

	/**
	 * create new IntArraySet
	 */
	public IntArraySet() {
		this(0);
	}

	/**
	 * create new IntArraySet
	 * 
	 * @param data the start value
	 */
	public IntArraySet(int... data) {
		this(data, 0, data.length);
	}

	/**
	 * create new IntArraySet
	 * 
	 * @param data the start value
	 * @param off  start offset to compy
	 * @param len  length of start value
	 */
	public IntArraySet(int[] data, int off, int len) {
		this.data = Arrays.copyOfRange(data, off, len);
		this.len = this.data.length;
		Arrays.sort(this.data);
	}

	/**
	 * create new IntArraySet
	 * 
	 * @param initSize initial array size
	 */
	public IntArraySet(int initSize) {
		data = new int[initSize];
		len = 0;
	}

	/**
	 * ensure that the internal array as enough space for l
	 * 
	 * @param l minimal size of internal array
	 */
	public void ensureCapacity(int l) {
		if (l < data.length)
			return;
		data = Arrays.copyOf(data, l);
	}

	@Override
	public boolean add(int i) {
		if (contains(i))
			return false;
		ensureCapacity(len + 1);
		data[len++] = i;
		Arrays.sort(data, 0, len);
		return true;
	}

	@Override
	public boolean add(int... t) {
		boolean b = false;
		for (int i = 0; i < t.length; i++)
			b |= add(t[i]);
		return b;
	}

	@Override
	public boolean addAll(IntCollection c) {
		if (containsAll(c))
			return false;
		int[] temp = new int[c.size()];
		int l = 0;
		IntIterator it = c.iterator();
		while (it.hasNext()) {
			int i = it.nextInt();
			if (!contains(i))
				temp[l++] = i;
		}
		ensureCapacity(len + l);
		System.arraycopy(temp, 0, data, len, l);
		len += l;
		Arrays.sort(data, 0, len);
		return true;
	}

	@Override
	public void clear() {
		len = 0;
	}

	@Override
	public boolean contains(int i) {
		return Arrays.binarySearch(data, 0, len, i) >= 0;
	}

	@Override
	public boolean containsAll(IntCollection col) {
		IntIterator it = col.iterator();
		while (it.hasNext()) {
			if (!contains(it.nextInt()))
				return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return len == 0;
	}

	@Override
	public IntIterator iterator() {
		return new It();
	}

	@Override
	public boolean remove(int v) {
		int i = Arrays.binarySearch(data, 0, len, v);
		if (i < 0)
			return false;
		System.arraycopy(data, i + 1, data, i, len - i - 1);
		len--;
		return true;
	}

	@Override
	public int size() {
		return len;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName());
		sb.append(" [");
		IntIterator it = iterator();
		while (it.hasNext()) {
			sb.append(it.nextInt());
			if (it.hasNext())
				sb.append(',');
		}
		return sb.append(']').toString();
	}

	private class It implements IntIterator {
		private int i;

		@Override
		public boolean hasNext() {
			return i < len;
		}

		@Override
		public int nextInt() {
			return data[i++];
		}

		@Override
		public void remove() {
			System.arraycopy(data, i, data, i - 1, len - i);
			len--;
			i--;
		}
	}
}
