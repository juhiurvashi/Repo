package com.k2js.bankbazar.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	private static WebDriver driver=null;
	private static void getBrowser(String bn,String rm)
	{
		if(rm.equalsIgnoreCase("remote"))
		{
			if(bn.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", ".//BrowserExe/geckodriver.exe");
				DesiredCapabilities dc=new DesiredCapabilities().firefox();
			}
		}
		else if(rm.equalsIgnoreCase("local"))
		{
			if(bn.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", ".//BrowserExe/chromedriver.exe");
				driver=new ChromeDriver();
			}
		}
	}
	public static WebDriver openBrowser(String bn,String rm)
	{
		if(driver==null)
		{
			BrowserFactory.getBrowser(bn, rm);
		}
		return driver;
	}
	public static void openURL(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}
}
