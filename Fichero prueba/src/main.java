import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// File fichero = new File("Prueba");
		// fichero.setWritable(true);
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("Prueba");
			pw = new PrintWriter(fichero);
			for(int i = 0; i<10;i++){
				Clase clase = new Clase(i+"");
				pw.print("Linea" + (i+1));
				pw.print(clase);
				if(i!=9){
					pw.print("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero) {
					fichero.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
