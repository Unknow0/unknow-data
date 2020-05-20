package unknow.common.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class IntArrayListTest {

	@Test
	public void baseMethod() {
		IntArrayList set = new IntArrayList(2);
		assertEquals(0, set.size());

		assertFalse(set.contains(1));

		set.add(1);
		assertTrue(set.contains(1));
		assertEquals(1, set.size());
		set.add(1);
		assertEquals(2, set.size());

		set.add(2, 3, 4, 5);
		assertEquals(6, set.size());
	}

	@Test
	public void remove() {
		IntArrayList set = new IntArrayList(1, 2, 3, 4, 5);

		assertTrue(set.remove(5));
		assertFalse(set.remove(5));
		assertEquals(4, set.size());
	}

	@Test
	public void iterator() {
		IntArrayList set = new IntArrayList(1, 2, 3, 4, 5);

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
