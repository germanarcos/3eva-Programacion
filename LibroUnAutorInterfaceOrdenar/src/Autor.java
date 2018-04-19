
public class Autor implements Comentable, Comparable{
	private String nombre;
	private String email;
	private Character sexo;
	private String biografia;
	private static Integer siguiente=0;
	
	public Autor(String nombre, String email, char sexo) {
		this.nombre = nombre;
		this.email = email;
		this.sexo = sexo;
		siguiente++;
	}
	
	public Autor(String nombre, String email, String sexo) {
		this.nombre = nombre;
		this.email = email;
		this.sexo = sexo.charAt(0);
		siguiente++;
	}
	
	public static Integer getSiguiente() {
		return siguiente;
	}

	public static void setSiguiente(Integer siguiente) {
		Autor.siguiente = siguiente;
	}

	
	public String cadenaAutor(){
		return "Autor[nombre = " + nombre + ", email = " + email + ", sexo = "+ sexo + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public char getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		return "Autor [nombre=" + nombre + ", email=" + email + ", sexo=" + sexo + "]\n";
	}

	@Override
	public void comentar(String texto) {
		// TODO Auto-generated method stub
		this.biografia=texto;
	}

	@Override
	public String comentario() {
		// TODO Auto-generated method stub
		return "Nombre: "+nombre+ "\n\t biografía : "+biografia;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		int valor;
		if (arg0 instanceof Autor){
			Autor autor= (Autor) arg0;
			valor= this.nombre.toUpperCase().compareTo(autor.getNombre().toUpperCase());
		} else {
			Libro libro= (Libro) arg0;
			valor=this.nombre.toUpperCase().compareTo(libro.getTitulo().toUpperCase());
		}
		return valor;
	}
	
}
