
public class Hora {
	Integer hora;
	Integer minuto;

	public Hora(Integer hora, Integer minuto) {
		this.hora = horaValida(hora);
		this.minuto = minutoValido(minuto);
	}

	
	public String toString(){
		return hora+":"+minuto;
	}
	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public static Integer horaValida(Integer hora) {
		while (hora < 0 || hora > 23) {
			hora = Leer.pedirEntero("No has introducido una hora valida.");
		}
		return hora;
	}

	public static Integer minutoValido(Integer minuto) {
		while (minuto < 0 || minuto > 59) {
			minuto = Leer.pedirEntero("No has introducido un minuto valido");
		}
		return minuto;

	}
}
