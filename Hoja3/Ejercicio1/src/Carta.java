
public class Carta {

	Plato[] platos = new Plato[6];

	Carta(Plato[] platos) {
		this.platos = platos;
	}
	public void mostrarCarta(){
		System.out.println("CARTA:\n");
		for(int i = 0;i<platos.length;i++){
			System.out.println("Plato "+(i+1));
			System.out.println(platos[i].toString());
		}
	}
}
