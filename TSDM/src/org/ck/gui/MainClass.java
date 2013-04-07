package org.ck.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ck.anomalifinder.Cusum_VmaskApproch;
import org.ck.forecaster.nn.NeuralNetwork;
import org.ck.gui.Constants.DatasetOptions;
import org.ck.sample.Approximator;
import org.ck.sample.DataHolder;
import org.ck.sample.Discretizer;
import org.ck.sample.Sample;
import org.ck.similarity.CommonSequenceFinder;
import org.ck.similarity.TimePeriodFinder;
import org.ck.similarity.CommonSequenceFinder.Tuple;
import org.ck.similarity.DynamicTimeWarper;


public class MainClass 
{
	public static void main(String args[])
	{	
		DataHolder.setDataset(DatasetOptions.ECG_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);	
		
		//use your respective methods for testing
		//satvik();
		//vaishakh();
		samir();
		
		
	}
	
	private static void samir()
	{
		DataHolder.setDataset(DatasetOptions.SEA_LEVEL_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);		
		
		DataHolder.setDataset(DatasetOptions.FINANCE_VIX_DATASET);
		Sample sample2 = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME).getSeriesSubset(0, 1400);
		
		System.out.println(seaSample.getSaxString());
		System.out.println(sample2.getSaxString());
		System.out.println(seaSample.compareToUsingSAX(sample2));
	}

	private static void vaishakh()
	{
		DataHolder.setDataset(DatasetOptions.ECG_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);	
		//System.out.println(getSortedSimilarSeries(seaSample));
		//testLineGraphDrawer(seaSample);
		//testCommonSequenceFinder(seaSample);
		//System.out.println(new MovingGeometricForecaster(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME).geometricMean());
				
		
	}

	private static void satvik()
	{
		DataHolder.setDataset(DatasetOptions.ECG_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);	
		//testNeuralNetwork(seaSample);
		testCusum(seaSample);
		
	}

	private static void testCusum(Sample seaSample) {
		Cusum_VmaskApproch finder = new Cusum_VmaskApproch(seaSample);
		finder.setHval(1.5);
		finder.computeCusumSereis();
		System.out.println(finder.getDefectiveDataPoints());
		
	}

	private static void testNeuralNetwork(Sample seaSample) {
		NeuralNetwork nn = new NeuralNetwork(new int[]{10,15,15,1}, seaSample);
		nn.train();
		System.out.println("Actual Value : "+seaSample.getValue(11)+"\t Predicted Value : "+
		nn.predict((ArrayList<Double>) seaSample.getSeriesSubset(10, 20).getNormalizedTimeSeries()));
	}

	private static void testCommonSequenceFinder(Sample seaSample) {
		CommonSequenceFinder csFinder = new CommonSequenceFinder(seaSample);
		csFinder.findPatterns();
		List<Tuple> similarPatterns = csFinder.getSimilarPatternList();
		for(Tuple t : similarPatterns)
		{
			System.out.print(t.getValue(0)+"\t"+t.getValue(1)+"\t");
			seaSample.display(t.getValue(0), t.getValue(1));
		}
	}

	public static String testLineGraphDrawer(Sample sample)
	{
		GraphDrawer lgd = new GraphDrawer(sample);
		String exampleLink = lgd.example1();
		System.out.println(exampleLink);
		System.out.println("Open this link in a brower!");
		return exampleLink;
	}

	public static String getSortedSimilarSeries(Sample seaSample)
	{
		DynamicTimeWarper dtw = new DynamicTimeWarper(seaSample.getSeriesSubset(0, 12));	
		
		Map<Double, Integer> similarityMap = new TreeMap<Double, Integer>();
		for(int i=0; i<seaSample.getNumOfValues(); i+=12)
			similarityMap.put(dtw.getDistanceFrom(seaSample.getSeriesSubset(i, i + 12)), i / 12);
		
		String output = "";
		output += "Subset\tDTW Distance\n";
		for(Double i : similarityMap.keySet())
			output += similarityMap.get(i) + "\t" + i + "\n";
		
		return output;
	}
}
