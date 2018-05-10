import java.util.ArrayList;
import java.util.TreeSet;

public class GestionVenta {

	public static void main(String[] args) {
		TreeSet<Billete> billetaje = new TreeSet<Billete>();
		TreeSet<Billete> billetesFecha = new TreeSet<Billete>(Billete.comparadorFecha);
		TreeSet<Billete> billetesTren = new TreeSet<Billete>(Billete.comparadorTren);
		Fecha[] fechas = generarFechas();
		ArrayList<Tren> trenes = new ArrayList<Tren>();
		// Crear estaciones
		Estacion[] estaciones = generarEstaciones();
		// Crear trenes y guardar el primero para usarlo mas adelante
		crearBilletesYTrenes(billetaje, billetesFecha, billetesTren, fechas, trenes, estaciones);
		int opcion;
		do {
			opcion = menu();
			switch (opcion) {
			case 1:
				listadoTrenes(trenes);
				break;
			case 2:
				listarBillete(billetaje);
				break;
			case 3:
				listarBillete(billetesFecha);
				break;
			case 4:
				listarBillete(billetesTren);
				break;
				/*
				 * Falta implementar para comprar y anular billetes aunque
				 * los metodos estan hechos
				 */
			case 5:
				break;
			case 6:
				break;
			default:
			}
		} while (opcion != 0);
	}

	private static void listarBillete(TreeSet<Billete> billetaje) {
		for (Billete bill : billetaje) {
			Leer.mostrarEnPantalla(bill.toString());
			System.out.println();
		}
	}

	private static void crearBilletesYTrenes(TreeSet<Billete> billetaje, TreeSet<Billete> billetesFecha,
			TreeSet<Billete> billetesTren, Fecha[] fechas, ArrayList<Tren> trenes, Estacion[] estaciones) {
		Tren primero = null;
		for (int i = 0; i < 3; i++) {
			Tren temp = new Tren(new Hora(5, 10), fechas[(int) (Math.random() * fechas.length)],
					estaciones[(int) (Math.random() * estaciones.length)],
					estaciones[(int) (Math.random() * estaciones.length)],
					(double) Math.round(((Math.random() * 30 + 10) * 100d)) / 100d);
			trenes.add(temp);
			if (i == 0) {
				primero = temp;
			}
			for (int j = 0; j < 5; j++) {
				Billete temporal = temp.comprarBillete();
				billetaje.add(temporal);
				billetesFecha.add(temporal);
				billetesTren.add(temporal);
			}
		}
		// Nuevo billete en el primer tren
		Billete temporal = primero.comprarBillete();
		billetaje.add(temporal);
		billetesFecha.add(temporal);
		billetesTren.add(temporal);
	}

	public static Fecha[] generarFechas() {
		Fecha[] fechas = new Fecha[10];
		for (int i = 0; i < 10; i++) {
			fechas[i] = new Fecha((int) (Math.random() * 26 + 1), (int) (Math.random() * 11 + 1),
					(int) (Math.random() * 23 + 1995));
		}
		return fechas;
	}

	public static Estacion[] generarEstaciones() {
		String[] nombres = new String[] { "Goya", "Delicias", "Atocha", "Malaga", "Barcelona", "Sevilla" };
		Estacion[] estaciones = new Estacion[nombres.length];
		for (int i = 0; i < estaciones.length; i++) {
			estaciones[i] = new Estacion(nombres[i]);
		}
		return estaciones;
	}

	public static void listadoTrenes(ArrayList<Tren> trenes) {
		for (Tren tren : trenes) {
			Leer.mostrarEnPantalla(tren.toString());
		}
	}

	public static int menu() {
		return Leer.pedirEntero(
				"1.Listado de trenes\n2.Listado de billetes por numero\n3.Listado de billetes por fecha\n4. Listado de billetes por tren\n5. Comprar billete\n6. Anular billete\n0. Salir");
	}
}
