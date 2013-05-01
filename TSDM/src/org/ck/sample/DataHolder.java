package org.ck.sample;

import org.ck.gui.Constants;
import org.ck.gui.Constants.DatasetOptions;

public class DataHolder implements Constants
{
	public static String TRAINING_FILE_NAME;
	public static String TIME_DATA_FILE;
	public static String SAMPLE_NAME;
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
		case FINANCE_NIFTY_DATASET: TRAINING_FILE_NAME = "Training Data/Finance/nifty.txt";
								    SAMPLE_NAME = "Nifty Stock Index Data";
								    TIME_DATA_FILE= "Training Data/Finance/nifty.tm";
								    break;
		case FINANCE_VIX_DATASET: TRAINING_FILE_NAME = "Training Data/Finance/vix.txt";
								  SAMPLE_NAME = "Vix Stock Index Data";
								  TIME_DATA_FILE = "Training Data/Finance/vix.tm";
								  break;
		case ECG_DATASET: TRAINING_FILE_NAME = "Training Data/Medical/ecg-data.txt";
		  SAMPLE_NAME = "ECG Data";
		  TIME_DATA_FILE = "Training Data/Medical/ecg-data.tm";
		  break;
		default:
			break;
		}
	}
}
