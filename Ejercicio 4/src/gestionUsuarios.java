import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gestionUsuarios {

	public static void main(String[] args) {
		Integer opcion;
		TreeMap<Usuario, String> usuarios = new TreeMap<Usuario, String>();
		crearAdmin(usuarios);
		do {
			/*
			 * TODO Gestion de usuario actual, limitar acciones a Usuarios no administradores, inicio/cierre de sesion, creacion de cuentas, modificacion de contrasenas, lista de usuarios.
			 * admin puede: crear, borrar, cambiar contrasenas, obtener un listado de usuarios, conectarse, desconectarse.
			 * user puede: borrar su cuenta, cambiar su contrasena, conectarse, desconectarse.
			 * sin cuenta puede: crear cuenta, conectarse
			 */
			opcion = menu();
			switch (opcion) {
			case 1:
				if (insertarUsuario(usuarios)) {
					Leer.mostrarEnPantalla("Se ha creado el usuario");
				} else {
					Leer.mostrarEnPantalla("No se ha podido crear el usuario");
				}
				break;
			case 2:
				if (borrarUsuario(usuarios)) {
					Leer.mostrarEnPantalla("Se ha borrado el usuario");
				} else {
					Leer.mostrarEnPantalla("No se ha podido borrar el usuario");
				}
				break;
			case 3:
				if (modificarClave(usuarios)) {
					Leer.mostrarEnPantalla("Se ha modificado la clave");
				} else {
					Leer.mostrarEnPantalla("No se ha podido modificar la clave");
				}
				break;
			case 4:
				if (comprobar(usuarios)) {
					Leer.mostrarEnPantalla("Sesi�n iniciada");
				} else {
					Leer.mostrarEnPantalla("No se ha podido iniciar sesi�n");
				}
				break;
			case 5:
				listadoUsuarios(usuarios);
				break;
			case 0:
				Leer.mostrarEnPantalla("Saliendo...");
				break;
			default:
				Leer.mostrarEnPantalla("No has escogido una opci�n correcta");
				break;
			}
		} while (opcion != 0);
	}

	public static String codificar(String psswd) {
		String actual = "";
		for (int i = 0; i < psswd.length(); i++) {
			actual += (char) (psswd.charAt(i) + 2);
		}
		return actual;
	}

	public static void crearAdmin(TreeMap<Usuario, String> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del administrador");
		String pwd;
		do {
			pwd = Leer.pedirCadena("Introduce una contrase�a");
		} while (!contrasenaValida(pwd));
		pwd = codificar(pwd);
		Usuario admin = new Usuario(nombre, pwd, true);
		usuarios.put(admin, pwd);
	}

	public static boolean comprobar(TreeMap<Usuario, String> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario");
		Usuario usr = devolverUsuario(nombre, usuarios);
		if (usr != null) {
			String prueba = Leer.pedirCadena("Introduce la contrase�a");
			String psswd = usuarios.get(usr);
			String contra = "";
			for (int i = 0; i < psswd.length(); i++) {
				contra += (char) (psswd.charAt(i) - 2);
			}
			if (contra.equals(prueba)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static Integer menu() {
		return Leer.pedirEntero("1. Insertar usuario, clave\n2. Borrar usuarios\n3. Modificar claves de un usuario"
				+ "\n4. Iniciar sesion\n5. Listado de todos los usuarios\n0. Salir");
	}

	public static void listadoUsuarios(TreeMap<Usuario, String> usuarios) {
		for (Usuario usr : usuarios.keySet()) {
			Leer.mostrarEnPantalla(usr.toString());
		}
	}

	public static boolean modificarClave(TreeMap<Usuario, String> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario a modificar");
		Usuario usr = devolverUsuario(nombre, usuarios);
		if (usr != null) {
			String pwd = Leer.pedirCadena("Introduce la nueva contrase�a");
			pwd = codificar(pwd);
			usr.setContraEncriptada(pwd);
			usuarios.put(usr, pwd);
			return true;
		}
		return false;

	}

	public static boolean insertarUsuario(TreeMap<Usuario, String> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario");
		if (encontrar(nombre, usuarios)) {
			Leer.mostrarEnPantalla("Ya existe un usuario con ese nombre");
			return false;
		}
		String pswd = Leer.pedirCadena("Introduce la contrase�a");
		if (contrasenaValida(pswd)) {
			Usuario usr = new Usuario(nombre, codificar(pswd));
			usuarios.put(usr, codificar(pswd));
			return true;
		} else {
			return false;
		}
	}

	public static boolean encontrar(String nombre, TreeMap<Usuario, String> usuarios) {
		for (Usuario i : usuarios.keySet()) {
			if (i.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	public static Usuario devolverUsuario(String nombre, TreeMap<Usuario, String> usuarios) {
		for (Usuario i : usuarios.keySet()) {
			if (i.getNombre().equals(nombre)) {
				return i;
			}
		}
		return null;
	}

	public static boolean borrarUsuario(TreeMap<Usuario, String> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario a borrar");
		Usuario usr = devolverUsuario(nombre, usuarios);
		if (usr != null) {
			usuarios.remove(usr);
			return true;
		} else {
			return false;
		}
	}

	public static boolean contrasenaValida(String contra) {
		// Expresi�n regular para que contenga solo alfanumericos
		Pattern patron = Pattern.compile("[a-z0-9A-Z��]+");
		Matcher comprobar = patron.matcher(contra);
		return comprobar.matches();
	}
}
