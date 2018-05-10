import java.util.ArrayList;

public class Tren implements Comparable<Tren> {
	static Integer siguienteNumero = 0;
	Integer numero;
	Double precio;
	Hora hora;
	Fecha fecha;
	Estacion estacionSalida;
	Estacion estacionLlegada;
	ArrayList<Vagon> vagones;
	private Integer ultimoVagon;

	public Tren(Hora hora, Fecha fecha, Estacion estacionSalida, Estacion estacionLlegada, Double precio) {
		siguienteNumero++;
		this.numero = siguienteNumero;
		this.hora = hora;
		this.fecha = fecha;
		this.estacionSalida = estacionSalida;
		this.estacionLlegada = estacionLlegada;
		this.precio = precio;
		inicializarVagones();
	}

	public String toString() {
		String devolver;
		devolver = "\nTren numero: " + numero;
		devolver += "\nCon salida en: " + estacionSalida.getNombre();
		devolver += "\nDestino: " + estacionLlegada.getNombre();
		devolver += "\n" + fecha.toString() + hora.toString();
		devolver += "\nNumero de vagones: " + vagones.size();
		devolver += "\nPrecio por billete: " + precio;
		return devolver;

	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Estacion getEstacionSalida() {
		return estacionSalida;
	}

	public void setEstacionSalida(Estacion estacionSalida) {
		this.estacionSalida = estacionSalida;
	}

	public Estacion getEstacionLlegada() {
		return estacionLlegada;
	}

	public void setEstacionLlegada(Estacion estacionLlegada) {
		this.estacionLlegada = estacionLlegada;
	}

	public ArrayList<Vagon> getVagones() {
		return vagones;
	}

	public void setVagones(ArrayList<Vagon> vagones) {
		this.vagones = vagones;
	}

	public void inicializarVagones() {
		vagones = new ArrayList<Vagon>();
		vagones.add(new Vagon(this));
		ultimoVagon = 0;
	}

	public void anadirVagon() {
		vagones.add(new Vagon(this));
		ultimoVagon++;
	}

	public Billete comprarBillete() {
		// Buscar espacios libres
		for (Vagon v : vagones) {
			if (!v.lleno()) {
				Billete billete = new Billete(precio, fecha, hora, this, v);
				billete.setAsiento(v.darAsiento(billete));
				return billete;
			}
		}
		anadirVagon();
		Billete billete = new Billete(precio, fecha, hora, this, vagones.get(ultimoVagon));
		billete.setAsiento(vagones.get(ultimoVagon).darAsiento(billete));
		return billete;
	}

	@Override
	public int compareTo(Tren tren) {
		// TODO Auto-generated method stub
		return numero - tren.getNumero();
	}
}
