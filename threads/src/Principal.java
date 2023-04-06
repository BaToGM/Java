import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Principal {
	
	static final String fichero_entrada = "entrada.txt"; //Este es el nombre del fichero entrada
	static final String fichero_salida = "salida.txt"; // Este es el nombre del fichero salida
	
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		
		int n = 3;
		FileReader f_entrada = new FileReader (fichero_entrada);
		BufferedReader bf_lec = new BufferedReader (new FileReader (fichero_entrada)); //Así o le pasamos solamente f_entrada
		PrintWriter pw = new PrintWriter (new OutputStreamWriter (new FileOutputStream(fichero_salida)));
		
		Thread.currentThread().setName("Principal");
		
		BlockingQueue<String> CTE = new ArrayBlockingQueue<String>(n);
		BlockingQueue<String> cRes = new ArrayBlockingQueue<String>(n);
		
		for (int i = 0; i<2; i++) {
			Generador obj_Gen = new Generador (i, CTE, bf_lec);
			Thread hilo_Gen = new Thread (obj_Gen);
			hilo_Gen.start();
		}
		
		for (int i = 0; i<2; i++) {
			Distribuidor obj_Dis = new Distribuidor (i, CTE, cRes);
			Thread hilo_Dis = new Thread (obj_Dis);
			hilo_Dis.start();
		}
		
		for (int i = 0; i<2; i++) {
			Receptor obj_Rec = new Receptor(CTE, i, pw);
			Thread hilo_Rec = new Thread (obj_Rec);
			hilo_Rec.start();
		}

	}

}
