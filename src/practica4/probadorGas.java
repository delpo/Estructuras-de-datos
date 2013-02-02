package practica4;

import java.util.LinkedList;
import java.util.Scanner;

public class probadorGas {

	static int menu() {
		System.out.print("\nElige una opción\n"
					+ "\n\t1) Imprimir grafo."
					+ "\n\t2) Invertir sentido de circulación de una tubería." 
					+ "\n\t3) Crear linkedlist en orden de aparición (OPCIONAL)." 
					+ "\n\n\t0) Salir\n");
		int op;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nNúmero: ");
			op = scanner.nextInt();
		} while (op < 0 && op > 3);
		return op;
	}
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al probador del grafo de la compañía de gas");
		//crear grafo de la compañía de gas
		EDListGraph<NodoGas,Integer> grafo = new EDListGraph<NodoGas,Integer>();
		//creamos los nodos
		NodoGas n1 = new NodoGas("1", 1, 2);
		NodoGas n2 = new NodoGas("2", 3, 5);
		NodoGas n3 = new NodoGas("3", 10, 4);
		NodoGas n4 = new NodoGas("4", 6, 2);
		NodoGas n5 = new NodoGas("5", 1, 8);
		NodoGas n6 = new NodoGas("6", 9, 7);
		//añadir nodos al grafo de la compañía de gas
		int c1 = grafo.insertNode(n1);
		int c2 = grafo.insertNode(n2);
		int c3 = grafo.insertNode(n3);
		int c4 = grafo.insertNode(n4);
		int c5 = grafo.insertNode(n5);
		int c6 = grafo.insertNode(n6);
		//añadir arcos al grafo de la compañía de gas
		EDEdge<Integer> tubo1 = new EDEdge<Integer>(c1, c2, n1.getDistance(n2));
		grafo.insertEdge(tubo1);
		EDEdge<Integer> tubo2 = new EDEdge<Integer>(c2, c5, n2.getDistance(n5));
		grafo.insertEdge(tubo2);
		EDEdge<Integer> tubo3 = new EDEdge<Integer>(c2, c4, n2.getDistance(n4));
		grafo.insertEdge(tubo3);
		EDEdge<Integer> tubo4 = new EDEdge<Integer>(c4, c3, n4.getDistance(n3));
		grafo.insertEdge(tubo4);
		EDEdge<Integer> tubo5 = new EDEdge<Integer>(c3, c6, n3.getDistance(n6));
		grafo.insertEdge(tubo5);
		EDEdge<Integer> tubo6 = new EDEdge<Integer>(c5, c6, n5.getDistance(n6));
		grafo.insertEdge(tubo6);
		
		int op;
		do{
			op = menu();
			switch(op){
			case 1:
				grafo.printGraphStructure();
				break;
			case 2:
				tubo1 = grafo.invertir(tubo1);
				tubo2 = grafo.invertir(tubo2);
				tubo3 = grafo.invertir(tubo3);
				tubo4 = grafo.invertir(tubo4);
				tubo5 = grafo.invertir(tubo5);
				tubo6 = grafo.invertir(tubo6);
				break;
			case 3:
				//opcional
				LinkedList<NodoGas> solucion = grafo.breathFirstSearch_list(0);
				System.out.println(solucion.toString());
				break;
			}
		}while(op != 0);
	}

}
