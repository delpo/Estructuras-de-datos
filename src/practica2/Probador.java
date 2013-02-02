package practica2;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Probador {

	static int Menu(){
		int opcion;
		Scanner teclado = new Scanner(System.in);
		do{
			System.out.println("--------------------------------------------------");
			System.out.println("Probador de la estructura de datos HashBag\n");
			System.out.println("Elija una opción:\n");
			System.out.println("O) Salir\n");
			System.out.println("1) Añadir elemento a la bolsa\n");
			System.out.println("2) Mostrar contenido bolsa\n");
			System.out.println("3) Vaciar bolsa\n");
			System.out.println("4) Mostrar número de veces que aparece un elemento\n");
			System.out.println("5) Modificar número de veces que aparece un elemento\n");
			System.out.println("6) Mostrar elementos diferentes\n");
			System.out.println("7) Quitar elemento a la bolsa\n");
			System.out.println("8) Quitar una colección de elementos a la bolsa\n");
			System.out.println("9) Añadir una colección de elementos a la bolsa\n");
			System.out.println("10) Quitar todos los elementos menos los de una colección\n");
			System.out.println("11) Comprobar si contiene todos los elementos de una colección\n");
			System.out.println("12) Comprobar si contiene el elemento\n");
			System.out.println("13) Ver estadísticas de la bolsa");
			System.out.println("\n");
			System.out.print("Introduce opción: ");
			opcion = teclado.nextInt();
		}while(opcion < 0 || opcion > 13);
		return opcion;
	}
	
	public static void main(String[] args) {
		HashBag<String> bolsa = new HashBag<String>();
		int op;
		op = Menu();
		do{
			if(op == 1){
				//añadir elemento a bolsa
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String elemento = teclado.nextLine();
				System.out.println("añadiendo elemento...");
				bolsa.add(elemento);
				System.out.println("Número elementos (con rep.): "+bolsa.size());
				System.out.println("Número elementos (sin rep.): "+bolsa.uniqueSize());
			}else if(op == 2){
				//mostrar contenido de bolsa
				bolsa.mostrarContenido();
			}else if(op == 3){
				System.out.println("vaciando bolsa...");
				bolsa.clear();
			}else if(op == 4){
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String elemento = teclado.nextLine();
				if(bolsa.getCount(elemento) == -1){
					System.out.println("El elemento no existe.");
				}else if(bolsa.getCount(elemento) == 0){
					System.out.println("El elemento se eliminó anteriormente.");
				}else{
					System.out.println("Número de veces del elemento: "+bolsa.getCount(elemento));
				}
			}else if(op == 5){
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String elemento = teclado.nextLine();
				System.out.print("Introduce número de veces: ");
				int veces = teclado.nextInt();
				if(bolsa.setCount(elemento, veces) == true){
					System.out.println("Se modificó el contador.");
				}else{
					System.out.println("No se encontró el elemento.");
				}
				
			}else if(op == 6){
				Set<String> diferentes = bolsa.elementSet();
				Iterator<String> iterator = diferentes.iterator();
				if(diferentes.isEmpty()) System.out.println("No hay elementos a mostrar.");
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
			}else if(op == 7){
				//quitar elemento a bolsa
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String elemento = teclado.nextLine();
				System.out.println("quitando elemento...");
				bolsa.remove(elemento);
				System.out.println("Número elementos (con rep.): "+bolsa.size());
				System.out.println("Número elementos (sin rep.): "+bolsa.uniqueSize());
			}else if(op == 8){
				HashBag<String> coleccion = new HashBag<String>();
				String elemento = "";
				while(elemento != "q"){
					System.out.println("-Introduzca elemento nuevo (para parar, escriba q)-");
					Scanner teclado = new Scanner(System.in);
					System.out.print("Introduce elemento: ");
					elemento = teclado.nextLine();
					if(elemento.compareTo("q") != 0){
						coleccion.add(elemento);
						coleccion.setCount(elemento, 1);
					}else{
						break;
					}
				}
				//ahora que ya tengo la colección creada, toca quitar los elementos
				if(bolsa.removeAll(coleccion)){
					System.out.println("Proceso completado con éxito. Se borraron los elementos.");
				}else{
					System.out.println("No habían elementos. No se hizo nada.");
				}
			}else if(op == 9){
				HashBag<String> coleccion = new HashBag<String>();
				String elemento = "";
				while(elemento != "q"){
					System.out.println("-Introduzca elemento nuevo (para parar, escriba q)-");
					Scanner teclado = new Scanner(System.in);
					System.out.print("Introduce elemento: ");
					elemento = teclado.nextLine();
					if(elemento.compareTo("q") != 0){
						coleccion.add(elemento);
					}else{
						break;
					}
				}
				//ahora que ya tengo la colección creada, toca añadir los elementos
				if(bolsa.addAll(coleccion)){
					System.out.println("Proceso completado con éxito. Se borraron los elementos.");
				}else{
					System.out.println("No habían elementos. No se hizo nada.");
				}
			}else if(op == 10){
				HashBag<String> coleccion = new HashBag<String>();
				String elemento = "";
				while(elemento != "q"){
					System.out.println("-Introduzca elemento nuevo (para parar, escriba q)-");
					Scanner teclado = new Scanner(System.in);
					System.out.print("Introduce elemento: ");
					elemento = teclado.nextLine();
					if(elemento.compareTo("q") != 0){
						coleccion.add(elemento);
						coleccion.setCount(elemento, 1);
					}else{
						break;
					}
				}
				//ahora que ya tengo la colección creada, toca eliminar todos menos los introducidos
				if(bolsa.retainAll(coleccion)){
					System.out.println("Proceso completado con éxito. Se borraron los elementos.");
				}else{
					System.out.println("No habían elementos. No se hizo nada.");
				}
			}else if(op == 11){
				HashBag<String> coleccion = new HashBag<String>();
				String elemento = "";
				while(elemento != "q"){
					System.out.println("-Introduzca elemento nuevo (para parar, escriba q)-");
					Scanner teclado = new Scanner(System.in);
					System.out.print("Introduce elemento: ");
					elemento = teclado.nextLine();
					if(elemento.compareTo("q") != 0){
						coleccion.add(elemento);
						coleccion.setCount(elemento, 1);
					}else{
						break;
					}
				}
				//ahora que ya tengo la colección creada, toca comprobar si contiene todos
				if(bolsa.containsAll(coleccion)){
					System.out.println("Contiene todos los elementos.");
				}else{
					System.out.println("No contiene todos los elementos o alguna de las dos colecciones está vacía.");
				}
			}else if(op == 12){
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduce elemento: ");
				String elemento = teclado.nextLine();
				if(bolsa.contains(elemento)){
					System.out.println("El elemento está en la bolsa");
				}else{
					System.out.println("El elemento no está en la bolsa");
				}
			}else if(op == 13){
				System.out.println("Número elementos (con rep.): "+bolsa.size());
				System.out.println("Número elementos (sin rep.): "+bolsa.uniqueSize());
			}else{
				System.out.println("No has introducido opción válida.");
			}
			bolsa.hashCode();
			op = Menu();
		}while(op != 0);
		System.out.println("¡Hasta luego!");
	}
}
