package org.ck.sample;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.math.*;


/**
 * Sample class indicates each horse sample.
 * 
 * 
 *
 */
public class Sample 
{
	private ArrayList<Double> time_series_values,normalised_time_series;
	private String Sample_Name;
	private double sum;
	public Sample(String file_path, String sample_name)
	{
		File f = new File(file_path);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			time_series_values = new ArrayList<Double>();
			this.Sample_Name = sample_name;
			String line = null;
			while((line = br.readLine())!=null)
			{
				this.time_series_values.add(Double.parseDouble(line));
			}
			
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}	
	}
	/**
	 * Implement  (val - mean)/sigma for all values in the list to normalize.
	 * The functions have been tested with a smaller data set.
	 * It returns proper mean, std deviation and normalised values. Returns NaN when 0/0 occurs. 
	 * Wrote this comment because they ask us to do unit testing.
	 * @return
	 */
	public Boolean Normalize()
	{
		normalised_time_series = new ArrayList<Double>();
		double mean = meanCalculator();
		double standardDeviation = standardDeviationCalculator();
	//	System.out.println("Mean : "+mean+ "Std Dev. :"+standardDeviation);
		for (double i : time_series_values)
		{
			normalised_time_series.add((i-mean)/standardDeviation);
		}
		/*for(int i = 0; i<normalised_time_series.size();i++)
		{
		System.out.println(+normalised_time_series.get(i));
		}*/
		return true;
	}
	public double standardDeviationCalculator()
	{
		double mean = meanCalculator();
		double deviation = 0;
		//ArrayList<Double> deviation; //Creating an array list because double may overflow
		for(int  i=0;  i <  time_series_values.size();i++)
		{
			deviation += ((time_series_values.get(i)-mean)*(time_series_values.get(i)-mean));
		}
		return Math.sqrt( deviation / (time_series_values.size()-1));		
	}
	public double meanCalculator()
	{
		sum = 0;
		for (int  i=0;  i <  time_series_values.size(); i++)
		{
			
			sum+=time_series_values.get(i);	
		}
		return sum/time_series_values.size();
	}
	public void displayTimeSeries(int limit)
	{
		System.out.println("Time\tValue\tNormalizedValue");
		for(int i=0;i<time_series_values.size() && limit-->0;i++)
		{
			System.out.println(""+(i+1)+"\t"+time_series_values.get(i)+"\t"+normalised_time_series.get(i));
		}
	}
	
}
