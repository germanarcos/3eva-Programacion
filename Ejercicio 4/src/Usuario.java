
public class Usuario implements Comparable {
	private String nombre;
	private String contraEncriptada;
	private boolean admin;

	public Usuario(String nombre, String contraEncriptada, boolean admin) {
		this.nombre = nombre;
		this.contraEncriptada = contraEncriptada;
		this.admin = admin;
	}

	public Usuario(String nombre, String contraEncriptada) {
		this.nombre = nombre;
		this.contraEncriptada = contraEncriptada;
		admin = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraEncriptada() {
		return contraEncriptada;
	}

	public void setContraEncriptada(String contraEncriptada) {
		this.contraEncriptada = contraEncriptada;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	@Override
	public int compareTo(Object usr) {
		// TODO Auto-generated method stub
		if (usr instanceof Usuario){
			return nombre.compareTo(((Usuario)usr).getNombre());
		}
		return 0;
	}
	
	public String toString(){
		String devuelve = "Usuario: "+nombre+"\n";
		if(isAdmin()){
			devuelve+="Admin\n";
		}
		return devuelve;
	}
}
