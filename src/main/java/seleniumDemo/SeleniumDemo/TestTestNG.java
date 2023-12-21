package seleniumDemo.SeleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestTestNG {
	WebDriver driver;
	 
	@BeforeTest
	
	public void loginTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
				 
				  
	    driver.findElement(By.className("form-control")).sendKeys("VM1494");//Username
				 
				 
	    driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");//Password
				 
	    driver.findElement(By.xpath("/html/body/div/div/form/button")).click();//Click on login
				 
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	}
	@Test (priority = 1)
	public void testCaseone() throws InterruptedException {
		

				driver.findElement(By.xpath("/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[5]/div[2]")).click();// Click on LMS
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div/div[2]/aside/div[2]/div/div[3]/span/a")).click();// Click on Add holiday module
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"AddHolidayBtn\"]")).click();// Click on the "Add Holiday" button
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div/div/div[4]/div/div[3]/form/div[1]/div/div/div")).click();// Click on the calendar
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[2]/a")).click();// Click andselect the date from date picker
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
																												
			}
	@Test (priority = 2)// Approve Leave
	public void testCaseTwo() {
		driver.findElement(By.xpath("//a[normalize-space()='Approve Leave']")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[9]/div[1]/span[1]/img[1]")).click();
		//driver.findElement(By.xpath("//input[@id='CommentBox']")).sendKeys("Approved");
		driver.findElement(By.xpath("//button[@id='approvebutton']")).click();
		//driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		
	}
	@Test (priority = 3)
	public void testCaseThree() {
		driver.findElement(By.xpath("//input[@id='CommentBox']")).sendKeys("Approved");
		driver.findElement(By.xpath("//button[@id='approvebutton']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		
	}
	@Test (priority = 4)//Reject leave
			public void testCaseFour() {
		driver.findElement(By.xpath("//tbody/tr[2]/td[9]/div[1]/span[2]/img[1]")).click();
		driver.findElement(By.xpath("//button[@id='rejectebutton']")).click();
		
	}
	@Test (priority = 5)
	public void testCaseFive() {
		driver.findElement(By.xpath("//tbody/tr[2]/td[9]/div[1]/span[2]/img[1]")).click();
		driver.findElement(By.xpath("//form[@id='RejectLeave']//div[@class='pdField w-100']")).sendKeys("Rejected");
		driver.findElement(By.xpath("//button[@id='rejectebutton']")).click();
		
	}
	

	}