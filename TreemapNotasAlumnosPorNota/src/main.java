import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class main {
	public static void main(String[] args) {
		String apellido;
		Integer nota;
		TreeMap<String, Integer> mapa = new TreeMap<String, Integer>();
		do {
			apellido = Leer.pedirCadena("Introduce un Apellido\nEscribe \"*\" para salir");
			if (!apellido.equals("*")) {
				do {
					nota = Leer.pedirEntero("Introduce una nota");
				} while (nota < 0 || nota > 10);
				mapa.put(apellido, nota);
			}
		} while (!apellido.equals("*"));
		
		TreeSet<Alumno> temporal = new TreeSet<Alumno>();
		for (Entry<String, Integer> valores : mapa.entrySet()) {
			Alumno temp;
			temp = new Alumno(valores.getKey(),valores.getValue());
			temporal.add(temp);
		}
		for (Alumno alumno : temporal) {
			Leer.mostrarEnPantalla(alumno.toString());
		}
	}
}
