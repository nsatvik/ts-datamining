package org.ck.tsdm;

import org.ck.gui.Constants;
import org.ck.sample.Sample;
import org.ck.tsdm.ga.Population;

/**
 * This class is to be used to find Temporal Patterns in Time Series 
 *
 */
public class TSDM implements Constants
{
	Sample sample;
	PhaseSpace phaseSpace;
	PhaseSpace augmentedPhaseSpace;
	
	public TSDM(Sample sample)
	{
		this.sample = sample;
		phaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION);
		augmentedPhaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION + 1);		
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
