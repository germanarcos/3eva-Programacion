
public class Ingrediente {
	public String nombre;
	Integer precio;

	Ingrediente(String nombre) {
		this.nombre = nombre;
		precio=(int)(Math.random()*3+1);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString(){
		return nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
}
