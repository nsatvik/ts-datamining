package org.ck.tsdm;

import org.ck.sample.Sample;

/**
 * This class is to be used to find Temporal Patterns in Time Series 
 *
 */
public class TSDM
{
	Sample sample;
	PhaseSpace phaseSpace;
	PhaseSpace augmentedPhaseSpace;
	
	public TSDM(Sample sample)
	{
		this.sample = sample;
		phaseSpace = new PhaseSpace(sample.getTimeSeries(), 2);
		augmentedPhaseSpace = new PhaseSpace(sample.getTimeSeries(), 3);
	}
	
	public String toString()
	{
		return phaseSpace.toString() + "\n"
				+ augmentedPhaseSpace.toString();		
	}
	
	public PhaseSpace getPhaseSpace()
	{
		return phaseSpace;
	}
	
	public PhaseSpace getAugmentedPhaseSpace()
	{
		return augmentedPhaseSpace;
	}
}
