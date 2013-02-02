package ejercicios_sueltos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImagenPGM {
	private String nombreFichero;
	private int maxNivelGris;
	private int[][] pixelImagen;
	
	//Constructor
	public ImagenPGM(String nombre){
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new FileInputStream(nombre));
			setNombreFichero(nombre);
			inputStream.nextLine(); //me salto el valor mágico
			inputStream.nextLine(); //me salto el nombre del fichero
			int filas = inputStream.nextInt();
			int cols = inputStream.nextInt();
			pixelImagen = new int[filas][cols];
			setMaxNivelGris(inputStream.nextInt());
			for(int i = 0; i < filas; i++){
				for(int j = 0; j < cols; j++){
					pixelImagen[i][j] = inputStream.nextInt();
				}
			}
			
			inputStream.close();
		}catch(FileNotFoundException e){
			System.out.println("Fichero no encontrado.");
			System.exit(0);
		}
	}
/* SI FUERA FICHERO BINARIO
	ObjectOutputStream outputStream = null;
	try {
		outputStream = new ObjectOutputStream(new
		FileOutputStream("cosas.dat"));
		
		//sería Input en caso de lectura binaria
	}
	catch (FileNotFoundException e) {
		System.out.println("File could not be opened");
		System.exit(0);
		
		////
		 .writeInt(int), .writeLong(long),
		.writeFloat(float), .writeDouble(double)
		.writeChar(int)
		.writeBoolean(boolean)
		Y para escribir Strings
		.writeUTF(String)
		
		
		///para leer, cambia write por read
	}
	
	*/
	public int getMaxNivelGris() {
		return maxNivelGris;
	}

	public void setMaxNivelGris(int maxNivelGris) {
		this.maxNivelGris = maxNivelGris;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
}
