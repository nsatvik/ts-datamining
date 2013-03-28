package org.ck.sample;

import org.ck.gui.Constants;
import org.ck.gui.Constants.DatasetOptions;

public class DataHolder implements Constants
{
	public static String TRAINING_FILE_NAME;
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
								SAMPLE_NAME = "Sea Level Data";
		break;
		case ELECTRICITY_DATASET: TRAINING_FILE_NAME = "Training Data/Electricity/electricity-demand.txt";
		SAMPLE_NAME = "Electricity Demand Data";
		break;
		case FINANCE_NIFTY_DATASET: TRAINING_FILE_NAME = "Training Data/Finance/nifty.txt";
		SAMPLE_NAME = "Nifty Stock Index Data";
		break;
		case FINANCE_VIX_DATASET: TRAINING_FILE_NAME = "Training Data/Finance/vix.txt";
		SAMPLE_NAME = "Vix Stock Index Data";
		break;
		default:
			break;
		}
	}
}
