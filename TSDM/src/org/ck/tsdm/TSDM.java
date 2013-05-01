package org.ck.tsdm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private Map<Integer, Double> temporalPatternMarkings;
	
	public TSDM(Sample sample)
	{
		this.sample = sample;
		phaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION);
		augmentedPhaseSpace = new PhaseSpace(sample.getNormalizedTimeSeries(), Q_DIMENSION + 1);
		
		findOptimalTemporalPattern();
		initTemporalPatternMarkings();
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
	
	public Map<Integer, Double> getTemporalPatternMarkers()
	{
		return temporalPatternMarkings;
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
	
	/**
	 * This fills up a structure that maps time indices to temporal pattern phase points of the optimal cluster
	 */
	private void initTemporalPatternMarkings()
	{
		temporalPatternMarkings = new HashMap<Integer, Double>();
		List<Double> timeSeries = sample.getNormalizedTimeSeries();
		for(int i=0; i<timeSeries.size() - Q_DIMENSION; i++)
		{
			if(clusterPhaseSpace.containsElements(timeSeries.subList(i, i + Q_DIMENSION)))
			{
				for(int j=i; j<i+Q_DIMENSION; j++)
					temporalPatternMarkings.put(j, timeSeries.get(j));
			}
		}
		log.info("Map = " + temporalPatternMarkings.toString());
	}
}
