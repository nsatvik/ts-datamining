package org.ck.sample;

import org.ck.gui.Constants;
import org.ck.gui.Constants.DatasetOptions;

public class DataHolder implements Constants
{
	public static String TRAINING_FILE_NAME;
	public static String TIME_DATA_FILE;
	public static String SAMPLE_NAME;
	private static double fitnessScoreThreshold = 5;			//Required by genetic algorithm
	static
	{
		setDataset(DatasetOptions.SEA_LEVEL_DATASET);
	}


	public static void setDataset(DatasetOptions option)
	{
		switch(option)
		{
		case SEA_LEVEL_DATASET: TRAINING_FILE_NAME = "Training Data/Sea/sea-level-data.txt";
		TIME_DATA_FILE = "Training Data/Sea/sea-level-data.tm";
		SAMPLE_NAME = "Sea Level Data";
		break;
		case WATER_LEVEL_DATASET: TRAINING_FILE_NAME = "Training Data/Water/Brahmavara.txt";
		SAMPLE_NAME = "Bullshit Water Data";
		TIME_DATA_FILE = "Training Data/Water/Brahmavara.tm";
		break;
		case ELECTRICITY_DATASET: TRAINING_FILE_NAME = "Training Data/Electricity/electricity-demand.txt";
		SAMPLE_NAME = "Electricity Demand Data";
		TIME_DATA_FILE = "Training Data/Electricity/electricity-demand.tm";
		break;
		case RAIN_BAGEPALLI: TRAINING_FILE_NAME = "Training Data/Water/Bagepalli.txt";
		SAMPLE_NAME = "Bagepalli Rainfall Data";
		TIME_DATA_FILE= "Training Data/Finance/nifty.tm";
		break;
		case RAIN_CHIKBALLAPUR: TRAINING_FILE_NAME = "Training Data/Water/Chikballapur.txt";
		SAMPLE_NAME = "Chikballapur Rainfall Data";

		break;
		case RAIN_CHINTAMANI: TRAINING_FILE_NAME = "Training Data/Water/Chintamani.txt";
		SAMPLE_NAME = "Chintamani Rainfall Data";

		break;
		case RAIN_GOWRIBIDANUR: TRAINING_FILE_NAME = "Training Data/Water/Gowribidanur.txt";
		SAMPLE_NAME = "Gowribidanur Rainfall Data";

		break;
		case RAIN_GUDIBANDA: TRAINING_FILE_NAME = "Training Data/Water/Gudibanda.txt";
		SAMPLE_NAME = "Gudibanda Rainfall Data";

		break;
		case RAIN_SIDLAGHATTA: TRAINING_FILE_NAME = "Training Data/Water/Sidlaghatta.txt";
		SAMPLE_NAME = "Sidlaghatta Rainfall Data";

		break;
		case ECG_DATASET: TRAINING_FILE_NAME = "Training Data/Medical/ecg-data.txt";
		SAMPLE_NAME = "ECG Data";
		break;
		default:
			break;
		}
	}

	public static double getFitnessScoreThreshold()
	{
		return fitnessScoreThreshold;
	}

	public static void setFitnessScoreThreshold(double fitnessScoreThreshold)
	{
		DataHolder.fitnessScoreThreshold = fitnessScoreThreshold;
	}
}
