import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contrasena {
	
	private static String codificar(String psswd) {
		String actual = "";
		for (int i = 0; i < psswd.length(); i++) {
			actual += (char) (psswd.charAt(i) + 2);
		}
		return actual;
	}
	
	
	public static boolean comprobar(String pswd){
		String prueba = Leer.pedirCadena("Introduce la contraseña");
		if(codificar(prueba).equals(pswd)){
			return true;
		}else
			return false;
	}
	
	//Comprueba que sea una contrasena correcta
	private static boolean contrasenaValida(String contra) {
		// Expresión regular para que contenga solo alfanumericos
		Pattern patron = Pattern.compile("[a-z0-9A-ZñÑ]+");
		Matcher comprobar = patron.matcher(contra);
		//Metodo clase leer
		return comprobar.matches();
	}
	
	
	//Devuelve contrasena codificada
	public static String nuevaContrasena(){
		String pswd;
		String encriptada;
		do {
			pswd = Leer.pedirCadena("Introduce la contraseña");
		} while (!Contrasena.contrasenaValida(pswd));
		encriptada=codificar(pswd);
		return encriptada;
	}

}
