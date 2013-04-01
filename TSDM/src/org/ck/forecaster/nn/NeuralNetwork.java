package org.ck.forecaster.nn;

import java.util.ArrayList;

import org.ck.sample.Sample;

public class NeuralNetwork {

	NetworkLayer []networkLayers;
	Sample trainigSample;


	public NeuralNetwork(int []layerSizes, Sample dataSample)
	{
		this.networkLayers = new NetworkLayer[layerSizes.length];
		for(int i=0;i<this.networkLayers.length-1;++i)
		{
			this.networkLayers[i] = new NetworkLayer(layerSizes[i]);
			this.networkLayers[i].initLayerNeurons(layerSizes[i+1]);
		}
		//Output layer initialization
		this.networkLayers[this.networkLayers.length-1] = new NetworkLayer(1);
		this.networkLayers[this.networkLayers.length-1].initLayerNeurons(0);
		this.trainigSample = dataSample;
	}

	public void train()
	{
		int windowSize = networkLayers[0].size();//Input size of the network
		for(int samSize=0;(samSize+windowSize)<trainigSample.getNumOfValues();samSize++)
		{
			this.networkLayers[0].initInputLayer(trainigSample.getSeriesSubset(samSize, samSize+windowSize).getNormalizedTimeSeries());
			for(int times = 0;times<50;times++)
			{
				//Forword Propagation Step
				for(int i=1;i<networkLayers.length;i++)
					networkLayers[i].calculateNeuronOutputs(this.networkLayers[i-1]);
				networkLayers[networkLayers.length-1].computeOutputLayerError(trainigSample.getValue(samSize+windowSize));
				//Backward Propagation Step
				for(int i=networkLayers.length-2;i>0;--i)
				{
					networkLayers[i].computeErrors(networkLayers[i+1]);
				}
				for(int i=1;i<networkLayers.length-1;i++)
					networkLayers[i].updateWeights(this.networkLayers[i+1]);
			}

		}
	}
	
	public double predict(ArrayList<Double> inputs)
	{
		this.networkLayers[0].initInputLayer(inputs);
		for(int i=1;i<networkLayers.length;++i)
			networkLayers[i].calculateNeuronOutputs(networkLayers[i-1]);
		return networkLayers[networkLayers.length-1].getOutput();
	}



}
