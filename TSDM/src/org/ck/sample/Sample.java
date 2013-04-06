package org.ck.sample;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.math.*;

import org.ck.smoothers.ExponentialMovingAverageSmoother;
import org.ck.smoothers.SmoothingFilter;


/**
 * This class keeps track of all dimensions of a given time-series
 */
public class Sample 
{
	private List<Double> timeSeriesValues, normalisedTimeSeries;
	private List<Double> smoothTimeSeriesValues, smoothNormalisedTimeSeries;
	private String sampleName;
	private Logger log = Logger.getLogger(Sample.class.getName());
		
	/**
	 * @param file_path - The path of the file that contains the time series values
	 * @param sample_name - The name of the given time series
	 */
	public Sample(String file_path, String sample_name)
	{		
		log.info(file_path);
		this.sampleName = sample_name;
		
		initFromFile(file_path);
		normalisedTimeSeries = normalize(timeSeriesValues);
		smoothenValues();	
		
		log.info("Initialized Sample");
	}
	
	/**
	 * Copy Constructor
	 * @param sample - The sample to be copied
	 */
	public Sample(Sample sample)
	{
		timeSeriesValues = sample.timeSeriesValues;
		normalisedTimeSeries = sample.normalisedTimeSeries;
		sampleName = sample.sampleName;
	}
	
	/**
	 * A constructor that creates a sample from
	 * @param timeSeriesValues - An arraylist of time series values
	 * @param normalisedTimeSeries - An arraylist of normalized time series values
	 * @param sampleName - The user-defined name of the time series
	 */
	public Sample(List<Double> timeSeriesValues, List<Double> normalisedTimeSeries, String sampleName)
	{
		this.timeSeriesValues = timeSeriesValues;
		this.normalisedTimeSeries = normalisedTimeSeries;
		this.sampleName = sampleName;
	}
	
	/**
	 * @param lowIndex - lower index of the time series list
	 * @param highIndex - upper index of the time series list
	 * @return A new sample consisting of a subset of the values of the time series.
	 */
	public Sample getSeriesSubset(int lowIndex, int highIndex)
	{
		if(lowIndex < highIndex)
		{
			if(highIndex > timeSeriesValues.size())
				highIndex = timeSeriesValues.size();
			if(lowIndex < 0)
				lowIndex = 0;		
			return new Sample(new ArrayList<Double>(timeSeriesValues.subList(lowIndex, highIndex)), 
								new ArrayList<Double>(normalisedTimeSeries.subList(lowIndex, highIndex)), 
									sampleName);
		}
		else
			return null;
	}
	
	/**
	 * Displays 'limit' number of normalized and un-normalized values of the time series
	 * @param limit - The number of values from the beginning of the time series that have to be displayed
	 */
	public void displayTimeSeries(int limit)
	{
		System.out.println("Time\tValue\tNormalizedValue");
		for(int i = 0; i < timeSeriesValues.size() && limit-- > 0; i++)
		{
			System.out.println("" + (i+1) + "\t" + timeSeriesValues.get(i) + "\t" + normalisedTimeSeries.get(i));
		}
	}	
	
	/**
	 * Displays the entire time series
	 */
	public void displayTimeSeries()
	{
		System.out.println("Time\tValue\tNormalizedValue");
		for(int i = 0; i < timeSeriesValues.size(); i++)		
			System.out.println("" + (i+1) + "\t" + timeSeriesValues.get(i) + "\t" + normalisedTimeSeries.get(i));		
	}
	
	/**
	 * @return List of all un-normalized time series values
	 */
	public List<Double> getTimeSeries()
	{
		return timeSeriesValues;
	}
	
	/**
	 * @return List of all normalized time series values
	 */
	public List<Double> getNormalizedTimeSeries()
	{
		return normalisedTimeSeries;
	}
	
	/**
	 * @return List of all un-normalized smooth time series values
	 */
	public List<Double> getSmoothTimeSeries()
	{
		return smoothTimeSeriesValues;
	}
	
	/**
	 * @return List of all normalized smooth time series values
	 */
	public List<Double> getSmoothNormalizedTimeSeries()
	{
		return smoothNormalisedTimeSeries;
	}
	
	/**
	 * @return Integer containing the number of values in the time series
	 */
	public int getNumOfValues()
	{
		return timeSeriesValues.size();
	}
	
	/**
	 * @return String containing the name of this time series
	 */
	public String getName()
	{
		return sampleName;
	}
	public double getStandardDeviation(){
		return standardDeviationCalculator();
	}
	public double getMean()
	{
		return meanCalculator();
	}
	
	/**
	 * Uses a smoothing filter to smoothen the given time series
	 * A smoothing filter might be any one of the following:
	 * 		1) Simple
	 * 		2) Geometric
	 * 		3) Exponential
	 * 		4) To be decided...
	 */
	private void smoothenValues()
	{
		SmoothingFilter smoothingFilter = new ExponentialMovingAverageSmoother(this, 0.5);
		smoothTimeSeriesValues = smoothingFilter.getSmoothedValues();
		smoothNormalisedTimeSeries = normalize(smoothTimeSeriesValues);
	}
	
	
	/**
	 * Implement  (val - mean)/sigma for all values in the list to normalize.
	 * The functions have been tested with a smaller data set.
	 * It returns proper mean, std deviation and normalised values. Returns NaN when 0/0 occurs. 
	 * Wrote this comment because they ask us to do unit testing.
	 * @param timeSeries The series to be normalized
	 * @return Normalized Series
	 */
	private List<Double> normalize(List<Double> timeSeries)
	{
		List<Double> normalizedValues = new ArrayList<Double>();
		double mean = meanCalculator();
		double standardDeviation = standardDeviationCalculator();

		for (double i : timeSeries)
		{
			normalizedValues.add((i-mean)/standardDeviation);
		}
		
		return normalizedValues;
	}
	
	public double getValue(int index)
	{
		return normalisedTimeSeries.get(index);
	}
	
	/**
	 * @return the standard deviation of the given time series
	 */
	private double standardDeviationCalculator()
	{
		double mean = meanCalculator();
		double deviation = 0;

		for(int  i = 0;  i < timeSeriesValues.size();i++)
			deviation += ((timeSeriesValues.get(i)-mean)*(timeSeriesValues.get(i)-mean));

		return Math.sqrt( deviation / (timeSeriesValues.size()-1));		
	}
	
	/**
	 * @return the mean of the given time series
	 */
	private double meanCalculator()
	{
		double sum = 0;
		for (int  i = 0;  i < timeSeriesValues.size(); i++)
		{			
			sum+=timeSeriesValues.get(i);	
		}
		return sum/timeSeriesValues.size();
	}
	/**
	 * Display the normalized time series between start and end.
	 * @param start
	 * @param end
	 */
	public void display(int start, int end) {
		for(int i=start;i<=end;i++)
		{
			System.out.print(normalisedTimeSeries.get(i)+" ");
		}
		System.out.println();
	}	
	
	/**
	 * Initializes timeSeriesValues (List<Double>) with values from file with filename = file_Path
	 * @param file_path
	 */
	private void initFromFile(String file_path)
	{
		try
		{	
			File f = new File(file_path);
			BufferedReader br = new BufferedReader(new FileReader(f));			
			timeSeriesValues = new ArrayList<Double>();			
			String line = null;
			while((line = br.readLine())!=null)
			{
				this.timeSeriesValues.add(Double.parseDouble(line));
			}			
		}
		catch(IOException ie)
		{
			log.info("IOException");
			ie.printStackTrace();
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
