package practica2;

public class Par<K,V>{
	private K key;
	private V value;

	public Par() {
		this.key = null;
		this.value = null;
	}

	public Par(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public V setValue(V newValue) {
		V old = value;
		value = newValue;
		return old;
	}

	public String toString() {
		String retValue ="<";
		
		retValue += (key == null ? "" : key.toString()) + ",";
		retValue += (value == null ? "" : value.toString()) + ">";
		
		return retValue;
	}
	
	//por si acaso
	public boolean equals(Par<K, V> pair) {
		return key.equals(pair.getKey());
	}
	
	public int hashCode() {
		return key.hashCode();
	}
	
}
