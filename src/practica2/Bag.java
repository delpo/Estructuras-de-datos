package practica2;

import java.util.Set;

public interface Bag <T> extends Set <T> {
	//creo estos
	public int getCount ( T element );
	public int uniqueSize ();
	public boolean setCount ( T element , int count ) throws IllegalArgumentException;
	public Set <T > elementSet ();
	//adapto estos de la interfaz Set
	public boolean add(T element);
	public boolean remove(Object element);
	public int size();
	//public boolean removeAll(Collection<T> c);
}