package practica1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class correctorTextos {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner teclado  = new Scanner(System.in);
		System.out.print("Introduce nombre del fichero a corregir: ");
		String texto_input = teclado.nextLine();
		System.out.print("Introduce fichero de diccionario: ");
		String diccionario = teclado.nextLine();
		Scanner scanner_dic = new Scanner (new File(diccionario));
		System.out.print("Introduce nombre del fichero a guardar: ");
		String texto_output = teclado.nextLine();
		Map <String, String> diccionario_hashmap = new HashMap<String, String>();
		String palabra = "";
		while (scanner_dic.hasNextLine()) {
			if(scanner_dic.hasNext()){
				palabra = scanner_dic.nextLine();
				diccionario_hashmap.put(palabra, palabra);
			}
		}
		Scanner scanner_lect = new Scanner (new File(texto_input));
		String linea = "";
		String[] linea_valores;
		Map <String, String> correcciones = new HashMap<String, String>(); //key=mala, value=buena
		Map <String, Integer> numero_correciones = new HashMap<String, Integer>(); //key=mala, value=veces
		File fichero = new File(texto_output);
		BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
		while (scanner_lect.hasNextLine()) {
			//Obtenemos la siguiente linea del fichero
			Scanner line = new Scanner(scanner_lect.nextLine());
			if(line.hasNext()){
				linea = line.nextLine();
				linea_valores = linea.split(" ");
				for(int i = 0; i<linea_valores.length; i++){
					if(!diccionario_hashmap.containsKey(linea_valores[i])){
						if(correcciones.containsKey(linea_valores[i])){
							bw.write(correcciones.get(linea_valores[i])+" ");
						}else{
							System.out.println("Palabra encontrada: "+linea_valores[i]);
							System.out.println("1) Añadir palabra a diccionario.");
							System.out.println("2) Sustituir por palabra correcta.");
							System.out.println("NOTA: Si introduces otro número, escribirá la palabra tal y como está sin guardarla en el diccionario.");
							System.out.print("Introduce opción: ");
							int opcion = teclado.nextInt();
							if(opcion == 1){
								//añadir palabra a diccionario
								diccionario_hashmap.put(linea_valores[i], linea_valores[i]);
								BufferedWriter out = new BufferedWriter(new FileWriter(diccionario, true));
								correcciones.put(linea_valores[i], linea_valores[i]);
								if(numero_correciones.containsKey(linea_valores[i])){
									int valor = numero_correciones.get(linea_valores[i]);
									numero_correciones.remove(linea_valores[i]);
									valor++;
									numero_correciones.put(linea_valores[i], valor);
								}else{
									numero_correciones.put(linea_valores[i], 1);
								}
								out.write(linea_valores[i]+"\n");
								out.close();
								//escribir palabra
								bw.write(linea_valores[i]+" ");
							}else if(opcion == 2){
								System.out.print("Introduce correcta: ");
								String correcta = teclado.next();
								correcciones.put(linea_valores[i], correcta);
								if(numero_correciones.containsKey(linea_valores[i])){
									int valor = numero_correciones.get(linea_valores[i]);
									valor++;
									numero_correciones.remove(linea_valores[i]);
									numero_correciones.put(linea_valores[i], valor);
								}else{
									numero_correciones.put(linea_valores[i], 1);
								}
								//escribir palabra
								bw.write(correcta+" ");
							}else{
								System.out.println("Opción elegida no válida. Se escribirá la palabra en el fichero nuevo como estaba.");
								//escribir palabra
								bw.write(linea_valores[i]+" ");
							}
						}
					}else{
						bw.write(linea_valores[i]+" ");
					}
				}

			}
			}
		scanner_lect.close();
		scanner_dic.close();
		bw.close();
		System.out.println("Tarea finalizada!");
		System.out.println("Numero de conflictos por palabra:");
		@SuppressWarnings("rawtypes")
		Iterator iterator = numero_correciones.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry c = (Map.Entry)iterator.next();
			int veces = (Integer) c.getValue();
			System.out.println(c.getKey()+": "+veces+" veces.");
		}
		}
	}
