package org.ck.smoothers;

import java.util.ArrayList;
import java.util.List;

import org.ck.gui.Constants;
import org.ck.sample.Sample;

public abstract class SmoothingFilter
{
	protected List<Double> values;	
	protected List<Double> smoothedValues;
	protected double k;

	/**
	 * Initializes the list of values from a Sample object and calculates smoothing values
	 * @param sample
	 * @param k - Smoothing factor
	 */
	public SmoothingFilter(Sample sample, double k)
	{
		values = sample.getNormalizedTimeSeries();
		smoothedValues = new ArrayList<Double>();
		this.k = k;
		calculateSmoothedValues();
	}
	
	/**
	 * Calculates and stores smoothed values in a list
	 * @return
	 */
	public abstract void calculateSmoothedValues();
	
	/**
	 * @return list of smoothed values
	 */
	public List<Double> getSmoothedValues()
	{
		return smoothedValues;
	}
	
	
	//LEGACY METHODS BELOW:
	
	/**
	 * Calculates Moving Average
	 * @param index The index of the dataset that has to be smoothed
	 * @param k In case of exponential smoothing, this is alpha - the smoothing factor
	 * @return Smoothed value of the 'index'th value of the dataset
	 */
	public abstract double getAverage(int index, int k);
	
	/**
	 * Calculates smoothed value of the last item of the dataset
	 * @return smoothed value of the last item of the dataset
	 */
	public double getAverage()
	{
		return getAverage(values.size() - 1, values.size() - 1);
	}
}
