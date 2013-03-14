package org.ck.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Sample class indicates each horse sample.
 * 
 * 
 *
 */
public class Sample 
{
	private ArrayList<Double> time_series_values;
	private String Sample_Name;
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
	 * @return
	 */
	public Boolean Normalize()
	{
		
		return true;
	}
	public void displayTimeSeries(int limit)
	{
		System.out.println("Time\tValues");
		for(int i=0;i<time_series_values.size() && limit-->0;i++)
		{
			System.out.println(""+(i+1)+"\t"+time_series_values.get(i));
		}
	}
}
