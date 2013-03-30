package org.ck.beans;

import java.io.Serializable;

import org.ck.gui.Constants;
import org.ck.sample.Sample;

/**
 * This is just a dummy bean 
 * @author samiriff
 *
 */
public class TimeSeriesBean implements Serializable, Constants
{
	private Sample sample;
	private String taskType;
	private String algorithmType;
	public Sample getSample()
	{
		return sample;
	}
	public String getTaskType()
	{
		return taskType;
	}
	public String getAlgorithmType()
	{
		return algorithmType;
	}
	public void setSample(Sample sample)
	{
		this.sample = sample;
	}
	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}
	public void setAlgorithmType(String algorithmType)
	{
		this.algorithmType = algorithmType;
	}
	
}
