package practica4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** Implementation of interface Graph using adjacency lists
 * @param <T> The base type of the nodes
 * @param <W> The base type of the weights of the edges
 */
public class EDListGraph<T,W> implements EDGraph<T,W> {
	private static int DEFAULT_SIZE = 20;
	
	@SuppressWarnings("hiding")
	private class Node<T> {
		T data;
		List< EDEdge<W> > lEdges;
		
		Node (T data) {
			this.data = data;
			this.lEdges = new LinkedList< EDEdge<W> >();
		}
	}
	
	// Private data
	private int size; //real number of nodes
	private Node<T>[] nodes;
	
	/** Constructor
	 * @param direct <code>true</code> for directed edges; 
	 * <code>false</code> for non directed edges.
	 */
	@SuppressWarnings("unchecked")
	public EDListGraph() {
		size = 0;
		nodes =  new Node[DEFAULT_SIZE];
	}
	
	@SuppressWarnings("unchecked")
	public EDListGraph(int nv) {
		size = 0;
		nodes = new Node[nv];
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		Node<T>[] aux = new Node[nodes.length*2];
		for (int i=0; i < nodes.length; i++) aux[i] = nodes[i];
		nodes = aux;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public int insertNode(T item) {
		if (size == nodes.length) resize();
			
		int i = 0;
		while (nodes[i] != null) i++;
			
		nodes[i] = new Node<T>(item);
		size++;
		
		return i;
	}
	
	@Override
	public int getNodeIndex(T item) {
		int i = 0;
		while(i < nodes.length) 
			if (nodes[i] != null && nodes[i].data.equals(item)) return i;
			else i++;
		
		return -1;
	}

	@Override
	public T getNodeValue(int index) {
		if (nodes[index] != null) return nodes[index].data;
		
		return null;
	}
	
	@Override
	public boolean insertEdge(EDEdge<W> edge) {
		if (nodes[edge.getSource()]!=null && nodes[edge.getDest()]!=null) {
			if (!nodes[edge.getSource()].lEdges.contains(edge))
				nodes[edge.getSource()].lEdges.add(edge);
				
			return true;
		}
		return false;
	}

	@Override
	public EDEdge<W> getEdge(int source, int dest) {	
		if (nodes[source] == null) return null;
		
		for (EDEdge<W> edge: nodes[source].lEdges)
			if (edge.getDest() == dest) return edge;
		
		return null;
	}
	
	@Override
	public EDEdge<W> removeEdge(int source, int dest) {
		if (nodes[source]==null || nodes[dest]==null) return null;
		
		EDEdge<W> edge = new EDEdge<W>(source, dest);
		int i = nodes[source].lEdges.indexOf(edge);
		if (i != -1) {
			edge = nodes[source].lEdges.remove(i);
			return edge;
		}
		return null;	
	}

	@Override
	public T removeNode(int index) {
		if (nodes[index]!=null) {
			nodes[index].lEdges.clear();
			for (int i=0; i<nodes.length;i++)
				if (i!=index && nodes[i]!=null)
					nodes[i].lEdges.remove(new EDEdge<T>(i,index));

			T ret = nodes[index].data;
			nodes[index] = null;
			size--;
			return ret;
		}
		return null;
	}
	
	@Override
	public int[] getAdyacentNodes(int index) {
		if (nodes[index] == null)
			return new int[0];
		
		int ret[] = new int[nodes[index].lEdges.size()];
		int i=0;
		for (EDEdge<W> ed: nodes[index].lEdges) {
			ret[i] = ed.getDest();
			i++;
		}
		
		return ret;
	}
	
	@Override
	public int[] breathFirstSearch (int start) {		
		Queue<Integer> qu = new LinkedList<Integer>();
		//Declare an array 'parent' and initialize its elements to -1
		int [] parent = new int[nodes.length];
		for (int i=0; i<parent.length; i++)
			parent[i]=-1;
		
		if (nodes[start] != null) {
			parent[start] = start;
			qu.add(start);
		}
		
		while (!qu.isEmpty()) {
			int current = qu.remove(); 
			for (EDEdge<W> edge: nodes[current].lEdges) {
				int neighbor = edge.getDest();
				if (parent[neighbor] == -1) {
					parent[neighbor] = current;
					qu.add(neighbor);
				}
			} 	
		}
		return parent;
	}
	
	private void depthFirstSearch (int current, int[] parent) {
		for (EDEdge<W> edge: nodes[current].lEdges)
			if (parent[edge.getDest()] == -1) {
				parent[edge.getDest()] = current;
				depthFirstSearch(edge.getDest(), parent);
			}
	}
	
	@Override
	public int[] depthFirstSearch(int start) {
		int [] parent = new int[nodes.length];
		for (int i=0; i<parent.length; i++)
			parent[i]=-1;
		
		if (nodes[start]!= null) {
			parent[start] = start;
			depthFirstSearch(start, parent);
		}
		
		return parent;
	}
	
	public void printGraphStructure() {
		System.out.println("Vector size= " + nodes.length);
		System.out.println("Nodes " + size);
		for (int i=0; i<nodes.length; i++) {
			System.out.print("pos "+i+": ");
			if (nodes[i] == null) {
				System.out.println("null");
			}
			else {
				System.out.print(nodes[i].data+" -- ");
				Iterator<EDEdge<W>> it = nodes[i].lEdges.listIterator();
				while (it.hasNext()) {
					EDEdge<W> e = it.next();
					System.out.print("("+e.getSource()+","+e.getDest()+")->"+" Distance: "+e.getWeight() );
				}
				System.out.println();
			}
		}
	}
	
	public EDEdge<W> invertir(EDEdge<W> tubo){
		removeEdge(tubo.getSource(), tubo.getDest());
		EDEdge<W> tubo1 = new EDEdge<W>(tubo.getDest(), tubo.getSource(), tubo.getWeight());
		insertEdge(tubo1);
		return tubo1;
	}
	
	public LinkedList<NodoGas> breathFirstSearch_list (int start) {
		LinkedList<NodoGas> lista = new LinkedList<NodoGas>();
		Queue<Integer> qu = new LinkedList<Integer>();
		//Declare an array 'parent' and initialize its elements to -1
		int [] parent = new int[nodes.length];
		for (int i=0; i<parent.length; i++)
			parent[i]=-1;
		
		if (nodes[start] != null) {
			parent[start] = start;
			qu.add(start);
		}
		
		while (!qu.isEmpty()) {
			int current = qu.remove(); 
			for (EDEdge<W> edge: nodes[current].lEdges) {
				int neighbor = edge.getDest();
				if (parent[neighbor] == -1) {
					parent[neighbor] = current;
					qu.add(neighbor);
					lista.add((NodoGas) getNodeValue(current));
				}
			} 	
		}
		return lista;
	}
}
