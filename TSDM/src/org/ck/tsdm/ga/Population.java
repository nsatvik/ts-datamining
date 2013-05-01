package org.ck.tsdm.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.ck.gui.Constants;
import org.ck.tsdm.PhaseSpace;
import org.ck.tsdm.TSDM;

public class Population implements Constants
{
	private List<Genome> genomes;
	private PhaseSpace phaseSpace;
	private PhaseSpace augmentedPhaseSpace;
	
	private double crossoverProbability = 0.85;
	private double mutationProbability = 0.025;
	
	private Random rgen = new Random();
	
	/**
	 * Initializes the population with for a TSDM object
	 * @param tsdm
	 * @throws OptimalScoreException
	 */
	public Population(TSDM tsdm) throws OptimalScoreException
	{
		genomes = new ArrayList<Genome>();
		phaseSpace = tsdm.getPhaseSpace();
		augmentedPhaseSpace = tsdm.getAugmentedPhaseSpace();
		randomPopulationInit(phaseSpace.getMaxValueOfPhaseSpace(), phaseSpace.getMinValueOfPhaseSpace());		
	}
	
	/**
	 * Darwin's Survival of the Fittest algorithm
	 * @throws OptimalScoreException
	 */
	public void runGeneticAlgorithm() throws OptimalScoreException
	{
		for(int i=0; i<NUM_OF_GENERATIONS; ++i)
		{
			double totalFitnessScore = assessFitness(genomes);
			System.out.println("Total Fitness Score = " + totalFitnessScore);
			naturalSelection(totalFitnessScore);			
		}
		
		findBestGenome();
	}
	
	/**
	 * This method is used only if the final generation is reached
	 * @throws OptimalScoreException 
	 */
	private void findBestGenome() throws OptimalScoreException
	{
		Logger.getLogger(Population.class.getName()).info("Reached the last generation");
		
		double fitnessScore = -Double.MIN_VALUE;
		Genome bestGenome = null;
		for(int i=0; i<genomes.size(); i++)
		{
			if(genomes.get(i).getFitnessScore() > fitnessScore)
			{
				bestGenome = genomes.get(i);
				fitnessScore = bestGenome.getFitnessScore();				
			}
		}
		
		throw new OptimalScoreException(bestGenome);
	}

	/**
	 * Creates a new population from the old population by selecting two genomes randomly at a time, and
	 * 		performing crossover and mutation operations.
	 * @param totalFitnessScore
	 * @throws OptimalScoreException
	 */
	private void naturalSelection(double totalFitnessScore) throws OptimalScoreException
	{
		ArrayList<Genome> newPopulation = new ArrayList<Genome>();
		
		while(newPopulation.size() < genomes.size())
		{
			Genome randGenome1 = rouletteSelection(totalFitnessScore);
			Genome randGenome2 = rouletteSelection(totalFitnessScore);
			
			//System.out.println("Selected 1 " + randGenome1.getFitnessScore());
			//System.out.println("Selected 2 " + randGenome2.getFitnessScore());
			
			crossoverGenomes(randGenome1, randGenome2, newPopulation);
			mutateGenomes(newPopulation);
			
			//displayPopulation();
		}
		
		genomes = newPopulation;
	}
	
	/**
	 * With a probability equal to CROSSOVER_PROBABILITY, two new children are created by mixing the traits of
	 * 		two genomes based on a crossover point. These new children are added to the new population. The parents aren't.
	 * With a probability equal to (1 - CROSSOVER_PROBABILITY), the father and mother are added to the new population.
	 * @param father
	 * @param mother
	 * @param newPopulation
	 * @throws OptimalScoreException
	 */
	private void crossoverGenomes(Genome father, Genome mother, ArrayList<Genome> newPopulation) throws OptimalScoreException
	{
		if(getProbabilisticOutcome(CROSSOVER_PROBABILITY_THRESHOLD))
		{
			int crossoverPoint = rgen.nextInt(father.getChromosome().length());
			
			Genome child1 = new Genome(father.getChromosome().substring(0, crossoverPoint) 
								+ mother.getChromosome().substring(crossoverPoint), 
								father.getNumDimensions(), father.getPhaseSpace());
			Genome child2 = new Genome(mother.getChromosome().substring(0, crossoverPoint) 
								+ father.getChromosome().substring(crossoverPoint),
								father.getNumDimensions(), father.getPhaseSpace());
			
			/*System.out.println("Child 1");
			child1.displayGenes();
			System.out.println("Child 2");
			child2.displayGenes();*/
			
			//New Generation
			newPopulation.add(child1);
			newPopulation.add(child2);
			
			return;
		}
		else
		{
			newPopulation.add(father);
			newPopulation.add(mother);
		}		
	}
	
