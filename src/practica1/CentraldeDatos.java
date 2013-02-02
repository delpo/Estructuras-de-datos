package practica1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class CentraldeDatos {
	Map <String, Ayuda> ayudas_solicitadas = new HashMap<String, Ayuda>();
	Map <String, Ayuda> ayudas_concedidas = new HashMap<String, Ayuda>();
	@SuppressWarnings("rawtypes")
	TreeMap<String, LinkedList> lista_ayudas_solicitadas = new TreeMap<String, LinkedList>();
	@SuppressWarnings("rawtypes")
	TreeMap<String, LinkedList> lista_ayudas_concedidas = new TreeMap<String, LinkedList>();
	
	@SuppressWarnings("unchecked")
	public boolean pedirAyuda(Ayuda ayuda){
		//recorrer ayudas_solicitadas para ver si el DNI está, devuelve true y solicita si no está y false si ya hay
		if(ayudas_solicitadas.containsKey(ayuda.getDni())){
			return false;
		}
		ayudas_solicitadas.put(ayuda.getDni(), ayuda);
		//ahora hay que meterlo en un linkedlist según fecha
		if(lista_ayudas_solicitadas.containsKey(ayuda.getFecha())){
			lista_ayudas_solicitadas.get(ayuda.getFecha()).add(ayuda);
		}else{
			LinkedList<Ayuda> ayudas = new LinkedList<Ayuda>();
			ayudas.add(ayuda);
			lista_ayudas_solicitadas.put(ayuda.getFecha(), ayudas);
		}
		return true;
	}
	
	public void buscarPersona(String DNI){
		if(ayudas_concedidas.containsKey(DNI)){
			System.out.println("Se encontró una ayuda concedida.");
			System.out.println(ayudas_concedidas.get(DNI).toString());
		}else if(ayudas_solicitadas.containsKey(DNI)){
			System.out.println("Se encontró una ayuda solicitada.");
			System.out.println(ayudas_solicitadas.get(DNI).toString());

		}else{
			System.out.println("DNI NO ENCONTRADO");
		}
	}
	
	public void concederAyuda(String DNI){
		Object fecha = ayudas_solicitadas.get(DNI).getFecha();
		if(ayudas_solicitadas.containsKey(DNI)){
			System.out.println("Se encontró una ayuda solicitada.");
			ayudas_concedidas.put(DNI, ayudas_solicitadas.get(DNI));
			if(lista_ayudas_concedidas.containsKey(DNI)){//el DNI ya obtuvo una ayuda anteriormente
				LinkedList<Ayuda> ayuda_nueva = new LinkedList<Ayuda>();
				ayuda_nueva.add(ayudas_concedidas.get(DNI));
				//quitar la que ya existe en el listado y en el TreeMap
				lista_ayudas_concedidas.remove(ayudas_solicitadas.get(DNI));
				ayudas_concedidas.remove(DNI);
				//meter nuevas solicitudes concedidas en el TreeMap y en el listado
				ayudas_concedidas.put(DNI, ayudas_solicitadas.get(DNI));
				lista_ayudas_concedidas.put(ayudas_concedidas.get(DNI).getFecha(), ayuda_nueva);
				//quito la ayuda ya concedida del listado de solicitadas
				@SuppressWarnings("rawtypes")
				Iterator iterator = lista_ayudas_solicitadas.get(ayudas_solicitadas.get(DNI).getFecha()).iterator();
				while (iterator.hasNext()) {
					Ayuda valor = (Ayuda) iterator.next();
					if(valor.getDni().compareTo(DNI) == 0){
						iterator.remove();
					}
				}	
				ayudas_solicitadas.remove(DNI);
			}else{ //el DNI no tiene ayuda concedida anterior
				LinkedList<Ayuda> ayuda_primera = new LinkedList<Ayuda>();
				ayuda_primera.add(ayudas_concedidas.get(DNI));
				ayudas_concedidas.put(DNI, ayudas_concedidas.get(DNI));
				lista_ayudas_concedidas.put(ayudas_concedidas.get(DNI).getFecha(), ayuda_primera);
				//quitar de ayudas_solicitadas y lista_ayudas_solicitadas
				//quito la ayuda ya concedida del listado de solicitadas
				@SuppressWarnings("rawtypes")
				Iterator iterator = lista_ayudas_solicitadas.get(ayudas_solicitadas.get(DNI).getFecha()).iterator();
				while (iterator.hasNext()) {
					Ayuda valor = (Ayuda) iterator.next();
					if(valor.getDni().compareTo(DNI) == 0){
						iterator.remove();
					}
				}				
				ayudas_solicitadas.remove(DNI);
			}
			System.out.println("Ayuda concedida!");
			if(lista_ayudas_solicitadas.get(fecha).isEmpty()){
				lista_ayudas_solicitadas.remove(fecha);
			}
		}else{
			System.out.println("No se ha podido completar la operación.");
		}
	}
	
	public void listadoPendientes(){
		@SuppressWarnings("rawtypes")
		Iterator iterator = lista_ayudas_solicitadas.keySet().iterator();
		if(lista_ayudas_solicitadas.isEmpty()) System.out.println("No hay elementos a mostrar.");
		while (iterator.hasNext()) {
			Object clave = iterator.next();
			System.out.println("-->Fecha: "+clave);
			System.out.println(lista_ayudas_solicitadas.get(clave));
		}
	}
	
	public void listadoConcedidas(){
		@SuppressWarnings("rawtypes")
		Iterator iterator = lista_ayudas_concedidas.keySet().iterator();
		if(lista_ayudas_concedidas.isEmpty()) System.out.println("No hay elementos a mostrar.");
		while (iterator.hasNext()) {
			Object clave = iterator.next();
			System.out.println("-->Fecha: "+clave);
			System.out.println(lista_ayudas_concedidas.get(clave));
		}
	}
	
	public void concederAyudas(double cantidad){
		double concedido_de_momento = 0;
		@SuppressWarnings("rawtypes")
		Iterator it_treemap = lista_ayudas_solicitadas.keySet().iterator();
		while (it_treemap.hasNext()) {
			Boolean end = false;
			Object clave = it_treemap.next();
			@SuppressWarnings("rawtypes")
			LinkedList valores = lista_ayudas_solicitadas.get(clave);			
			@SuppressWarnings("rawtypes")
			Iterator iterator = valores.iterator();
			while (iterator.hasNext()) {
				Ayuda valor = (Ayuda) iterator.next();
				if(valor.getCantidad()+concedido_de_momento <= cantidad){
					concedido_de_momento += valor.getCantidad();
					//conceder a ambos
					ayudas_concedidas.put(valor.getDni(), ayudas_solicitadas.get(valor.getDni()));
					if(lista_ayudas_concedidas.containsKey(valor.getDni())){//el DNI ya obtuvo una ayuda anteriormente
						//quitar la que ya existe en el listado y en el TreeMap
						lista_ayudas_concedidas.remove(ayudas_solicitadas.get(valor.getDni()));
						ayudas_concedidas.remove(valor.getDni());
						//meter nuevas solicitudes concedidas en el TreeMap y en el listado
						LinkedList<Ayuda> ayuda_nueva = new LinkedList<Ayuda>();
						ayuda_nueva.add(ayudas_concedidas.get(valor.getDni()));
						ayudas_concedidas.put(valor.getDni(), ayudas_solicitadas.get(valor.getDni()));
						lista_ayudas_concedidas.put(ayudas_concedidas.get(valor.getDni()).getFecha(), ayuda_nueva);
					}else{//primera vez que ese DNI consigue una ayuda
						LinkedList<Ayuda> ayuda_primera = new LinkedList<Ayuda>();
						ayuda_primera.add(ayudas_concedidas.get(valor.getDni()));
						ayudas_concedidas.put(valor.getDni(), ayudas_concedidas.get(valor.getDni()));
						lista_ayudas_concedidas.put(ayudas_concedidas.get(valor.getDni()).getFecha(), ayuda_primera);
					}
					//eliminar a ambos
					iterator.remove();
					ayudas_solicitadas.remove(valor.getDni());
				}else{
					end = true;
				}
				if(end) break;
			}
			if(end) break;
		}
		//voy a quitar los LinkedList de fechas que no tengan ninguna solicitud
		@SuppressWarnings("rawtypes")
		Iterator it_treemap_limpiar = lista_ayudas_solicitadas.keySet().iterator();
		while (it_treemap_limpiar.hasNext()) {
			Object clave = it_treemap_limpiar.next();
			@SuppressWarnings("rawtypes")
			LinkedList valores = lista_ayudas_solicitadas.get(clave);
			if(valores.isEmpty()){
				it_treemap_limpiar.remove();
			}
		}
		System.out.println("Tarea completada!");
	}
	
	@SuppressWarnings("rawtypes")
	public void sumaTotales(){
		double suma_solicitudes = 0; double suma_concedidas = 0;
		Iterator it = ayudas_solicitadas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry s = (Map.Entry)it.next();
			double cantidad_a_sumar = ((Ayuda) s.getValue()).getCantidad();
			suma_solicitudes += cantidad_a_sumar;
		}
		
		Iterator it2 = ayudas_concedidas.entrySet().iterator();
		while (it2.hasNext()) {
			Map.Entry c = (Map.Entry)it2.next();
			double cantidad_a_sumar = ((Ayuda) c.getValue()).getCantidad();
			suma_concedidas += cantidad_a_sumar;
		}
		
		System.out.println("SUMA DE AYUDAS:\n");
		System.out.println("Total ayudas solicitadas:"+suma_solicitudes);
		System.out.println("Total ayudas concedidas:"+suma_concedidas);
	}
}
