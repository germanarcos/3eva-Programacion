import java.util.Arrays;

public class GestionLibroAutor {
	public static void main(String[] args) {
		Libro[] libros = null;
		Autor[] autores = null;
		String  nombre = "", titulo = "";
		Comentable[] comentarios = null;

		Integer opcion, cuantosLibros;

		libros = new Libro[10];
		autores = new Autor[10];
		comentarios = new Comentable[20];

		creaAutores(autores, comentarios);
		creaLibros(autores,libros, comentarios);
		
		do {
			
			libros = ampliaLibros(libros);// amplia los libros si es necesario
			autores = ampliaAutores(autores);// amplia los autores si es necesario
			comentarios = ampliaComentarios(comentarios);// amplia los comentarios si es necesario
			opcion = menu();//muestra menu y lee la opción

			switch (opcion) {
			case 1: // creamos un autor guardándolo en la posición posAutor
				creaAutor( autores, comentarios);					
				break;
			case 2: // creamos un libro guardándolo en la posición posLibro
				if(Autor.getSiguiente()==0){
					Leer.mostrarEnPantalla("Debe crear antes al menos un autor");
				} else{
					crearLibros( libros, autores, comentarios);					
				}
				break;
			case 3: // modificar autor 				
				if(Autor.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay autores en la lista");
				}else {
					listarAutores(autores);
					nombre = Leer.pedirCadena("Nombre del autor a modificar (* para terminar)");
					if (!nombre.equals("*")) {
						modificarAutor(autores, nombre);
					}
				}
				break;
			case 4: // modificar libro
				if(Libro.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay libros en la lista");
				}else {
					listarLibros(libros);
					titulo = Leer.pedirCadena("Titulo del libro a modificar (* para terminar)");
					if (!titulo.equals("*")) {
						modificarLibro(libros, titulo, autores);
					}
				}
				break;
			case 5: // listar autores
				if(Autor.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay autores en la lista");
				}else {
					listarAutores(autores);
				}

				break;
			case 6: // listar libros
				if(Libro.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay libros en la lista");
				}else {
					listarLibros(libros);
				}
				break;
			case 7: // listar comentarios
				if(Libro.getSiguiente()+ Autor.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay comentarios en la lista");
				}else {
					listarComentarios(comentarios);
				}
				break;
			case 8: // introducir comentarios
				if(Libro.getSiguiente()+ Autor.getSiguiente()==0){
					Leer.mostrarEnPantalla("No hay autores ni libros que comentar");
				}else {
					introComentarios(comentarios);
				}
				break;
			case 9: // ordena vectores
				Leer.mostrarEnPantalla("\n1- Ordena autores.\n2- Ordena libros.\n3- Ordena Comentarios.\n"
                + "0- Salir.");
				opcion = Leer.pedirEntero("\nElija opcion","[0-3]");
				switch (opcion)
				{
				case 1:
					Arrays.sort(autores,0, Autor.getSiguiente());
					break;
				case 2:
					Arrays.sort(libros,0, Libro.getSiguiente());
					break;
				case 3:
					Arrays.sort(comentarios,0, sigComentario()+1);
					break;
				}
				break;
			}
		} while (opcion != 0);

	}

	private static Integer menu() {
		Integer opcion;
		Leer.mostrarEnPantalla("\n1- Crear autor.\n2- Crear libro.\n3- Modificar autor.\n" + "4- Modificar libro.\n" + "5- Listado de autores.\n"
			+ "6- Listado de libros.\n7- Listado de comentarios.\n8- Introducir comentarios.\n9- Ordena vectores.\n" + "0- Salir.");
		opcion = Leer.pedirEntero("\nElija opcion","[0-9]");
		return opcion;
	}//menu y opción

	private static Autor[] ampliaAutores(Autor[] autores) {
		if(Autor.getSiguiente()>0.8*autores.length){
			autores=Arrays.copyOf(autores, autores.length+10);
		}
		return autores;
	}// amplía el vector de autores cuando se supera el 80%

