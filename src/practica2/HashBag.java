package practica2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashBag<T> implements Bag<T> {
	static private double DEFAULT_LOAD_FACTOR = 0.75;
	static private int DEFAULT_CAPACITY = 7;
	
	private Par<T,Integer>[] table;
	private double loadFactor = DEFAULT_LOAD_FACTOR;
	private int size = 0;
	private int used = 0;
	
	@SuppressWarnings("unchecked")
	public HashBag() {
		table = new Par[DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public HashBag(int initialCapacity) {
		table = new Par[initialCapacity];
	}
	
	
	@SuppressWarnings("unchecked")
	public HashBag(int initialCapacity, double loadFactor) {
		table =  new Par[initialCapacity];
		this.loadFactor = loadFactor;
	}
	
	private boolean nullEntry(Par<T,Integer> entry) {
		return entry.getKey() == null && entry.getValue() == null;
	}
	
	@SuppressWarnings("unchecked")
	private boolean rehash() {
		if (used > table.length * loadFactor) {
			Par<T, Integer> oldTable[] = table;
			table = new Par[table.length*2];
			size = 0;
			used = 0;

			for (Par<T, Integer> entry: oldTable) {
				if (entry != null)
					addEntry(entry);
			}	
			System.out.println("Rehash hecho.");
			return true;
		}
		return false;
	}
	
	
	public Par<T, Integer> addEntry(Par<T,Integer> entry) {		
		int pos = Math.abs(entry.getKey().hashCode() % table.length); 
		int newPos = -1;
		boolean found = false;
		
		while (!found && table[pos] != null) {
			if (nullEntry(table[pos])) {
				if (newPos == -1) newPos = pos;
				pos = Math.abs((pos +1) % table.length); 
			} 
			else if (table[pos].getKey().equals(entry.getKey())) 
				found = true;
			else
				pos = Math.abs((pos +1) % table.length); 
		}
		
		if (newPos == -1 || found) newPos = pos;
	
		if (table[newPos] == null) used++;
		
		Par<T, Integer> old = found ? table[newPos] : null;
		table[newPos] = entry;
		
		if (!found) size++;
	
		rehash();
		return old;
	}
	
	private int indexEntry(T key) {
		int pos = key.hashCode()%table.length;
		
		while (table[pos] != null) {
			if (!nullEntry(table[pos]) && table[pos].getKey().equals(key)) {
				return pos;
			}
			pos = (pos + 1) % table.length; 
		}
		
		return -1;
		
	}

	public Par<T, Integer> getEntry(T key) {
		int pos = indexEntry(key);
		
		if (pos == -1)
			return null;
		else
			return table[pos];
	}

	public Par<T, Integer> removeEntry(T key) {
		int pos = indexEntry(key);
		
		if (pos == -1)
			return null;
		else {
			Par<T, Integer>  retEntry = table[pos];
			table[pos] = new Par<T, Integer>();
			size--;
			return retEntry;
		}
			
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public String toString() {
		String retValue = "";
		
		for (int i=0; i < table.length; i++) {
			if (table[i] != null) 
				retValue += String.format("[%3d]: %s\n", i, table[i].toString());
		}
		retValue += String.format("Used: %d out of %d (%.2f %%)\n", used, table.length, ((float)used/(float)table.length) * 100.0);
		return retValue;
	}
	
	// Iterable interface implementation
	public class HashBagIterator implements Iterator<Par<T, Integer>> {
		private int index;
		private int last;
		
		public HashBagIterator() {
			index = 0;
			last = -1;
			
			while (index < table.length && (table[index] == null || nullEntry(table[index])))
				index++;			
		}

		public boolean hasNext() {
			return index != table.length;
		}

		public Par<T, Integer> next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException();
			
			last = index;
			index++;
			
			while (index < table.length && (table[index] == null || nullEntry(table[index])))
				index++;
				
			return table[last];
		}

		public void remove(){
			if (last == -1)
				throw new IllegalStateException();
			
			table[last] = new Par<T, Integer>();
			last = -1;
			size--;
		}
		
		public String toString() {
			String retValue = "";
			
			if (index == table.length)
				retValue += "Next [<>]";
			else 
				retValue += String.format("Next [I: %d %s]", index, table[index].toString()); 
			
			if (last == -1)
				retValue += " Last [<>]";
			else 
				retValue += String.format(" Last [I: %d %s]", index, table[last].toString()); 
				
			return retValue;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
		return (Iterator<T>) new HashBagIterator();
	}

	public void clear() {
		for(int i = 0; i < table.length; i++)
			table[i] = null;
		
		used = size = 0;
		
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<?> iterator = arg0.iterator();
		if(arg0.isEmpty()) return false;
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_añadir = (Par<T, Integer>) iterator.next();
			this.add(cosa_a_añadir.getKey());
		}
		return true;
	}

	@Override
	public boolean contains(Object arg0) {
		Iterator<T> iterator = iterator();
		if(isEmpty()) return false;
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_eliminar = (Par<T, Integer>) iterator.next();
			if(cosa_a_eliminar.getKey().equals(arg0)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> iterator = arg0.iterator();
		if(isEmpty() && arg0.size() != 0) return false;
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_comprobar = (Par<T, Integer>) iterator.next();
			if(!this.contains(cosa_a_comprobar.getKey())){
				return false;
			}
			
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		Boolean elimino_alguno = false;
		Iterator<?> iterator = arg0.iterator();
		if(isEmpty()) return false;
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_eliminar = (Par<T, Integer>) iterator.next();
			if(this.remove(cosa_a_eliminar.getKey())){
				elimino_alguno = true;
			}
			
		}
		return elimino_alguno;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		Boolean elimino_alguno = false;
		Iterator<T> iterator = iterator();
		if(isEmpty()) return false;
		if(arg0.size() == 0){
			clear();
		}
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_eliminar = (Par<T, Integer>) iterator.next();
			if(!arg0.contains(cosa_a_eliminar.getKey())){
				if(this.remove(cosa_a_eliminar.getKey())){
					elimino_alguno = true;
				}
			}
		}
		return elimino_alguno;
	}

	@Override
	public Object[] toArray() {
		try{
			throw new UnsupportedOperationException();
		}
		catch(UnsupportedOperationException UnsupportedOperationException){
		}
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		try{
			throw new UnsupportedOperationException();
		}
		catch(UnsupportedOperationException UnsupportedOperationException){
		}
		return null;
	}

	@Override
	public int getCount(T element) {
		Iterator<T> iterator = iterator();
		if(isEmpty()) System.out.println("La lista está vacía.");
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_comprobar = (Par<T, Integer>) iterator.next();
			if(cosa_a_comprobar.getKey().equals(element) && cosa_a_comprobar.getValue() != 0){
				return cosa_a_comprobar.getValue();
			}else if(cosa_a_comprobar.getKey().equals(element) && cosa_a_comprobar.getValue() == 0){
				return 0;
			}
		}
		return -1;
	}

	@Override
	public int uniqueSize() {
		return used;
	}

	@Override
	public boolean setCount(T element, int contador) throws IllegalArgumentException {
		try{
			if(contador < 0){
				throw new IllegalArgumentException();
			}
			Iterator<T> iterator = iterator();
			if(isEmpty()) System.out.println("La lista está vacía.");
			while (iterator.hasNext()) {
				@SuppressWarnings("unchecked")
				Par<T, Integer> cosa_a_comprobar = (Par<T, Integer>) iterator.next();
				if(cosa_a_comprobar.getKey().equals(element)){
					if(cosa_a_comprobar.getValue() > contador && contador != 0){
						size = size - (cosa_a_comprobar.getValue() - contador);
					}else if(cosa_a_comprobar.getValue() < contador){
						size = size + (contador - cosa_a_comprobar.getValue());
					}else{ //contador = 0
						size = size - (cosa_a_comprobar.getValue() - contador);
						used--;
					}
					cosa_a_comprobar.setValue(contador);
					return true;
				}
			}
			return false;
		}
		
		catch(IllegalArgumentException IllegalArgumentException){
		}
		return false;
	}

	@Override
	public Set<T> elementSet() {
		Set<T> diferentes = new HashSet<T>();
		Iterator<T> iterator = iterator();
		if(isEmpty()) return (Set<T>) diferentes;
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_comprobar = (Par<T, Integer>) iterator.next();
			diferentes.add(cosa_a_comprobar.getKey());
		}
		return (Set<T>) diferentes;
	}

	@Override
	public boolean add(T element) {
		int pos = Math.abs(element.hashCode() % table.length); 
		int newPos = -1;
		boolean found = false;
		
		while (!found && table[pos] != null) {
			if (nullEntry(table[pos])) {
				if (newPos == -1) newPos = pos;
				pos = (pos +1) % table.length; 
			} 
			else if (table[pos].getKey().equals(element)) {
				found = true;
				if(table[pos].getValue() == 0) used++; //si existió anteriormente, vuelve a aumentar used
			}else
				pos = (pos +1) % table.length; 
		}
		
		if (newPos == -1 || found) newPos = pos;
	
		if (table[newPos] == null) used++;
		
		Par<T, Integer> elemento_a_añadir = new Par<T, Integer>(element, 1);
		table[newPos] = elemento_a_añadir;
		if(found){
			table[newPos].setValue(table[newPos].getValue()+1);
		}
		size++;
		if(used > table.length * loadFactor) rehash();
		System.out.println("Tamaño de tabla: "+table.length);
		return found;
	}

	@Override
	public boolean remove(Object element) {
		Iterator<T> iterator = iterator();
		if(isEmpty()) System.out.println("No hay elementos a mostrar.");
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Par<T, Integer> cosa_a_eliminar = (Par<T, Integer>) iterator.next();
			if(cosa_a_eliminar.getKey().equals(element) && cosa_a_eliminar.getValue() != 0){
				if(cosa_a_eliminar.getValue() - 1 > 0){
					size--;
				}else{ //contador se hará = 0
					size--;
					used--;
				}
				cosa_a_eliminar.setValue(cosa_a_eliminar.getValue()-1);
				return true;
			}else if(cosa_a_eliminar.getKey().equals(element) && cosa_a_eliminar.getValue() == 0){
				System.out.println("El elemento ya se eliminó.");
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void mostrarContenido() {
		Iterator<T> iterator = iterator();
		if(isEmpty()) System.out.println("No hay elementos a mostrar.");
		while (iterator.hasNext()) {
			Par<T, Integer> cosa_a_mostrar = (Par<T, Integer>) iterator.next();
			if(cosa_a_mostrar.getValue() != 0){
				System.out.println("Elemento: "+cosa_a_mostrar.getKey());
				System.out.println(cosa_a_mostrar.getValue());
			}
		}
	}
	
	public int hashCode() throws UnsupportedOperationException{
		try{
			throw new UnsupportedOperationException();
		}
		catch(UnsupportedOperationException UnsupportedOperationException){
		}
		return 0;
	}

}
