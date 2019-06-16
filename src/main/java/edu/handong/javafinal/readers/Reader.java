package edu.handong.javafinal.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Reader {
	
	public ArrayList<String> getData(String path, boolean removeHeader) {
		ArrayList<String> values = new ArrayList<String>();
		
		
		try (InputStream inp = new FileInputStream(path)) {
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			
			for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String line = "";
				
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					
					line += cell.getStringCellValue();
					if (j != row.getLastCellNum()-1) {
						line += ",";
					}
				}
				values.add(line);
			}
		        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return values;
	}
	
	public ArrayList<String> getData(InputStream is, boolean removeHeader) {
		ArrayList<String> values = new ArrayList<String>();
		
		try (InputStream inp = is) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");

			Workbook wb = WorkbookFactory.create(inp);
			
			Sheet sheet = wb.getSheetAt(0);
			int start = removeHeader ? sheet.getFirstRowNum()+1 : sheet.getFirstRowNum();
			int end = sheet.getLastRowNum();
			System.out.println("Start : " + start + "\nEnd : " + end);
			
			for (int i = start; i <= end; i++) {
				Row row = sheet.getRow(i);
				if (row == null) return values;
				String line = "";
				
				int rowStart = row.getFirstCellNum();
				int rowEnd = row.getLastCellNum();
				for (int j = rowStart; j < rowEnd; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) break;

					if (cell.getCellType().equals(CellType.STRING)) 
						line += cell.getStringCellValue();
					else 
						line += cell.getNumericCellValue();
					System.out.println(line);
					
					if (j != row.getLastCellNum()-1) {
						line += ",";
					}
				}
				values.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return values;
	}
}
