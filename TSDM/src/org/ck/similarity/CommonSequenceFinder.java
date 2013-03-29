package org.ck.similarity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;
import org.ck.similarity.CommonSequenceFinder.Tuple;




public class CommonSequenceFinder {
	private Sample dataSample;
	private int timePeriod;
	private int sequenceLength;
	private List<Tuple> patternList;
	
	/**
	 * A tuple class to hold the end indices of the matched patterns
	 * The common sequence finder class returns the list of all tuples
	 * which represent the end indices of the matched values.
	 * @author Satvik
	 *
	 */
	public class Tuple
	{
		final int a;
		final int b;
		public Tuple(int a,int b)
		{
			this.a = a;
			this.b = b;
		}
		public int getValue(int index)
		{
			switch(index)
			{
			case 0:
				return a;
			case 1:
				return b;
				default:
					return -1;
			}
		}
	}
	public CommonSequenceFinder(Sample sample)
	{
		this.dataSample = sample;
		this.timePeriod = 20;
		this.sequenceLength = 5;
	}
	
	public void findPatterns()
	{
		final List<Double> sampleValues = dataSample.getNormalizedTimeSeries();
		int n = sampleValues.size();
		int i=0;
		this.patternList = new ArrayList<Tuple>();
		for(int k=0;(k+sequenceLength-1)<this.timePeriod;k++)
		{
			Tuple pattern = new Tuple(k,k+sequenceLength-1);
			for(i=this.timePeriod;i<n;i+=this.timePeriod)
			{
				if(isSimilar(sampleValues,i+k,i+k+this.sequenceLength-1,pattern))
				{
					this.patternList.add(new Tuple(i+k,i+k+this.sequenceLength-1));
				}					
			}
			
		}
		
	}
	
	/**
	 * Checks whether the pattern and the sampleValues within the indices are 
	 * similar. Uses euclidian distance 
	 * @param sampleValues
	 * @param i 
	 * @param j
	 * @param pattern
	 * @return
	 */
	private boolean isSimilar(List<Double> sampleValues, int startIndex, int endIndex,
			Tuple pattern) {
		int patternBegin = pattern.getValue(0),patternEnd = pattern.getValue(1);
		
		double difference = 0;
		for(;patternBegin<=patternEnd;patternBegin++,startIndex++)
			difference += Math.pow((sampleValues.get(patternBegin)-sampleValues.get(startIndex)),2);
		double disSimilarity = Math.sqrt(difference);
		
		if(disSimilarity<0.5)
			return true;
		
		return false;
	}
	public List<Tuple> getSimilarPatternList()
	{
		return this.patternList;
	}

	public void setTimePeriod(int t)
	{
		this.timePeriod = t;
	}
	public void setSequenceLength(int slen)
	{
		this.sequenceLength = slen;
	}

}
