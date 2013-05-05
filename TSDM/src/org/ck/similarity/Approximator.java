package org.ck.similarity;

import java.util.ArrayList;
import java.util.List;

public class Approximator
{	
	/**
	 * n/w
	 */
	private double portionSize;
	/**
	 * PAA Segments, before average operation
	 */
	private List<List<Double>> segments;	
	/**
	 * This list will contain fewer values (dimension reduction)
	 */
	private List<Double> averagePAA;		
	/**
	 * This list will contain the same amount of values as the timeSeries
	 */
	private List<Double> averageVisualPAA;  	
	
	public Approximator(List<Double> timeSeries, int windowSize)
	{
		portionSize = (double)timeSeries.size() / windowSize;
		getApproximatedSeries(timeSeries, windowSize);	
	}
	
	/**
	 * Calculates a piece-wise approximation of the time series, where the size of each segment is equal 
	 * 	to windowSize.
	 * @param timeSeries
	 * @param windowSize
	 */
	public void getApproximatedSeries(List<Double> timeSeries, int windowSize)
	{	
		segments = formGroups(timeSeries, windowSize);
		averageOut(timeSeries.size(), windowSize, segments);		
	}	
	
	/**
	 * Forms groups
	 * Two cases
		1) n/w is a whole number
			Simple case of each portion having n/w number of values from the input time series
		2) n/w is a fraction
			Complicated case because you cannot assign equal number of whole numbered values from the input series to w equal sized portions
			Our example data has n = 14
			If w = 3, then n/w is a fraction
			The length of each portion is 14/3 = 4.66667
			Each portion should have 4.66667 values from the original time series

	 * @param timeSeries
	 * @param windowSize w
	 * @return 
	 */
	private List<List<Double>> formGroups(List<Double> timeSeries, int windowSize)
	{
		int N = timeSeries.size();		
		List<List<Double>> paa = new ArrayList<List<Double>>();
		
		//Case 1
		if((N % windowSize) == 0)
		{
			for(int i=0; i<timeSeries.size(); i += windowSize)
				paa.add(new ArrayList<Double>(timeSeries.subList(i, (int) Math.min(N, i + windowSize))));			
		}	
		
		//Case 2
		else
		{
		
			paa.add(new ArrayList<Double>());
			
			double sumCounter = 0;
			double multFactor = 1;
			for(int i=0; i<timeSeries.size(); i++)
			{
				if(sumCounter >= (int) portionSize)
				{
					multFactor  = portionSize - sumCounter;
					if(multFactor != 0)					
						paa.get(paa.size() - 1).add(timeSeries.get(i) * multFactor);
					
					paa.add(new ArrayList<Double>());
					multFactor = 1 - multFactor;
					
					paa.get(paa.size() - 1).add(timeSeries.get(i) * multFactor);
					sumCounter = multFactor;
					
					multFactor = 1;				
				}
				else
				{
					paa.get(paa.size() - 1).add(timeSeries.get(i) * multFactor);
					sumCounter += multFactor;
				}
			}
		}
		
		return paa;
	}
	
	/**
	 * Replaces all values with average values
	 * @param N
	 * @param windowSize
	 * @param segments
	 */
	private void averageOut(int N, int windowSize, List<List<Double>> segments)
	{
		averagePAA = new ArrayList<Double>();
		averageVisualPAA = new ArrayList<Double>();
		boolean isFirstIteration = true;
		for(List<Double> list : segments)
		{
			double sum = 0;
			for(double num : list)			
				sum += num;
			
			double avg = sum / list.size();
			
			//The following code is required for visualization of step-segments
			int i = (N % windowSize == 0 || isFirstIteration) ? 0 : 1;
			isFirstIteration = false;
			for(; i<list.size(); i++)							
				averageVisualPAA.add(avg);			
			
			//For calculation
			averagePAA.add(avg);
		}
	}

	/**
	 * @return the segments
	 */
	public List<List<Double>> getSegments()
	{
		return segments;
	}

	/**
	 * @return the averagePAA
	 */
	public List<Double> getAveragePAA()
	{
		return averagePAA;
	}

	/**
	 * @return the averageVisualPAA
	 */
	public List<Double> getAverageVisualPAA()
	{
		return averageVisualPAA;
	}	
}
