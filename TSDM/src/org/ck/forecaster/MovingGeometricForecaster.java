package org.ck.forecaster;
import java.util.ArrayList;
import java.util.List;

import org.ck.sample.*;
public class MovingGeometricForecaster extends Forecaster 
{
	private List<Double> values;
	int size;
	double product = 1;
	String file_path, sample_name;
	
	
	public MovingGeometricForecaster(String file_path, String sample_name)
	{
		this.file_path = file_path;
		this.sample_name = sample_name;
	}
	
	public double geometricMean()
	{
		values = new ArrayList<Double>();
		Sample s = new Sample(file_path, sample_name); // I did'nt know what to use. So, I hard coded. Change it during the review.
		values = s.getTimeSeries();
		size = values.size();
		for (int i = 0; i<values.size();i++)
		{
			product*= values.get(i);
		}
		return Math.pow(product, 1/values.size());		 
	}
}
