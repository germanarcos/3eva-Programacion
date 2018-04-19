import java.util.Arrays;

public class ColaDinamica implements Dinamizable {
	private Integer cola[];
	private Integer ultimo;
	private final Integer inicio = 0;

	ColaDinamica(Integer tamanioInicial) {
		cola = new Integer[tamanioInicial];
		ultimo = 0;
	}

	public void add(Integer elemento) { // colocar al final un elemento
		if (ultimo == cola.length) { // si esta llena la ampliamos
			cola = Arrays.copyOf(cola, (cola.length + 1));
			cola[ultimo] = elemento;
			ultimo++;
		} else {
			cola[ultimo] = elemento;
			ultimo++;
		}
	}

	public Integer delete() { // borrar un elemento
		if (ultimo == 0) {
			System.out.println("Pila vacía.");
			return 0;
		} else {
			ultimo--;
			Integer devolver = cola[inicio];
			cola = Arrays.copyOfRange(cola, 1, cola.length);
			return devolver;
		}
	}
}
