import java.util.TreeMap;
import java.io.Serializable;

public class Usuario implements Serializable{
	private String nombre;
	private String contraEncriptada;
	protected boolean baneado;

	public Usuario(String nombre, String contraEncriptada) {
		this.nombre = nombre;
		this.contraEncriptada = contraEncriptada;
		baneado = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean borrarCuenta(TreeMap<String, Usuario> usuarios) {
		String decision = Leer.pedirCadena(
				"¿Seguro que quieres borrar tu cuenta?\nEste paso es irreversible.\nIntroduce S o s para borrar, cualquier otra cosa para cancelar.");
		if (decision.charAt(0) == 's' || decision.charAt(0) == 'S') {
			usuarios.remove(nombre);
			Leer.mostrarEnPantalla("Tu cuenta ha sido borrada, saliendo...");
			return true;
		} else {
			Leer.mostrarEnPantalla("Cancelando...");
			return false;
		}
	}

	public boolean cambiarNombre(TreeMap<String, Usuario> usuarios) {
		String nombreActual = nombre;
		String futuroNombre = Leer.pedirCadena("Introduce el nombre que quieres");
		if (gestionUsuarios.usuarioExiste(usuarios, futuroNombre)) {
			Leer.mostrarEnPantalla("Ya existe un usuario con ese nombre");
			return false;
		} else {
			nombre = futuroNombre;
			usuarios.remove(nombreActual);
			usuarios.put(futuroNombre, this);
			Leer.mostrarEnPantalla("Se ha cambiado el nombre de usuario.");
			return true;
		}

	}

	protected void cambiarContrasena() {
		contraEncriptada = Contrasena.nuevaContrasena();
	}

	public String toString() {
		String devuelve = "Usuario: " + nombre + "\n";
		if (baneado) {
			devuelve += "BANEADO\n";
		}
		return devuelve;
	}

	public boolean inicioSesion() {
		return Contrasena.comprobar(contraEncriptada);
	}
}
