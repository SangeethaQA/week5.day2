package week5.day2.assignment1;

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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignIncident extends BaseClass {
	@Test
	public   void runAssignIncident() {
		// TODO Auto-generated method stub
	
		driver.findElementByXPath("//div[text()='Open']").click();

		driver.switchTo().frame("gsft_main");
		Select sl = new Select (driver.findElementByXPath("//div[@class='input-group']//select"));
		sl.selectByValue("number");
		driver.findElementByXPath("//div[@class='input-group']//input[@placeholder='Search']").sendKeys("INC0010048",Keys.ENTER);
		driver.findElementByXPath("//a[@class='linked formlink']").click();
		//select the group assignment 
		driver.findElementByXPath("//button[@id='lookup.incident.assignment_group']//span").click();
		Set <String> handleWindow= driver.getWindowHandles();
		List <String> winHandle= new ArrayList <String>(handleWindow);
		driver.switchTo().window(winHandle.get(1));
		driver.findElementByXPath("//label[text()='Search']/following-sibling::input").sendKeys("Software",Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Software']")));
		// click on the compose button as soon as the "compose" button is visible
		driver.findElementByXPath("//a[text()='Software']").click();
		driver.switchTo().window(winHandle.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElementByXPath("//textarea[@id='activity-stream-textarea']").sendKeys("Notes");
		String expectedAssignedGroup= "Software";
		String expectedAssignedTo= "(empty)";

		
		driver.findElementById("sysverb_update").click();		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='input-group']//input[@placeholder='Search']")));

		String assignedGroup= driver.findElementByXPath("//table[@id='incident_table']//tr[1]/td[10]").getText();
		
		String assignedTo= driver.findElementByXPath("//table[@id='incident_table']//tr[1]/td[11]").getText();
		System.out.println("expected" +expectedAssignedGroup+"acutal "+assignedGroup);
		System.out.println("expected"+expectedAssignedTo+"actual "+assignedTo);
		if (expectedAssignedGroup.equals(assignedGroup))
		{
			System.out.println("verified Assigned group");}
		else 
			System.out.println("Assigned group not verified");
		if(expectedAssignedTo.equals(assignedTo))
			{System.out.println("verified AssignedTo");}
		else 
			System.out.println("AssignedTo not verified");
		

	}

}
