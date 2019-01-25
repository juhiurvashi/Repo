package com.k2js.bankbazar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtil {
	public static String getPropertyValue(String fn,String key) throws IOException
	{
		FileInputStream fis =new FileInputStream("./TestData/"+fn+".properties");
		Properties p=new Properties();
		p.load(fis);
		return p.getProperty(key);
		
	}
	
	public static String getCorrectTimeDate()
	{
		DateFormat dateFormat=new SimpleDateFormat("dd_MM_yyyy");
		Date date=new Date();
		return dateFormat.format(date);
		
	}
	
	public static void takeScreenshot(WebDriver driver) throws IOException
	{
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\\\Users\\\\IBM_ADMIN\\\\Desktop\\\\selenium\\\\SaveFiles\\\\screenshot.png\""));
	}
}
