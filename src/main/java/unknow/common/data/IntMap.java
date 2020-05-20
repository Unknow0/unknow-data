/**
 * 
 */
package unknow.common.data;

import java.util.Arrays;

/**
 * @author unknow
 */
public class IntMap<V> {
	private Entry<V>[] table;
	private int len;

	public IntMap() {
		this(16);
	}

	@SuppressWarnings("unchecked")
	public IntMap(int len) {
		table = new Entry[len];
	}

	public V get(int k) {
		int i = Arrays.binarySearch(table, 0, len, k);
		if (i < 0)
			return null;
		return table[i].v;
	}

	/**
	 * put a new key value mapping
	 * 
	 * @param k the key to add
	 * @param v the new value
	 * @return the old value of k
	 */
	public V put(int k, V v) {
		int i = Arrays.binarySearch(table, 0, len, k);
		if (i < 0) { // insert
			ensureSize(len + 1);
			i = -i - 1;
			System.arraycopy(table, i, table, i + 1, len - i);
			len++;
			table[i] = new Entry<>(k, v);
			return null;
		}
		Entry<V> e = table[i];
		if (e == null) {
			table[i] = new Entry<>(k, v);
			return null;
		}
		V old = e.v;
		e.v = v;
		return old;
	}

	/**
	 * check if a key exists
	 * 
	 * @param k the key to check
	 * @return true if the key was found
	 */
	public boolean containsKey(int k) {
		return Arrays.binarySearch(table, 0, len, k) >= 0;
	}

	/**
	 * remove a key from the map
	 * 
	 * @param k key to remove
	 * @return true if the key was removed and true if the key wasn't there
	 */
	public boolean remove(int k) {
		int i = Arrays.binarySearch(table, 0, len, k);
		if (i < 0)
			return false;
		len--;
		System.arraycopy(table, i + 1, table, i, len - i);
		return true;
	}

	/**
	 * ensure that the internal array can contain at least len object
	 * 
	 * @param len the minial size of internal array
	 */
	public void ensureSize(int len) {
		if (table.length < len)
			table = Arrays.copyOf(table, len);
	}

	/**
	 * the size of this map
	 * 
	 * @return the size
	 */
	public int size() {
		return len;
	}

	private static class Entry<V> implements Comparable<Object> {
		private final int k;
		private V v;

		/**
		 * create new Entry
		 * 
		 * @param k the key
		 * @param v the value
		 */
		public Entry(int k, V v) {
			this.k = k;
			this.v = v;
		}

		@Override
		public int compareTo(Object o) {
			if (o instanceof Integer)
				return k - ((Integer) o);
			if (o instanceof IntMap.Entry)
				return k - ((Entry<?>) o).k;
			throw new IllegalArgumentException();
		}
	}
}
