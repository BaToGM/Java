import java.io.BufferedReader;
import java.util.concurrent.BlockingQueue;

public class Generador implements Runnable {
	
	private int id;
	private BlockingQueue<String> cp;
	private BufferedReader bf_leer;
	
	
	public Generador (int id, BlockingQueue<String> cp, BufferedReader bf_leer) {
		this.id = id;
		this.cp = cp;
		this.bf_leer = bf_leer;
		
	}

	@Override
	public void run() {
		
		try {
			while (bf_leer.ready()) {
				String msj = bf_leer.readLine();
				System.out.println(msj);
				cp.put("Productor mete en la cola el siguiente mensaje: "+ msj);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
