
public class Libro implements Comentable, Comparable{
	private String titulo;
	private Autor autor;
	private Double precio;
	private Integer cantidad;
	private String resena;
	private static Integer siguiente=0;

	public Libro(String titulo, Autor autor, Double precio) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		cantidad = 0;
		siguiente++;
	}

	public Libro(String titulo, Autor autor, Double precio, Integer cantidad) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.cantidad = cantidad;
		siguiente++;
	}
	
	public static Integer getSiguiente() {
		return siguiente;
	}

	public static void setSiguiente(Integer siguiente) {
		Libro.siguiente = siguiente;
	}

	public String cadenaLibro() {
		String cadena = "Libro [titulo = " + titulo  +  "; precio = " + precio + " cantidad = "
				+ cantidad + "\n\t" + autor.cadenaAutor() + "]";
		return cadena;
	}


	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Autor getAutor() {
		return autor;
	}

	@Override
	public void comentar(String texto) {
		// TODO Auto-generated method stub
		this.resena=texto;
	}

	@Override
	public String comentario() {
		// TODO Auto-generated method stub
		return "Título: "+titulo+ "\n\t reseña : "+resena;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		int valor;
		if (arg0 instanceof Libro){
			Libro libro= (Libro) arg0;
			valor=this.titulo.toUpperCase().compareTo(libro.getTitulo().toUpperCase());
		} else {
			Autor autor= (Autor) arg0;
			valor= this.titulo.toUpperCase().compareTo(autor.getNombre().toUpperCase());
		}
		return valor;
	}

}// Libro
