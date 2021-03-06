
public class Comedor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ingrediente[] ingredientes = crearIngredientes();
		Carta carta = crearCarta(ingredientes);
		carta.mostrarCarta();
	}

	public static Ingrediente[] crearIngredientes() {
		String nombres[] = { "Croquetas", "Pimientos", "Mortadela", "Champi�ones", "Ternera", "Tortilla", "Huevos",
				"At�n", "Jam�n", "Esp�rragos", "Patatas", "Cebolla", "An�s" , "Or�gano", "Queso", "Tomate", "Cebolla",
				"Pimiento", "Macarrones", "Bacon", "Zanahoria", "Chorizo", "Morcilla", "Berenjena","Bolo�esa","Roquefort","Sopa",
				"Potaje","Rissotto","Pizza","Pollo","Salchichas"};
		Ingrediente[] ingredientes = new Ingrediente[nombres.length];
		for (int i = 0; i < ingredientes.length; i++) {
			ingredientes[i] = new Ingrediente(nombres[i]);
		}
		return ingredientes;
	}

	public static Ingrediente[] ingredientesAleatorios(Ingrediente[] ingredientes) {
		int numeroIngr = (int) (Math.random() * 7 + 0);
		Ingrediente[] ingredientesPlato = new Ingrediente[numeroIngr];
		for (int i = 0; i < ingredientesPlato.length; i++) {
			ingredientesPlato[i] = ingredientes[(int) (Math.random() * ingredientes.length)];
		}
		return ingredientesPlato;
	}
	
	public static Plato platoAleatorio(Ingrediente[] ingredientes){
		return new Plato(ingredientesAleatorios(ingredientes));
	}
	public static Carta crearCarta (Ingrediente[] ingredientes){
		Plato[] platos = new Plato[6];
		for(int i = 0;i<platos.length;i++){
			platos[i]=platoAleatorio(ingredientes);
		}
		return new Carta(platos);
	}
}
