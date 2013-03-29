package org.ck.forecaster;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;

public class MovingAverageForecaster extends Forecaster {
	
	private List<Double> values;
	int size;
	double product = 1;
	String file_path, sample_name;
	 public MovingAverageForecaster(String file_path, String sample_name)
	{
		this.file_path = file_path;
		this.sample_name = sample_name;
	}
	 public double movingAverage(int period) //To be implemented
	 {
		 values = new ArrayList<Double>();
		 Sample s = new Sample(file_path, sample_name);
		 values = s.getTimeSeries();
		// for (i)
	//	return product;
		 return 0;
	 }
}
