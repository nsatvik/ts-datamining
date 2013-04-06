package org.ck.smoothers;
import java.util.ArrayList;
import java.util.List;

import org.ck.sample.*;
public class GeometricMovingAverageSmoother extends SmoothingFilter
{		
	public GeometricMovingAverageSmoother(Sample sample, int k)
	{
		super(sample, k);
	}
	
	public void calculateSmoothedValues()
	{
		smoothedValues.clear();
		for(int i=0; i<values.size(); i++)
		{
			smoothedValues.add(getAverage(i, (int) k));
		}		
	}	
	
	public double getAverage(int index, int k)
	{
		if(index == 0 || k == 0){
			return values.get(index);
		}
		return Math.sqrt(values.get(index) * getAverage(index - 1, k - 1));
	}

	
}
