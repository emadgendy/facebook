package com.custom.library;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.common.Logger;
import jxl.read.biff.BiffException;

public class ExcelManager {
	
	final static Logger logger = Logger.getLogger(ExcelManager.class);

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String excelFile;

	public ExcelManager(String excelFilePath) {
		excelFile = excelFilePath;
	}

	public String[][] getExcelData(String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(excelFile);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

			for (int i = 1; i < totalNoOfRows; i++) {
				for (int j = 0; j < totalNoOfCols; j++) {
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}
			}

		} catch (FileNotFoundException e) {
			logger.error("Error: Excel file is not found: ", e);
		} catch (BiffException e) {
			logger.error("Error: BiffException occured: ", e);
		} catch (IOException e) {
			logger.error("Error: IOException occured: ", e);
		}
		return arrayExcelData;
	}

	public static String getCellData(int RowNum, int ColNum) {
		String cellData = null;
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			cellData = Cell.getStringCellValue();
		} catch (Exception e) {
			logger.error("Error: exception occured: ", e);
		}
		return cellData;
	}

	/*@SuppressWarnings("static-access")
	public static void setCellData(String data, int RowNum, int ColNum) {
		try {
			
			Row = ExcelWSheet.createRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(data);

				FileOutputStream fileOut = new FileOutputStream(excelFile);
				ExcelWBook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
		} catch (Exception e) {
			logger.error("Error: exception occured: ", e);
		}
	}

	public static void setExcelFile(String sheetName) {
		try {
			//FileOutputStream file = new FileOutputStream(excelFile);
			ExcelWBook = new XSSFWorkbook();
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (Exception e) {
			logger.error("Error: exception occured: ", e);
		}
	}*/

	public static void main(String[] args) {
		ExcelManager myRead = new ExcelManager("src/test/resources/testData.xls");
		System.out.println("Data results: " + myRead.getExcelData("Sheet1"));
	}
	
}
