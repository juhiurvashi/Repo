package com.k2js.bankbazar.testrunner;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.k2js.bankbazar.pageobject.HomePage;
import com.k2js.bankbazar.util.BrowserFactory;
import com.k2js.bankbazar.util.CommonUtil;

public class BankBazarTestRunner {
	String br_name,run_mode;
	WebDriver driver;
	HomePage hp;
	@Parameters({"bn","rm"})
	@BeforeClass
	public void init(@Optional("chrome") String b,@Optional("local") String r)
	{
		this.br_name=b;
		this.run_mode=r;
	}
	@BeforeMethod
	public void preCondition() throws IOException
	{
		this.driver=BrowserFactory.openBrowser(br_name, run_mode);
		String url=CommonUtil.getPropertyValue("config", "url");
		BrowserFactory.openURL(url);
		hp=PageFactory.initElements(driver, HomePage.class);
	}
	@Test
	public void verifyHomePage() throws InterruptedException, IOException
	{
		hp.selectCards();
		hp.selectEligibility();
		hp.moveSlider();
		hp.selectBank();
		hp.moveSlidercreditLimit();
		hp.moveSliderAge();
	}
}
