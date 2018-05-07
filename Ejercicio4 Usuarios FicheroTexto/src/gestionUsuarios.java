import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gestionUsuarios {

	public static void main(String[] args) {
		Integer opcion;
		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
		crearAdmin(usuarios);
		Usuario actual = null;
		boolean exit = false;
		do {
			if (actual == null) {
				opcion = menuNoLog();
				switch (opcion) {
				case 1:
					crearUsuario(usuarios);
					break;
				case 2:
					actual = conectarse(usuarios);
					break;
				case 0:
					exit = true;
					break;
				}
			} // actual == null aka no hay sesiones iniciadas
			else if (actual.baneado == true) {
				usuarioActual(actual);
				Leer.mostrarEnPantalla("EL USUARIO " + actual.getNombre().toUpperCase() + " ESTA BANEADO");
				actual = null;
			} else if (actual instanceof Admin) {
				usuarioActual(actual);
				opcion = menuAdmin();
				switch (opcion) {
				case 1:
					actual.cambiarContrasena();
					break;
				case 2:
					usuarioActual(actual);
					switch (opcionesAdmin()) {
					case 1: {
						String nombre = Leer.pedirCadena("¿Que usuario quieres banear?");
						if (nombre.equals(actual.getNombre())) {
							Leer.mostrarEnPantalla("No puedes banearte a ti mismo, admin.");
						} else if (usuarioExiste(usuarios, nombre)) {
							Leer.mostrarEnPantalla(
									((Admin) actual).ban(((Admin) actual).devolverUsuario(nombre, usuarios)));
						} else {
							Leer.mostrarEnPantalla("No se ha encontrado dicho usuario");
						}
					}
						break;
					case 2: {
						String nombre = Leer.pedirCadena("¿Que usuario quieres unbanear?");
						if (nombre.equals(actual.getNombre())) {
							Leer.mostrarEnPantalla("No puedes unbanearte a ti mismo, admin.");
						} else if (usuarioExiste(usuarios, nombre)) {
							Leer.mostrarEnPantalla(
									((Admin) actual).unban(((Admin) actual).devolverUsuario(nombre, usuarios)));
						} else {
							Leer.mostrarEnPantalla("No se ha encontrado dicho usuario");
						}
					}
						break;
					case 3: {
						String nombre = Leer.pedirCadena("¿Que usuario quieres borrar?");
						if (nombre.equals(actual.getNombre())) {
							Leer.mostrarEnPantalla("No puedes borrar tu propia cuenta, admin.");
						} else if (usuarioExiste(usuarios, nombre)) {
							if (((Admin) actual).borrarUsuario(usuarios, nombre)) {
								Leer.mostrarEnPantalla("El usuario " + nombre + " ha sido borrado");
							} else {
								Leer.mostrarEnPantalla("No se ha podido borrar el usuario " + nombre);
							}
						} else {
							Leer.mostrarEnPantalla("El usuario " + nombre + " no existe.");
						}
					}
						break;
					case 4: {
						String nombre = Leer.pedirCadena("¿A que usuario le quieres cambiar el nombre?");
						if (nombre.equals(actual.getNombre())) {
							Leer.mostrarEnPantalla("No puedes cambiar el nombre de tu cuenta, admin.");
						} else if (usuarioExiste(usuarios, nombre)) {
							Usuario user = ((Admin) actual).devolverUsuario(nombre, usuarios);
							user.cambiarNombre(usuarios);
						} else {
							Leer.mostrarEnPantalla("No se encuentra dicho usuario");
						}
					}
						break;
					case 5: {
						String nombre = Leer.pedirCadena("¿A que usuario le quieres cambiar la contrasena?");
						if (nombre.equals(actual.getNombre())) {
							Leer.mostrarEnPantalla("También puedes cambiar tu contraseña desde el menu anterior.");
						}
						if (usuarioExiste(usuarios, nombre)) {
							Usuario user = ((Admin) actual).devolverUsuario(nombre, usuarios);
							user.cambiarContrasena();
						}
					}
					case 6:
						if (manejoFichero.escribir(usuarios)) {
							Leer.mostrarEnPantalla("Se han guardado los datos");
						} else {
							Leer.mostrarEnPantalla("Algo ha salido mal");
						}
						break;
					case 7:
						if (manejoFichero.obtener(usuarios)) {
							Leer.mostrarEnPantalla("Se han recuperado los datos, cerrando sesion");
							actual = null;
						} else {
							Leer.mostrarEnPantalla("Algo ha salido mal");
						}
						break;
					case 0:
						break;
					default:
						break;
					}// Opciones de admin
					break;
				case 3:
					((Admin) actual).listadoUsuarios(usuarios);
					break;
				case 4:
					actual = null;
					break;
				case 0:
					exit = true;
					break;
				default:
					Leer.mostrarEnPantalla("Has elegido una opcion incorrecta");
				}
			} else if (actual instanceof Usuario) {
				usuarioActual(actual);
				opcion = menuLog();
				switch (opcion) {
				case 1:
					actual.cambiarContrasena();
					break;
				case 2:
					if (actual.cambiarNombre(usuarios)) {
						Leer.mostrarEnPantalla("Tienes que volver a iniciar sesion.");
						actual = null;
					}
					break;
				case 3:
					if (actual.borrarCuenta(usuarios))
						actual = null;
					break;
				case 4:
					actual = null;
					break;
				case 0:
					exit = true;
					break;
				default:
					Leer.mostrarEnPantalla("Has elegido una opcion incorrecta");
				}
			}
		} while (!exit);
	}

	public static int menuNoLog() {
		return Leer.pedirEntero("1. Crear Usuario\n2. Conectarse\n0. Salir");
	}

	public static int menuLog() {
		return Leer.pedirEntero(
				"1. Cambiar tu contraseña\n2. Cambiar tu nombre\n3. Borrar tu cuenta\n4. Desconectarte\n0.Salir");
	}

	public static int menuAdmin() {
		return Leer.pedirEntero(
				"1. Cambiar tu contraseña\n2. Opciones de administracion\n3. Lista de usuarios\n4. Desconectarte\n0.Salir");
	}

	public static int opcionesAdmin() {
		return Leer.pedirEntero(
				"1. Banear usuario\n2. Unbanear usuario\n3. Borrar usuario\n4. Cambiar nombre de otro usuario"
						+ "\n5. Cambiar contraseña de otro usuario\n6. Guardar datos a un fichero"
						+ "\n7. Leer datos de un fichero\n0.Salir");
	}

	public static void crearAdmin(TreeMap<String, Usuario> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del administrador");
		String pwd = Contrasena.nuevaContrasena();
		Usuario admin = new Admin(nombre, pwd);
		usuarios.put(nombre, admin);
	}

	public static boolean usuarioExiste(TreeMap<String, Usuario> usuarios, String nombre) {
		return usuarios.containsKey(nombre);
	}

	public static void crearUsuario(TreeMap<String, Usuario> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario");
		if (usuarioExiste(usuarios, nombre)) {
			Leer.mostrarEnPantalla("Ya existe un usuario con ese nombre");
		} else {
			String pswd = Contrasena.nuevaContrasena();
			Usuario usr = new Usuario(nombre, pswd);
			usuarios.put(nombre, usr);
		}
	}

	public static Usuario conectarse(TreeMap<String, Usuario> usuarios) {
		String nombre = Leer.pedirCadena("Introduce el nombre del usuario");
		if (usuarioExiste(usuarios, nombre)) {
			Usuario user = usuarios.get(nombre);
			if (user.inicioSesion()) {
				Leer.mostrarEnPantalla("Iniciando sesion");
				return user;
			} else {
				Leer.mostrarEnPantalla("La contraseña es incorrecta");
				return null;
			}
		} else {
			Leer.mostrarEnPantalla("El usuario no existe");
			return null;
		}
	}

	public static void usuarioActual(Usuario actual) {
		Leer.mostrarEnPantalla("Sesión iniciada como: " + actual.getNombre());
	}
}
