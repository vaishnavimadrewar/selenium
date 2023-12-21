package seleniumDemo.SeleniumDemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class example {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

		driver.findElement(By.id("password-field")).sendKeys("Vaishavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
		Thread.sleep(1000);
		
		 List<WebElement> links = driver.findElements(By.tagName("a"));
		 
		 System.out.println("Total number of links on the webpage: " + links.size());
		 

	        // Print each link
	        for (int i = 0; i < links.size(); i++) {
	            System.out.println((i + 1) + ". " + links.get(i).getAttribute("href"));
	        }
	        
	        }
	            
			/*
			 * driver.findElement(By.xpath(
			 * "/html/body/section/main/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/a")).
			 * click();// Click on the Access Management System List<WebElement> AMSlinks =
			 * driver.findElements(By.tagName("a"));
			 * 
			 * System.out.println("Total number of links on the webpage: " +
			 * AMSlinks.size());
			 * 
			 * 
			 * // Print each link for (int i = 0; i < AMSlinks.size(); i++) {
			 * System.out.println((i + 1) + ". " + AMSlinks.get(i).getAttribute("href")); }
			 */
	          
			 
		 }




