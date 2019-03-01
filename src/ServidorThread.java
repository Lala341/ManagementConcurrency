public class ServidorThread extends Thread {

	private static Buffer buffer;

	public ServidorThread(Buffer buffer) {
		ServidorThread.buffer = buffer;
	}

	@Override
	public void run() {

		boolean disponible = true;

		while(buffer.darNumCliente() != 0){
			Mensaje mensaje = ServidorThread.buffer.retirar();		
			if (mensaje != null)
			{
				synchronized (mensaje) {
					mensaje.notify();
				}
			}
		}
		System.out.println("Servidor retirado");
	}

}
