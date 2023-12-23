package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Apputil {
public static Properties conpro;
public static WebDriver dri;
@BeforeTest
public static void setUp() throws Throwable//method name given as set 

{
	//load property file 
	//initiate class for propertyclass
	conpro = new Properties();
	conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
	//load propery file//path of file
		//to path select Environment.properties right click
		//go to properties define path copy from property..no need full path
		//"./PropertyFile/Environment.properties"...write like this
		//change key value in propety
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			dri = new ChromeDriver();
			dri.manage().window().maximize();

			
		}//if suddenly want to change browser change key valu of browser in environment file
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
			
		{ //if key matching to chrome run on it, 
			//if key matching firefox run onit
			dri = new FirefoxDriver();
		}
		//all these are precondition
	}
	@AfterTest
	public static void tearDown()
	{
		dri.close();
	}
	}
