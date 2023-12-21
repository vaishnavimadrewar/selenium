package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTestNG {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path of the ChromeDriver executable
    	System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

		driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login
    }

    @Test
    public void findAndClickLink() throws InterruptedException {
        // Find all the links on the webpage
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the webpage: " + allLinks.size());
		 

        // Print each link
        for (int i = 0; i < allLinks.size(); i++) {
            System.out.println((i + 1) + ". " + allLinks.get(i).getAttribute("href"));
        }

        // Loop through each link
        for (WebElement link : allLinks) {
            // Check if the link text or any other attribute matches the specific link you are looking for
        if (link.getAttribute("href").equals("http://3.13.184.127:8080/rnt/AccessManagementdash.do")) {
           // Click on the specific link
         link.click();
                
         Thread.sleep(5000);
         List<WebElement> AMSlinks = driver.findElements(By.tagName("a"));
           			 
        // System.out.println("Total number of links on the webpage: " + AMSlinks.size());
           			  
            
           			  for (int i = 0; i < AMSlinks.size(); i++) {
           			  System.out.println((i + 1) + ". " + AMSlinks.get(i).getAttribute("href")); }
           			  
           			  
           			List<WebElement> allButtonsOnNewPage = driver.findElements(By.tagName("button"));
           	        System.out.println("Total number of buttons on the new page: " + allButtonsOnNewPage.size());

           	        // Loop through each button on the new page
           	        for (int i = 0; i < allButtonsOnNewPage.size(); i++) {
           	            System.out.println((i + 1) + ". " +allButtonsOnNewPage.get(i).getAttribute("type")); // You can print other attributes as needed
           	        }
					/*
					 * List<WebElement> applicationLinks = driver.findElements(By.tagName("a"));
					 * System.out.println("Total number of links on the webpage: " +
					 * applicationLinks.size());
					 * 
					 * 
					 * // Print each link for (int i = 0; i < applicationLinks.size(); i++) {
					 * System.out.println((i + 1) + ". " +
					 * applicationLinks.get(i).getAttribute("href")); }
					 * 
					 * // Loop through each link for (WebElement link : applicationLinks) { // Check
					 * if the link text or any other attribute matches the specific link you are
					 * looking for if (link.getAttribute("href").equals(
					 * "http://3.13.184.127:8080/rnt/AccessManagementdash.do")) { // Click on the
					 * specific link link.click();
					 */
                          
           			 
                 // Break out of the loop once the link is clicked
                break;
            }
        }

        // You can add assertions or further actions after clicking the link
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.close();
    }
}

