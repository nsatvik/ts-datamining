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
	private SubTaskType subTaskType = SubTaskType.NONE;
	private AlgorithmType algorithmType = AlgorithmType.DTW;
	private DatasetOptions dataset = DatasetOptions.SEA_LEVEL_DATASET;
	
	private String params = "";		//Required for processing user-defined queries
	
	private String result = "TBD";
	
	public Sample getSample()
	{
		return sample;
	}
	
	public TaskType getTaskType()
	{
		return taskType;
	}
	
	public SubTaskType getSubTaskType()
	{
		return subTaskType;
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
	
	public void setTaskType(String taskType)
	{
		if(taskType == null)
			return; 
		this.taskType = TaskType.valueOf(taskType);
	}
	
	public void setSubTaskType(String subTaskType)
	{
		if(subTaskType == null)
			return; 		
		this.subTaskType = SubTaskType.valueOf(subTaskType);
	}
	
	public void setAlgorithmType(String algorithmType)
	{
		if(algorithmType == null)
			return;		
		this.algorithmType = AlgorithmType.valueOf(algorithmType);
	}
	
	public void setDataset(String dataset)
	{
		if(this.dataset.equals(dataset))
			return;
		
		if(dataset == null)
			return;		
		this.dataset = DatasetOptions.valueOf(dataset);		
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}	
	
	public String getParams()
	{
		return params;
	}

	public void setParams(String params)
	{
		this.params = params;
	}
	
	@Override
	public String toString()
	{
		return "TimeSeriesBean [sample=" + sample + ", taskType=" + taskType
				+ ", subTaskType=" + subTaskType + ", algorithmType="
				+ algorithmType + ", dataset=" + dataset + ", params=" + params
				+ ", result=" + result + "]";
	}
}
