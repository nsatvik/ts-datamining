package org.ck.smoothers;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;

public class SimpleMovingAverageSmoother extends SmoothingFilter 
{	
	public SimpleMovingAverageSmoother(Sample sample, int k)
	{
		super(sample, k);		
	}
	
	public void calculateSmoothedValues()
	{
		smoothedValues.clear();
		for(int i=0; i<values.size(); i++)
		{
			if(i > k)
				smoothedValues.add(smoothedValues.get(i - 1) + (values.get(i) - values.get((int) (i - k))) / k);
			else			
				smoothedValues.add(values.get(i));			
		}
	}

	public double getAverage(int index, int k)
	{
		if(index == 0 || k == 0)
			return values.get(index);
		return ((values.get(index)+ getAverage(index - 1, k - 1)) / 2);
	}
}
