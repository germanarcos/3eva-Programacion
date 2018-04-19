
public class PilaFija implements Dinamizable{

	private Integer pila[];
	private Integer cima;

	PilaFija(Integer tamanioMax) {
		pila = new Integer[tamanioMax];
		cima = 0;
	}
	public void add(Integer elemento){ // apilar un elemento
		if(cima==pila.length){  
			System.out.println("La pila esta llena.");
		}else{
			pila[cima] = elemento;
			cima++;
		}
	}

	public Integer delete(){ // desapilar un elemento
		if(cima == 0) {
			System.out.println("Pila vacía.");
			return 0;
		}
		else {
			cima--;
			return pila[cima];
		}
	}
}
