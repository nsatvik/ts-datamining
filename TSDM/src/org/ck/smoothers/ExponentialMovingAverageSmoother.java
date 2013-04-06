package org.ck.smoothers;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;

public class ExponentialMovingAverageSmoother extends SmoothingFilter 
{		
	public ExponentialMovingAverageSmoother(Sample sample, double k)
	{
		super(sample, k);
	}
	
	public void calculateSmoothedValues()
	{
		if(k < 0 || k > 1)
		{
			System.out.println("SMOOTHING FACTOR IS NOT VALID");
			return;
		}
		
		smoothedValues.clear();		
		
		smoothedValues.add(values.get(0));
		for(int i=1; i<values.size(); i++)
		{
			smoothedValues.add(k * values.get(i) + (1 - k) * smoothedValues.get(i - 1));
		}
	}

	public double getAverage(int index, int k)
	{		
		return values.get(index);
	}
}
