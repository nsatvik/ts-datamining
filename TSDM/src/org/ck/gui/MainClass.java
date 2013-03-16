package org.ck.gui;

import org.ck.sample.DataHolder;
import org.ck.sample.Sample;


public class MainClass 
{
	public static void main(String args[])
	{
		System.out.println("Hello,World!");		
		Sample sea_sample = new Sample(DataHolder.TRAINING_FILE_NAME,"Sea Level Data");
		sea_sample.Normalize();
		sea_sample.displayTimeSeries(10);
		 
	}
}
