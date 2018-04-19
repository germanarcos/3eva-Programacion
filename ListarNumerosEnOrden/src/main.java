import java.util.TreeSet;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<Integer> arbol = new TreeSet<Integer>();
		Integer num;

		num = Leer.pedirEntero("Introduce un número, -9999 para salir");
		while (num != -9999) {
			arbol.add(num);
			num = Leer.pedirEntero("Introduce un número, -9999 para salir");
		}
		for (Integer i : arbol) {
			Leer.mostrarEnPantalla(i.toString());
		}
	}

}
