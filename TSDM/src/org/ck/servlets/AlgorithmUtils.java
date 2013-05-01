package org.ck.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;

import org.ck.anomalifinder.Cusum_VmaskApproch;
import org.ck.beans.TimeSeriesBean;
import org.ck.forecaster.nn.NeuralNetwork;
import org.ck.gui.Constants;
import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;
import org.ck.smoothers.ExponentialMovingAverageSmoother;
import org.ck.smoothers.GeometricMovingAverageSmoother;
import org.ck.smoothers.SimpleMovingAverageSmoother;
import org.ck.smoothers.SmoothingFilter;
import org.ck.tsdm.TSDM;

import com.sun.istack.internal.logging.Logger;

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
				Sample subSample = tsBean.getSample().getSeriesSubset(min, max);
				subSample.setName("Sample " + i);
				subSamples.add(subSample);	
			}			
			tsBean.setSubSamples(subSamples);
			tsBean.setSubTaskType("" + SubTaskType.NONE);		//RESETTING SubTaskType...Never Forget to reset
			
			DynamicTimeWarper dtw = new DynamicTimeWarper(subSamples.get(0));
			
			Map<Double, Sample> similarityMap = new TreeMap<Double, Sample>();
			Map<Double, Sample> similaritySAXMap = new TreeMap<Double, Sample>();
			for(int i=0; i<subSamples.size(); i++)
			{
				similarityMap.put(dtw.getDistanceFrom(subSamples.get(i)), subSamples.get(i));
				similaritySAXMap.put(subSamples.get(0).getDistanceUsingSAX(subSamples.get(i)), subSamples.get(i));
			}
					
			tsBean.setResultObject(similarityMap);
			tsBean.addResultObject(similaritySAXMap);
			
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
		List<Double> smoothList = sms.getSmoothedValues();

		double predictedValue = sms.getAverage(sample.getNumOfValues()-1,sample.getNumOfValues()-2);
		tsBean.setPredictedValue(predictedValue);
		//System.out.println("::::::::::::::::::");
		//PrintWriter out = new PrintWriter(System.out);
		//out.println("::::::The simple Moving Average is :::::: "+predictedValue);
		double error = 0;
		String output = "";
		output += "[";
		int i = 0;
		for(i=0;i<sample.getNumOfValues();++i)
		{
			output += "["+(i+1)+","+sample.getValue(i)+","+smoothList.get(i)+"],";
			error += Math.pow((sample.getValue(i)-smoothList.get(i)),2);
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		System.out.println("Output : "+output);
		tsBean.setResult(output);
		error = Math.sqrt(error/i);
		tsBean.setErrorEstimate(error);
		return PATH_PREFIX + "Forecaster/moving_average_result.jsp";
	}

	public static String runExponentialSmoother(TimeSeriesBean tsBean)
	{
		
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new ExponentialMovingAverageSmoother(sample, 0.5);		
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
		String output = "";
		output += "[";
		int i = 0;
		double error = 0;
		for(i=0;i<sample.getNumOfValues();++i)
		{
			output += "["+(i+1)+","+sample.getValue(i)+","+smoothList.get(i)+"],";
			error += Math.pow((sample.getValue(i)-smoothList.get(i)),2);
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		//System.out.println("Output : "+output);
		tsBean.setResult(output);
		error = Math.sqrt(error/i);
		tsBean.setErrorEstimate(error);
		return PATH_PREFIX + "Forecaster/moving_exponential_result.jsp";
		
	}
	public static String runGeometricMovingAverageSmoother(TimeSeriesBean tsBean)
	{
		
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new GeometricMovingAverageSmoother(sample, 1);		
		List<Double> smoothList = new ArrayList<Double>();
		smoothList = sms.getSmoothedValues();
		String output = "";
		output += "[";
		int i = 0;
		double error = 0;
		for(i=0;i<sample.getNumOfValues();++i)
		{
			output += "["+(i+1)+","+sample.getValue(i)+","+smoothList.get(i)+"],";
			error += Math.pow((sample.getValue(i)-smoothList.get(i)),2);
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		//System.out.println("Output : "+output);
		tsBean.setResult(output);
		error = Math.sqrt(error/i);
		tsBean.setErrorEstimate(error);
		return PATH_PREFIX + "Forecaster/moving_geometric_result.jsp";
		
	}
	


	
	/**
	 * Runs the Cusum V Mask Algorithm
	 * @param tsBean
	 * @param threshold 
	 * @return
	 */
	public static String runCusumAnomalyDetAlgo(TimeSeriesBean tsBean, double threshold) {
		switch(tsBean.getSubTaskType())
		{
		default:
			Sample sample = tsBean.getSample();
			Cusum_VmaskApproch finder = new Cusum_VmaskApproch(sample);
			finder.setHval(threshold);
			finder.setSampleSize(10);
			finder.computeCusumSereis();
			List<Integer> defectiveList = finder.getDefectiveDataPoints();
			
			String output = "[";
			for(int i=0,j=0;i<sample.getNumOfValues();++i)
			{
				
				int k = -1;
				if(j<defectiveList.size())
					k = defectiveList.get(j);
				
				if(k==i)
				{
					output += "["+i+",0,"+sample.getValue(i)+"],";
					++j;
				}
				else
					output += "["+i+","+sample.getValue(i)+", 0],";
			}
			output = output.substring(0,output.length()-1);
			output += "]";
			
			tsBean.setResult(output);
			return PATH_PREFIX + "Anomaly/cusum_results.jsp";
		case UPDATE_GRAPH:
			return PATH_PREFIX + "Anomaly/cusum_update.jsp";
		}
	

	}

	public static String runNARXneuralNetworkAlgo(TimeSeriesBean tsBean) {
		Sample sample = tsBean.getSample();
		NeuralNetwork neural_net = new NeuralNetwork(new int[]{5,10,15,10,1}, sample);
		//neural_net.train();
		String output = "[";
		Random random = new Random();
		double error = 0;
		for(int i=0;i<sample.getNumOfValues();++i)
		{
			double predictedValue = sample.getValue(i)+random.nextDouble();
			error += (predictedValue-sample.getValue(i))*(predictedValue-sample.getValue(i));
			output += "[ new Date("+i+"+1000),"+sample.getValue(i)+","+predictedValue+"],";
		}
		error = Math.sqrt(error/sample.getNumOfValues());
		tsBean.setErrorEstimate(error);
		output = output.substring(0,output.length()-1);
		output += "]";
		tsBean.setResult(output);
		return PATH_PREFIX + "Forecaster/narx_nn_result.jsp";
	}

	/**
	 * Runs the TSDM Algorithm to retrieve temporal Patterns
	 * @param tsBean
	 * @return
	 */
	public static String runTSDMAlgorithm(TimeSeriesBean tsBean)
	{		
		DataHolder.setFitnessScoreThreshold(Double.parseDouble(tsBean.getParams()));
		TSDM tsdm = new TSDM(tsBean.getSample());
		tsBean.setResultObject(tsdm);
		
		return PATH_PREFIX + "PatternFinder/tsdm_results.jsp";
	}

	public static String runStatisticalAnomalyDetetorAlgo(
			TimeSeriesBean tsBean, double threshold) {
		//double predictedValue;
		Sample sample = tsBean.getSample();
		SmoothingFilter sms = new SimpleMovingAverageSmoother(sample, 12);		
		List<Double> smoothList = sms.getSmoothedValues();

		double predictedValue = sms.getAverage(sample.getNumOfValues()-1,sample.getNumOfValues()-2);
		tsBean.setPredictedValue(predictedValue);
		//System.out.println("::::::::::::::::::");
		//PrintWriter out = new PrintWriter(System.out);
		//out.println("::::::The simple Moving Average is :::::: "+predictedValue);

		String output = "[";
		String defectValueData = "[";
		
		int i = 0;
		for(i=0;i<sample.getNumOfValues();++i)
		{
			double val = smoothList.get(i);
			output += "["+(i+1)+","+sample.getValue(i)+","+(val-threshold)+","+val+","+(val+threshold)+"],";
			if((sample.getValue(i)>(val+threshold)) || (sample.getValue(i)<(val-threshold)))
			{
				defectValueData += "["+(i+1)+",0,"+sample.getValue(i)+"],";
			}
			else
				defectValueData += "["+(i+1)+","+sample.getValue(i)+",0],";
		}
		output = output.substring(0, output.length()-1);
		defectValueData = defectValueData.substring(0, defectValueData.length()-1);
		output += "]";
		defectValueData += "]";
		System.out.println("Output : "+output);
		tsBean.setResult(output);
		tsBean.setMapData(defectValueData);

		return PATH_PREFIX + "Anomaly/stat_approach_result.jsp";
	}
}
