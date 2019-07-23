
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AAquiz 
{
	 public static void main(String[] args) 
{

	long endTime = System.currentTimeMillis() + 30000;
	
	HashMap<String, String> hmap = new HashMap<String, String>();
	Integer correct = 0;
	Integer incorrect = 0;
	
	
	hmap.put("Alanine", "A");
	hmap.put("Arginine", "R");
	hmap.put("Asparagine", "N");
	hmap.put("Aspartic_acid", "D");
	hmap.put("Cysteine", "C");
	hmap.put("Glutamine", "Q");
	hmap.put("Glutamic_acid", "E");
	hmap.put("Glycine", "G");
	hmap.put("Histidine", "H");
	hmap.put("Isoleucine", "I");
	hmap.put("Leucine", "L");
	hmap.put("Lysine", "K");
	hmap.put("Methionine", "M");
	hmap.put("Phenylalanine", "F");
	hmap.put("Proline", "P");
	hmap.put("Serine", "S");
	hmap.put("Threonine", "T");
	hmap.put("Tryptophan", "W");
	hmap.put("Tyrosine", "Y");
	hmap.put("Valine", "V");
	

	

for (int i=0; i<50; i++){
	while (System.currentTimeMillis() < endTime) {
	Random random = new Random();
	List<String> keys      = new ArrayList<String>(hmap.keySet());
	String       randomKey = keys.get( random.nextInt(keys.size()) );
	String       value     = hmap.get(randomKey);
	String 		Aminoacid;
	Scanner input = new Scanner( System.in );
	System.out.println(randomKey);
	System.out.println("The abbreviation for this amino acid is:");
	Aminoacid = input.next( ).toUpperCase();
			
			String current = hmap.get(randomKey);
			//System.out.println(current);
			if (current.equals(Aminoacid))
			{
			//System.out.println("true");
				correct = correct + 1;
			} else {
				//System.out.println("wrong");
				incorrect = incorrect + 0;
			}
	
	}
	
}
int totalscore = correct; 
System.out.println("Your Total Score for this Quiz is:"+ totalscore);
}
}

