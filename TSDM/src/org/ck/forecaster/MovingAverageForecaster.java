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
	public double getAverage(int index){
		if(index == 0)
			return values.get(0);
		return ((values.get(index)+ getAverage(index-1))/2);
	}
	 public double movingAverage() 
	 {
		 values = new ArrayList<Double>();
		 Sample s = new Sample(file_path, sample_name);
		 values = s.getTimeSeries();
		 return getAverage(values.size()-1);

	 }
}
