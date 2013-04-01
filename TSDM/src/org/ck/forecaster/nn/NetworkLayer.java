package org.ck.forecaster.nn;

import java.util.ArrayList;
import java.util.List;


public class NetworkLayer {
	Neuron  neurons[];
	public NetworkLayer(int numNeurons)
	{
		this.neurons = new Neuron[numNeurons];
	}

	public void initLayerNeurons(int nextLayerSize)
	{
		for(int i=0;i<neurons.length;++i)
			this.neurons[i] = new Neuron(nextLayerSize);
	}

	public int size()
	{
		return neurons.length;
	}

	public void calculateNeuronOutputs(NetworkLayer previousLayer)
	{
		
		for(int i=0;i<neurons.length;++i)
		{
			
			neurons[i].computeOutput(previousLayer.getWeightOutputProduct(i));
		}

	}
	private double getWeightOutputProduct(int index) {
		double woproduct_sum = 0;
		for(int i=0;i<neurons.length;++i)
		{
			woproduct_sum += neurons[i].getWeightOutputProduct(index);
		}
		return woproduct_sum;
	}

	public Neuron[] getNeurons()
	{
		return neurons;
	}

	public void computeErrors(NetworkLayer nextLayer) {
		for(int i=0;i<neurons.length;++i)
		{
			neurons[i].ComputeandSetError(nextLayer.getNeuronErrors());
		}
		
	}

	public void updateWeights(NetworkLayer nextLayer) {
		for(int i=0;i<neurons.length;++i)
		{
			neurons[i].updateWeights(nextLayer.getNeuronErrors());
		}

	}

	public void initInputLayer(List<Double> normalizedTimeSeries) {
		if(neurons.length==normalizedTimeSeries.size())
		{
			for(int i=0;i<normalizedTimeSeries.size();i++)
			{
				neurons[i].setOutput(normalizedTimeSeries.get(i));
			}
		}
	}
	public ArrayList<Double> getNeuronErrors()
	{
		ArrayList<Double> neuron_errors = new ArrayList<Double>();
		for(int i=0;i<neurons.length;i++)
			neuron_errors.add(neurons[i].getError());
		return neuron_errors;
	}

	public void computeOutputLayerError(double actualOutput) {

		for(Neuron neuron : neurons)
		{
			neuron.computeError(actualOutput);
		}

	}

	public double getOutput() {
		return neurons[0].getOutput();
	}
}
