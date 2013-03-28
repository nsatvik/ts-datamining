package org.ck.dataExtractor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
/*
 * I implemented this class in a different place. I added it so it here so that this becomes a part of the project.
 *  I copied the excel cells (Replaced empty cells with 0) and pasted it in a text file,
 *   and formatted the text file as per our requirements.  
 */

@SuppressWarnings("unused")
public class dataExtractor {
	public void extractor()  {
		try{
		FileReader fr = new FileReader("C:\\Users\\Vaishakh\\Desktop\\water.txt");
		FileWriter fw = new FileWriter("C:\\Users\\Vaishakh\\Desktop\\newWater.txt");
		BufferedReader br = new BufferedReader(fr); 
		String s; 
		while((s = br.readLine()) != null) { 
		StringTokenizer st = new StringTokenizer(s);
			 while (st.hasMoreElements()) {
			     System.out.println(st.nextElement());
			     
			     //  fw.write((String)st.nextElement()); Something is wrong with this. Tried casting and all.
			     //Did not work. 
			     //So I copied whatever was printing on the console and pasted it in the file.
			   
			     }
		}
		fr.close();
		fw.close();
		}
		catch (IOException e){
			System.out.println("So? What can I do?");
		}
	}
}
