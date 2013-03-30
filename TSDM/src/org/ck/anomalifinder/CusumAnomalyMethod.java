package org.ck.anomalifinder;

import java.util.List;

import org.ck.sample.Sample;

@SuppressWarnings("unused")
public class CusumAnomalyMethod {
	double positivesum = 0, negetivesum = 0;
	public CusumAnomalyMethod(String file_path, String sample_name,double weight, double threshold) {
		double w, t;
		Sample series = new Sample(file_path, sample_name);
		List<Double> fullSeries = series.getTimeSeries();
	 	Sample subSeries = series.getSeriesSubset(0, series.getNumOfValues()/10);
	 	double mean = subSeries.getMean();
	 	double stdDeviation = subSeries.getStandardDeviation();
	 	w = weight;
	 	t = threshold;
	 	cusum(w,t,fullSeries,series.getNumOfValues());
	 	
	}
	double max(double a, double b)
	{
		if(a<b)
			return b;
		else
			return a;
		
	}
	double min(double a, double b)
	{
		if(a<b)
			return a;
		else
			return b;
		
	}
	public double cusum(double w, double t, List<Double> fullSeries, int number){
		if(number == 0)
			return 0;
		else if(fullSeries.get(number)>positivesum)
			return positivesum = max(0,cusum(w,t,fullSeries,number-1)-w);
		else
			return negetivesum = min(0,cusum(w,t,fullSeries,number-1)-w);
		
	}

}
