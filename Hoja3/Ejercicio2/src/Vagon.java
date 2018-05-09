
public class Vagon {
	Tren tren;
	Integer[] asientos = new Integer[10];
	Integer numero;
	Integer ultimoVagon = 0;

	public Vagon(Tren tren) {
		super();
		this.tren = tren;
		inicializarAsientos();
		ultimoVagon++;
		this.numero = ultimoVagon;
	}

	public Tren getTren() {
		return tren;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void inicializarAsientos() {
		for (int i = 0; i < asientos.length; i++) {
			asientos[i] = null;
		}
	}

	public boolean lleno() {
		for (int i = 0; i < asientos.length; i++) {
			if (asientos[i] == null) {
				return false;
			}
		}
		return true;
	}

	public Integer darAsiento(Billete billete) {
		for (int i = 0; i < asientos.length; i++) {
			if (asientos[i] == null) {
				asientos[i] = billete.getNumeroBillete();
				billete.setAsiento(i);
				return i;
			}
		}
		return null;
	}

	public boolean anularAsiento(Billete billete) {
		if (billete.getVagon() == this) {
			if (asientos[billete.getAsiento()] == billete.numeroBillete) {
				asientos[billete.getAsiento()] = null;
				return true;
			}
		}
		return false;
	}
}
