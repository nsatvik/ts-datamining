package org.ck.tsdm.ga;

import java.util.ArrayList;

import org.ck.gui.Constants;
import org.ck.sample.DataHolder;

public class OptimalScoreException extends Exception implements Constants
{
	private Genome genomeSolution;
	
	public OptimalScoreException()
	{}
	
	public OptimalScoreException(String msg) 
	{
	    super(msg);
	}

	/*
	 * Since this exception is thrown when the Genetic algorithm has found a genome that has a high fitness 
	 * score, this constructor finds out the accuracy of the chosen genome's decision tree on the test set.
	 */
	public OptimalScoreException(Genome genome) 
	{
		this.genomeSolution = genome;	
		System.out.println("OPTIMAL GENOME CAUGHT");
		System.out.println(genome.toString());
	}
	
	public Genome getGenome()
	{
		return genomeSolution;
	}
}
