
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		Project3.java
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project3 {
	static String inputFileName = "project3.txt";

	public static void main(String[] args) throws IOException {
		try {
			BufferedReader brTest = new BufferedReader(new FileReader(inputFileName));
			FileInputStream in = new FileInputStream(inputFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//Topological Sort is in Conversion Class
			Conversion a = new Conversion(br);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}