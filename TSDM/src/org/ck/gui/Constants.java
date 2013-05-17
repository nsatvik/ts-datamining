package org.ck.gui;

public interface Constants
{
	public static final int I_AM_A_CONSTANT = 2; // :D
	public static final int INFINITY = Integer.MAX_VALUE;
	public static final int PAA_WINDOW_SIZE = 11;
	public static final int SAX_ALPHA_SIZE = 6;
	public static final String PATH_PREFIX = "/jsp/";
	
	public static final int Q_DIMENSION = 2;
	public static final int ALLELE_SIZE = 8;
	
	public static final int POPULATION_SIZE = 75;
	public static final int NUM_OF_GENERATIONS = 500;
	public static final double FITNESS_SCORE_THRESHOLD = 7;
	public static final double CROSSOVER_PROBABILITY_THRESHOLD = 0.85;
	public static final double MUTATION_PROBABILITY_THRESHOLD = 0.025;
	
	
	enum DatasetOptions
	{
		SEA_LEVEL_DATASET,
		WATER_LEVEL_DATASET,
		RAIN_BAGEPALLI,
		RAIN_CHIKBALLAPUR,
		RAIN_CHINTAMANI,
		RAIN_GOWRIBIDANUR,
		RAIN_GUDIBANDA,
		RAIN_SIDLAGHATTA,
		ELECTRICITY_DATASET,
		ECG_DATASET
	}
	
	enum TaskType
	{
		SIMILARITY,
		FORTUNE_TELLER,
		ANOMALY_DETECTIVE, 
		TEMPORAL_PATTERN_MINER
	}
	
	enum SubTaskType
	{
		NONE, 
		UPDATE_GRAPH
	}
	
	enum AlgorithmType
	{
		DTW,
		SAX,
		MOVING_AVERAGE,
		COMMON_SUBSEQUENCE,
		NARX_NN,
		CUSUM,
		MOVING_EXPONENTIAL,
		MOVING_GEOMETRIC,
		MARKOV_MODEL_TECHNIQUE,
		TSDM,
		STATISTICAL_APPROACH
	}	
}
