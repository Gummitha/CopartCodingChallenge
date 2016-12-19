package copart;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DNASequence {

	private String Sequence;
	
	
	public String getSequence() {
		return Sequence;
	}

	public void setSequence(String sequence) {
		Sequence = sequence;
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		readTextFile("./src/copart/DNAInput.txt")	;
			
	}
	public void longestSubstringLength()
	{
		char beforeMax = 'Z';
		boolean invalid=false;
		
		int max=-1;
		if(this.Sequence.length()==0)
		{
			System.out.println("enter a valid DNA");
		}
		else{
			char[] input=this.Sequence.toCharArray();
			int count=0;
			for(int i=0;i<input.length;i++)
			{
				
				if(i==0&&(input[i]=='A'||input[i]=='T'||input[i]=='G'||input[i]=='C')){
					count=1;
					max=count;
					beforeMax=input[i];
				}
				else if(input[i]==input[i-1])
				{
					count++;
					if(count>max)
					{
					beforeMax=input[i];
					max=count;
					}
					
				}
				else if(input[i]=='A'||input[i]=='T'||input[i]=='G'||input[i]=='C'){
					count=1;
				}
				else{
					System.out.println("not a valid sequence");
					invalid=true;
					break;
				}
			}
		}
		if(!invalid)
		{
		System.out.println(beforeMax+" "+max);
		}
		//return 0;
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
				DNASequence obj=new DNASequence();
				obj.setSequence(line);
				obj.longestSubstringLength();
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
	
}
