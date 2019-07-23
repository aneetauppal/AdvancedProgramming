package javaGui;


public class PrimeNumberGen {

	    public String isPrime(int numbers) {
	    	Integer integerTwo = new Integer(0);
			for(int check = 2; check < numbers; ++check) {
	            if(numbers % check == 0) {
	             Integer integer = new Integer(1);
	               return "True"; 
	            }
			}
			return "False";  
	    }
	    
	}


