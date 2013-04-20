package org.ck.gui;

public interface Constants
{
	public static final int I_AM_A_CONSTANT = 2; // :D
	public static final int INFINITY = Integer.MAX_VALUE;
	public static final int PAA_WINDOW_SIZE = 11;
	public static final int SAX_ALPHA_SIZE = 6;
	public static final String PATH_PREFIX = "/jsp/";
	
	enum DatasetOptions
	{
		SEA_LEVEL_DATASET,
		WATER_LEVEL_DATASET,
		ELECTRICITY_DATASET,
		FINANCE_NIFTY_DATASET,
		FINANCE_VIX_DATASET,
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
		MARKOV_MODEL_TECHNIQUE,
		TSDM
	}	
}
