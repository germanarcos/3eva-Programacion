
public class Mesa {
	public final int numeroMesa;
	public static int siguienteMesa = 0;
	public final int maximoComensales;
	public int numeroComensales;
	public boolean libre;
	public Menu[] menusActuales;

	Mesa() {
		numeroMesa = siguienteMesa++;
		maximoComensales = (int) (Math.random() * 6 + 3);
		libre = true;
	}

	public void liberarMesa() {
		numeroComensales = 0;
		libre = true;
		menusActuales = null;
	}

	public void mostrarMenus() {
		if (libre)
			for (int i = 0; i < menusActuales.length; i++) {
				menusActuales[i].mostrarMenu();
				System.out.println();
			}
	}
	
	public void ocuparMesa(int numeroComensales, Menu[] menus){
		if(libre&&numeroComensales<maximoComensales)
		this.numeroComensales=numeroComensales;
		menusActuales=menus;
	}
}
