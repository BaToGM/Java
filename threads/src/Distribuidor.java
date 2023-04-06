import java.util.concurrent.BlockingQueue;

public class Distribuidor implements Runnable{
	
	private BlockingQueue<String> cp;
	private BlockingQueue<String> cRes;
	private int n;
	
	public Distribuidor (int n, BlockingQueue<String> cp, BlockingQueue<String> cRes) {
		this.n = n;
		this.cp = cp;
		this.cRes = cRes;
		
	}

	@Override
	public void run() {
		
		
	}

}
