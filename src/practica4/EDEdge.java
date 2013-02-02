package practica4;

public class EDEdge<T> {
	private int dest;  //destination vertex of the edge
	private int source;   //source vertex of the edge
	private T weight;  //weigth of the edge, by default 1
	
	//constructors
	public EDEdge(int source, int dest) {
		this.source = source;
		this.dest = dest;
		this.weight = null;
	}
	
	public EDEdge(int source, int dest, T weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	
	//methods
	//two edges are equal if source and dest are equal. The weight is not considered
	public boolean equals(Object o) {
		if (o.getClass() != this.getClass()) return false;
		EDEdge<T> localEdge = (EDEdge<T>) o;
		return (this.source == localEdge.source && this.dest == localEdge.dest);
	}
	
	//returns destination vertex
	public int getDest() {
		return this.dest;
	}
	
	//returns source vertex
	public int getSource() {
		return this.source;
	}
	
	//returns weight
	public T getWeight() {
		return this.weight;
	}
	
	public String toString() {
		String s = "["+this.source+"->"+this.dest+":"+this.weight+"]";
		return s;		
	}
}
