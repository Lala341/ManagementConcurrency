
public class Mensaje {

	private int mensaje;
	private int respuesta;

	public Mensaje(int n) {
		this.mensaje = n;
	}

	public int darMensaje(){
		return mensaje;
	}

	public int darRespuesta(){
		return respuesta;
	}

	public void responderMensaje(){	
		this.respuesta = this.mensaje + 1;	
	}


}
