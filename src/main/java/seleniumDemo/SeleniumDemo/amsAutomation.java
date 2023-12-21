package seleniumDemo.SeleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class amsAutomation {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

		driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/a")).click();// Click on the Access Management System
        driver.findElement(By.xpath("//*[@id=\"activeRoles\"]/tbody/tr[1]/td[5]/form")).click();
        driver.findElement(By.xpath("/html/body/section[1]/section[2]/div/section[1]/div[2]/div/div/form/div[1]/div[3]/div/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div[3]/ul[3]/li[25]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"saveBtn\"]")).click();
        driver.findElement(By.xpath("//button[@id='savedBtn']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@id='btnlink']")).click();
		WebElement testDropDown = driver.findElement(By.id("roleName"));

	    Thread.sleep(2000);
	    Select dropdown = new Select(testDropDown);
		
		dropdown.selectByVisibleText("Automation Test Engineer");// Select Automation Test Engineer from the dropdown
		
		  driver.findElement(By.xpath("//img[@id='calenderOne']")).click();
		  driver.findElement(By.xpath("//li[@data-view='day'][normalize-space()='30']")
		  ).click();
		  
		  driver.findElement(By.xpath("//div[@class='multiselect-dropdown']")).click();
		  Thread.sleep(3000); WebElement checkbox1 =
		  driver.findElement(By.xpath("//div[137]//input[1]")); WebElement checkbox2 =
		  driver.findElement(By.xpath("//div[139]//input[1]")); ((JavascriptExecutor)
		  driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
		  ((JavascriptExecutor)
		  driver).executeScript("arguments[0].scrollIntoView(true);", checkbox2); //Check the checkboxes
		  checkbox1.click(); checkbox2.click();
		 
        driver.findElement(By.xpath("//button[@id='saveBtn']")).click();
        driver.findElement(By.xpath("//button[@id='savedBtn']")).click();
        driver.findElement(By.xpath("//a[@href='newApplicationPage.do']")).click();
        driver.findElement(By.xpath("//button[@id='addApplication']")).click();
        WebElement testDropDown2 = driver.findElement(By.id("selectApplication"));

    	Select dropdown2 = new Select(testDropDown2);
    	Thread.sleep(1000);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown2);
    	dropdown2.selectByVisibleText("PMS");// Select PMS from the dropdown
    	driver.findElement(By.xpath("//input[@id='submitThis']")).click();
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//input[@id='addAppModule']")).sendKeys("Admin");
    	driver.findElement(By.xpath("//button[@id='adSubmodule']")).click();
    	driver.findElement(By.xpath("//input[@id='addAppModule']")).sendKeys("Manager");
    	driver.findElement(By.xpath("//button[@id='adSubmodule']")).click();
    	driver.findElement(By.xpath("//input[@id='submitThis']")).click();
    	driver.findElement(By.xpath("//div[@id='savedSuccessFully']//div[1]//div[1]//div[3]//button[1]")).click();
    	
        // update modules
    	driver.findElement(By.xpath("//tr[@class='show Banded154 odd Banded']//i[@class='fa-solid fa-pen']")).click();//click on the edit icon
    	driver.findElement(By.xpath("//div[@class='modal-body']//div[2]//i[1]")).click();//Delete the module name
    	driver.findElement(By.xpath("//input[@id='editaddAppModule']")).sendKeys("AMS Admin");
    	driver.findElement(By.xpath("//button[@id='adSubmodule']")).click();
    	driver.findElement(By.xpath("//input[@value='Update']")).click();
    	driver.findElement(By.xpath("//div[@id='updatedSuccessFully']//div[1]//div[1]//div[3]//button[1]")).click();
    	

	}

}
