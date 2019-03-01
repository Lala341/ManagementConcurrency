import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		int numClientes = 0;
		int numServidores = 0;
		int bufferTam = 0;
		int numMensajesCliente = 0;


		File file = new File("./data/data.txt");  
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String linea;

			while ((linea = br.readLine()) != null) {
				linea=linea.replace(" ", "");

				String[] palabras = linea.split("=");

				switch (palabras[0]) {
				case "numClientes":
					numClientes = Integer.parseInt(palabras[1]);
					break;

				case "numServidores":
					numServidores = Integer.parseInt(palabras[1]);
					break;

				case "numMensajesCliente":
					numMensajesCliente = Integer.parseInt(palabras[1]);
					break;
				case "bufferTam":
					bufferTam = Integer.parseInt(palabras[1]);
					break;
				} 
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

		Buffer buffer = new Buffer(bufferTam, numClientes);

		for(int i = 0; i < numClientes; i++) {
			Cliente c = new Cliente(numMensajesCliente,  buffer);   
			c.start();
		}
		for(int i = 0; i < numServidores; i++) {
			ServidorThread s = new ServidorThread(buffer);    
			s.start();
		}
	}

}
