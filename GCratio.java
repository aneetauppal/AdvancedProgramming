package lab5;

import java.io.File;
import java.util.List;

public class Mainclass {
	private String[] GCratio;
	private String[] Sequence;
	private String[] Header; 
	

	
	public Mainclass (parsefile); {
		this.Header = FileHeader;
		this.Sequence = FileSequence; 
		//this.GCratio =
		}
	
	public static List ReadFasta(File) {
		BufferedReader File = new BufferedReader(new FileReader("//Users//aneetauppal//Downloads//File1.fasta"));
		String line;
		boolean found=false;
		while((line=File.readLine())!=null)
		    {
		   if(line.startsWith(">"))
		           {
		          if(found);
		        String FileHeader = line;
		        	  break;
		          List FastaSequence = line.substring(1).split(" ")[0];
		    //System.out.println(bodyseq);
		    return FastaSequence;
		    }
		File.close();
		}
	}
	

	public String[] getGCRatio() {
		int Gcount = 0;
		int Ccount = 0;
		
		if (FastaSequence.contains('G')){
			Gcount = Gcount + 1;
		}else if (FastaSequence.contains('C')){
			Ccount = Ccount + 1; 
		}
		else {
			 
		}
	
	}



		public static void main(String[] args) {
			try {
				List<FastaSequence> fastaList = FastaSequence.readFastaFile("//Users//aneetauppal//Downloads//File1.fasta");
				for (FastaSequence fs : fastaList) {
					System.out.println(fs.getHeader());
					System.out.println(fs.getSequence());
					System.out.println(fs.getGCRatio());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
	}
*\
