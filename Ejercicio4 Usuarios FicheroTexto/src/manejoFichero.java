//import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

public class manejoFichero {

	public static boolean escribir(TreeMap<String, Usuario> usuarios) {
		String nombre;
		nombre = Leer.pedirCadena("¿Cómo quieres llamar al fichero?");
		try {
			FileOutputStream fichero = new FileOutputStream(nombre);
			DataOutputStream salida = new DataOutputStream(fichero);
			for (String i : usuarios.keySet()) {
				Usuario temp = usuarios.get(i);
				if (temp instanceof Admin) {
					salida.writeUTF("Admin");
				} else {
					salida.writeUTF("Usuario");
				}
				salida.writeUTF(temp.getNombre());
				salida.writeUTF(temp.getContraEncriptada());
				salida.writeBoolean(temp.isBaneado());
			}
			salida.writeUTF("FINAL");
			salida.close();
			fichero.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean obtener(TreeMap<String, Usuario> usuarios) {
		FileInputStream ficheroLectura = null;
		DataInputStream entrada = null;
		String ruta;
		File directorio = new File("./");
		if (directorio.isDirectory()) {
			for (int i = 0; i < directorio.list().length; i++) {
				Leer.mostrarEnPantalla(directorio.list()[i]);
			}
		}
		ruta = Leer.pedirCadena("¿Que fichero quieres utilizar?");
		try {
			boolean hayDatos = true;
			Usuario usuario;
			Admin admin = null;
			ficheroLectura = new FileInputStream(ruta);
			entrada = new DataInputStream(ficheroLectura);
			usuarios.clear();
			String modo;
			while (hayDatos) {
				try {
					modo = entrada.readUTF();
					if (modo.equals("Admin")) {
						admin = new Admin(entrada.readUTF(), entrada.readUTF());
						entrada.readBoolean();
						usuarios.put(admin.getNombre(), admin);
					} else if (modo.equals("Usuario")) {
						usuario = new Usuario(entrada.readUTF(), entrada.readUTF());
						if (entrada.readBoolean()) {
							admin.ban(usuario);
						}
						usuarios.put(usuario.getNombre(), usuario);
					}
					if (modo.equals("FINAL")) {
						hayDatos = false;
					}
				} catch (Exception algoMal) {
					algoMal.printStackTrace();
					hayDatos = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ficheroLectura.close();
				entrada.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return true;
	}
}
