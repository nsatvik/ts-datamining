package org.ck.beans;

import java.io.Serializable;

import org.ck.gui.Constants;
import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import java.util.logging.Logger;

/**
 * This bean stores information about requested values and results of calculations 
 * @author samiriff
 *
 */
public class TimeSeriesBean implements Serializable, Constants
{	
	private static final long serialVersionUID = -8676918565788849188L;	
	
	private Sample sample;
	private TaskType taskType = TaskType.SIMILARITY;
	private AlgorithmType algorithmType = AlgorithmType.DTW;
	private DatasetOptions dataset = DatasetOptions.SEA_LEVEL_DATASET;
	private String result = "TBD";
	
	public Sample getSample()
	{
		return sample;
	}
	
	public TaskType getTaskType()
	{
		return taskType;
	}
	
	public AlgorithmType getAlgorithmType()
	{
		return algorithmType;
	}
	
	public DatasetOptions getDataset()
	{
		return dataset;
	}
	
	public void setSample(Sample sample)
	{
		this.sample = sample;
	}
	
	public void setTaskType(TaskType taskType)
	{
		this.taskType = taskType;
	}
	
	public void setAlgorithmType(AlgorithmType algorithmType)
	{
		this.algorithmType = algorithmType;
	}
	
	public void setDataset(DatasetOptions dataset)
	{
		if(this.dataset.equals(dataset))
			return;
		
		this.dataset = dataset;		
		//sample = new Sample(DataHolder.TRAINING_FILE_NAME, DataHolder.SAMPLE_NAME);
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}	
}
