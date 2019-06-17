package edu.handong.javafinal.readers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import edu.handong.javafinal.customized.CustomizedGenerics;


public class Reader {
	
	public CustomizedGenerics<String> getData(String path, boolean removeHeader, int select) {
		CustomizedGenerics<String> values = new CustomizedGenerics<String>();
		
		
		try (InputStream inp = new FileInputStream(path)) {
			//InputStream inp = new FileInputStream("workbook.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			int start = removeHeader ? sheet.getFirstRowNum()+1 : sheet.getFirstRowNum();
			start = select == 2 ? start + 1 : start;
			int end = sheet.getLastRowNum();
			
			for (int i = start; i <= end; i++) {
				boolean check = false;
				
				Row row = sheet.getRow(i);
				String line = "";
				
				int rowEnd = row.getLastCellNum();
				if (rowEnd <= 1) continue;
				for (int j = 0; j < rowEnd; j++) {
					Cell cell = row.getCell(j);
					if (cell == null)
						cell = row.createCell(j);
					
					if (cell.getCellType().equals(CellType.STRING)) { 
						String value = cell.getStringCellValue();
						if (value == null)
							line += "\" \"";
						else {
							line += "\"" + cell.getStringCellValue() + "\"";
							check = true;
						}
					}else if (cell.getCellType().equals(CellType.NUMERIC)){
						double value = cell.getNumericCellValue();
						if (value == 0.0)
							line += "\" \"";
						else {
							line += "\"" + cell.getNumericCellValue() + "\"";
							check = true;
						}
					} else {
						System.out.println(cell.getCellType());
						System.out.println(cell.getStringCellValue());
						return null;
					}
					
					if (j != row.getLastCellNum()-1) {
						line += ",";
					}
				}
				if (check)
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
	
	public CustomizedGenerics<String> getData(InputStream is, boolean removeHeader, int select){
		CustomizedGenerics<String> values = new CustomizedGenerics<String>();
		
		try (InputStream inp = is) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");

			Workbook wb = WorkbookFactory.create(inp);
			
			Sheet sheet = wb.getSheetAt(0);
			int start = removeHeader ? sheet.getFirstRowNum()+1 : sheet.getFirstRowNum();
			start = select == 2 ? start + 1 : start;
			int end = sheet.getLastRowNum();
			
			for (int i = start; i <= end; i++) {
				boolean check = false;
				
				Row row = sheet.getRow(i);
				String line = "";
				
				int rowEnd = row.getLastCellNum();
				if (rowEnd <= 1) continue;
				for (int j = 0; j < rowEnd; j++) {
					Cell cell = row.getCell(j);
					if (cell == null)
						cell = row.createCell(j);
					
					if (cell.getCellType().equals(CellType.STRING) || cell.getCellType().equals(CellType.BLANK)) { 
						String value = cell.getStringCellValue();
						if (value == null || cell.getCellType().equals(CellType.BLANK))
							line += "\" \"";
						else {
							line += "\"" + cell.getStringCellValue() + "\"";
							check = true;
						}
					}else if (cell.getCellType().equals(CellType.NUMERIC) || cell.getCellType().equals(CellType.BOOLEAN)){
						double value = cell.getNumericCellValue();
						if (value == 0.0)
							line += "\" \"";
						else {
							line += "\"" + cell.getNumericCellValue() + "\"";
							check = true;
						}
					}else {
						return null;
					}
					
					if (j != row.getLastCellNum()-1) {
						line += ",";
					}
				}
				if (check)
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
