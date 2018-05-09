import java.util.ArrayList;

public class Tren {
	static Integer siguienteNumero = 0;
	Integer numero;
	Double precio;
	Hora hora;
	Fecha fecha;
	Estacion estacionSalida;
	Estacion estacionLlegada;
	ArrayList<Vagon> vagones;
	private Integer ultimoVagon;

	public Tren(Hora hora, Fecha fecha, Estacion estacionSalida, Estacion estacionLlegada) {
		siguienteNumero++;
		this.numero = siguienteNumero;
		this.hora = hora;
		this.fecha = fecha;
		this.estacionSalida = estacionSalida;
		this.estacionLlegada = estacionLlegada;
		inicializarVagones();
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
				billete.darAsiento(v.darAsiento(billete));
				return billete;
			}
		}
		anadirVagon();
		Billete billete = new Billete(precio, fecha, hora, this, vagones.get(ultimoVagon));
		billete.darAsiento(vagones.get(ultimoVagon).darAsiento(billete));
		return billete;
	}
}
