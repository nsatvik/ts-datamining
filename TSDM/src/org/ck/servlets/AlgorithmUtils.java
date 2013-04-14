package org.ck.servlets;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;

import org.ck.anomalifinder.Cusum_VmaskApproch;
import org.ck.beans.TimeSeriesBean;
import org.ck.gui.Constants;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;
import org.ck.smoothers.ExponentialMovingAverageSmoother;
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
			//Add sub-samples to compare against
			String[] ranges = tsBean.getParams().split(";");
			ArrayList<Sample> subSamples = tsBean.getSubSamples();
			subSamples.clear();
			for(int i=0; i<ranges.length; i++)
			{
				StringTokenizer tokens = new StringTokenizer(ranges[i], " to ");
				int min = Integer.parseInt(tokens.nextToken());
				int max = Integer.parseInt(tokens.nextToken());
				Logger.getLogger(AlgorithmUtils.class).log(Level.WARNING, "" + min + "\t" + max);
				subSamples.add(tsBean.getSample().getSeriesSubset(min, max));				
			}			
			tsBean.setSubSamples(subSamples);
			tsBean.setSubTaskType("" + SubTaskType.NONE);		//RESETTING SubTaskType...Never Forget to reset
			
			DynamicTimeWarper dtw = new DynamicTimeWarper(subSamples.get(0));
			
			Map<Double, String> similarityMap = new TreeMap<Double, String>();
			for(int i=0; i<subSamples.size(); i++)
				similarityMap.put(dtw.getDistanceFrom(subSamples.get(i)), "Sample " + i);
			
			String output = "";			
			for(Double i : similarityMap.keySet())
				output += similarityMap.get(i) + "&nbsp" + i + "<br/>";
			
			tsBean.setResult(output);
			
			return PATH_PREFIX + "Similarity/dtw_update.jsp";
			
		default:
			return PATH_PREFIX + "Similarity/dtw_results.jsp";
		}		
	}
	
	/**
	 * Runs a Simple Moving Average Smoother to predict a future value of the given time series
	 * @param tsBean
	 */
	public static String runMovingAverageSmoother(TimeSeriesBean tsBean)
	{
		//double predictedValue;
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new SimpleMovingAverageSmoother(sample, 12);		
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
<<<<<<< HEAD
		predictedValue = sms.getAverage(sample.getNumOfValues()-1,sample.getNumOfValues()-2);
		tsBean.setPredictedValue(predictedValue);
		System.out.println("::::::::::::::::::");
		//PrintWriter out = new PrintWriter(System.out);
		//out.println("::::::The simple Moving Average is :::::: "+predictedValue);
=======
		//predictedValue = sms.getAverage();
>>>>>>> e90174e958ed8f244a8e967b4a935d9c09f45d60
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
<<<<<<< HEAD
	public static String runExponentialSmoother(TimeSeriesBean tsBean)
	{
		
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new ExponentialMovingAverageSmoother(sample, 1);		
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
		String output = "";
		output += "Set\tMoving Exponential\n";
		int i = 0;
		while(i < smoothList.size())
		{
			output += smoothList.get(i) + "&nbsp" + i + "<br/>";
			i++;			
		}
		tsBean.setResult(output);
		
		return PATH_PREFIX + "Forecaster/moving_exponential.jsp";
		
	}
	}

=======
	
	/**
	 * Runs the Cusum V Mask Algorithm
	 * @param tsBean
	 * @return
	 */
	public static String runCusumAnomalyDetAlgo(TimeSeriesBean tsBean) {
		switch(tsBean.getSubTaskType())
		{
		case UPDATE_GRAPH:
			Sample sample = tsBean.getSample();
			Cusum_VmaskApproch cusumAnoFinder = new Cusum_VmaskApproch(sample);		
			cusumAnoFinder.setHval(3.0); //Need to add slider to set this value.
			cusumAnoFinder.computeCusumSereis();
			List<Integer> defectiveList = new ArrayList<Integer>();
			defectiveList = cusumAnoFinder.getDefectiveDataPoints();
			String output = "";
			output += "Index\tValue\n";
			int i = 0;
			while(i < defectiveList.size())
			{
				int index = defectiveList.get(i);
				output +=  index + "&nbsp" + sample.getValue(index) + "<br/>";
				i++;			
			}
			tsBean.setResult(output);
			return PATH_PREFIX + "Anomaly/cusum_update.jsp";
		default:
			return PATH_PREFIX + "Anomaly/cusum_results.jsp";
		}
	}
}
>>>>>>> e90174e958ed8f244a8e967b4a935d9c09f45d60
