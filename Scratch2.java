package scratch;

import java.util.Random;

public class Scratch2 
{
	public static void main(String[] args)
	{
		int sum = 0;
		double percent;
		String trimer = "";
		int x[] = new int[1000];
		Random random = new Random();
			for (int i=0; i < x.length; i++){
			     int y = (random.nextInt(4));
			     int z = (random.nextInt(4));
			     int a = (random.nextInt(4));
			     
			     String first = new String();
			     String second = new String();
			     String third = new String();
			     if (y == 0) {
			    	first = "A";
			     } else if (y == 1){
			    	 first = "G";
			     } else if  (y == 2){
			    	 first = "C";
			     } else if (y == 3) {
			    	 first = "T";
			     } else {
			    	 first = "Not found";
			     }
			     if (z == 0) {
			    	second = "A";
			     } else if (z == 1){
			    	 second = "G";
			     } else if  (z == 2){
			    	 second = "C";
			     } else if (z == 3) {
			    	 second = "T";
			     } else {
			    	 second = "Not found";
			     }
			     if (a == 0) {
				    third = "A";
				 } else if (a == 1){
				     third = "G";
				 } else if  (a == 2){
				     third = "C";
				 } else if (a == 3) {
				     third = "T";
				 } else {
					 third = "Not found";
				 }
			     trimer = (first + second + third);
			     System.out.println("These are the randomly generated Trimers:" + trimer);
			     if (trimer.contains("AAA")) {
			    	 sum = sum + 1;
			     } else if (trimer.contains("")){
			    	 
			     } else {
			    	 System.out.println("This is not a trimer");
			     }
			    
			     }
			 System.out.println("The total Number of AAA Trimers = " + sum);
			 percent = (sum);
			 System.out.println("The total percentage of AAA Trimers out of 1000 = "+ (percent/1000)*100 + "%");
			}
	}	

