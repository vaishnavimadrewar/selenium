package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static WebDriver driver;
    public static String originalWindowHandle;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");
        
        // Store the original window handle
        originalWindowHandle = driver.getWindowHandle();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Test
    public void clickOnSpecificLink() throws InterruptedException {
        // Find all links on the page
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the webpage: " + allLinks.size());

        // Print each link
        for (int i = 0; i < allLinks.size(); i++) {
            System.out.println((i + 1) + ". " + allLinks.get(i).getAttribute("href"));
        }

        String targetHrefValue = "https://www.rabbitandtortoise.com/4-Dec-2023/domain-bankinginsurance.html";
        ((JavascriptExecutor) driver).executeScript("window.open('" + targetHrefValue + "', '_blank');");
        
        // Switch to the new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Explicitly wait for the presence of links on the new page
        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("a")));

        // Find all links on the new page
        List<WebElement> linksOnNewPage = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the new webpage: " + linksOnNewPage.size());

        // Print each link on the new page
        for (int i = 0; i < linksOnNewPage.size(); i++) {
            System.out.println((i + 1) + ". " + linksOnNewPage.get(i).getAttribute("href"));
        }

        // Continue with other operations on the new page
    }}