package org.ck.servlets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.ck.beans.TimeSeriesBean;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;
import org.ck.smoothers.SimpleMovingAverageSmoother;

import com.sun.org.apache.bcel.internal.generic.L2D;

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
	public static void runMovingAverageSmoother(TimeSeriesBean tsBean)
	{
		double predictedValue;
		Sample sample = tsBean.getSample();
		SimpleMovingAverageSmoother sms = new SimpleMovingAverageSmoother(sample, 12);
		sms.calculateSmoothedValues();
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
		predictedValue = sms.getAverage();
		String output = "";
		output += "Set\tMoving Average\n";
		int i = 0;
		while(i<=smoothList.size())
		{
			output += smoothList.get(i) + "&nbsp" + i + "<br/>";
		i++;
		tsBean.setResult(output);
		}
	}
}
