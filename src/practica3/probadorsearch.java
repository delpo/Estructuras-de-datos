package practica3;

import java.util.Scanner;

public class probadorsearch {

	static int menu() {
		System.out.print("\nElige una opción\n"
					+ "\n\t1) Insertar un número."
					+ "\n\t2) Borrar una aparición de un número." 
					+ "\n\t3) Número de apariciones de número."
					+ "\n\t4) Dibujar el BinaryTree"
					+ "\n\t5) Borrar todas las apariciones de un número."
					+ "\n\n\t0) Salir\n");
		int op;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nNúmero: ");
			op = scanner.nextInt();
		} while (op < 0 && op > 5);
		return op;
	}

	public static void main(String[] args) {
		PRBinarySearchTree<Integer> arbol = new PRBinarySearchTree<Integer>();
		
		//voy añadiendo aleatorios
		for (int i = 0; i < 10; i++) {
			arbol.insert((int) Math.round(Math.random() * 32));
		}
		int numero, op;
		Scanner scanner = new Scanner(System.in);
		do{
			op = menu();
			switch(op){
			case 0: break; //fin de ejecución
			case 1:  //añade un elemento
				System.out.print("\nNúmero: ");
				numero = scanner.nextInt();
				arbol.insert(numero);
				break;
			case 2: //borra un elemento
				System.out.print("\nNúmero: ");
				numero = scanner.nextInt();
				if(arbol.remove(numero) == null) System.out.println("No se encontró el número.");
				else System.out.println("Número borrado.");
				break;
			case 3: //número de veces
				System.out.print("\nNúmero: ");
				numero = scanner.nextInt();
				int veces = arbol.contadorVeces(numero);
				System.out.println("Número de apariciones: "+veces+".");
				break;
			case 4: //dibujar el árbol
				arbol.displayTree();
				break;
			case 5:
				System.out.print("\nNúmero: ");
				numero = scanner.nextInt();
				arbol.borradoTotal(numero);
				break;
			}
		}while(op != 0);
	}

}
