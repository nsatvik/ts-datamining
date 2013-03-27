package org.ck.gui;

import java.util.Map;
import java.util.TreeMap;

import org.ck.gui.Constants.DatasetOptions;
import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;


public class MainClass 
{
	public static void main(String args[])
	{	
		DataHolder.setDataset(DatasetOptions.ELECTRICITY_DATASET);
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,DataHolder.SAMPLE_NAME);	
		
		//System.out.println(getSortedSimilarSeries(seaSample));
		LineGraphDrawer lgd = new LineGraphDrawer(seaSample);
		System.out.println(lgd.simpleExample());
		System.out.println("Open this link in a brower!");
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
