package org.ck.tsdm;

import java.util.ArrayList;
import java.util.List;

import org.ck.sample.Sample;

/**
 * Represents a Q-Dimensional Phase Space
 *
 */
public class PhaseSpace
{
	int numDimensions;
	List<Double> series;
	List<PhasePoint> points;
	
	public PhaseSpace(List<Double> series, int Q)
	{
		numDimensions = Q;
		this.series = series;
		points = new ArrayList<PhasePoint>();
		initPhaseSpace();
	}
	
	private void initPhaseSpace()
	{
		int numValues = series.size() - numDimensions + 1;
		for(int i = 0; i < numValues; i++)
		{
			points.add(new PhasePoint(series.subList(i, i + numDimensions)));			
		}
	}

	public String toString()
	{
		return "" + points;
	}

	
}
