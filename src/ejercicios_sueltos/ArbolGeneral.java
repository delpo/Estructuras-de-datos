package ejercicios_sueltos;

import java.util.List;

public class ArbolGeneral<T> {
	public T raiz;
	public List<ArbolGeneral<T>> hijos;
	
	//http://stackoverflow.com/questions/936377/static-method-in-a-generic-class
	public static <T> int gradoMaximo(ArbolGeneral<T> arbol){
		int max = arbol.hijos.size();
		for(ArbolGeneral<T> element:arbol.hijos){
			if(gradoMaximo(element) > max) max = gradoMaximo(element);
		}
		return max;
	}
}
