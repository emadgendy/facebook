package com.custom.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class TextFileManager {

	private String fileName;

	public TextFileManager(String filePath) {
		fileName = filePath;
	}

	public String readFile() {
		String finalText = null;
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bfr = new BufferedReader(fileReader);
			StringBuffer sb = new StringBuffer();
			while ((line = bfr.readLine()) != null) {
				sb.append(line);
			}
			finalText = sb.toString();
			bfr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalText;
	}

	public void writeFile(String inputData) {
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(inputData);
			bw.close();
			System.out.println("File created: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TextFileManager fileM = new TextFileManager("src/test/resources/welcome.txt");
		// fileM.writeFile("I love java programming. I want to buy TESLA. I have
		// to do practice a lot to achive my goals and dreams.");

		String result = fileM.readFile();
		System.out.println("data: " + result);

	}

}
