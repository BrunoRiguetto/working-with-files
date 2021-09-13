package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Enter a file path: ");
		String strPath = sc.nextLine();
		
		List<Product> list = new ArrayList<Product>();
		
		File sourceFile = new File(strPath);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";		
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))){
			
			String line = br.readLine();
			line = br.readLine();
			while(line != null) {
				
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				Integer qte = Integer.parseInt(vect[2]);
				
				Product prod = new Product (name, price, qte);
				list.add(prod);			
				
				line = br.readLine();
				
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Product item : list) {
					bw.write(item.getName() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(targetFileStr + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}
			
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		
		sc.close();
	}
}

