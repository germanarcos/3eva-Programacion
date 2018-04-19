
public class Alumno implements Comparable {
	private String nombre;
	private Integer nota;

	public Alumno(String nombre, Integer nota) {
		this.nombre = nombre;
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nNota: " + nota;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Alumno) {
			Alumno temporal = (Alumno) arg0;
			return this.nota - temporal.getNota();
			
			/* Para una ordenacion descendente:
			 * return temporal.getNota() - this.nota;
			 * 
			 */
		}

		return 0;
	}
}
