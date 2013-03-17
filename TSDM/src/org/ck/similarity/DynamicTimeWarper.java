package org.ck.similarity;

import java.util.List;

import org.ck.gui.Constants;
import org.ck.sample.Sample;

public class DynamicTimeWarper extends SimilarityFinder implements Constants
{
	private Sample sample;
	
	/**
	 * Constructor that takes
	 * @param sample - A Sample object
	 */
	public DynamicTimeWarper(Sample sample)
	{
		this.sample = sample;
	}
	
	/**
	 * Calculates the distance between this sample and a query sample
	 * @param querySample - A sample object, i.e., another time series
	 * @return - the distance calculated by dynamic time warping
	 */
	public double getDistanceFrom(Sample querySample)
	{
		return getDistanceBetween(sample.getNormalizedTimeSeries(), querySample.getNormalizedTimeSeries());
	}

	/**
	 * A private method that calculates the distance between two time series
	 * @param s - a list containing time series values
	 * @param t - a list containing time series values
	 * @return the distance calculated by dynamic time warping
	 */
	private double getDistanceBetween(List<Double> s, List<Double> t)
	{
		int n = s.size();
		int m = t.size();
		double dtw[][] = new double[n+1][m+1];
		
		//These 2 steps are required to convert X[0...n-1] to X[1...n]
		s.add(0, 0.0);
		t.add(0, 0.0);

		for(int i=1; i<=m; i++)
			dtw[0][i] = INFINITY;		
		for(int i=1; i<=n; i++)
			dtw[i][0] = INFINITY;		
		dtw[0][0] = 0;
		
 		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=m; j++)
			{
				double cost = distance(s.get(i), t.get(j));
				dtw[i][j] = cost + Math.min(dtw[i-1][j],		//insertion 
									Math.min(dtw[i][j-1], 		//deletion
											dtw[i-1][j-1]));	//match
			}
		}             
		return dtw[n][m];
	}

	/**
	 * Euclidean distance calculator. Can be changed to something else.
	 * d(x, y) is a distance between symbols, i.e. d(x, y) = | x - y |.
	 * @return - Returns |x-y|
	 */
	private double distance(double x, double y)
	{		
		return Math.abs(x - y);
	}
}
