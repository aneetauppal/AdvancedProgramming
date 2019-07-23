package roccurve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Writer;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.SwingUtilities;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

@SuppressWarnings("restriction")
public class TestClassifier {

		
		public static void main(String[] args) throws Exception {
			Random random = new Random();
			File inputFile = new File("/Users/aneetauppal/data.arff");
			ThresholdVisualizePanel tvp = TestClassifier.getVisPanel(inputFile.getName());
			TestClassifier.plotROCForAnArff(inputFile, 1, random, false, tvp);
			}
			
		public static ThresholdVisualizePanel getVisPanel(File inputFile, int numPermutations, Random random, boolean scramble, String tvp, String title) throws Exception
		{

			 ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
			 vmc.setName(title);
			 
		     final javax.swing.JFrame jf =
		       new javax.swing.JFrame("Weka Classifier Visualize: "+title);
		     jf.setSize(500,400);
		     jf.getContentPane().setLayout(new BorderLayout());
		     jf.getContentPane().add(vmc, BorderLayout.CENTER);
		     jf.addWindowListener(new java.awt.event.WindowAdapter() {
		       public void windowClosing(java.awt.event.WindowEvent e) {
		       jf.dispose();
		       }
		     });
		     jf.setVisible(true);
		     
		     return vmc;
		
		}
		
		public static void scrambeLastColumn( Instances instances, Random random )
		{
			List<Double> list = new ArrayList<Double>();
			
			for(Instance i : instances)
				list.add(i.value(i.numAttributes()-1));
			
			Collections.shuffle(list,random);
			
			for( int x=0; x < instances.size(); x++)
			{
				Instance i = instances.get(x);
				i.setValue(i.numAttributes()-1, list.get(x));
			}
		}
		
		public static List<Double> plotROCForAnArff( File inputFile, 
				int numPermutations, Random random , boolean scramble, 
					ThresholdVisualizePanel tvp, Classifier classifier, Color nonScrambleColor) 
					throws Exception
		{	 
			
			List<Double> areaUnderCurve = new ArrayList<Double>();
			
			for( int x=0; x< numPermutations; x++)
			{
				Instances data = DataSource.read(inputFile.getAbsolutePath());
				
				if(scramble)
					scrambeLastColumn(data, random);
				
				data.setClassIndex(data.numAttributes() -1);
				Evaluation ev = new Evaluation(data);
				
				classifier.buildClassifier(data);
				ev.crossValidateModel(classifier, data, 10, random);
				//System.out.println(ev.toSummaryString("\nResults\n\n", false));
				//System.out.println(x + " " + ev.areaUnderROC(0) + " " + ev.pctCorrect());
				areaUnderCurve.add(ev.areaUnderROC(0));
				//System.out.println(x + " " + ev.areaUnderROC(0));
				
				//if( tvp != null)
					//addROC(ev,tvp, scramble ? Color.red: nonScrambleColor);
			}
			
			return areaUnderCurve;
			
		}


public static void addROC(Evaluation eval, final ThresholdVisualizePanel vmc,
		Color color) throws Exception
{
	final ThresholdCurve tc = new ThresholdCurve();
    final Instances result = tc.getCurve(eval.predictions(), 0);
    final Object visibilityLock = new Object();
     
    final PlotData2D tempd = new PlotData2D(result);
    tempd.setPlotName(result.relationName());
    tempd.addInstanceNumberAttribute();
    
    // specify which points are connected
    boolean[] cp = new boolean[result.numInstances()];
    for (int n = 1; n < cp.length; n++)
       cp[n] = true;
    tempd.setConnectPoints(cp);
    tempd.setCustomColour(color);
   
    //writeROCToFile(eval, new File("c:\\temp\\temp.txt"));
    
    // make sure everything in this thread will be visible to AWT thread
    synchronized(visibilityLock) 
    {
    	SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
			    // make sure everything is visible to the AWT thread
			    synchronized(visibilityLock) 
			    {						
			    	try
			    	{
					     vmc.addPlot(tempd);
					}
					catch(Exception ex)
					{
						throw new RuntimeException(ex);
					}
			    }
			}
		});    
	}
}
}
