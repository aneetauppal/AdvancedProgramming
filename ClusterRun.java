package cluster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClusterRun {

	
    public static final File results = new File("/users/auppal/clustertest");
    public static final File run = new File(results.getAbsolutePath() + "/runScript.sh"); 
    
    public static void main(String[] args) throws IOException {
        
        BufferedWriter runScript = new BufferedWriter(new FileWriter(run.getAbsolutePath()));
        int numRuns = 100; 
        
        for(int i =0; i < numRuns;i++){
            File fileName = new File(results.getAbsolutePath() + File.separator + "/file" + i + ".bat"); 
            BufferedWriter writer  = new BufferedWriter(new FileWriter(fileName)); 
            String resultFile = "out" + i + ".txt";
            File resultsFile = new File(results.getAbsolutePath() + File.separator + resultFile);
            
            writer.write("java -jar /users/auppal/clusterstuff/src/cluster/FactorNumbers.jar" + resultsFile.getAbsolutePath().toString());
            writer.newLine();
            writer.flush(); writer.close(); 
            
            runScript.write("qsub " + fileName.getAbsolutePath()); 
            runScript.newLine(); 
        }
        runScript.flush(); runScript.close();

    }
    
}
	

