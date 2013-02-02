package practica1;

/*PARA USO ORDENADO MAPTREE Y PARA EL RESTO HASMAP, ASÍ QUE USAR DOS ADICIONES Y DOS BORRADOS
A LA VEZ, UNO PARA CADA ESTRUCTURA DE DATOS*/

/*COMO TREEMAP NO PERMITE CLAVES REPETIDAS, ENTONCES SE PODRIA HACER UNA LISTA PARA CADA FECHA Y
 *COMPROBAR SI FECHA EXISTE*/

public class Ayuda {
	private Datos datos;
	private double cantidad;
	private String fecha;
	public String dni;
	public Boolean concedida;
	
	public Ayuda(Datos datos, double cantidad, String dni){
		this.datos = datos;
		this.cantidad = cantidad;
		this.concedida = false;
		this.dni = dni;
	}
	public Ayuda(){
		
	}
	
	public String toString(){
		return "\nDATOS:\nNombre: "+datos.getNombre()+"\nNIF: "+dni+"\nDomicilio: "+
	datos.getDomicilio()+"\nCantidad: "+cantidad+"\nFecha: "+fecha;
		
	}

	public Datos getDatos() {
		return datos;
	}

	public void setDatos(Datos datos) {
		this.datos = datos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public Boolean equals(Ayuda ayuda){
		if(this.dni.compareTo(ayuda.dni) == 0){
			return true;
		}else{
			return false;
		}	
	}
}
