package seleniumDemo.SeleniumDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CommandTest {

	public static void main(String[] args) {
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
		String pageSource = driver.getPageSource();
		System.out.println("Page Source: " + pageSource);

		
	}

}
