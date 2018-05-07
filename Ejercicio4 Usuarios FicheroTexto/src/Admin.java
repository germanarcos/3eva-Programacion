import java.util.TreeMap;

public class Admin extends Usuario {

	public Admin(String nombre, String contraEncriptada) {
		super(nombre, contraEncriptada);
	}

	public String ban(Usuario user) {
		if (user.baneado) {
			return ("El usuario " + user.getNombre() + " ya estaba baneado.");

		} else {
			user.baneado = true;
			return ("El usuario " + user.getNombre()) + " ha sido baneado.";
		}
	}

	public String unban(Usuario user) {
		if (user.baneado) {
			user.baneado = false;
			return ("El usuario " + user.getNombre() + " ha sido unbaneado.");
		} else {
			return ("El usuario " + user.getNombre() + "no estaba baneado.");
		}
	}

	public String toString() {
		String devuelve = "Usuario: " + getNombre() + "\nADMIN\n";
		return devuelve;
	}

	public boolean borrarUsuario(TreeMap<String, Usuario> usuarios, String nombre) {
		if (usuarios.containsKey(nombre)) {
			usuarios.remove(nombre);
			return true;
		} else {
			return false;
		}
	}

	public Usuario devolverUsuario(String nombre, TreeMap<String, Usuario> usuarios) {
		for (String i : usuarios.keySet()) {
			if (i.equals(nombre)) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
	public void listadoUsuarios(TreeMap<String, Usuario> usuarios) {
		for (String usr : usuarios.keySet()) {
			Leer.mostrarEnPantalla(usuarios.get(usr).toString());
		}
	}
}
