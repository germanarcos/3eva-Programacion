import java.util.TreeSet;

public class GestionVenta {

	public static void main(String[] args) {
		TreeSet<Billete> billetaje = new TreeSet<Billete>();
		String[] nombres = new String[] { "Goya", "Delicias", "Atocha", "Malaga", "Barcelona", "Sevilla" };
		Estacion[] estaciones = new Estacion[6];
		for (int i = 0; i < estaciones.length; i++) {
			estaciones[i] = new Estacion(nombres[i]);
		}
		for (int i = 0; i < 5; i++) {
			Tren temp = new Tren(new Hora(5, 10), new Fecha(23, 10, 2018), estaciones[i], estaciones[i + 1]);
			for (int j = 0; j < 10; j++) {
				billetaje.add(temp.comprarBillete());
			}
		}
		for (Billete bill : billetaje) {
			Leer.mostrarEnPantalla(bill.toString());
			System.out.println();
		}

	}
}