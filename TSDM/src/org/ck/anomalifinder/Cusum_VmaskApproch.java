package org.ck.anomalifinder;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;

public class Cusum_VmaskApproch {
	
	private List<Double> dataSampleTimeSeries;
	private List<Double> cumulatedTimeSeries;
	private List<Double> cusumHiSeries;
	private List<Double> cusumLowSeries;
	private double h,k; //h and k are parameters of the V mask.
	
	private double sampleSize=12;
	private double thresholdMean = 0; //Threshold Mean
	private double thresholdSD = 1;//Threshold Standard deviation
	
	public Cusum_VmaskApproch(Sample sample)
	{
		this.dataSampleTimeSeries = sample.getNormalizedTimeSeries();
		this.cumulatedTimeSeries = new ArrayList<Double>();
		this.cusumHiSeries = new ArrayList<Double>();
		this.cusumLowSeries = new ArrayList<Double>();
		this.h = 4;//Some random values! 
		this.k = 1;
	}
	
	public void computeCusumSereis()
	{
		findCumulatedTimeSeries();
		this.cusumHiSeries.add(0.0);
		this.cusumLowSeries.add(0.0);
		for(int i=1;i<this.cumulatedTimeSeries.size();++i)
		{
			
			this.cusumHiSeries.add(Math.max(0, this.cusumHiSeries.get(i-1)+this.cumulatedTimeSeries.get(i)-this.thresholdMean-this.k));
			this.cusumLowSeries.add(Math.max(0, this.cusumHiSeries.get(i-1)-this.cumulatedTimeSeries.get(i)+this.thresholdMean-this.k));
			
		}
		
	}
	private void findCumulatedTimeSeries() {
		int i = 0;
		while(i<dataSampleTimeSeries.size())
		{
			double sum = 0;
			for(int j=0;j<this.sampleSize&&(i+j)<dataSampleTimeSeries.size();++j)
			{
				sum += this.dataSampleTimeSeries.get(i+j);
			}
			this.cumulatedTimeSeries.add(sum/this.sampleSize);
			i += this.sampleSize;
		}
		
	}

	public void setHval(double hVal)
	{
		this.h = hVal;
	}
	
	
	public void setKval(double kVal)
	{
		this.k = kVal;
	}
	
	public List<Integer> getDefectiveDataPoints()
	{
		List<Integer> defectivePoints = new ArrayList<Integer>();
		System.out.println(this.cumulatedTimeSeries);
		for(int i=0;i<this.cumulatedTimeSeries.size();++i)
		{
			
			if(this.cusumHiSeries.get(i)>this.h || this.cusumLowSeries.get(i)>(this.h))
			{
				defectivePoints.add(i);
			}
		}
		return defectivePoints;
	}

}
