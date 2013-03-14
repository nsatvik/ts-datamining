package org.ck.sample;

import org.ck.gui.Constants;
import org.ck.gui.Constants.DatasetOptions;

public class DataHolder implements Constants
{
	public static String TRAINING_FILE_NAME;
	static
	{
		setDataset(DatasetOptions.SEA_LEVEL_DATASET);
	}
	
	
	public static void setDataset(DatasetOptions option)
	{
		switch(option)
		{
		case SEA_LEVEL_DATASET: TRAINING_FILE_NAME = "Training Data/Sea/sea-level-data.txt";
							
							break;
		
		case WINE_DATASET:	
							break;
		default :		
		case WATER_DATASET: break;
							
		}
	}
}
