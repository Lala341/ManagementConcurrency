import java.net.Socket;

public class ServidorThread extends Thread {
	
	private Socket socketC;
	private int id;
	private Buffer buffer;
	
	public ServidorThread(Socket socketC,int id, Buffer buffer) {
		// TODO Auto-generated constructor stub
		this.socketC=socketC;
		this.id=id;
		this.buffer=buffer;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean disponible=true;
		while(disponible){
			Mensaje actual=this.buffer.retirar();
			
			if(actual!=null){
				int m=actual.darMensaje();
				actual.responderMensaje(m+1);
				actual.notify();
			}
			this.yield();
			
		}
		
	}

}
