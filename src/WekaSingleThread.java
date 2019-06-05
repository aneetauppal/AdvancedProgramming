package research;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.RandomForest;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemoveMisclassified;
import weka.gui.visualize.ThresholdVisualizePanel;
import weka.gui.visualize.PlotData2D;
import java.awt.BorderLayout;

public class WekaSingleThread {
	private final Random random = new Random();
	private final File inputFileTrain;
	private final File inputFileTest;
	private static List<Instances> singleThread;
	private static List<Double> secondThread;


public WekaSingleThread(File inputFileTrain, File inputFileTest){
		this.inputFileTrain = inputFileTrain;
		this.inputFileTest = inputFileTest;

	}
	
	public static List<Instances> getResultsFromOneThread(File inputFileTrain, File inputFileTest, Random random) throws Exception {
		List<Instances> predResults = new ArrayList<>();
		ThresholdCurve tc = new ThresholdCurve();
		Instances train = DataSource.read(inputFileTrain.getAbsolutePath());
		Instances test = DataSource.read(inputFileTest.getAbsolutePath());
		
		int trainNumbInst = train.numInstances();
		int numInstances = test.numInstances();
		train.setClassIndex(train.numAttributes() -1 );
		test.setClassIndex(test.numAttributes()-1);
		
		Evaluation ev = new Evaluation(train);
		AbstractClassifier randFor = new RandomForest();
		randFor.buildClassifier(train);
		
		//Use this ev. if I'm just training a model
		//ev.crossValidateModel(randFor, train, trainNumbInst, random);
		
		//Use this ev. if I'm using model as training against new test set
		//ev.crossValidateModel(randFor, test, numInstances, random);
		ev.evaluateModel(randFor, test);
		
		//find area under ROC
		Instances results = tc.getCurve(ev.predictions(), 0);
		predResults.add(results);
		
		//Outputs I would sometimes use to check num. instances, etc. 
		//predResults.add(ThresholdCurve.getROCArea(results));
		//predResults.add(ev.areaUnderROC(0));
		//predResults.add(ev.numInstances());
				
		
		//To get FPR/TPR output to a file, parsed file and plotted in R
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aneetauppal/output.arff"));
	      writer.write(results.toString());
	      writer.newLine();
	      writer.flush();
	      writer.close();
		
		return predResults;
	}
	/*
		
				
		 //Used this curve to plot ROC through Weka 
	     ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
	     vmc.setROCString("(Area under ROC = " + Utils.doubleToString(tc.getROCArea(results), 4) + ")");
	     vmc.setName(results.relationName());
	     PlotData2D tempd = new PlotData2D(results);
	     tempd.setPlotName(results.relationName());
	     tempd.addInstanceNumberAttribute();
	     // specify which points are connected
	     boolean[] cp = new boolean[results.numInstances()];
	     for (int n = 1; n < cp.length; n++)
	       cp[n] = true;
	     tempd.setConnectPoints(cp);
	     // add plot
	     vmc.addPlot(tempd);
	 
	     // display curve
	     String plotName = vmc.getName();
	     final javax.swing.JFrame jf =
	       new javax.swing.JFrame("Weka Classifier Visualize: "+plotName);
	     jf.setSize(500,400);
	     jf.getContentPane().setLayout(new BorderLayout());
	     jf.getContentPane().add(vmc, BorderLayout.CENTER);
	     jf.addWindowListener(new java.awt.event.WindowAdapter() {
	       public void windowClosing(java.awt.event.WindowEvent e) {
	       jf.dispose();
	       }
	     });
	     jf.setVisible(true);
	     return predResults;   
	} */
	
		//Used this to Remove Misclassified on training set 
		
	/*    Classifier c = AbstractClassifier.makeCopy(randFor);
		
	      // setup and run filter
	      RemoveMisclassified filter = new RemoveMisclassified();
	      filter.setClassifier(c);
	      filter.setClassIndex(train.numAttributes() -1);
	      filter.setNumFolds(trainNumbInst);
	      filter.setThreshold(0.1);
	      filter.setMaxIterations(0);
	      filter.setInputFormat(train);
	      Instances output = Filter.useFilter(train, filter);  
	 
	      // output file - each time will filter misclassified results into new file
	      BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aneetauppal/output.arff"));
	      writer.write(output.toString());
	      writer.newLine();
	      writer.flush();
	      writer.close(); */
	      //return predResults;
	  //  } 
	
	public static List<Double> getResults(File inputFileTrain, File inputFileTest, Random random) throws Exception {
		List<Double> aucResults = new ArrayList<>();
		ThresholdCurve tc = new ThresholdCurve();
		Instances train = DataSource.read(inputFileTrain.getAbsolutePath());
		Instances test = DataSource.read(inputFileTest.getAbsolutePath());
		
		int trainNumbInst = train.numInstances();
		int numInstances = test.numInstances();
		train.setClassIndex(train.numAttributes() -1 );
		test.setClassIndex(test.numAttributes()-1);
		
		Evaluation ev = new Evaluation(train);
		AbstractClassifier randFor = new RandomForest();
		randFor.buildClassifier(train);
		
		//Use this ev. if I'm just training a model
		//ev.crossValidateModel(randFor, train, trainNumbInst, random);
		
		//Use this ev. if I'm using model as training against new test set
		//ev.crossValidateModel(randFor, test, numInstances, random);
		ev.evaluateModel(randFor, test);
		
		//find area under ROC
		Instances results = tc.getCurve(ev.predictions(), 0);
		//predResults.add(results);
		
		//Outputs I would sometimes use to check num. instances, etc. 
		aucResults.add(ThresholdCurve.getROCArea(results));
		//aucResults.add(ev.areaUnderROC(0));
		aucResults.add(ev.numInstances());
				
		return aucResults;
	}
	
public static void main(String[] args) throws Exception{

	//File inputFileTrain = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/Output_results_removeMisclassified/obesity_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale_output2_perfect.arff");
	
	//File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/China_WGS_minikraken_merged_taxaAsCol_familykrakenLinearNormCommonScale.arff");
	//File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/cirrhosis_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/colorectal_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	//File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/ibd_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	File inputFileTrain = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/obesity_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	//File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/t2d_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	//File inputFileTest = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/wt2d_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
	Random random = new Random();
	//Use this when I just want to train a model and not test against anything
	//List<Double> singleThread = getResultsFromOneThread(inputFileTrain, random);
	List<Instances> singleThread = getResultsFromOneThread(inputFileTrain, inputFileTest, random);
	List<Double> secondThread = getResults(inputFileTrain, inputFileTest, random);

	System.out.println(singleThread);
	System.out.println(secondThread);

}
    	
}
