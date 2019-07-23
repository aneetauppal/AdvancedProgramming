import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FastaSequence {
	static class mycomparator implements Comparator {

		Map map;

		public mycomparator(Map map) {
			this.map = map;
		}

		public int compare(Object one, Object two) {

			return ((Integer) map.get(one)).compareTo((Integer) map.get(two));

		}
	}

	private String Header;
	private String Sequence;
	private float GCRatio;

	public FastaSequence() {
		super();
		Header = "";
		Sequence = "";
		GCRatio = (float) 0.0;
	}

	//static method to write unique sequence to the output file, parses file
	public static void writeUnique(File inFile, File outFile) throws Exception {
		List<FastaSequence> tempList = new ArrayList<FastaSequence>();

		try (BufferedReader br = new BufferedReader(new FileReader(inFile))) {
			String line;
			FastaSequence fs = null;
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty())
					continue;
				if (line.trim().charAt(0) == '>') {
					if (!first) {
						tempList.add(fs);
					}
					fs = new FastaSequence();
					fs.setHeader(line.trim().substring(1));
					first = false;
				} else {
					fs.setSeqeuence(fs.getSequence().trim() + "" + line.trim());
				}
			}
			if (!first)
				tempList.add(fs);
		}
		//adds to hashmap 
		Map<String, Integer> tempMap = new HashMap<>();
		for (FastaSequence fs : tempList) {
			if (tempMap.get(fs.getSeqeuence()) == null) {
				tempMap.put(fs.getSeqeuence(), new Integer(1));
			} else {
				Integer value = new Integer(tempMap.get(fs.getSeqeuence()));
				value++;
				tempMap.put(fs.getSeqeuence(), value);
			}
		}
		ArrayList<tempClass> templist = new ArrayList<>();
		for (String s : tempMap.keySet()) {
			tempClass temporclass = new tempClass(tempMap.get(s), s);
			templist.add(temporclass);
		}
		Collections.sort(templist);
		File OutFile = new File("/Users/aneetauppal/Graduate_MS/Fall_Semester_2016/Advanced_Programming/Java_Scripts/Lab5/src/sequencecount.txt");
		PrintWriter pw = new PrintWriter(OutFile);
		for(tempClass temporclass : templist)
		{
			System.out.println(">"+temporclass.count+"\n"+temporclass.key);
		}
		pw.close();

	}

	public static class tempClass implements Comparable<tempClass> {
		public int count;
		public String key;

		public tempClass(int count, String key) {
			super();
			this.count = count;
			this.key = key;
		}

		@Override
		public int compareTo(tempClass o) {
			return this.count - o.count;
		}

	}

	public static List<FastaSequence> readFastaFile(String filepath)
			throws Exception {
		List<FastaSequence> tempList = new ArrayList<FastaSequence>();
//read in new file 
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			FastaSequence fs = null;
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty())
					continue;
				if (line.trim().charAt(0) == '>') {
					if (!first) {
						tempList.add(fs);
					}
					fs = new FastaSequence();
					fs.setHeader(line.trim().substring(1));
					first = false;
				} else {
					fs.setSeqeuence(fs.getSequence().trim() + "" + line.trim());
				}
			}
			if (!first)
				tempList.add(fs);
		}
		return tempList;
	}

    //add all of my getters
    //returns header
	public String getHeader()
    {
		return Header;

	}

    // get sequence
	public String getSequence()
    {
		return Sequence;

	}
    
	//get gcratio of sequence, look for c and g & measure by sequence length
	public float getGCRatio()
    {
		int len = Sequence.length();
		int gcCount = 0;
		for (int i = 0; i < Sequence.length(); i++) {
			if (Sequence.charAt(i) == 'G'||Sequence.charAt(i)=='C')
				gcCount++;
		}
		return ((float)gcCount/(float)len)*100;

	}

	public String getSeqeuence()
    {
		return Sequence;
	}

    //Add all of my setters
	public void setSeqeuence(String seqeuence)
    {
		Sequence = seqeuence;
	}

	public void setHeader(String header)
    {
		Header = header;
	}

	public void setGCRatio(float CgRatio)
    {
		GCRatio = CgRatio;
	}
	
	public static void main(String[] args) throws Exception
	{
	List<FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/aneetauppal/Downloads/file1.fasta");

	for( FastaSequence fs : fastaList){
	System.out.println("The header of the sequence is:" + fs.getHeader());
	System.out.println("The sequence is:" + fs.getSequence());
	System.out.println("The GCRatio of the sequence is:" + fs.getGCRatio() + "%");
	}
	
	}

}
