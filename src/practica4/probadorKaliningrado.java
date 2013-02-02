package practica4;

import java.util.Scanner;

public class probadorKaliningrado {

	static int menu() {
		System.out.print("\nElige una opción\n"
					+ "\n\t1) Imprimir grafo."
					+ "\n\t2) Recorrido por anchura." 
					+ "\n\t3) Recorrido en profundidad."
					+ "\n\t4) Mostrar nodos adyacentes."
					+ "\n\n\t0) Salir\n");
		int op;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nNúmero: ");
			op = scanner.nextInt();
		} while (op < 0 && op > 4);
		return op;
	}
	
	public static void main(String[] args) {
		System.out.println("Bienvenido al probador del grafo de Kaliningrado");
		//crear grafo de Kaliningrado
		EDListGraph<String,String> grafo = new EDListGraph<String,String>();
		//añadir nodos al grafo de Kaliningrado
		int a = grafo.insertNode("A");
		int b = grafo.insertNode("B");
		int c = grafo.insertNode("C");
		int d = grafo.insertNode("D");
		//añadir arcos al grafo de Kaliningrado
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("A"), grafo.getNodeIndex("B")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("B"), grafo.getNodeIndex("A")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("A"), grafo.getNodeIndex("B")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("B"), grafo.getNodeIndex("A")));
		
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("C"), grafo.getNodeIndex("B")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("B"), grafo.getNodeIndex("C")));
		
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("A"), grafo.getNodeIndex("C")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("C"), grafo.getNodeIndex("A")));
		
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("A"), grafo.getNodeIndex("D")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("D"), grafo.getNodeIndex("A")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("A"), grafo.getNodeIndex("D")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("D"), grafo.getNodeIndex("A")));
		
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("C"), grafo.getNodeIndex("D")));
		grafo.insertEdge(new EDEdge<String>(grafo.getNodeIndex("D"), grafo.getNodeIndex("C")));
		
		int op;
		do{
			op = menu();
			switch(op){
			case 1:
				grafo.printGraphStructure();
				break;
			case 2:
				int[] solucion = grafo.breathFirstSearch(0);
				for(int i = 0; i < solucion.length; i++)
					System.out.println(solucion[i]);
				break;
			case 3:
				int[] solucion2 = grafo.depthFirstSearch(0);
				for(int i = 0; i < solucion2.length; i++)
					System.out.println(solucion2[i]);
				break;
			case 4:
				int[] solucion3 = grafo.getAdyacentNodes(a);
				for(int j = 0; j < solucion3.length; j++)
					System.out.println(solucion3[j]);
				int[] solucion4 = grafo.getAdyacentNodes(b);
				for(int j = 0; j < solucion4.length; j++)
					System.out.println(solucion4[j]);
				int[] solucion5 = grafo.getAdyacentNodes(c);
				for(int j = 0; j < solucion5.length; j++)
					System.out.println(solucion5[j]);
				int[] solucion6 = grafo.getAdyacentNodes(d);
				for(int j = 0; j < solucion6.length; j++)
					System.out.println(solucion6[j]);
				break;
			}
		}while(op != 0);
	}

}
