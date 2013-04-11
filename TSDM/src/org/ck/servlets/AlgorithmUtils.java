package org.ck.servlets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;

import org.ck.beans.TimeSeriesBean;
import org.ck.gui.Constants;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;
import org.ck.smoothers.SimpleMovingAverageSmoother;
import org.ck.smoothers.SmoothingFilter;

import com.sun.istack.internal.logging.Logger;
import com.sun.org.apache.bcel.internal.generic.L2D;

/**
 * 
 * This class is a utility class that contains methods to run various algorithms in this tool
 *
 */
public class AlgorithmUtils implements Constants
{
	/**
	 * Runs the Dynamic Time Warping Algorithm for similarity detection
	 * @param tsBean
	 */
	public static String runDTWAlgorithm(TimeSeriesBean tsBean)
	{
		switch(tsBean.getSubTaskType())
		{
		case UPDATE_GRAPH:
			StringTokenizer tokens = new StringTokenizer(tsBean.getParams(), " to ");
			int min = Integer.parseInt(tokens.nextToken());
			int max = Integer.parseInt(tokens.nextToken());
			Logger.getLogger(AlgorithmUtils.class).log(Level.WARNING, "" + min + "\t" + max);
			tsBean.setSample(tsBean.getSample().getSeriesSubset(min, max));
			tsBean.setSubTaskType("" + SubTaskType.NONE);		//RESETTING SubTaskType...Never Forget to reset
			return PATH_PREFIX + "Similarity/dtw_update.jsp";
		}
		
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
		
		return PATH_PREFIX + "Similarity/dtw_results.jsp";
	}
	
	/**
	 * Runs a Simple Moving Average Smoother to predict a future value of the given time series
	 * @param tsBean
	 */
	public static String runMovingAverageSmoother(TimeSeriesBean tsBean)
	{
		double predictedValue;
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new SimpleMovingAverageSmoother(sample, 12);		
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
		predictedValue = sms.getAverage();
		String output = "";
		output += "Set\tMoving Average\n";
		int i = 0;
		while(i < smoothList.size())
		{
			output += smoothList.get(i) + "&nbsp" + i + "<br/>";
			i++;			
		}
		tsBean.setResult(output);
		
		return PATH_PREFIX + "Forecaster/moving_average_result.jsp";
	}
}
