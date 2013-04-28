package org.ck.tsdm.ga;

import java.util.ArrayList;
import java.util.List;

import org.ck.gui.Constants;
import org.ck.tsdm.PhasePoint;
import org.ck.tsdm.PhaseSpace;

public class Genome implements Constants
{
	private String chromosome;
	private double fitnessScore;
	
	private PhasePoint clusterCenter;
	private double clusterRadius;
	
	private int numDimensions;
	private int alleleSize;		// the number of bits in each dimensions
	private PhaseSpace phaseSpace;
	
	/**
	 * Initializes a genome with the given chromosome, for a numDimension Phase Space
	 * @param chromosome - a bit string of size = (numDimensions + 1) * alleleSize
	 * @param numDimensions - number of dimensions in the phase space
	 * @param phaseSpace - the phaseSpace against which fitness score is to be calculated
	 * @throws OptimalScoreException 
	 * @throws Exception 
	 */
	public Genome(String chromosome, int numDimensions, PhaseSpace phaseSpace) throws OptimalScoreException
	{		
		this.chromosome = chromosome;
		this.numDimensions = numDimensions;
		this.alleleSize = chromosome.length() / (numDimensions + 1);
		this.phaseSpace = phaseSpace;
		
		initClusterFromChromosome(chromosome, phaseSpace);
		calculateFitnessScore(phaseSpace);
		
		if(fitnessScore >= FITNESS_SCORE_THRESHOLD)
			throw new OptimalScoreException(this);
	}

	/**
	 * Initializes the cluster parameters from information contained in the chromosome
	 * @param chromosome
	 * @param phaseSpace
	 */
	private void initClusterFromChromosome(String chromosome, PhaseSpace phaseSpace)
	{
		double pmax = phaseSpace.getMaxValueOfPhaseSpace();
		double pmin = phaseSpace.getMinValueOfPhaseSpace();
		
		clusterCenter = new PhasePoint(numDimensions);
		int i;
		for(i=0; i<numDimensions; i++)
		{
			String gene = chromosome.substring(i * alleleSize, i * alleleSize + alleleSize);
			clusterCenter.addPoint(gene, pmax, pmin);
		}
		
		//Cluster Radius init
		String gene = chromosome.substring(i * alleleSize, i * alleleSize + alleleSize);
		double sum = 0;
		for(i=0; i<gene.length(); i++)
		{
			sum += Math.pow(2, i) * (gene.charAt(i) == '1' ? 1 : 0);
		}
		
		clusterRadius = Math.abs((sum / Math.pow(2, gene.length()) * (pmax - pmin)) + pmin);		
	}
	
	public String getChromosome()
	{
		return chromosome;
	}

	
	/**
	 * Calculates the finess of this genome, by using the t-statistical test against the 
	 * 	given phase space
	 * @param phaseSpace
	 */
	private void calculateFitnessScore(PhaseSpace phaseSpace)
	{
		List<Integer> indicesInsideCluster = new ArrayList<Integer>();
		List<Integer> indicesOutsideCluster = new ArrayList<Integer>();
		phaseSpace.findClusterPointIndices(clusterCenter, clusterRadius, indicesInsideCluster, indicesOutsideCluster);
		
		double averageEventnessInsideCluster = phaseSpace.getAverageEventness(indicesInsideCluster);
		double averageEventnessOutsideCluster = phaseSpace.getAverageEventness(indicesOutsideCluster);
		
		double varianceEventnessInsideCluster = phaseSpace.getVarianceOfEventness(indicesInsideCluster, averageEventnessInsideCluster);
		double varianceEventnessOutsideCluster = phaseSpace.getVarianceOfEventness(indicesOutsideCluster, averageEventnessOutsideCluster);
		
		fitnessScore = (averageEventnessInsideCluster - averageEventnessOutsideCluster) * 
				Math.sqrt(varianceEventnessInsideCluster / indicesInsideCluster.size() 
						+ varianceEventnessOutsideCluster / indicesOutsideCluster.size());
	}
	
	/**
	 * IMPORTANT: This method is to be used only by the Optimal Genome 
	 * 		(added to save heap space when Population size is very large)
	 * @return
	 */
	public List<Integer> getIndicesInsideCluster()
	{
		List<Integer> indicesInsideCluster = new ArrayList<Integer>();
		List<Integer> indicesOutsideCluster = new ArrayList<Integer>();
		phaseSpace.findClusterPointIndices(clusterCenter, clusterRadius, indicesInsideCluster, indicesOutsideCluster);
		
		return indicesInsideCluster;
	}
	
	/**
	 * Every bit of the chromosome string has a probability equal to mutationProbability of mutating.
	 * 		After mutation, the decision tree of this genome is reinitialized
	 * @param mutationProbability
	 * @throws OptimalScoreException
	 */
	public void mutate(double mutationProbability) throws OptimalScoreException
	{
		StringBuffer chromosomeBuffer = new StringBuffer(getChromosome());
		for(int i=0; i<chromosomeBuffer.length(); i++)
		{
			if(Population.getProbabilisticOutcome(mutationProbability))
			{				
				chromosomeBuffer.setCharAt(i, (chromosomeBuffer.charAt(i) == '0') ? '1':'0');
			}
		}		
		
		initClusterFromChromosome(chromosomeBuffer.toString(), phaseSpace);	
		calculateFitnessScore(phaseSpace);		
		if(fitnessScore >= FITNESS_SCORE_THRESHOLD)
			throw new OptimalScoreException(this);
	}
	
	/**
	 * Returns the already-calculated Fitness Score
	 * @return
	 */
	public double getFitnessScore()
	{
		return fitnessScore;
	}
	
	public int getNumDimensions()
	{
		return numDimensions;
	}
	
	public PhaseSpace getPhaseSpace()
	{
		return phaseSpace;
	}
	
	public PhasePoint getClusterCenter()
	{
		return clusterCenter;
	}
	
	public double getClusterRadius()
	{
		return clusterRadius;
	}

	@Override
	public String toString()
	{
		return "Genome [chromosome=" + chromosome + ", fitnessScore="
				+ fitnessScore + ", clusterPoint=" + clusterCenter
				+ ", clusterRadius=" + clusterRadius + ", numDimensions="
				+ numDimensions + ", alleleSize=" + alleleSize + "]";
	}
}
