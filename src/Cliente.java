
public class Cliente extends Thread{

	private int numMensajes;
	private static Buffer buffer;

	public Cliente(int i, Buffer buffer) {
		this.numMensajes = i;
		Cliente.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < numMensajes; i++) {
			Mensaje mensaje = new Mensaje((int) (Math.random()*100));
			buffer.almacenar(mensaje);
			try {
				synchronized (mensaje) {
					mensaje.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		buffer.retirarCliente();	
		System.out.println("Cliente retirado");
	}

}
