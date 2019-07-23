package cluster;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FactorNumbers {

	private List<Integer> generate100numbers(){
        List<Integer> randomNumList = new ArrayList<>(); 
        Random random = new Random(); 
        for(int i = 0; i < 100;i++){
            randomNumList.add(random.nextInt(100)); 
        }
        return randomNumList; 
    }
	
    private List<Long> factorNum(int num){
        List<Long> factors = new ArrayList<Long>();     
        int half = num/2 +1; 
        for (long x = 2; x < half; x++){
            if(num % x == 0){
                factors.add(x); 
            }
        }
        return factors; 
    }
	
    public List<List<Long>> factorNumbers(){
        List<Integer> numbers = new ArrayList<>(); 
        numbers = this.generate100numbers(); 
      
        List<List<Long>> ListofFacNum = new ArrayList<>(); 
        
        for(int y =0; y< numbers.size(); y++){
            ListofFacNum.add(factorNum(numbers.get(y))); 
        }
        return ListofFacNum; 
    }


private static void main(String[] args) throws IOException{
	BufferedWriter bufferwriter;
	if(0 > args.length){
		File resultfile = new File(args[0]);
		bufferwriter = new BufferedWriter(new FileWriter(resultfile));	
	
	FactorNumbers factorization = new FactorNumbers();
	List<List<Long>> factors = factorization.factorNumbers();
	
    for(int z = 0; z < factors.size();z++){
        List<Long> list = factors.get(z); 
        for(int a=0; a < list.size(); a++){
            bufferwriter.write(list.get(a) + " "); 
        }
        bufferwriter.write("\n"); 
	}
    bufferwriter.flush(); bufferwriter.close(); 
}
}
}
