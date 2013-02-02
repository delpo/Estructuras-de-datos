package practica1;

public class Datos {
	private String nombre;
	//private String NIF;
	private String domicilio;
	
	public Datos(String nombre, String domicilio){
		this.nombre = nombre;
		//this.NIF = NIF;
		this.domicilio = domicilio;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	/*public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		NIF = nIF;
	}*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
