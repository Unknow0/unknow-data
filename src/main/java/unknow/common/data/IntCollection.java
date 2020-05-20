/**
 * 
 */
package unknow.common.data;

/**
 * @author unknow
 */
public interface IntCollection extends Iterable<Integer> {
	/**
	 * Returns the number of elements in this collection. If this collection contains more than <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this collection
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this collection contains no elements.
	 *
	 * @return <tt>true</tt> if this collection contains no elements
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this collection contains the specified element.
	 *
	 * @param i element whose presence in this collection is to be tested
	 * @return <tt>true</tt> if this collection contains the specified element
	 */
	boolean contains(int i);

	/**
	 * Returns an iterator over the elements in this collection. There are no guarantees concerning the order in which the elements are returned (unless this collection is an instance of some class that provides a guarantee).
	 *
	 * @return an <tt>IntIterator</tt> over the elements in this collection
	 */
	@Override
	IntIterator iterator();

	// Modification Operations

	/**
	 * Ensures that this collection contains the specified element (optional operation). Returns <tt>true</tt> if this collection changed as a result of the call. (Returns <tt>false</tt> if this collection does not permit duplicates and already contains the specified element.)
	 * <p>
	 *
	 * Collections that support this operation may place limitations on what elements may be added to this collection. Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
	 * <p>
	 *
	 * If a collection refuses to add a particular element for any reason other than that it already contains the element, it <i>must</i> throw an exception (rather than returning <tt>false</tt>). This preserves the invariant that a collection always contains the specified element after this call returns.
	 *
	 * @param e element whose presence in this collection is to be ensured
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>add</tt> operation is not supported by this collection
	 * @throws IllegalArgumentException      if some property of the element prevents it from being added to this collection
	 * @throws IllegalStateException         if the element cannot be added at this time due to insertion restrictions
	 */
	boolean add(int e);

	/**
	 * Ensures that this collection contains the specified element (optional operation). Returns <tt>true</tt> if this collection changed as a result of the call. (Returns <tt>false</tt> if this collection does not permit duplicates and already contains the specified element.)
	 * <p>
	 *
	 * Collections that support this operation may place limitations on what elements may be added to this collection. Collection classes should clearly specify in their documentation any restrictions on what elements may be added.
	 * <p>
	 *
	 * If a collection refuses to add a particular element for any reason other than that it already contains the element, it <i>must</i> throw an exception (rather than returning <tt>false</tt>). This preserves the invariant that a collection always contains the specified element after this call returns.
	 *
	 * @param e element whose presence in this collection is to be ensured
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>add</tt> operation is not supported by this collection
	 * @throws IllegalArgumentException      if some property of the element prevents it from being added to this collection
	 * @throws IllegalStateException         if the element cannot be added at this time due to insertion restrictions
	 * @see #add(int)
	 */
	boolean add(int... e);

	/**
	 * Removes a single instance of the specified element from this collection, if it is present (optional operation). Returns <tt>true</tt> if this collection contained the specified element (or equivalently, if this collection changed as a result of the call).
	 *
	 * @param o element to be removed from this collection, if present
	 * @return <tt>true</tt> if an element was removed as a result of this call
	 * @throws UnsupportedOperationException if the <tt>remove</tt> operation is not supported by this collection
	 */
	boolean remove(int o);

	// Bulk Operations

	/**
	 * Returns <tt>true</tt> if this collection contains all of the elements in the specified collection.
	 *
	 * @param c collection to be checked for containment in this collection
	 * @return <tt>true</tt> if this collection contains all of the elements in the specified collection
	 * @throws NullPointerException if the specified collection is null.
	 * @see #contains(int)
	 */
	boolean containsAll(IntCollection c);

	/**
	 * Adds all of the elements in the specified collection to this collection (optional operation). The behavior of this operation is undefined if the specified collection is modified while the operation is in progress. (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
	 *
	 * @param c collection containing elements to be added to this collection
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is not supported by this collection
	 * @throws NullPointerException          if the specified collection is null
	 * @throws IllegalArgumentException      if some property of an element of the specified collection prevents it from being added to this collection
	 * @throws IllegalStateException         if not all the elements can be added at this time due to insertion restrictions
	 * @see #add(int)
	 */
	boolean addAll(IntCollection c);

	/**
	 * Removes all of this collection's elements that are also contained in the specified collection (optional operation). After this call returns, this collection will contain no elements in common with the specified collection.
	 *
	 * @param c collection containing elements to be removed from this collection
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>removeAll</tt> method is not supported by this collection
	 * @throws NullPointerException          if the specified collection is null
	 * @see #remove(int)
	 * @see #contains(int)
	 */
	default boolean removeAll(IntCollection c) {
		boolean b = false;
		IntIterator it = c.iterator();
		while (it.hasNext())
			b |= remove(it.nextInt());
		return b;
	}

	/**
	 * Retains only the elements in this collection that are contained in the specified collection (optional operation). In other words, removes from this collection all of its elements that are not contained in the specified collection.
	 *
	 * @param c collection containing elements to be retained in this collection
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation is not supported by this collection
	 * @throws NullPointerException          if the specified collection is null
	 * @see #remove(int)
	 * @see #contains(int)
	 */
	default boolean retainAll(IntCollection c) {
		IntIterator it = iterator();
		boolean b = false;
		while (it.hasNext()) {
			if (!c.contains(it.nextInt())) {
				it.remove();
				b = true;
			}
		}
		return b;
	}

	/**
	 * Removes all of the elements from this collection (optional operation). The collection will be empty after this method returns.
	 *
	 * @throws UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this collection
	 */
	void clear();
}
