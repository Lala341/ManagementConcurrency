
public class Cliente extends Thread{
	
	private static int numMensajes;//DUDA STATIC?
	private static Buffer buffer;
	
	public Cliente(int i, Buffer buffer) {
		// TODO Auto-generated constructor stub
		this.numMensajes=i;
		this.buffer=buffer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < numMensajes; i++) {
			Mensaje actual= new Mensaje((int) Math.random());
			buffer.almacenar(actual);
			try {
				actual.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(i==numMensajes-1){
				buffer.retirarCliente();
			}
		}
	}
	
	

}
