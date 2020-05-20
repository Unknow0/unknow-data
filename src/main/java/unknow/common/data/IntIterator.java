/**
 * 
 */
package unknow.common.data;

import java.util.Iterator;

/**
 * @author unknow
 */
public interface IntIterator extends Iterator<Integer> {

	/**
	 * @return the next int
	 */
	int nextInt();

	@Override
	default Integer next() {
		return nextInt();
	}
}
