package org.ck.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This Singleton class is used to convert a time series represented by a PAA, to a string representation (SAX)
 *
 */
public class Discretizer
{
	private Map<Integer, List<Double>> breakpointMap;	
	private static Discretizer singleInstance;

	/**
	 * A Private Constructor
	 */
	private Discretizer() 
	{
		initBreakpointMap();	
	}

	/**
	 * A lookup table used by the MINDIST function. This
		table is for an alphabet of cardinality of alphaSize. 
	 * @param alphaSize
	 * @return A 2-D Lookup Table
	 */
	private double[][] initMinDistTable(int alphaSize)
	{
		List<Double> betaTable = breakpointMap.get(alphaSize);		
		double minDistTable[][] = new double[alphaSize][alphaSize];
		
		for(int r=0; r<minDistTable.length; r++)
		{
			for(int c=0; c<minDistTable[0].length; c++)
			{
				if(Math.abs(r - c) <= 1)
					minDistTable[r][c] = 0;
				else
					minDistTable[r][c] = betaTable.get(Math.max(r, c) - 1) - betaTable.get(Math.min(r, c));
				System.out.print(minDistTable[r][c] + "\t");
			}
			System.out.println();
		}
		return minDistTable;
	}

	/**
	 * The breakpoint map is a lookup table for a Gaussian distribution that is divided into n equal parts
	 * 	where 2 < n < 11
	 */
	private void initBreakpointMap()
	{
		breakpointMap = new HashMap<Integer, List<Double>>();
		breakpointMap.put(3, Arrays.asList(-0.43,   0.43));
		breakpointMap.put(4, Arrays.asList(-0.67,   0.0,   0.67));
		breakpointMap.put(5, Arrays.asList(-0.84,   -0.25,   0.25,   0.84));
		breakpointMap.put(6, Arrays.asList(-0.97,	-0.43,	0.0,	0.43,	0.97));
		breakpointMap.put(7, Arrays.asList(-1.07,	-0.57,	-0.18,	0.18,	0.57,	1.07));
		breakpointMap.put(8, Arrays.asList(-1.15,	-0.67,	-0.32,	0.0,	0.32,	0.67,	1.15));
		breakpointMap.put(9, Arrays.asList(-1.22,	-0.76,	-0.43,	-0.14,	0.14,	0.43,	0.76,	1.22));
		breakpointMap.put(10, Arrays.asList(-1.28,	-0.84,	-0.52,	-0.25,	0.0,	0.25,	0.52,	0.84,	1.28));		
	}

	/**
	 * Creates a single instance of this class
	 * @return
	 */
	public static Discretizer getInstance() 
	{
		if (singleInstance == null) 
		{
			synchronized (Discretizer.class) 
			{
				if (singleInstance == null) 
				{
					singleInstance = new Discretizer();
				}
			}
		}
		return singleInstance;
	}
	
	/**
	 * Converts a piecewise aggregate approximated time series into a string with an alphabet of size alphaSize
	 * @param paaTimeSeries
	 * @param alphaSize
	 * @return
	 */
	public String convertSeriesToString(List<Double> paaTimeSeries, int alphaSize)
	{
		//Ensuring that alphaSize is between 3 and 10 (inclusive)
		if(alphaSize < 3)
			alphaSize = 3;
		if(alphaSize > 10)
			alphaSize = 10;
		
		String word = "";
		for(double num : paaTimeSeries)
		{
			word += convertNumToCharacter(num, alphaSize);
		}
		return word;
	}
	
	/**
	 * Finds the SAX distance between two SAX strings
	 * @param word1
	 * @param word2
	 * @param n
	 * @param w
	 * @param alphaSize
	 * @return double
	 */
	public double findDistance(String word1, String word2, int n, int w, int alphaSize)
	{
		double[][] minDistTable = initMinDistTable(alphaSize);
		
		double sum = 0;
		int length = Math.min(word1.length(), word2.length());
		for(int i=0; i<length; i++)
		{
			sum += Math.pow(minDistTable[word1.charAt(i) - 'a'][word2.charAt(i) - 'a'], 2);
		}
		
		return Math.sqrt((double)n / w) * Math.sqrt(sum);
	}

	/**
	 * Converts a number into a character from an alphabet of size alphaSize
	 * @param num
	 * @param alphaSize
	 * @return
	 */
	private String convertNumToCharacter(double num, int alphaSize)
	{
		List<Double> breakpoints = breakpointMap.get(alphaSize);
		int i = 0;
		for(; i<breakpoints.size(); i++)
		{
			if(num < breakpoints.get(i))
				return "" + (char)('a' + i);
		}
		return "" + (char)('a' + i);
	}
	
	
}
