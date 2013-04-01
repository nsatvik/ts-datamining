package org.ck.forecaster.nn;

import java.util.ArrayList;


public class Neuron {
	static float learning_rate = (float) 0.01;
	private ArrayList<Double> weights; //weights of the edges that connect to the next layer
	private double output; //output of this neuron
	private double error;
	public Neuron(int numNeuron)
	{
		this.weights = new ArrayList<Double>();
		initWeights(numNeuron);
	}
	
	public void setOutput(double out)
	{
		this.output = out;
	}
	
	public void computeOutput(double weight_output_product)
	{
		this.output = ActivationFunction(weight_output_product);  
	}
	
	private double ActivationFunction(double weight_output_product) {
		return 1/(1+Math.exp(-weight_output_product));
	}

	public double getWeightOutputProduct(int index)
	{
		if(index > weights.size())
			index = weights.size();
		return output*weights.get(index);
	}
	private void initWeights(int numNeuron)
	{
		for(int i=0;i<numNeuron;++i)
		{
			this.weights.add(0.5);
		}
	}
	
	public double getWeight(int index)
	{
		return weights.get(index);
	}
	
	public double getOutput()
	{
		return this.output;
	}
	
	public double getError()
	{
		return this.error;
	}

	public void computeError(double actualOutput) {
		error = actualOutput-output;
		
	}

	public void ComputeandSetError(ArrayList<Double> neuronErrors) {
		error = 0;
		for(int i=0;i<neuronErrors.size();++i)
		{
			error += weights.get(i)*neuronErrors.get(i);
		}
		error *= (output)*(1-output);
		
	}

	public void updateWeights(ArrayList<Double> neuronErrors) {
		
		for(int i=0;i<neuronErrors.size();i++)
		{
			weights.set(i, weights.get(i)+learning_rate*neuronErrors.get(i)*output);
		}
		
	}
}
