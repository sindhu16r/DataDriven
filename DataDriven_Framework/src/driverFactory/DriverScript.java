package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.Apputil;
import utilities.ExcelFileUtil;
import config.Apputil;

public class DriverScript extends Apputil {
	//june22
// precodition in apputil
	//to read excel file
	String inputpath = "./FileInput/logindataqedge.xlsx"; // written input path file from fileinput excel name, 
	//writen from fileinput(./FileInput/~$orangehrm) b/c it can search from anywhere, not onlyd drive
	String outputpath =  "./FileOutput/DataDrivenResults.xlsx";//outputpath is variable declared
	//FileOutput is by clicking Fileoutput folder properties, last folder name in path is copied
//output folder selenium will create by which name we specify after fileoutput..given as datadriven results
@Test
public void starTest() throws Throwable
{
//writing driver script, no webdriver script written here everything here calling 
//we prepared excel already, data stored here in excel
	//if we want to call methods like getcelldata, setcelldata
	//for that we need to create object for constructors or for class name(ExcelFileUtil)
	//with  of  that reference obj only we can call all methods and reduce code
	
//crrate object for Excel file util class
	//
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);//ExcelFileUtil is excel file package which we created before
	//ExcelFileUtil will have have all excel methods, which we defined there, so need of writing again
	//xl is variable holding all ExcelFileUtil packages
	//# count no of rows in login sheet
	int rc = xl.rowCount(inputpath);//from ExcelFileUtil file methods
	Reporter.log("No of rows "+rc,true);	
	for(int i=1; i<=rc; i++)
	{
		//read cell/ colum data
		String user = xl.getCellData("Login",i, 0);
		String pass = xl.getCellData("Login",i, 1);
		boolean res = FunctionLibrary.Check_Login(user, pass);
		//res holding true, or false, if true login sucess
		if(res)
		{
			xl.setCellData("Login", i, 2,"Login Sucess",outputpath);
			//write pass in status cell
			xl.setCellData("Login", i, 3, "pass",outputpath);
		}
		else
		{
		File screen =((TakesScreenshot)dri).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("./Screenshot/loginpage.png"));
			//write error message
String error_msg = dri.findElement(By.xpath("//span[@id='spanMessage']")).getText();
	xl.setCellData("Login", i, rc, error_msg,outputpath);
		}
	}
}
}
 