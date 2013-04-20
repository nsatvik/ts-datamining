package org.ck.tsdm;

import java.util.List;

/**
 * A point in a Q-Dimensional Phase Space 
 * This is a useless class that may prove useful in the future.
 *
 */
public class PhasePoint
{
	private List<Double> coords;	
		
	public PhasePoint(List<Double> subList)
	{
		coords = subList;
	}

	public List<Double> getCoords()
	{
		return coords;
	}

	public String toString()
	{
		return coords.toString();
	}
	
	
}
