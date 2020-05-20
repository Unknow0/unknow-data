package unknow.common.data;

import java.util.Arrays;

/**
 * Map with enum as key and float as value
 * 
 * @author unknow
 *
 * @param <K> the enum type
 */
public class EnumFloatMap<K extends Enum<?>> {
	/** the default value to use */
	private float defaultValue;
	/** all the value in this map (index is K.ordinal()) */
	private float[] val;

	/**
	 * create an empty map
	 * 
	 * @param clazz        the enum class
	 * @param defaultValue the default value
	 */
	public EnumFloatMap(Class<K> clazz, float defaultValue) {
		K[] c = clazz.getEnumConstants();
		this.defaultValue = defaultValue;
		val = new float[c.length];
		Arrays.fill(val, defaultValue);
	}

	/**
	 * put a value
	 * 
	 * @param k the key
	 * @param v the value
	 */
	public void set(K k, float v) {
		val[k.ordinal()] = v;
	}

	/**
	 * add a value to the key
	 * 
	 * @param k the key
	 * @param v the value to add
	 */
	public void add(K k, float v) {
		val[k.ordinal()] += v;
	}

	/**
	 * get the value of a key
	 * 
	 * @param k the key
	 * @return the value
	 */
	public float get(K k) {
		return val[k.ordinal()];
	}

	/**
	 * clear this map, replace all value with the default one
	 */
	public void clear() {
		Arrays.fill(val, defaultValue);
	}
}
