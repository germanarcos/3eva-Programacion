import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class manejoFichero {

	public static boolean escribir(TreeMap<String, Usuario> usuarios) {
		String nombre;
		nombre = Leer.pedirCadena("¿Cómo quieres llamar al fichero?");
		try {
			ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(nombre));
			for (String i : usuarios.keySet()) {
				fichero.writeObject(usuarios.get(i));
			}
			fichero.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean obtener(TreeMap<String, Usuario> usuarios) {
		ObjectInputStream ficheroLectura = null;
		String ruta;
		File directorio = new File ("./");
		if (directorio.isDirectory()){
			for(int i = 0;i<directorio.list().length;i++){
				Leer.mostrarEnPantalla(directorio.list()[i]);
			}
		}
		ruta = Leer.pedirCadena("¿Que fichero quieres utilizar?");
		try {
			boolean hayDatos = true;
			Usuario usuario;
			ficheroLectura = new ObjectInputStream(new FileInputStream(ruta));
			usuarios.clear();
			while (hayDatos) {
				try {
					usuario = (Usuario) ficheroLectura.readObject();
					usuarios.put(usuario.getNombre(), usuario);
				} catch (EOFException finFichero) {
					hayDatos = false;
				}
				catch(Exception algoMal){
					algoMal.printStackTrace();
					hayDatos=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ficheroLectura.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return true;
	}
}
