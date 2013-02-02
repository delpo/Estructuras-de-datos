package practica3;

import java.util.ArrayList;
import java.util.Scanner;

public class probador {
	static int Menu(){
		int opcion;
		Scanner teclado = new Scanner(System.in);
		do{
			System.out.println("--------------------------------------------------");
			System.out.println("Probador de la estructura de datos PRBinaryTree\n");
			System.out.println("Elija una opción:\n");
			System.out.println("O) Salir\n");
			System.out.println("1) Construir árbol con expresión a introducir\n");
			System.out.println("2) Mostrar expresión introducida anteriormente\n");
			System.out.println("3) Batería de pruebas del boletín\n");
			System.out.println("4) Ver en función de notación el árbol\n");
			System.out.println("5) Evaluar función\n");
			System.out.println("6) Añadir contenido en vector\n");
			System.out.println("\n");
			System.out.print("Introduce opción: ");
			opcion = teclado.nextInt();
		}while(opcion < 0 || opcion > 6);
		return opcion;
	}
	
	public static void main(String[] args) {
		PRBinaryTree<Character> arbol = new PRBinaryTree<Character>();
		int op;
		op = Menu();
		do{
			if(op == 1){
				//construye el árbol y lo almacena
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String cadena = teclado.nextLine();
				arbol = arbol.createsBinaryExprTree(cadena);
				arbol.displayTree();
			}else if(op == 2){
				//muestra en pantalla el árbol almacenado
				System.out.println("Recorrido en Inorden");
				String resultado = arbol.printInOrder();
				System.out.println("El árbol es: "+resultado);
				System.out.println("Recorrido en Postorden");
				resultado = arbol.printPostOrder();
				System.out.println("El árbol es: "+resultado);
			}else if(op == 3){
				System.out.println("-Batería de pruebas-\n");
				String bateria[] = {"5", "34+", "3+4", "+34", "341+", "34+1", "34", "34+1/*", "25^", "35^62/+", "04/", "40/", "82/6-321+*+", "35+26+4*-"}; 
				int indice = 0;
				for(int i = 0; i < bateria.length; i++){
					try{
						indice = i;
						arbol = arbol.createsBinaryExprTree(bateria[i]);
					}catch(Exception e){
						System.err.println("No se pudo completar la creación del árbol "+indice+".");
					}
				}
			}else if(op == 4){
				String resultado = arbol.functionalNotation();
				System.out.println(resultado);
			}else if(op == 5){
				float resultado = arbol.evaluate();
				System.out.println(resultado);
			}else if(op == 6){
				Character[] v = new Character[(int) Math.pow(2, arbol.height())];
				arbol.toArray(v);
				for(int i = 0; i<v.length; i++){
					if(v[i] != null)
						System.out.print(v[i]+" ");
					else
						System.out.print("|_| ");
				}
				System.out.print("\n");
			}
			op = Menu();
		}while(op != 0);
		System.out.println("¡Hasta luego!");
	}
}
