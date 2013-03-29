package org.ck.gui;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.ck.gui.Constants.DatasetOptions;
import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import org.ck.similarity.CommonSequenceFinder;
import org.ck.similarity.CommonSequenceFinder.Tuple;
import org.ck.similarity.DynamicTimeWarper;


public class MainClass 
{
	public static void main(String args[])
	{	
		DataHolder.setDataset(DatasetOptions.WATER_LEVEL_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);	
		
		//System.out.println(getSortedSimilarSeries(seaSample));
		//testLineGraphDrawer(seaSample);
		testCommonSequenceFinder(seaSample);
		//System.out.println(new MovingGeometricForecaster(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME).geometricMean());
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
		LineGraphDrawer lgd = new LineGraphDrawer(sample);
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
