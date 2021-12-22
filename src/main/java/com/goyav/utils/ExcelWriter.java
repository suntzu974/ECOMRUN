package com.goyav.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	public void createHeaderRow(Sheet sheet) {
		 
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 16);
	    cellStyle.setFont(font);
	 
	    Row row = sheet.createRow(0);
	    Cell cellTitle = row.createCell(0);
	 
	    cellTitle.setCellStyle(cellStyle);
	    cellTitle.setCellValue("Reference");
	 
	    Cell cellAuthor = row.createCell(1);
	    cellAuthor.setCellStyle(cellStyle);
	    cellAuthor.setCellValue("D�signation");
	 
	    Cell cellPrice = row.createCell(2);
	    cellPrice.setCellStyle(cellStyle);
	    cellPrice.setCellValue("Code barre");
	}
	private Workbook getWorkbook(String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	private void flushToExcel(File file, Workbook workbook) {
		  
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		finally {
			try {
				fos.close();
			}
			catch (IOException e) {
				System.out.println("Can't close the Output Stream");
				e.printStackTrace();
			}
		}
	}   
		  
	private File createFile(String fileName)  {
		String filePath_Name =System.getProperty("java.io.tmpdir") + "\\" + fileName ;
		File file = new File(filePath_Name);
		System.out.println("FileName : " + filePath_Name);
		return file;
	}
}
