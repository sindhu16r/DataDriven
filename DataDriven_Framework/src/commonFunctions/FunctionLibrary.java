package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import config.Apputil;

public class FunctionLibrary extends Apputil {//parents extends to child
//june21
	public static boolean Check_Login(String username,String password)
	//test data is from excel,
	{
		dri.get(conpro.getProperty("Url"));
		dri.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		dri.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
		dri.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(password);
		dri.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected = "http://flights.qedgetech.com/user/ranga.html";
		String actual = dri.getCurrentUrl();
		//we gng with assert conditon package, testng package
		//can also go with if 
		if(actual.contains(expected))
		{
			Reporter.log("login sucess  "+expected+"    "+actual,true);
		
		return true;
		}
		else {
			Reporter.log("login fail  "+expected+"    "+actual,true);
		}
		return false;
	}
}
