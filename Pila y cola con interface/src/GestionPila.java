
public class GestionPila {
	public static void main(String args[]) {
		PilaFija pilaFija1 = new PilaFija(5);
		PilaFija pilaFija2 = new PilaFija(8);

		// apilamos unos numeros
		for(int i=0; i<5; i++) pilaFija1.add(i);
		for(int i=0; i<8; i++) pilaFija2.add(i);

		// los desapilamos
		System.out.println("Pila pila1:");
		for(int i=0; i<5; i++) 
			System.out.println(pilaFija1.delete());

		System.out.println("Pila pila2:");
		for(int i=0; i<8; i++) 
			System.out.println(pilaFija2.delete());
		
		ColaDinamica pilaDinamica1 = new ColaDinamica(5);
		ColaDinamica pilaDinamica2 = new ColaDinamica(8);
		
		for(int i=0; i<12; i++){
			pilaDinamica1.add(i);
		}
		
	    for(int i=0; i<20; i++){
	    	pilaDinamica2.add(i);
	    }
	    
	    System.out.println("Pila pilaDinamica1:");
	    System.out.println("-------------------");
	    
	    for(int i=0; i<12; i++) {
	       System.out.println(pilaDinamica1.delete());
	    }
	    
	    System.out.println("Pila pilaDinamica2:");
	    System.out.println("-------------------");
	    
	    for(int i=0; i<20; i++){ 
	       System.out.println(pilaDinamica2.delete());
	    }
	    
	    // utilizamos una variable de tipo IpilaDeEnteros para guardar tanto la pila fija como la dinámica
	    
	    Dinamizable pila;
	    ColaDinamica dinamica = new ColaDinamica(5);
	    PilaFija fija = new PilaFija(8);
	    
	    pila = dinamica; // apunta a la pila dinamica
	    // apilamos unos cuantos numeros
	    for(int i=0; i<12; i++) pila.add(i);
	    
	    pila = fija; // apunta a la pila fija
	    // apilamos unos cuantos numeros
	    for(int i=0; i<8; i++) pila.add(i);
	    
	    pila = dinamica; // apunta a la pila dinamica
	    System.out.println("\nValores en pila dinamica:");
	    System.out.println("------------------------:");
	    
	    for(int i=0; i<12; i++) 
	        System.out.println(pila.delete());
	    
	    pila = fija; // apunta a la pila fija
	    System.out.println("\nValores en pila fija:");
	    System.out.println("--------------------:");
	    
	    for(int i=0; i<8; i++) 
	        System.out.println(pila.delete());
	}// main
}
