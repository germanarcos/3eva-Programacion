import java.util.Arrays;

public class Menu {

	Plato[] platos;
	Integer precio;
	Menu(Plato[] platos) {
		this.platos = Arrays.copyOfRange(platos, 0, 2);
		calcularPrecio();
	}
	private void calcularPrecio(){
		precio = 0;
		for(Plato p: platos){
			precio+=p.getPrecio();
		}
	}
	public void mostrarMenu(){
		for(int i = 0;i<platos.length;i++){
			System.out.println(platos[i].toString());
		}
	}
}
