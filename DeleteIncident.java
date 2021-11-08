package week5.day2.assignment1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident extends BaseClass{
	@Test
	public   void runDeleteIncident() throws InterruptedException {
		// TODO Auto-generated method stub
	
	
		driver.findElementByXPath("(//div[contains(text(),'All')])[2]").click();
		driver.switchTo().frame("gsft_main");
		Select sl = new Select (driver.findElementByXPath("//div[@class='input-group']//select"));
		sl.selectByValue("number");
		driver.findElementByXPath("//div[@class='input-group']//input[@placeholder='Search']").sendKeys("INC0010042",Keys.ENTER);
		driver.findElementByXPath("//a[@class='linked formlink']").click();
		driver.findElementById("sysverb_delete").click();
	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok_button']")));
		driver.findElementById("ok_button").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='input-group']//input[@placeholder='Search']")));
		String noRecord= driver.findElementByXPath("//table[@id='incident_table']//tr[1]/td[1]").getText();
		
		System.out.println("expected"+noRecord);
		if(noRecord.contains("No records"))
			System.out.println("incident is deleted");	
		else 
			System.out.println("incident is not deleted");
		
		
	}

}
