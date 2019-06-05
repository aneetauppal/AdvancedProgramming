package research;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import static research.WekaThread1.getResultsFromOneFile;

public class WekaWorker1 extends Thread {

		private List<Double> results = new ArrayList<>();
		private final int numPermutations = 25;
		private final Random random = new Random();
		//private final File child;
		private final File inputFile;
		//private final Semaphore semaphore;

		
		public WekaWorker1(File inputFile, Semaphore sem){
			//this.child = child;
			this.inputFile = inputFile;
			//this.semaphore = sem;

		}
	  // @Override
		/*public void run(){
			try {
				results = getResultsFromOneFile(child, numPermutations, random);
			} catch (Exception ex){
				System.out.println(ex.getStackTrace());
			}finally{
				semaphore.release();
			}
		} 
		public List<Double> getresults(){
			return results;
		}*/

	}


