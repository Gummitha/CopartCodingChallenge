package copart;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ZipCodeLookup {

	public static void main(String[] args) {
		readTextFile("./src/copart/zipcodeinput.txt");//Input text file path	
	}
	public static void readTextFile( String filepath)
	{
		File  file = new File(filepath);
		FileReader fileReader= null;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line =null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(zipLookup(line));
			}   
			bufferedReader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String zipLookup(String zip)
	{	
		String url= "http://maps.googleapis.com/maps/api/geocode/json?address="+zip+"&sensor=true";
		String output = null;
		try {
			URL myURL = new URL(url);
			URLConnection myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			BufferedReader in = new BufferedReader( new InputStreamReader(myURL.openStream()));
			String inputLine;   
			while ((inputLine = in.readLine()) != null)
				if(inputLine.contains("formatted_address"))
				{
					output=	inputLine;
				}
			in.close();
		} 
		catch (MalformedURLException e) { 
			// new URL() failed
		} 
		catch (IOException e) {   
			// openConnection() failed
		}
		String []splitOne= output.split(":");
		String []splitTwo= splitOne[1].split(",");
		String []splitThree=splitTwo[1].split(" ");
		return(splitTwo[0].substring(2, splitTwo[0].length())+","+splitThree[1]);
	}
}