	/*
	 * Performs genetic mutation on the two most recent offspring 
	 */
	private void mutateGenomes(ArrayList<Genome> newPopulation) throws OptimalScoreException
	{
		newPopulation.get(newPopulation.size() - 1).mutate(MUTATION_PROBABILITY_THRESHOLD);
		newPopulation.get(newPopulation.size() - 2).mutate(MUTATION_PROBABILITY_THRESHOLD);
	}
	
	/**
	 *  This kind of selection ensures that genomes having a higher fitness score than others have a better
	 * 		chance of being selected for reproduction.
	 * @param totalFitnessScore
	 * @return
	 */
	private Genome rouletteSelection(double totalFitnessScore)
	{
	    double ball  = rgen.nextDouble() * totalFitnessScore;
	    double slice = 0.0;
	 
	    for(int i=0; i<genomes.size(); i++)
	    {
	    	double fitnessScore = genomes.get(i).getFitnessScore();	    	
	    	if(Double.isNaN(fitnessScore))
	    		continue;
	        slice += fitnessScore;
	 
	        if(ball < slice)
	            return genomes.get(i);
	    }
	    
	    return genomes.get(0);
	}
	
	/**
	 * Returns the sum of all fitness scores of all the genomes in the current population.
	 * @param genomes
	 * @return
	 */
	private double assessFitness(List<Genome> genomes)
	{
		double totalFitnessScore = 0.0;
		for(int i=0; i<genomes.size(); i++)
		{
			double fitnessScore = genomes.get(i).getFitnessScore();
			if(Double.isNaN(fitnessScore))
				continue;
			totalFitnessScore += fitnessScore;
		}
		
		return totalFitnessScore;
	}

	/**
	 * Randomly initializes a set of genomes
	 * @param pmin 
	 * @param pmax 
	 * @throws OptimalScoreException 
	 */
	private void randomPopulationInit(double pmax, double pmin) throws OptimalScoreException
	{
		double maxBinValue = Math.pow(2, ALLELE_SIZE) - 1;
		for(int i=0; i<POPULATION_SIZE; i++)
		{
			genomes.add(new Genome(getRandomGenome(maxBinValue), Q_DIMENSION + 1, augmentedPhaseSpace));
		}
	}
	
	/**
	 * Randomly initializes a genome	
	 */
	private String getRandomGenome(double maxValue)
	{
		String chromosome = "";
		
		for(int i=0; i<Q_DIMENSION + 1; i++)
		{
			chromosome += toNBitBinaryString((int)(Math.random() * maxValue), ALLELE_SIZE);
		}
		
		return chromosome;
	}

	/**
	 * Converts a number i of any length to a bit string of length n 
	 * @param i
	 * @param n
	 * @return
	 */
	private String toNBitBinaryString(int i, int n)
	{
		String str = Integer.toBinaryString(i);
		
		if(str.length() == n)
			return str;
		
		String zeroes = new String(new char[n - str.length()]).replace("\0", "0");
		return zeroes + str;
	}
	
	public String toString()
	{
		String str = "";
		for(Genome genome : genomes)
			str += genome.toString() + "\n";
		return str;
	}
	
	/**
	 * Simulates a random event
	 * @param probability
	 * @return
	 */
	public static boolean getProbabilisticOutcome(double probability)
	{
		boolean result = false;
		if (probability == 1.0)
			result = true;		
		else
		{
			double randomValue = Math.random();
 
			if (randomValue <= probability)			
				result = true;
		}
 
		return result;
	}	
}
