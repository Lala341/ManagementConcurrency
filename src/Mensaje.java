
public class Mensaje {
	
	
	private int mensaje;
	private int respuesta;
	
	public Mensaje(int n) {
		// TODO Auto-generated constructor stub
		this.mensaje=n;
	}
	public int darMensaje(){
		return mensaje;
	}
	public void responderMensaje(int m){
		
		this.respuesta=m;
		
	}

}
