package practica1;

import java.util.Scanner;
import practica1.Ayuda;
import practica1.CentraldeDatos;

public class Principal {
	
	static int Menu(){
		int opcion;
		Scanner teclado = new Scanner(System.in);
		do{
			System.out.println("--------------------------------------------------");
			System.out.println("¡Hola! ¡A gestionar solicitudes se ha dicho!\n");
			System.out.println("Elija una opción:\n");
			System.out.println("O) Salir\n");
			System.out.println("1) Añadir solicitud¡\n");
			System.out.println("2) Buscar persona\n");
			System.out.println("3) Conceder ayuda\n");
			System.out.println("4) Mostrar solicitudes pendientes\n");
			System.out.println("5) Mostrar ayudas concedidas\n");
			System.out.println("6) Conceder ayudas por una cantidad total\n");
			System.out.println("7) Mostrar totales");
			System.out.println("\n");
			System.out.print("Introduce opción: ");
			opcion = teclado.nextInt();
		}while(opcion < 0 || opcion > 7);
		return opcion;
	}
	
	public static void main(String[] args) {
		CentraldeDatos a = new CentraldeDatos();
		int op;
		op = Menu();
		do{
			if(op == 1){
				Scanner teclado  = new Scanner(System.in);
				Scanner teclado2 = new Scanner(System.in);
				Scanner teclado3 = new Scanner(System.in);
				System.out.print("Introduce nombre: ");
				/*con nextLine cojo nombres y apellidos (ojo, sólo funciona con strings,
				no uso next() porque no coje los espacios) */
				String nombre = teclado.nextLine();
				System.out.print("Introduce NIF: ");
				String NIF = teclado2.next();
				System.out.print("Introduce domicilio: ");
				/*Ojo, nextLine no funciona con algo que no sean Strings, pero next no coge los espacios*/
				String domicilio = teclado.nextLine();
				System.out.print("Introduce cantidad de ayuda: ");
				String cant = teclado2.next();
				double cantidad = Double.valueOf(cant).doubleValue();
				System.out.print("Introduce fecha en formato AAAAMMDD: ");
				String fecha = teclado3.next();
				Ayuda ayuda = new Ayuda(new Datos(nombre, domicilio), cantidad, NIF);
				ayuda.setFecha(fecha);
				//Comprobar si no ha pedido ayuda.
				if(a.pedirAyuda(ayuda)){
					System.out.println("Ayuda solicitada.");
				}else
					System.out.println("ERROR: El solicitante ya pidió anteriormente una ayuda.");
			}else if(op == 2){
				Scanner teclado  = new Scanner(System.in);
				System.out.print("Introduce DNI/NIF: ");
				String dni = teclado.nextLine();
				a.buscarPersona(dni);
			}else if(op == 3){
				Scanner teclado  = new Scanner(System.in);
				System.out.print("Introduce DNI/NIF: ");
				String dni = teclado.nextLine();
				a.concederAyuda(dni);
			}else if(op == 4){
				System.out.println("Solicitudes pendientes: \n");
				a.listadoPendientes();
			}else if(op == 5){
				System.out.println("Solicitudes concedidas: \n");
				a.listadoConcedidas();
			}else if(op == 6){
				Scanner teclado  = new Scanner(System.in);
				System.out.print("Introduce cantidad de ayuda: ");
				String cant = teclado.next();
				double cantidad = Double.valueOf(cant).doubleValue();
				a.concederAyudas(cantidad);
			}else if(op == 7){
				a.sumaTotales();
			}
			op = Menu();
		}while(op != 0);
		System.out.println("¡Hasta luego!");
	}
}
