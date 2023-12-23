package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
//june 21
	//only excel methods no webdriver methods
	
	XSSFWorkbook wbk;
	public ExcelFileUtil(String ExcelPath) throws Throwable//ExcelFileUtil...constructor name and class name should be same
	//default constructor maintain public acess modifier
	//Excel path is argument, not path of system as everyone file path changes
	
	{
		FileInputStream fip = new FileInputStream(ExcelPath);
		//define excelpath, argument only
		//workbook from file, wbk globally declared
		wbk = new XSSFWorkbook(fip);
		//this is constructor
		
//when acessing other classes initiate obj for constructors
//call with help of obj whereever ,anyone acess, 
		
	}
	//Writing 1st method in excel...count no of rows in sheet
	//rowCount is method..we creating,...we can call in other programs
	public int  rowCount(String Sheetname)//dtat type int..count int
	{
		return wbk.getSheet(Sheetname).getLastRowNum();//for counting rows we need wbk.getsheet (sheetname)
		//get last row num...
	}
		//2nd method getCellData
public String getCellData(String Sheetname, int row , int column)
		{
			String data = ""; // declared null b/c nothing stored in it
			//y declaring null type is to store string data
			if(wbk.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
			{
				int celldata = (int) wbk.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
				data = String.valueOf(celldata);
			}
			else {
				data =wbk.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
				//else part executes when any cell contains string type data
				//when still showing error write return statement
				
			}
			return data;
			
		}
//just writing , void type
//3rd method setCellData
public void setCellData(String Sheetname, int row, int column,String status, String WriteExcel)throws Throwable
{
	//get sheet from workbook
	XSSFSheet ws = wbk.getSheet(Sheetname);
	//get specifuc row from sheet
	XSSFRow rowNum = ws.getRow(row);
	//create cell
	XSSFCell cell = rowNum.createCell(column);
	//write status above  reate  ell
	cell.setCellValue(status);
	//three conditions we want pass, fail, blocked
	if(status.equalsIgnoreCase("Pass"))
	{
		XSSFCellStyle style = wbk.createCellStyle();
		XSSFFont fonts = wbk.createFont();
		fonts.setColor(IndexedColors.GREEN.getIndex());
		fonts.setBold(true);//true means want to bold, false no bold
		fonts.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fonts);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
			
	else if(status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style = wbk.createCellStyle();
		XSSFFont fonts = wbk.createFont();
		fonts.setColor(IndexedColors.RED.getIndex());
		fonts.setBold(true);//true means want to bold, false no bold
		fonts.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fonts);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))	
	{
		XSSFCellStyle style = wbk.createCellStyle();
		XSSFFont fonts = wbk.createFont();
		fonts.setColor(IndexedColors.BLUE.getIndex());
		fonts.setBold(true);//true means want to bold, false no bold
		fonts.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(fonts);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	//finally o/p into 
	
	FileOutputStream fop = new FileOutputStream(WriteExcel);
			wbk.write(fop);
			//dont use close here, it may interupt
}
	
	
}
