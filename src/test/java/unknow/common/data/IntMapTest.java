package unknow.common.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class IntMapTest {
	private static final Object OBJECT = new Object();

	@Test
	public void baseMethod() {
		IntMap map = new IntMap(2);
		assertEquals(0, map.size());

		assertFalse(map.containsKey(1));

		assertNull(map.put(1, OBJECT));
		assertTrue(map.containsKey(1));
		assertEquals(OBJECT, map.put(1, OBJECT));
		assertEquals(1, map.size());

		map.put(2, OBJECT);
		map.put(3, OBJECT);
		map.put(4, OBJECT);
		map.put(5, OBJECT);
//		assertTrue(map.addAll(Arrays.asList(2, 3, 4, 5)));
//		assertFalse(map.addAll(Arrays.asList(2, 3, 4, 5)));
		assertEquals(5, map.size());
	}

	@Test
	public void remove() {
		IntMap map = new IntMap();
		map.put(1, OBJECT);
		map.put(2, OBJECT);
		map.put(3, OBJECT);
		map.put(4, OBJECT);
		map.put(5, OBJECT);

		assertTrue(map.remove(5));
		assertFalse(map.remove(5));
		assertEquals(4, map.size());
	}

//	@Test
//	public void iterator() {
//		IntMap set = new IntMap(new int[] { 1, 2, 3, 4, 5 });
//
//		int i = 1;
//		Iterator<Integer> it = set.iterator();
//		while (it.hasNext()) {
//			assertEquals(i++, (int) it.next());
//		}
//		assertEquals(6, i);
//		it.remove();
//
//		it = set.iterator();
//		it.next();
//		it.remove();
//		assertEquals(2, (int) it.next());
//	}
}
