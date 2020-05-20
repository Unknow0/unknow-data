package unknow.common.data;

import java.util.Arrays;

/**
 * map with enum as key and int as value
 * 
 * @author unknow
 *
 * @param <K> the enum type
 */
public class EnumIntMap<K extends Enum<?>> {
	/** the default value to use */
	private int defaultValue;
	/** all the value in this map (index is K.ordinal()) */
	private int[] val;

	/**
	 * create an empty map
	 * 
	 * @param clazz        the enum class
	 * @param defaultValue the default value
	 */
	public EnumIntMap(Class<K> clazz, int defaultValue) {
		K[] c = clazz.getEnumConstants();
		this.defaultValue = defaultValue;
		val = new int[c.length];
		Arrays.fill(val, defaultValue);
	}

	/**
	 * put a value
	 * 
	 * @param k the key
	 * @param v the value
	 */
	public void set(K k, int v) {
		val[k.ordinal()] = v;
	}

	/**
	 * add a value to the key
	 * 
	 * @param k the key
	 * @param v the value to add
	 */
	public void add(K k, int v) {
		val[k.ordinal()] += v;
	}

	/**
	 * get the value of a key
	 * 
	 * @param k the key
	 * @return the value
	 */
	public int get(K k) {
		return val[k.ordinal()];
	}

	/**
	 * clear this map, replace all value with the default one
	 */
	public void clear() {
		Arrays.fill(val, defaultValue);
	}
}
