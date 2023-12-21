package seleniumDemo.SeleniumDemo;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username
		// driver.findElement(By.className("fa fa-lg field-icon toggle-password
		// fa-eye-slash")).click();
		driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
		driver.findElement(By.xpath("//div[@class='applicationContainer']")).click();
		/*
		 * Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_CONTROL);
		 * robot.keyPress(KeyEvent.VK_END); robot.keyRelease(KeyEvent.VK_END);
		 * robot.keyRelease(KeyEvent.VK_CONTROL); driver.findElement(By.
		 * xpath("//a[normalize-space()='Service Management System']")).click();
		 */
		 WebDriverWait wait = new WebDriverWait(driver, 10);

		 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("//div[@class='applicationSection']//div[8]//div[2]")));
		 //WebElement element1 = driver.findElement(By.linkText("Your Link Text"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		 element.click();
		 
		driver.findElement(By.xpath("//*[@id=\"incidentId\"]/ul")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Create New Incident')]")).click();// Click on Create New
																								// Incident

		WebElement testDropDown = driver.findElement(By.id("urgency"));
		Select dropdown = new Select(testDropDown);
		dropdown.selectByVisibleText("Medium");// Select urgency from the dropdown
		driver.findElement(By.xpath("//*[@id=\"txtincShortDesc\"]")).sendKeys("Outlook has crashed");// Enter Short
																										// description

		driver.findElement(By.xpath("//*[@id=\"btnAction\"]")).click();// Click on submit button
		driver.findElement(By.xpath("//*[@id=\"incidentForUser\"]/div[4]/div[7]/div/button")).click();// Click on OK
																										// button

		driver.findElement(By.xpath("//*[@id=\"All\"]")).click();// Click on ALL submodule
		driver.findElement(By.xpath("//*[@id=\"tList1\"]/tbody/tr[1]/td[2]/form/button")).click();// Click on Inc Number
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"postBtn\"]")).click();// Click on the Post button
       
		//*[@id="postBtn"]
		  Alert alert = driver.switchTo().alert(); String alertMessage =
		  driver.switchTo().alert().getText(); System.out.println(alertMessage);
		  
		  Thread.sleep(4000); System.out.println("script executed"); alert.accept();
		 

		driver.quit();

	}
}
