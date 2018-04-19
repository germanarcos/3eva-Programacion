import java.util.Map.Entry;
import java.util.TreeMap;

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
		for (Entry<String, Integer> valores : mapa.entrySet()) {
			apellido = valores.getKey();
			nota = valores.getValue();
			Leer.mostrarEnPantalla("Apellido: " + apellido + "\nNota: " + nota);
		}
	}
}
