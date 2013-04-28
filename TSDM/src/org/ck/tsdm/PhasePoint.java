package org.ck.tsdm;

import java.util.ArrayList;
import java.util.List;

/**
 * A point in a Q-Dimensional Phase Space 
 * This is a useless class that may prove useful in the future.
 *
 */
public class PhasePoint
{
	private List<Double> coords;
	private int size;
	
	public PhasePoint(int size)
	{
		coords = new ArrayList<Double>();
		this.size = size;
	}
		
	public PhasePoint(List<Double> subList)
	{
		coords = subList;
		size = coords.size();
	}

	public List<Double> getCoords()
	{
		return coords;
	}
	
	public void addPoint(double point)
	{
		if(coords.size() < size)
			coords.add(point);
	}
	
	/**
	 * Converts a gene to a phase point based on the formula given in Formula 3.12 in Jackpot.pdf
	 * 		and adds it to the Phase Point if and only if there is space in the PhasePoint.
	 * @param gene
	 * @param pmax
	 * @param pmin
	 * @return the converted value
	 */
	public double addPoint(String gene, double pmax, double pmin)
	{
		double sum = 0;
		for(int i=0; i<gene.length(); i++)
		{
			sum += Math.pow(2, i) * (gene.charAt(i) == '1' ? 1 : 0);
		}
		
		double value = (sum / Math.pow(2, gene.length()) * (pmax - pmin)) + pmin;
		addPoint(value);
		return value;
	}
	
	/**
	 * Finds the Euclidean distance between this point and point p
	 * @param p
	 * @return
	 */
	public double getDistanceFrom(PhasePoint p)
	{
		double sum = 0;
		for(int i=0; i<coords.size(); i++)
			sum += Math.pow(coords.get(i) - p.coords.get(i), 2);
		return Math.sqrt(sum);
	}

	public String toString()
	{
		return coords.toString();
	}	
}
