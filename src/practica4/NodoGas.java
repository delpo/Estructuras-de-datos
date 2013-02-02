package practica4;

public class NodoGas {
	String nombre;
	int x;
	int y;
	
	public NodoGas(String nombre, int x, int y){
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	public String toString(){
		return "\nDATOS:\nNombre: "+nombre+"\nX: "+x+"\nY: "+y;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDistance(NodoGas nodo){
		int x1 = nodo.getX();
		int y1 = nodo.getY();
		int x2 = this.getX();
		int y2 = this.getY();
		return (int) Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
}