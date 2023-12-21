package seleniumDemo.SeleniumDemo;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

		driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[5]/div[2]")).click();// Click
																													// on
																													// LMS
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div[2]/aside/div[2]/div/div[3]/span/a")).click();// Click on Add
																										// Holiday
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"AddHolidayBtn\"]")).click();// Click on the "Add Holiday" button
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/main/div/div/div/div[4]/div/div[3]/form/div[1]/div/div/div"))
				.click();// Click on the calendar
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[2]/a")).click();// Click and select the date from date picker
																											
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"Ocassion\"]")).sendKeys("Diwali");// Enter text data in holiday field
		Thread.sleep(1000);
		WebElement testDropDown = driver.findElement(By.id("is_optional_holiday"));
																				
		Thread.sleep(1000);
		Select dropdown = new Select(testDropDown);
		Thread.sleep(1000);
		dropdown.selectByVisibleText("No");// Select No from the dropdown
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"btnAction\"]")).click();// Click on the "Save" button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='confirm']")).click(); // Click on the OK button
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/aside/div[2]/div/div[2]/span/a")).click();// Click on the Dashboard module
																										
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[1]/a/img")).click();// Click on the RNT Logo
		driver.findElement(By.xpath("/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/a")).click();// Click on the Access Management System
        driver.findElement(By.xpath("//*[@id=\"activeRoles\"]/tbody/tr[1]/td[5]/form")).click();// Click on the Edit icon
        driver.findElement(By.xpath("/html/body/section[1]/section[2]/div/section[1]/div[2]/div/div/form/div[1]/div[3]/div/span")).click();//Click on the calendar icon
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div[3]/ul[3]/li[25]")).click();// Select the date from date picker
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"saveBtn\"]")).click();// Click on the Update button
        driver.findElement(By.xpath("//a[@id='btnlink']")).click();
        
	}
}
