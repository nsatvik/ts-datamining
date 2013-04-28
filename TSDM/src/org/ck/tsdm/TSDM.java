package org.ck.tsdm;

import java.util.logging.Logger;

import org.ck.gui.Constants;
import org.ck.sample.Sample;
import org.ck.tsdm.ga.Genome;
import org.ck.tsdm.ga.OptimalScoreException;
import org.ck.tsdm.ga.Population;

/**
 * This class is to be used to find Temporal Patterns in Time Series 
 *
 */
public class TSDM implements Constants
{
	private Logger log = Logger.getLogger(TSDM.class.getName());
	
	private Sample sample;
	private PhaseSpace phaseSpace;
	private PhaseSpace augmentedPhaseSpace;
	
	private Population population;
	PhaseSpace clusterPhaseSpace;
	
	public TSDM(Sample sample)
	{
		this.sample = sample;
		phaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION);
		augmentedPhaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION + 1);
		
		findOptimalTemporalPattern();
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
	
	public PhaseSpace getClusterPhaseSpace()
	{
		return clusterPhaseSpace;
	}
	
	/**
	 * Initializes a population and runs a Genetic Algorithm to find an optimal temporal pattern
	 */
	private void findOptimalTemporalPattern()
	{
		try
		{
			Population population = new Population(this);
			population.runGeneticAlgorithm();
			System.out.println(population.toString());
		}
		catch(OptimalScoreException e)
		{
			Genome optimalGenome = e.getGenome();
			log.info(optimalGenome.toString());
			clusterPhaseSpace = phaseSpace.getClusterPhaseSpace(optimalGenome.getClusterCenter(), 
					optimalGenome.getClusterRadius(), optimalGenome.getIndicesInsideCluster());
			log.info(clusterPhaseSpace.toString());
		}	
	}
}
