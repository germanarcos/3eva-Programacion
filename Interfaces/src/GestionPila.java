
public class GestionPila {
	public static void main(String args[]) {
		PilaFija pilaFija1 = new PilaFija(5);
		PilaFija pilaFija2 = new PilaFija(8);

		// apilamos unos numeros
		for(int i=0; i<5; i++) pilaFija1.push(i);
		for(int i=0; i<8; i++) pilaFija2.push(i);

		// los desapilamos
		System.out.println("Pila pila1:");
		for(int i=0; i<5; i++) 
			System.out.println(pilaFija1.pop());

		System.out.println("Pila pila2:");
		for(int i=0; i<8; i++) 
			System.out.println(pilaFija2.pop());
		
		PilaDinamica pilaDinamica1 = new PilaDinamica(5);
		PilaDinamica pilaDinamica2 = new PilaDinamica(8);
		
		for(int i=0; i<12; i++){
			pilaDinamica1.push(i);
		}
		
	    for(int i=0; i<20; i++){
	    	pilaDinamica2.push(i);
	    }
	    
	    System.out.println("Pila pilaDinamica1:");
	    System.out.println("-------------------");
	    
	    for(int i=0; i<12; i++) {
	       System.out.println(pilaDinamica1.pop());
	    }
	    
	    System.out.println("Pila pilaDinamica2:");
	    System.out.println("-------------------");
	    
	    for(int i=0; i<20; i++){ 
	       System.out.println(pilaDinamica2.pop());
	    }
	    
	    // utilizamos una variable de tipo IpilaDeEnteros para guardar tanto la pila fija como la dinámica
	    
	    IpilaDeEnteros pila;
	    PilaDinamica dinamica = new PilaDinamica(5);
	    PilaFija fija = new PilaFija(8);
	    
	    pila = dinamica; // apunta a la pila dinamica
	    // apilamos unos cuantos numeros
	    for(int i=0; i<12; i++) pila.push(i);
	    
	    pila = fija; // apunta a la pila fija
	    // apilamos unos cuantos numeros
	    for(int i=0; i<8; i++) pila.push(i);
	    
	    pila = dinamica; // apunta a la pila dinamica
	    System.out.println("\nValores en pila dinamica:");
	    System.out.println("------------------------:");
	    
	    for(int i=0; i<12; i++) 
	        System.out.println(pila.pop());
	    
	    pila = fija; // apunta a la pila fija
	    System.out.println("\nValores en pila fija:");
	    System.out.println("--------------------:");
	    
	    for(int i=0; i<8; i++) 
	        System.out.println(pila.pop());
	}// main
}
