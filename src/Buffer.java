import java.util.ArrayList;

public class Buffer {

	private ArrayList<Mensaje> buffer;
	private int n;
	private int numClientes;
	Object lleno;
	Object vacio;
	
	public Buffer(int n, int m) {
		this.n = n;
		this.numClientes=m;
		buffer = new ArrayList<Mensaje>();
		lleno = new Object();
		vacio = new Object();
	}
	
	public void almacenar(Mensaje i) {
		synchronized (lleno) {
			while (buffer.size() == n) {
				try {
				System.out.println("Buffer lleno");
				lleno.wait();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		}
		synchronized (this) {
		buffer.add(i);
		}
		synchronized (vacio) {
		vacio.notify();
		}
	}
	public Mensaje retirar() {
		
		synchronized (vacio) {
			while (buffer.size() == 0) {
//				try {
//				System.out.println("Buffer vacio");
//				vacio.wait();
//				
//				} catch (InterruptedException e) {
//				e.printStackTrace();
//				}
				//Espera activa
				System.out.println("Buffer vacio");
				return null;
			}
		}
		Mensaje i;
		synchronized (this) {
			i = buffer.remove(0);
		}
		synchronized (lleno) {
			lleno.notify();
		}
			return i;
		}
	
	public void retirarCliente(){
		this.numClientes--;
	}
}
