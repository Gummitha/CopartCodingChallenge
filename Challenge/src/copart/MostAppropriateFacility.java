package copart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MostAppropriateFacility {
	public static void main(String[] args) {

		ArrayList<CopartFacility> facilityList=readStoreCsv("./src/copart/CopartStoreLocations.csv");
		readclientCsv("./src/copart/AppropriateFacilityInput.csv",facilityList);	
		
//		String [] one =location("94523");
//		String [] two =location("99501");
//		System.out.println(locationDistance(one,two));
	}
	public static CopartFacility  mostAppropriateFacility(String [] clientLocation,ArrayList<CopartFacility> facilityDetails)
	{
		double distance =0;
		for (CopartFacility  facility: facilityDetails) {
			facility.setCustomerDistance(locationDistance(clientLocation, facility.getLocation()));
			//System.out.println(locationDistance(clientLocation, facility.getLocation()));
		}
		PriorityQueue<CopartFacility> facilityPriorityQueue = new PriorityQueue<>(new DistanceComparable());

		for(CopartFacility facility : facilityDetails) {
			facilityPriorityQueue.add(facility);	
		}
		
		
		Customer customer = new Customer("A001", "36613"); //putting a rule that the customer doesn't prefer the facility in zip code 36613

		CopartFacility facility = null;
		while(!facilityPriorityQueue.isEmpty()) {
			facility = facilityPriorityQueue.remove();
			if(!facility.getZipcode().equals(customer.getExcludeZip()) ) {
				break;
			}
		}
		return facility;
	}

	/**  Distance between two locations based on longitude and latitude  details*/
	public synchronized static double locationDistance(String[] pointOne,String [] pointTwo)
	{
		double  x1 = Double.parseDouble(pointOne[0]);
		double  x2 =Double.parseDouble(pointTwo[0]);
		double  y1 =Double.parseDouble(pointOne[1]);
		double  y2 =Double.parseDouble(pointTwo[1]);
		double distance = Math.sqrt(Math.pow(x1-x2, 2)+ Math.pow(y1-y2, 2));
		return distance;
	}
	/**  Reading the client details text file*/
	public static void readclientCsv( String filepath, ArrayList<CopartFacility> facilityList)
	{
		File  file = new File(filepath);
		FileReader fileReader= null;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line =null;
			while((line = bufferedReader.readLine()) != null) {
				
				String []li =line.split(",");
				String []clintLocation=location(li[1]);
				//System.out.println("\n\ndetails for " + li[0]);
				CopartFacility facility = mostAppropriateFacility(clintLocation,facilityList);
				System.out.println("For customer " + li[0] + " with a provided zip code of " + li[1] + ", closest Copartr Facility is " + facility.getCity() + ", " + facility.getState());

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
	/**  Reading the Copart facility details text file and storing the each record in to java object*/
	public static  ArrayList <CopartFacility> readStoreCsv( String filepath)
	{
		File  file = new File(filepath);
		FileReader fileReader= null;
		String []output= null;
		String[] parse =null;
		ArrayList<CopartFacility> cfList= new ArrayList<CopartFacility>();
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line =null;
			while((line = bufferedReader.readLine()) != null) {
				parse=line.split(",");
				CopartFacility  obj = new CopartFacility(parse[0],parse[1],parse[2],location(parse[2]));
				cfList.add(obj);
			}   
			bufferedReader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cfList;
	}
	/**method gets the latitude and longitude details based on zip */
	public static String[] location(String zip)
	{	
		String url= "http://maps.googleapis.com/maps/api/geocode/json?address="+zip+"&sensor=true";
		String []output = new String[2];
		try {
			URL myURL = new URL(url);
			HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
			myURLConnection.connect();
			BufferedReader in = new BufferedReader( new InputStreamReader(myURL.openStream()));
			String inputLine=null;
			String []temp=null;
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.trim().startsWith("\"location\""))
				{
					temp = in.readLine().split(":");
					output[0] =temp[1].substring(0, temp[1].length()-1);//latitude
					//System.out.println(output[0]);
					temp = in.readLine().split(":");
					output[1] =temp[1].substring(0, temp[1].length()-1);//longitude
					//System.out.println(output[1]);
					if(null==output[0] || null==output[1]) {
						break;
					}
				}
			}
			in.close();
			myURLConnection.disconnect();
		} 
		catch (MalformedURLException e) { 
			// new URL() failed
		} 
		catch (IOException e) {   
			// openConnection() failed
		}
		return output;
	}
}
