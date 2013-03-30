package org.ck.servlets;

import java.util.Map;
import java.util.TreeMap;

import org.ck.beans.TimeSeriesBean;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;

/**
 * 
 * This class is a utility class that contains methods to run various algorithms in this tool
 *
 */
public class AlgorithmUtils
{
	/**
	 * Runs the Dynamic Time Warping Algorithm for similarity detection
	 * @param tsBean
	 */
	public static void runDTWAlgorithm(TimeSeriesBean tsBean)
	{
		Sample sample = tsBean.getSample();
		DynamicTimeWarper dtw = new DynamicTimeWarper(sample.getSeriesSubset(0, 12));	
		
		Map<Double, Integer> similarityMap = new TreeMap<Double, Integer>();
		for(int i=0; i<sample.getNumOfValues(); i+=12)
			similarityMap.put(dtw.getDistanceFrom(sample.getSeriesSubset(i, i + 12)), i / 12);
		
		String output = "";
		output += "Subset\tDTW Distance\n";
		for(Double i : similarityMap.keySet())
			output += similarityMap.get(i) + "&nbsp" + i + "<br/>";
		
		tsBean.setResult(output);
	}

}
