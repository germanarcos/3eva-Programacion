import java.util.Comparator;

public class Billete implements Comparable<Billete> {
	Integer numeroBillete;
	static Integer ultimoBillete = 0;
	Double precio;
	Fecha fecha;
	Hora hora;
	Tren tren;
	Vagon vagon;
	Integer asiento;

	public Billete(Double precio, Fecha fecha, Hora hora, Tren tren, Vagon vagon) {
		ultimoBillete++;
		numeroBillete = ultimoBillete;
		this.precio = precio;
		this.fecha = fecha;
		this.hora = hora;
		this.tren = tren;
		this.vagon = vagon;
	}

	public String toString() {
		String devolver = "Billete numero: " + numeroBillete;
		devolver += "\nDestino: " + tren.getEstacionLlegada().getNombre();
		devolver += "\nSalida: " + tren.getEstacionSalida().getNombre();
		devolver += "\n" + fecha.toString() + hora.toString();
		devolver += "\nTren numero " + tren.getNumero();
		devolver += "\nVagon numero " + vagon.getNumero();
		devolver += "\nAsiento numero " + asiento;
		devolver += "\nPrecio: " + precio;
		return devolver;
	}

	public void setAsiento(Integer asiento) {
		this.asiento = asiento;
	}

	public Integer getNumeroBillete() {
		return numeroBillete;
	}

	public void anularBillete() {
		if (vagon.anularAsiento(this)) {
			vagon = null;
			asiento = null;
			tren = null;
		}

	}

	public void setNumeroBillete(Integer numeroBillete) {
		this.numeroBillete = numeroBillete;
	}

	public static Integer getUltimoBillete() {
		return ultimoBillete;
	}

	public static void setUltimoBillete(Integer ultimoBillete) {
		Billete.ultimoBillete = ultimoBillete;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Tren getTren() {
		return tren;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}

	public Vagon getVagon() {
		return vagon;
	}

	public void setVagon(Vagon vagon) {
		this.vagon = vagon;
	}

	public Integer getAsiento() {
		return asiento;
	}

	@Override
	public int compareTo(Billete bill) {
		// TODO Auto-generated method stub
		return numeroBillete - bill.getNumeroBillete();
	}

	public static Comparator<Billete> comparadorFecha = new Comparator<Billete>(){
		public int compare(Billete bill1, Billete bill2) {
			// TODO Auto-generated method stub
			int resultado;
			resultado = bill1.getFecha().difFechas(bill2.getFecha());
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getTren().getNumero() - bill2.getTren().getNumero();
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getVagon().getNumero() - bill2.getVagon().getNumero();
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getAsiento() - bill2.getAsiento();
			return resultado;
		}

	};
	
	public static Comparator<Billete> comparadorTren = new Comparator<Billete>(){
		public int compare(Billete bill1, Billete bill2) {
			// TODO Auto-generated method stub
			int resultado;
			resultado = bill1.getTren().getNumero() - bill2.getTren().getNumero();
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getFecha().difFechas(bill2.getFecha());
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getVagon().getNumero() - bill2.getVagon().getNumero();
			if (resultado != 0) {
				return resultado;
			}
			resultado = bill1.getAsiento() - bill2.getAsiento();
			return resultado;
		}
	};
	
}
