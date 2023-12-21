package seleniumDemo.SeleniumDemo;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class AutomationTestNG {
	public WebDriver driver;
	
	@BeforeTest
	//@BeforeMethod
	public void loginTest() throws InterruptedException {
		System.out.println("Thread Id :" + Thread.currentThread().getId());
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
	

	}

	@Test(priority = 1)
	public void testOne() throws InterruptedException {
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

		driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
		Thread.sleep(2000);

	}

	/*
	 * @Test(priority = 2) public void testTwo() throws InterruptedException {
	 * 
	 * 
	 * System.setProperty("webdriver.chrome.driver",
	 * "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe"
	 * ); //this.driver = new ChromeDriver();
	 * driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
	 * 
	 * 
	 * driver.findElement(By.className("form-control")).sendKeys("VM1494");//
	 * Username
	 * 
	 * 
	 * driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");//
	 * Password
	 * 
	 * driver.findElement(By.xpath("/html/body/div/div/form/button")).click();//
	 * Click on login
	 * 
	 * driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	 * 
	 * driver.findElement(By.xpath(
	 * "/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[5]/div[2]")).click(
	 * );// Click on LMS Thread.sleep(1000); driver.findElement(By.xpath(
	 * "/html/body/div/div[2]/aside/div[2]/div/div[3]/span/a")).click();// Click on
	 * Add holiday module Thread.sleep(1000);
	 * driver.findElement(By.xpath("//*[@id=\"AddHolidayBtn\"]")).click();// Click
	 * on the "Add Holiday" button Thread.sleep(1000); driver.findElement(By.xpath(
	 * "/html/body/div[1]/div[3]/main/div/div/div/div[4]/div/div[3]/form/div[1]/div/div/div"
	 * )).click();// Click on the calendar Thread.sleep(1000);
	 * driver.findElement(By.xpath(
	 * "//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[2]/a")).click();// Click
	 * andselect the date from date picker Thread.sleep(1000);
	 * driver.findElement(By.xpath("//*[@id=\"Ocassion\"]")).sendKeys("Diwali");//
	 * Enter text data in holiday field Thread.sleep(1000); WebElement testDropDown
	 * = driver.findElement(By.id("is_optional_holiday"));
	 * 
	 * Thread.sleep(1000); Select dropdown = new Select(testDropDown);
	 * Thread.sleep(1000); dropdown.selectByVisibleText("No");// Select No from the
	 * dropdown Thread.sleep(1000);
	 * 
	 * driver.findElement(By.xpath("//*[@id=\"btnAction\"]")).click();// Click on
	 * the "Save" button Thread.sleep(2000);
	 * driver.findElement(By.xpath("//button[@class='confirm']")).click(); // Click
	 * on the OK button driver.findElement(By.xpath(
	 * "/html/body/div[1]/div[3]/aside/div[2]/div/div[2]/span/a")).click();// Click
	 * on the Dashboard module
	 * 
	 * }
	 */
	@Test(priority = 2)
	public void testTwo() throws InterruptedException {
		//driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[1]/a/img")).click();// Click on the RNT Logo
		driver.findElement(By.xpath("/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/a")).click();//Click on the Access Management System
		driver.findElement(By.xpath("//*[@id=\"activeRoles\"]/tbody/tr[1]/td[5]/form")).click();// Click on the Edit icon
		driver.findElement(By.xpath("/html/body/section[1]/section[2]/div/section[1]/div[2]/div/div/form/div[1]/div[3]/div/span")).click();// Click on the calendar icon
		driver.findElement(By.xpath("/html/body/div/div[3]/ul[3]/li[25]")).click();// Select the date from date picker
		driver.findElement(By.xpath("//*[@id=\"saveBtn\"]")).click();// Click on the Update button
		driver.findElement(By.xpath("//button[@id='savedBtn']")).click();
		Thread.sleep(4000);

	}
	@Test (priority = 3)
    public void testThree () throws InterruptedException {
    	driver.findElement(By.xpath("//a[@id='btnlink']")).click();
        WebElement testDropDown = driver.findElement(By.id("roleName"));

    	Thread.sleep(2000);
    	Select dropdown = new Select(testDropDown);
    	Thread.sleep(2000);
    	dropdown.selectByVisibleText("Automation Test Engineer");// Select Automation Test Engineer from the dropdown
    	driver.findElement(By.xpath("//button[@id='saveBtn']")).click();
    	driver.findElement(By.xpath("//button[@id='errorBtn']")).click();
	}
    @Test (priority = 4)
    public void testFour() throws InterruptedException {
		
	driver.findElement(By.xpath("//img[@id='calenderOne']")).click();
	driver.findElement(By.xpath("//li[@data-view='day'][normalize-space()='30']")).click();
	
	driver.findElement(By.xpath("//div[@class='multiselect-dropdown']")).click();
	Thread.sleep(3000);
	WebElement checkbox1 = driver.findElement(By.xpath("//div[137]//input[1]"));
    WebElement checkbox2 = driver.findElement(By.xpath("//div[139]//input[1]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);// Check the checkbox
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox2);// Check the checkbox
   
    checkbox1.click();
    checkbox2.click();
    driver.findElement(By.xpath("//button[@id='saveBtn']")).click();
    driver.findElement(By.xpath("//button[@id='savedBtn']")).click();

    }
    @Test (priority = 5)
    public void testFive() throws InterruptedException {
    driver.findElement(By.xpath("//a[@href='newApplicationPage.do']")).click();
    driver.findElement(By.xpath("//button[@id='addApplication']")).click();
    WebElement testDropDown = driver.findElement(By.id("selectApplication"));

	Select dropdown = new Select(testDropDown);
	Thread.sleep(1000);
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
	dropdown.selectByVisibleText("PMS");// Select PMS from the dropdown
	driver.findElement(By.xpath("//input[@id='submitThis']")).click();
	Thread.sleep(5000);
    }
    @Test (priority = 6)//Add modules
    public void testSix() {
    	
	driver.findElement(By.xpath("//input[@id='addAppModule']")).sendKeys("Admin");
	driver.findElement(By.xpath("//button[@id='adSubmodule']")).click();
	driver.findElement(By.xpath("//input[@id='addAppModule']")).sendKeys("Manager");
	driver.findElement(By.xpath("//button[@id='adSubmodule']")).click();
	driver.findElement(By.xpath("//input[@id='submitThis']")).click();
	driver.findElement(By.xpath("//div[@id='savedSuccessFully']//div[1]//div[1]//div[3]//button[1]")).click();
	
    }
    @Test (priority = 7)// Update modules
    public void testSeven() {
    	driver.findElement(By.xpath("//tr[@class='show Banded154 odd Banded']//i[@class='fa-solid fa-pen']")).click();//click on the edit icon
    	driver.findElement(By.xpath("//div[@class='modal-body']//div[2]//i[1]")).click();//Delete the module name
    	driver.findElement(By.xpath("//input[@id='editaddAppModule']")).sendKeys("AMS Admin");
    	driver.findElement(By.xpath("//button[@id='editadSubmodule']")).click();
    	driver.findElement(By.xpath("//input[@value='Update']")).click();
    	driver.findElement(By.xpath("//div[@id='updatedSuccessFully']//div[1]//div[1]//div[3]//button[1]")).click();
    	
    }
     
    
}