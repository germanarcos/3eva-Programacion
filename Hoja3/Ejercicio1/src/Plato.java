
public class Plato {
	Ingrediente[] ingredientes;
	String nombre;
	Integer precio;

	Plato(Ingrediente[] ingredientes) {
		this.ingredientes = ingredientes;
		nombreAleatorio();
		calcularPrecio();
		
	}

	public Ingrediente[] getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Ingrediente[] ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public String toString(){
		String devolver = nombre+"\n\tPrecio: "+precio+"€\n\tIngredientes: ";
		for(Ingrediente i: ingredientes){
			devolver+=i.getNombre()+", ";
		}
		devolver+="\n";
		return devolver;
	}
	
	//Metodos que solo usa la propia clase
	private void nombreAleatorio() {
		int var = ingredientes.length;
		switch (var) {
		case 1:
			nombre = ingredientes[0].toString();
			break;
		case 2:
			nombre = ingredientes[0].toString() + " al " + ingredientes[1].toString();
			break;
		default:
		case 4:
			nombre = ingredientes[0].toString() + " al " + ingredientes[1].toString() + " con "
				+ ingredientes[2].toString() + " y guarnición de "+ingredientes[3].toString();
			break;
		case 3:
			nombre = ingredientes[0].toString() + " al " + ingredientes[1].toString() + " con "
					+ ingredientes[2].toString();
			break;
		case 0:
			nombre = "Pieza de ceramica redonda.";
			break;
		}
	}
	

	private void calcularPrecio(){
		precio = 0;
		for(Ingrediente  i: ingredientes){
			precio+=i.getPrecio();
		}
		precio+=1;
	}
}