	private static Libro[] ampliaLibros(Libro[] libros) {
		if(Libro.getSiguiente()>0.8*libros.length){
			libros=Arrays.copyOf(libros, libros.length+10);
		}
		return libros;
	}// amplía el vector de libros cuando se supera el 80%

	private static Comentable[] ampliaComentarios( Comentable[] comentarios) {
		if(Libro.getSiguiente()+Autor.getSiguiente()>0.8*comentarios.length){
			comentarios=Arrays.copyOf(comentarios, comentarios.length+10);
		}
		return comentarios;
	}// amplía el vector de libros cuando se supera el 80%
	private static void creaAutor(Autor[] autores, Comentable [] comentarios){
		Autor autor;
		String nombreAutor, email, sexo;
		Character letraSexo;
		Integer indiceAutor;
		// Comprobar si el autor ya está en el vector
		nombreAutor = Leer.pedirCadena("Nombre del autor?");
		indiceAutor = buscarAutor(autores, nombreAutor);// devuelve la posicion del nombre o -1 si no está
		if (indiceAutor == -1) {
			// creamos el autor y lo guardamos en el vector
			email = Leer.pedirCadena("Email autor?","^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			sexo = Leer.pedirCadena("Genero autor: (m)asculino (f)emenino ?","^[mMfF]$");
			letraSexo = sexo.charAt(0);
			autor = new Autor(nombreAutor, email, letraSexo);
			autores[Autor.getSiguiente()-1] = autor;
			comentarios[sigComentario()]=autor;
			Leer.mostrarEnPantalla("Autor creado correctamente");
		} else {// el autor ya existe
			Leer.mostrarEnPantalla("El autor ya existe");
		}

	}//crear autor

	public static void crearLibros(Libro[] libros, Autor[] autores, Comentable []comentarios) {
		int indiceAutor, indiceTitulo;
		String titulo, nombreAutor;
		Autor autor;
		Libro libro;
		Double precio;
		Integer cantidad;
		// Comprobar si el libro ya esta en el vector 
		titulo = Leer.pedirCadena("Titulo del libro?");
		indiceTitulo = buscarTitulo(libros, titulo);
		while (indiceTitulo != -1) {
			titulo = Leer.pedirCadena("El libro ya existe y no se puede repetir. Titulo del libro?");
			indiceTitulo = buscarTitulo(libros, titulo);
		}
		// Comprobar si el autor ya esta en el vector
		nombreAutor = Leer.pedirCadena("Nombre del autor?");
		indiceAutor = buscarAutor(autores, nombreAutor);// devuelve la posicion del nombre o -1 si no lo encuentra
		if (indiceAutor == -1) {
			Leer.mostrarEnPantalla("El autor no existe");
		} else {// se crea el libro con ese autor
			autor = autores[indiceAutor];
			//pedir el resto de los datos del libro
			precio = Leer.pedirDouble("Precio del libro?","^\\d+[.][0-9]?[0-9]?$");//decimal con un máximo de dos posiciones decimales
			cantidad = Leer.pedirEntero("Cantidad de libros?","^\\d+$");//entero positivo
			libro = new Libro(titulo, autor, precio, cantidad);
			libros[Libro.getSiguiente()-1] = libro;
			comentarios[sigComentario()]= libro;
			Leer.mostrarEnPantalla("Libro creado correctamente");
		}
	}// crear un Libro

	
	public static void creaAutores(Autor []autores, Comentable [] comentarios){
		// creamos 3 autores
		autores[Autor.getSiguiente()]= new Autor("Rosa Montero", "rmontero@gmail.com", 'f');
		comentarios[sigComentario()]=autores[Autor.getSiguiente()-1];
		autores[Autor.getSiguiente()]= new Autor("Juan Jose Millas", "jjmillas@hotmail.es",'m');
		comentarios[sigComentario()]=autores[Autor.getSiguiente()-1];
		autores[Autor.getSiguiente()]= new Autor("Almudena Grandes", "ag@gmail.com",'f');
		comentarios[sigComentario()]=autores[Autor.getSiguiente()-1];
	}// crea 3 autores
	
	public static void creaLibros(Autor[] autores, Libro[] libros, Comentable[] comentarios){
		// creamos 5 libros
		libros[Libro.getSiguiente()]= new Libro("Lágrimas en la lluvia", autores[0],19.95 ,100000);
		comentarios[sigComentario()]=libros[Libro.getSiguiente()-1];
		libros[Libro.getSiguiente()]= new Libro("La ridícula idea de no volver a verte", autores[0], 15., 200000);
		comentarios[sigComentario()]=libros[Libro.getSiguiente()-1];
		libros[Libro.getSiguiente()]= new Libro("Desde la sombra", autores[1], 25., 75000);
		comentarios[sigComentario()]=libros[Libro.getSiguiente()-1];
		libros[Libro.getSiguiente()]= new Libro("Malena es un nombre de tango", autores[2], 22., 100000);
		comentarios[sigComentario()]=libros[Libro.getSiguiente()-1];
		libros[Libro.getSiguiente()]= new Libro("Las edades de Lulú", autores[2], 30., 200000);
		comentarios[sigComentario()]=libros[Libro.getSiguiente()-1];
	}// crea 5 libros

	public static int buscarAutor(Autor[] autores, String nombre) {
		// devuelve la posicion del nombre o -1 si no lo encuentra
		int indiceAutor=-1, i=0;
		while (autores[i] != null && i < autores.length && indiceAutor==-1) {
			if (autores[i].getNombre().equals(nombre)) {
				indiceAutor = i;
			}
			i++;
		}
		return indiceAutor;
	}//buscar autor

	public static int buscarTitulo(Libro[] libros, String titulo) {
		// devuelve el indice de la posición donde está ese título o el valor -1 si no lo encuentra
		int i = 0, indiceLibro = -1;
		while ( libros[i] != null && i < libros.length && indiceLibro==-1) {
			if (libros[i].getTitulo().equals(titulo)) {
				indiceLibro = i;
			}
			i++;
		}
		return indiceLibro;
	}//buscar libro
	
	public static void listarAutores(Autor[] autores) {
		int indiceAutor;
		Leer.mostrarEnPantalla("\nListado de autores");
		Leer.mostrarEnPantalla("-------------------");
		for (indiceAutor = 0; indiceAutor < autores.length && autores[indiceAutor] != null; indiceAutor++) {
			Leer.mostrarEnPantalla(autores[indiceAutor].cadenaAutor());
		}
		Leer.mostrarEnPantalla("------------------------------------------------------------------");
	}//listarAutores

	public static void listarLibros(Libro[] libros) {
		int indiceLibro;
		Leer.mostrarEnPantalla("\nListado de libros");
		Leer.mostrarEnPantalla("------------------");
		for (indiceLibro = 0; indiceLibro < libros.length && libros[indiceLibro] != null; indiceLibro++) {
			Leer.mostrarEnPantalla(libros[indiceLibro].cadenaLibro());
		}
		Leer.mostrarEnPantalla("------------------------------------------------------------------");
	}//listarLibros

	public static int listarComentarios(Comentable[] comentarios) {
		int indiceComen;
		Leer.mostrarEnPantalla("\nListado de libros");
		Leer.mostrarEnPantalla("------------------");
		for (indiceComen = 0; indiceComen < comentarios.length && comentarios[indiceComen] != null; indiceComen++) {
			Leer.mostrarEnPantalla((indiceComen+1) + " " +comentarios[indiceComen].comentario());
		}
		Leer.mostrarEnPantalla("------------------------------------------------------------------");
		return indiceComen-1;
	}//listarLibros
	public static void modificarAutor(Autor [] autores, String nombre){
		Integer indiceAutor, indice2;
		String email, sexo;
		Autor autor;
		
		//posicion del autor o -1 si no está
		indiceAutor = buscarAutor(autores, nombre);
		if(indiceAutor != -1){
			autor=autores[indiceAutor];
			nombre = Leer.pedirCadena("Nuevo nombre (* no cambiar)");
			if(!nombre.equals("*")){
				indice2 = buscarAutor(autores, nombre);//verificamos que el nuevo nombre no está repetido
				if(indice2!= -1){//nombre ya existente
					Leer.mostrarEnPantalla("El nuevo nombre ya está en la lista, posición "+indice2+" No se puede cambiar");
				}else{//nombre nuevo
					autor.setNombre(nombre);	
				}
			}
			email = Leer.pedirCadena("Nuevo email (* no cambiar)","^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|[*]");
			if(!email.equals("*")){
				autor.setEmail(email);
			}
			sexo=Leer.pedirCadena("Nuevo sexo ","[mMfF]");
			autor.setSexo(sexo.charAt(0));
		}else{
			Leer.mostrarEnPantalla("Error. El autor " + nombre + " no existe");
		}
	}//modificarAutor

	public static void modificarLibro(Libro [] libros, String titulo, Autor [] autores){
		Integer indiceAutor, indice2, indiceLibro;
		Autor autor;
		Libro libro;
		Double precio;
		Integer cantidad;
		String nombre;
		
		//posicion del libro, -1 si no lo encuentra
		indiceLibro = buscarTitulo(libros, titulo); 
		if(indiceLibro != -1){
			libro=libros[indiceLibro];
			titulo = Leer.pedirCadena("Nuevo título (* no cambiar)");
			if(!titulo.equals("*")){//si no se teclea * se mantiene el título
				indice2 = buscarTitulo(libros, titulo);//verificamos que el nuevo título no está repetido
				if(indice2!= -1){//el título ya existe (puede ser el mismo)
					Leer.mostrarEnPantalla("El nuevo título ya está en la lista, posición "+indice2+" No se puede cambiar");
				}else{//el nuevo título es nuevo de verdad
					libro.setTitulo(titulo);	
				}
			}
			precio = Leer.pedirDouble("Precio "+libro.getPrecio()+" Nuevo precio del libro ","^\\d+[.][0-9]?[0-9]?$");
			libro.setPrecio(precio);
			cantidad = Leer.pedirEntero("Cantidad " +libro.getCantidad()+" Nueva cantidad de libros ","^\\d+$");
			libro.setCantidad(cantidad);
			autor=libro.getAutor();
			nombre=Leer.pedirCadena("Nombre del nuevo autor (* mantener el actual)");
			if(!nombre.equals("*")){// se ha dado un nuevo nombre de autor
				indice2=buscarAutor(autores,nombre);
				if (indice2==-1){//autor no encontrado
					Leer.mostrarEnPantalla("Ese autor no está en la lista. No se puede cambiar");
				} else {//nuevo autor encontrado
					libro.setAutor(autores[indice2]);
				}
			}
		}else{
			Leer.mostrarEnPantalla("Error. El libro " + titulo + " no existe");
		}		
	}//modificarLibro

	public static void introComentarios(Comentable [] comentarios){
		int indice, indice2;
		String texto;
		Autor autor;
		indice2 = listarComentarios(comentarios);
		do{
			indice = Leer.pedirEntero("Introduce el número de línea sobre la que introducir el comentario: ", "^\\d+$");
		} while(indice > indice2+1 || indice < 1);
		//posicion del autor o -1 si no está

		texto = Leer.pedirCadena("Nuevo comentario (* no cambiar)");
		if(!texto.equals("*")){
			comentarios[indice-1].comentar(texto);	
		}
	}//modificarComentario

	
	public static int sigComentario(){
		int i = 0;
		if (Autor.getSiguiente()!=0 && Libro.getSiguiente()==0){
			i = Autor.getSiguiente()-1;
		}
		if (Libro.getSiguiente()!=0 && Autor.getSiguiente()==0){
			i = Libro.getSiguiente()-1;
		}
		if (Libro.getSiguiente()!=0 && Autor.getSiguiente()!=0){
			i = Libro.getSiguiente()+Autor.getSiguiente()-1;
		}
		return i;
	}
}// class 
