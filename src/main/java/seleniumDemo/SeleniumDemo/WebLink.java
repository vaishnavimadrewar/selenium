package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebLink {

	public static WebDriver driver;

	public static String originalWindowHandle;

	@BeforeClass

	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

		this.driver = new ChromeDriver();

		driver.get("http://3.13.184.127:8080/rnt");

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		// Login

		driver.findElement(By.xpath("/html/body/div/div/form/div[1]/input")).sendKeys("dn1388");//Username

		driver.findElement(By.name("password")).sendKeys("Rnt@@123");//password

		driver.findElement(By.xpath("/html/body/div/div/form/button")).click();//click on login

		Thread.sleep(1000);

		// Store the original window handle

		originalWindowHandle = driver.getWindowHandle();

	}

	@AfterClass

	public void close() {

		driver.quit();

	}

	@Test

	public void openLinksInTabs() throws InterruptedException {

		// Find all links on the page

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		System.out.println("Total number of links on the webpage: " + allLinks.size());

		// Print each link

		for (int i = 0; i < allLinks.size(); i++) {

			System.out.println((i + 1) + ". " + allLinks.get(i).getAttribute("href"));

		}

		Thread.sleep(2000);

		// Loop through each link

		for (WebElement link : allLinks) {

			// Get the href attribute of the link

			String linkHref = link.getAttribute("href");

			Thread.sleep(2000);

			// Check if the link is the logout link, and skip if necessary

			if (linkHref != null && linkHref.contains("logout")) {

				System.out.println("Skipping logout link: " + linkHref);

				continue;

			}

			Thread.sleep(2000);

			// Open the link in a new tab using JavaScript

			openLinkInNewTab(link);

			// Perform any operations on the new tab as needed

			Thread.sleep(2000);

			// Close the new tab

			closeCurrentTab();

		}

	}

	private void openLinkInNewTab(WebElement link) throws InterruptedException {

		// Use JavaScript to open the link in a new tab

		((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", link.getAttribute("href"));

		Thread.sleep(2000);

		// Switch to the new tab

		switchToNewTab();

	}

	private void switchToNewTab() throws InterruptedException {

		// Switch to the new tab by comparing window handles

		Set<String> allHandles = driver.getWindowHandles();

		allHandles.remove(originalWindowHandle);

		Thread.sleep(2000);

		if (!allHandles.isEmpty()) {

			driver.switchTo().window(allHandles.iterator().next());

		}

	}

	private void closeCurrentTab() {

		driver.close();

		// Switch back to the original tab

		driver.switchTo().window(originalWindowHandle);

	}
 
}
