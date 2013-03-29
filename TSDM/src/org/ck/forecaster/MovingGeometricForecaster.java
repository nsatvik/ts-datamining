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
	public double gma(int i){
		if(i==0){
			return values.get(0);
		}
		return Math.sqrt(values.get(i)*gma(i-1));
	}
	public double geometricMean()
	{
		values = new ArrayList<Double>();
		Sample s = new Sample(file_path, sample_name);
		values = s.getTimeSeries();
		return gma(values.size()-1);		 
	}
}
