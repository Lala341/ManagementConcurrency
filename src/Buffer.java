import java.util.ArrayList;

public class Buffer {

	private ArrayList<Mensaje> buffer;
	private int n;
	private int numClientes;
	Object lleno;

	public Buffer(int n, int m) {
		this.n = n;
		this.numClientes = m;
		buffer = new ArrayList<Mensaje>();
		lleno = new Object();
	}

	public int darNumCliente() {
		return numClientes;
	}

	public void almacenar(Mensaje mensaje) {
		synchronized (lleno) {
			while (buffer.size() == n) {
				try {
					System.out.println("Buffer lleno: espera pasiva del cliente");
					lleno.wait(); //Espera pasiva
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		synchronized (this) {
			System.out.println("Mensaje almacenado: " + mensaje.darMensaje());
			buffer.add(mensaje);
		}
	}

	public Mensaje retirar() {
		//Espera activa
		while (buffer.size() == 0 && numClientes != 0) {
			Thread.yield(); //Cede el procesador
		}


		Mensaje mensaje = null;
		synchronized (this) {
			if(numClientes != 0 && buffer.size() != 0)
			{
				try{
					mensaje = buffer.remove(0);
					mensaje.responderMensaje();
					System.out.println("Respuesta mensaje retirado: " + mensaje.darRespuesta());
					synchronized (lleno) {
						lleno.notify(); //Despierta a los clientes que quieren almacenar un mensaje
					}
				}catch(Exception e){
				}
			}	
		}
		return mensaje;

	}

	public void retirarCliente() {
		synchronized(this){
			numClientes--;
		}	
	}
}
