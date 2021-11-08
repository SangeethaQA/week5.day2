package week5.day2.assignment1;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import week5.day2.assignment2.ReadExcel;

public class UpdateIncident extends BaseClass {
	@Test(dataProvider="ProvideData")
	public  void runUpdateIncident(String urgency, String state) {

		// public void runUpdateIncident() {
		// TODO Auto-generated method stub
	
		driver.findElementByXPath("//span[text()='Service Desk']/parent::a/following::ul//div[text()='Incidents']")
				.click();

		driver.switchTo().frame("gsft_main");
		Select sl = new Select(driver.findElementByXPath("//div[@class='input-group']//select"));
		sl.selectByValue("number");
		driver.findElementByXPath("//div[@class='input-group']//input[@placeholder='Search']").sendKeys("INC0010048",
				Keys.ENTER);
		driver.findElementByXPath("//a[@class='linked formlink']").click();

		Select s2 = new Select(driver.findElementById("incident.urgency"));
		s2.selectByVisibleText(urgency);

		Select s3 = new Select(driver.findElementById("incident.state"));
		s3.selectByVisibleText(state);
		String expectedState= s3.getFirstSelectedOption().getText();
		Select s4 = new Select(driver.findElementById("incident.priority"));
		String priority=s4.getFirstSelectedOption().getText();
		driver.findElementById("sysverb_update").click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='input-group']//input[@placeholder='Search']")));

		/*WebElement table = driver.findElementByXPath("//table[@id='incident_table']/tbody");
		List<WebElement> row = table.findElements(By.tagName("tr"));
		WebElement firstRow =row.get(0);
		List<WebElement> col = firstRow.findElements(By.tagName("td"));

		String Expectedstate = "In Progress";

		for (WebElement column : col) {
		if(column.getText().equals(Expectedstate)){
				System.out.println("state is verified");
		
			}
		}*/
		String priority1= driver.findElementByXPath("//table[@id='incident_table']//tr[1]/td[7]").getText();
		
		String state1= driver.findElementByXPath("//table[@id='incident_table']//tr[1]/td[8]").getText();
		System.out.println(priority+""+expectedState);
		if (priority1.equals(priority))
		{
			System.out.println("verified priority");}
		else 
			System.out.println("priority not verified");
		if(expectedState.equals(state1))
			{System.out.println("verified state");}
		else 
			System.out.println("state not verified");
		
	}
	
	@DataProvider(name="ProvideData")
	public String [][] sendData() throws IOException{
		return ReadExcel.readData("UpdateIncident");
	}

}


