package org.ck.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ck.gui.Constants;
import org.ck.sample.Sample;

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
	
	private ArrayList<Sample> subSamples = new ArrayList<Sample>();		//Holds samples that are to be compared to Sample
	
	private String params = "";		//Required for processing user-defined queries
	private String mapData = "";
	private String result = "TBD";
	
	//This List of objects can be used to store and send custom data structures of any type to JSP Pages 
	// IMPORTANT - REMEMBER TO ALWAYS TYPECAST THIS OBJECT PROPERLY IN THE RESPECTIVE JSP PAGE
	private List<Object> resultObjects = new ArrayList<Object>();		
	
	private double pred = 0;
	
	public Sample getSample()
	{
		return sample;
	}
	public void setPredictedValue(double pred)
	{
		this.pred= pred; 
	}
	public double getPredictedValue()
	{
		return pred;
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
	
	public ArrayList<Sample> getSubSamples()
	{
		return subSamples;
	}

	public void setSubSamples(ArrayList<Sample> subSamples)
	{
		this.subSamples = subSamples;
	}
	
	/**
	 * @return List of Objects
	 */
	public Object getResultObjects()
	{
		return resultObjects;
	}
	
	/**
	 * Sets the list of result objects
	 * @param resultObjects
	 */
	public void setResultObjects(List<Object> resultObjects)
	{
		this.resultObjects.clear();
		this.resultObjects = resultObjects;
	}
	
	/**
	 * Clears the current list of result objects and adds resultObject to the cleared list
	 * @param resultObject
	 */
	public void setResultObject(Object resultObject)
	{
		this.resultObjects.clear();
		addResultObject(resultObject);
	}
	
	/**
	 * Appends resultObject to the current list of result objects
	 * @param resultObject
	 */
	public void addResultObject(Object resultObject)
	{
		this.resultObjects.add(resultObject);
	}
	
	@Override
	public String toString()
	{
		return "TimeSeriesBean [sample=" + sample + ", taskType=" + taskType
				+ ", subTaskType=" + subTaskType + ", algorithmType="
				+ algorithmType + ", dataset=" + dataset + ", params=" + params
				+ ", result=" + result + "]";
	}
	public void setMapData(String mapData) {
		this.mapData = mapData;
		
	}
	public String getMapData()
	{
		return this.mapData;
	}
}
