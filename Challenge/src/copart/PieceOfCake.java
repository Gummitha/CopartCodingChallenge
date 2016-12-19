package copart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PieceOfCake {
	
	private static List<Integer> primeList;

	public static void main(String[] args) {
		String inp;
		List<String> inputList = new ArrayList<String>();
		int i;
		
		File file = new File("./src/copart/PieceOfCakeInput.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			System.out.println("Input:\n");
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				inputList.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		primeList = new ArrayList<Integer>();
		primeList = getPrimeNumberList();
		
		System.out.println("\nPerimeters:\n");
		for (i = 1; i < inputList.size(); i++) {
			inp = inputList.get(i);
			System.out.println(minPerimeter(Integer.parseInt(inp)));
		}
	}

	public static int minPerimeter(int input) {
		int inp = input, side1 = 1, side2 = 1;
		List<Integer> divisors = new ArrayList<Integer>();
		while (inp != 1) {
			for (int prime : primeList) {
				while (inp % prime == 0) {
					inp = inp / prime;
					divisors.add(prime);
				}
			}
		}
		int counter = 0;
		for (int div : divisors) {
			side1 = side1 * div;
			counter++;
			if (counter == divisors.size() / 2) {
				break;
			}
		}
		side2 = input / side1;
		return (2 * (side1 + side2));
	}

	public static List<Integer> getPrimeNumberList() {
		List<Integer> primeList = new ArrayList<Integer>();
		for (int i = 1; i < 10000; i++) {
			boolean isPrimeNumber = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrimeNumber = false;
					break;
				}
			}
			if (isPrimeNumber) {
				if (i != 1) {
					primeList.add(i);
				}
			}
		}
		return primeList;
	}
}
