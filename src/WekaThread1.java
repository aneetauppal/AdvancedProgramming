package research;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.RandomForest;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class WekaThread1 {
		
		public static void main(String[] args) throws Exception{
			//File dir = new File("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/");
			//File[] directoryListing = dir.listFiles();
			 //for (File child : directoryListing) {
				// runMultThread(child);
			    //}
			File inputFile = new File ("/Users/aneetauppal/Graduate_PhD/Research_Rotation_1/Data/Across_data_LinearNormCommonScale/wt2d_minikraken_merged_taxaAsCol_withMeta_familykrakenLinearNormCommonScale.arff");
			List<Double> runSingle = runSingleThread(inputFile);
			System.out.println(runSingle);
		
		}

		private static List<Double> runSingleThread(File inputFile) throws Exception{
			Random random = new Random();
			List<Double> singleThread = getResultsFromOneFile(inputFile, 25, random);
			return singleThread;
		}
		 
		//private static void runMultThread(File child) throws Exception{
			//final int numThreads = 5;
			//final Semaphore semaphore = new Semaphore(numThreads);
			//List<WekaWorker1> work = new ArrayList<>();

			
			/*for(int i=0; i<numThreads; i++){
				semaphore.acquire();
				WekaWorker1 thread = new WekaWorker1(child, semaphore);
				work.add(thread);
				thread.start();
			}
			for(int i=0; i<numThreads; i++){
				semaphore.acquire();
			}
			
			for (int i=0; i<numThreads; i++){
				System.out.println(work.get(i).getresults());
			}
		} */
		public static List<Double> getResultsFromOneFile(File inputFile, int numPermutations, Random random) throws Exception {
			List<Double> fileresults = new ArrayList<>();
			for(int y = 0; y < numPermutations; y++){
				Instances information = DataSource.read(inputFile.getAbsolutePath());
				information.setClassIndex(information.numAttributes() -1 );
				Evaluation evaluation = new Evaluation(information);
				AbstractClassifier randFor = new RandomForest();
				randFor.buildClassifier(information);
				
				evaluation.crossValidateModel(randFor, information, 96, random);
				//fileresults.add(evaluation.pctCorrect());
				fileresults.add(evaluation.areaUnderROC(y));

				
			}
			return fileresults;
			}
			

		
	}

