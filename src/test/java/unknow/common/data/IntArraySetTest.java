package unknow.common.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class IntArraySetTest {

	@Test
	public void baseMethod() {
		IntArraySet set = new IntArraySet(2);
		assertEquals(0, set.size());

		assertFalse(set.contains(1));

		assertTrue(set.add(1));
		assertTrue(set.contains(1));
		assertFalse(set.add(1));
		assertEquals(1, set.size());

		assertTrue(set.add(2, 3, 4, 5));
		assertFalse(set.add(2, 3, 4, 5));
		assertEquals(5, set.size());
		assertTrue(set.add(5, 6));
		assertEquals(6, set.size());

		IntArraySet temp = new IntArraySet(7, 8);
		assertTrue(set.addAll(temp));
		assertFalse(set.addAll(temp));
		assertEquals(8, set.size());

		temp.add(9);
		assertTrue(set.addAll(temp));
		assertEquals(9, set.size());
	}

	@Test
	public void remove() {
		IntArraySet set = new IntArraySet(1, 2, 3, 4, 5);

		assertTrue(set.remove(5));
		assertFalse(set.remove(5));
		assertEquals(4, set.size());
	}

	@Test
	public void iterator() {
		IntArraySet set = new IntArraySet(1, 2, 3, 4, 5);

		int i = 1;
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			assertEquals(i++, (int) it.next());
		}
		assertEquals(6, i);
		it.remove();

		it = set.iterator();
		it.next();
		it.remove();
		assertEquals(2, (int) it.next());
	}
}
