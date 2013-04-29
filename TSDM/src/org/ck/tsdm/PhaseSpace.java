package org.ck.tsdm;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;
import org.ck.tsdm.ga.Genome;

/**
 * Represents a Q-Dimensional Phase Space
 *
 */
public class PhaseSpace
{
	private int numDimensions;
	private List<Double> series;
	private List<PhasePoint> points;
	private double maxValue;
	private double minValue;
	
	public PhaseSpace(List<Double> series, int Q)
	{
		numDimensions = Q;
		this.series = series;
		points = new ArrayList<PhasePoint>();
		initPhaseSpace();
		findMaxMinValues();
	}
	
	/**
	 * A pseudo-copy-constructor that initializes the phase space list with "points".
	 * @param phaseSpace
	 * @param points
	 */
	public PhaseSpace(PhaseSpace phaseSpace, List<PhasePoint> points)
	{
		numDimensions = phaseSpace.numDimensions;
		series = phaseSpace.series;
		this.points = points;
		findMaxMinValues();
	}
	
	private void findMaxMinValues()
	{
		maxValue = -Double.MIN_VALUE;
		minValue = Double.MAX_VALUE;
		for(double val : series)
		{
			if(val > maxValue)
				maxValue = val;
			if(val < minValue)
				minValue = val;
		}
	}

	private void initPhaseSpace()
	{
		int numValues = series.size() - numDimensions + 1;
		for(int i = 0; i < numValues; i++)
		{
			points.add(new PhasePoint(series.subList(i, i + numDimensions)));			
		}
	}
	
	/**
	 * 
	 * @return maxX, where X is the training time series.
	 */
	public double getMaxValueOfPhaseSpace()
	{
		return maxValue;
	}
	
	/**
	 * 
	 * @return minX, where X is the training time series
	 */
	public double getMinValueOfPhaseSpace()
	{
		return minValue;
	}

	public String toString()
	{
		return "" + points;
	}
	
	public int getNumOfDimensions()
	{
		return numDimensions;
	}
	
	public List<PhasePoint> getPhasePoints()
	{
		return points;
	}
	
	/**
	 * Fills up the two Integer lists with indices of phase points that lie 
	 * 		inside and outside the cluster respectively.
	 * The cluster is defined by clusterCenter and clusterRadius
	 * @param clusterCenter
	 * @param clusterRadius
	 * @param indicesInsideCluster
	 * @param indicesOutsideCluster
	 */
	public void findClusterPointIndices(PhasePoint clusterCenter, double clusterRadius,
			List<Integer> indicesInsideCluster, List<Integer> indicesOutsideCluster)
	{				
		int startIndex = (numDimensions - 2);
		for(PhasePoint point : points)
		{
			if(isWithinCluster(point, clusterCenter, clusterRadius))
				indicesInsideCluster.add(startIndex++);
			else
				indicesOutsideCluster.add(startIndex++);
		}		
	}

	/**
	 * Checks if point lies within a cluster defined by clusterCenter and clusterRadius
	 * @param point
	 * @param clusterCenter
	 * @param clusterRadius
	 * @return true, if point lies within cluster; 
	 * 		   false, otherwise
	 */
	private boolean isWithinCluster(PhasePoint point, PhasePoint clusterCenter,
			double clusterRadius)
	{
		return (point.getDistanceFrom(clusterCenter) <= clusterRadius);		
	}
	
	/**
	 * Calculates the average eventness, given the list of indices of points that lie in/out side the cluster
	 * 	calculated before
	 * @param indices
	 * @return
	 */
	public double getAverageEventness(List<Integer> indices)
	{
		double sum = 0;
		for(int index : indices)
			sum += series.get(index + 1);
		return (1.0 / indices.size()) * sum;
	}
	
	/**
	 * Calculates the variance of the eventness
	 * @param indices
	 * @param average
	 * @return
	 */
	public double getVarianceOfEventness(List<Integer> indices, double average)
	{
		double sum = 0;
		for(int index : indices)
			sum += Math.pow(series.get(index) - average, 2);
		return (1.0 / indices.size()) * sum;
	}
	
	/**
	 * Finds out all the Phase Points that lie within the given cluster, based on the list of
	 * 	indices passed as a parameter
	 * @param clusterCenter
	 * @param clusterRadius
	 * @param indicesInsideCluster - Indices of phase points in a (numDimensions + 1) phase space that lie within this cluster.
	 * @return
	 */
	public PhaseSpace getClusterPhaseSpace(PhasePoint clusterCenter, double clusterRadius, 
			List<Integer> indicesInsideCluster)
	{			
		List<PhasePoint> clusterPhasePoints = new ArrayList<PhasePoint>();
		
		/*int subFactor = numDimensions - 1;
		for(int index : indicesInsideCluster)
		{
			clusterPhasePoints.add(points.get(index - subFactor));
		}*/
		
		for(PhasePoint point : points)
		{
			if(isWithinCluster(point, clusterCenter, clusterRadius))
				clusterPhasePoints.add(point);			
		}	
		
		return new PhaseSpace(this, clusterPhasePoints);
	}

	public boolean containsElements(List<Double> subList)
	{
		for(PhasePoint phasePoint : points)
		{
			if(phasePoint.containsElement(subList))
				return true;
		}
		
		return false;
	}
}
