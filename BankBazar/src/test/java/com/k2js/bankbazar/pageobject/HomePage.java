package com.k2js.bankbazar.pageobject;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.k2js.bankbazar.util.CommonUtil;

public class HomePage {
	private WebDriver driver = null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectCards() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement card = driver.findElement(By.className("credit-cards-menu"));
		action.moveToElement(card).build().perform();
		WebElement creditCard = driver.findElement(By.linkText("Credit Cards"));
		creditCard.click();
		Thread.sleep(1000);

	}

	public void selectEligibility() throws InterruptedException {
		WebDriverWait ww = new WebDriverWait(driver, 25);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1400)", "");
		TimeUnit.SECONDS.sleep(2);
		Actions action = new Actions(driver);
		WebElement sel = driver.findElement(By.xpath(
				"//div[@class='offers-wrapper js-offers-wrapper']//div//div[5]//a[@class='btn btn-primary get-cc-card ' and @data-modaltarget='AMERICAN EXPRESS BANKING CORPORATION' and@productid='40916']"));
		TimeUnit.SECONDS.sleep(2);
		action.moveToElement(sel).click().perform();
		// WebElement
		// frame_ele=driver.findElement(By.xpath("//iframe[@id='oauth2relay716984820']"));
		// driver.switchTo().frame(frame_ele);
		WebElement city = driver.findElement(By.xpath(
				"//input[@class='form-control ns-other-city-input fallback-placeholder other-city-required-when-selected validate']"));
		TimeUnit.SECONDS.sleep(1);
		city.click();
		WebElement entercity = driver.findElement(By.xpath("//input[@name='otherCityInputField']"));
		entercity.sendKeys("Bangalore");
		WebElement selcity = driver.findElement(By.xpath("//a[@data-value='BANGALORE']"));
		selcity.click();
		WebElement company = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//input[@data-autocompletetype='COMPANY' and @id='form_applicantPlaceHolder_companyName_autoComplete']")));
		company.sendKeys("IBM DAKSH");
		TimeUnit.SECONDS.sleep(3);
		action.sendKeys(Keys.ENTER).build().perform();

		TimeUnit.SECONDS.sleep(3);
		// company.submit();
		TimeUnit.SECONDS.sleep(3);
	}

	public void moveSlider() throws InterruptedException {
		WebDriverWait ww = new WebDriverWait(driver, 25);
		WebElement priceSlider = ww.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//form[@id='slideForm']//div[@slidename='salary']//div[@class='slider slider-horizontal']")));

		assertTrue(priceSlider.isDisplayed());

		Dimension sliderSize = priceSlider.getSize();
		int sliderWidth = sliderSize.getWidth();

		int xCoord = priceSlider.getLocation().getX();
		System.out.println(sliderWidth + "  " + xCoord);
		// for(int i=0;i<101;i=i++)
	//	String slider = CommonUtil.getPropertyValue("home", "slider");
			Actions builder = new Actions(driver);
			builder.moveToElement(priceSlider).click().dragAndDropBy(priceSlider, -202, 0).build().perform();
		

		WebElement sal = driver.findElement(By
				.xpath("//form[@id='slideForm']//input[@name='form.details.applicant.income.monthlyTakeHomeSalary']"));
		System.out.println(sal.getAttribute("value"));
		if (sal.getAttribute("value").equalsIgnoreCase("20,000")) {
			System.out.println("Slider is working ");
		} else {
			System.out.println("Fail");
		}

		WebElement con = driver.findElement(By.xpath(
				"//form[@id='slideForm']//div[@class='item active']//div[@class='eform-btn clearfix']//button[@class='btn  js-move-next js-move-next-btn']"));
		con.click();
		TimeUnit.SECONDS.sleep(5);
	}

	public void selectBank() throws IOException {
		Actions action = new Actions(driver);
		String bank1 = CommonUtil.getPropertyValue("home", "bank");
		WebDriverWait ww = new WebDriverWait(driver, 25);
		WebElement b = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@class='col-md-2 col-xs-3 eform-col col-12 eform-col-section eform-col-section']//span[contains(text(),'"
						+ bank1 + "')]")));
		action.moveToElement(b).click().perform();
		String card1 = CommonUtil.getPropertyValue("home", "card");
		WebElement c = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@class='item active']//div[@class='clearfix']//span[contains(text(),'"+card1+"')]")));
		action.moveToElement(c).click().perform();
		WebElement cont = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@class='item active']//button[@class='btn  js-move-next js-move-next-btn']")));
		action.moveToElement(cont).click().perform();
	}

	public void moveSlidercreditLimit() throws InterruptedException {
		WebDriverWait ww = new WebDriverWait(driver, 25);
		WebElement creditSlider = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@slidename='maximumcredit' and @class='item active']//div[@class='slider slider-horizontal']")));
		assertTrue(creditSlider.isDisplayed());

		Dimension sliderSize = creditSlider.getSize();
		//int sliderWidth = sliderSize.getWidth();
		Actions builder = new Actions(driver);
		builder.moveToElement(creditSlider).click().dragAndDropBy(creditSlider, -175, 0).build().perform();
		WebElement sal = driver.findElement(By.xpath(
				"//form[@id='slideForm']//input[@name='form.details.applicant.income.maximumCreditOfExistingCards']"));
		System.out.println(sal.getAttribute("value"));
		if (sal.getAttribute("value").equalsIgnoreCase("1,00,000")) {
			System.out.println("Slider is working ");
		} else {
			System.out.println("Fail");
		}

		WebElement con = driver.findElement(By.xpath(
				"//form[@id='slideForm']//div[@class='item active' and @slidename='maximumcredit']//div[@class='eform-btn clearfix']//button[@class='btn  js-move-next js-move-next-btn']"));
		con.click();
		TimeUnit.SECONDS.sleep(5);
	}

	public void moveSliderAge() throws InterruptedException, IOException {
		WebDriverWait ww = new WebDriverWait(driver, 25);
		WebElement ageSlider = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@slidename='dob' and @class='item active']//div[@class='slider slider-horizontal']")));
		assertTrue(ageSlider.isDisplayed());

		Dimension sliderSize = ageSlider.getSize();
		int sliderWidth = sliderSize.getWidth();
		System.out.println(sliderWidth);
		Actions builder = new Actions(driver);
		builder.moveToElement(ageSlider).click().dragAndDropBy(ageSlider, -146, 0).build().perform();
		String monthyear = CommonUtil.getPropertyValue("home", "monthYear");
		WebElement dob = driver.findElement(By.xpath(
				"//form[@id='slideForm']//div[@slidename='dob' and @class='item active']//div[@class='col-lg-8 col-md-10 col-sm-12 pull-right table-responsive news-tagger-date-picker']//tbody//a[contains(text(),'"
						+ monthyear + "')]"));
		Actions action = new Actions(driver);
		action.moveToElement(dob).click().perform();
		String date3 = CommonUtil.getPropertyValue("home", "date");
		WebElement date = ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//form[@id='slideForm']//div[@slidename='dob' and @class='item active']//div[@class='col-md-12 news-tagger-age-picker-pc']//div[2]//div[@class='col-lg-8 col-md-10 col-sm-12 pull-left table-responsive news-tagger-date-picker']//div[@id='form_applicantPlaceHolder_dob_dob-date-picker']//table//tbody//tr[1]//td["
						+ date3 + "]")));
		action.moveToElement(date).click().perform();
		WebElement displayDob = driver.findElement(By.xpath(
				"//form[@id='slideForm']//div[@slidename='dob' and @class='item active']//div[@class='col-md-12 news-tagger-age-picker-pc']//div[@class='col-md-6 col-sm-6']//input[@name='dob']"));
		System.out.println(displayDob.getAttribute("value"));
		if (displayDob.getAttribute("value").equalsIgnoreCase("3 Mar 1992")) {
			System.out.println("DOB is Correct");
		} else {
			System.out.println("Fail");
		}
		String todaydate = CommonUtil.getCorrectTimeDate();
		System.out.println(todaydate);
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(1992, Month.MARCH, 3);

		Period p = Period.between(birthday, today);
		System.out.println(p.getYears() + "Age is Correct:Pass");
		WebElement con = driver.findElement(By.xpath(
				"//form[@id='slideForm']//div[@class='item active' and @slidename='dob']//div[@class='eform-btn clearfix']//button[@class='btn  js-move-next js-move-next-btn']"));
		con.click();
		TimeUnit.SECONDS.sleep(5);
	}

}
