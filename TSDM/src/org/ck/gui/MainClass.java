package org.ck.gui;

import java.util.Map;
import java.util.TreeMap;

import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;


public class MainClass 
{
	public static void main(String args[])
	{		
		Sample seaSample = new Sample(DataHolder.TRAINING_FILE_NAME,"Sea Level Data");				
		
		displaySortedSimilarSeries(seaSample);		
	}

	private static void displaySortedSimilarSeries(Sample seaSample)
	{
		DynamicTimeWarper dtw = new DynamicTimeWarper(seaSample.getSeriesSubset(0, 12));	
		
		Map<Double, Integer> similarityMap = new TreeMap<Double, Integer>();
		for(int i=0; i<seaSample.getNumOfValues(); i+=12)
			similarityMap.put(dtw.getDistanceFrom(seaSample.getSeriesSubset(i, i + 12)), i / 12);
		
		System.out.println("Subset\tDTW Distance");
		for(Double i : similarityMap.keySet())
			System.out.println(similarityMap.get(i) + "\t" + i);
	}
}
