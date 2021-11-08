package week5.day2.assignment1;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import week5.day2.assignment2.ReadExcel;

public class ServiceNowCreation extends BaseClass{
	@Test(dataProvider="ProvideData")
	public void runServiceNow(String description) throws InterruptedException {

	// TODO Auto-generated method stub


	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	driver.findElementByXPath("//span[text()='Incident']").click();
	driver.findElementByXPath("(//div[contains(text(),'Create New')])").click();
	
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Service Desk']/ancestor::li/following-sibling::li//span[text()='Incident']")));
	//driver.findElementById("//span[text()='Service Desk']/ancestor::li/following-sibling::li//span[text()='Incident']").click();
	
	//driver.findElementById("//div[text()='Assigned to me']/ancestor::li/preceding-sibling::li//div[text()='Create New']").click();
	driver.switchTo().frame("gsft_main");
	driver.findElementById("lookup.incident.caller_id").click();
	Set <String> handleWindow= driver.getWindowHandles();
	List <String> winHandle= new ArrayList <String>(handleWindow);
	driver.switchTo().window(winHandle.get(1));
	driver.findElementByXPath("//a[text()='survey user']").click();
	driver.switchTo().window(winHandle.get(0));
	driver.switchTo().frame("gsft_main");
	driver.findElementById("incident.short_description").sendKeys(description);
	String value=driver.findElementByXPath("//input[@id='incident.number']").getAttribute("value");
	System.out.println("value is :"+value);
	driver.findElementById("sysverb_insert").click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame("gsft_main");		
		
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='New']")));
	
	Select sl = new Select (driver.findElementByXPath("//div[@class='input-group']//select"));
	sl.selectByValue("number");
	driver.findElementByXPath("//div[@class='input-group']//input[@placeholder='Search']").sendKeys(value,Keys.ENTER);

	String text1= driver.findElementByXPath("//a[@class='linked formlink']").getText();
	if (text1.equals(value))
			System.out.println("Verified the incident");
	else
		System.out.println("Incident is incorrect");
	
	
	
	}
	
	@DataProvider(name="ProvideData")
	public String [][] sendData() throws IOException{
		return ReadExcel.readData("ServiceNow");
	}

}
