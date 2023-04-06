import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class Receptor implements Runnable{
	
	private BlockingQueue<String> cola;
	private int id;
	private PrintWriter pw;
	
	public Receptor (BlockingQueue<String> cola, int id, PrintWriter pw) {
		this.cola = cola;
		this.id = id;
		this.pw = pw;
		
	}

	@Override
	public void run() {
		
		while (true) {
			String msj;
			try {
				msj = cola.take();
				pw.println(msj);
				pw.flush(); //Para limpiar el buffer
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}

}
