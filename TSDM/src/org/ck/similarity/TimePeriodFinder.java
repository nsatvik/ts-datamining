package org.ck.similarity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.ck.sample.Sample;

/**
 * This class helps in finding the time period of
 * the sampleValues. 
 * @author Satvik
 *
 */
public class TimePeriodFinder {
	
	private Sample dataSample;
	private int sampleLimit;
	private int timePeriod;
	private final double comparisionThreshold = 0.05;
	public TimePeriodFinder(Sample dataSample)
	{
		this.dataSample = dataSample;
		this.sampleLimit = dataSample.getNumOfValues();
		
	}
	
	public void setLimit(int limit)
	{
		this.sampleLimit = limit;
	}
	
	private List<Integer> getComparisionList() {
		
		List<Integer> c = new ArrayList<Integer>();
		List<Double> sampleValues = dataSample.getSeriesSubset(0, sampleLimit).getNormalizedTimeSeries();
		c.add(0);
		for (int i=1;i<sampleValues.size();i++)
		{
			c.add(getSimilarityValue(sampleValues,i));
		}
		return c;
	}
	private Integer getSimilarityValue(List<Double> sampleValues, int index) {
		int i=0,count = 0;
		double diffValue = Math.sqrt(Math.pow(sampleValues.get(i)-sampleValues.get(index),2));
		while(diffValue<comparisionThreshold && index<sampleLimit)
		{
			
			diffValue = Math.sqrt(Math.pow(sampleValues.get(i)-sampleValues.get(index),2));
			++count;
			++i;++index;
		}
			
		return count;
	}

	public void findTimePeriod()
	{
		List<Integer> cmpList = getComparisionList();
		int max = getMaxValue(cmpList);
		this.timePeriod = cmpList.indexOf(max)+1;
		System.out.println(cmpList);
		System.out.println(this.timePeriod);
		
	}
	private int getMaxValue(List<Integer> list)
	{
		int max = Integer.MIN_VALUE;
		for(int i=0;i<list.size();++i)
		{
			int val = list.get(i);
			if(val>max)
				max = val;
		}
		return max;
	}
	public int getTimePeriod()
	{
		return this.timePeriod;
	}
}
