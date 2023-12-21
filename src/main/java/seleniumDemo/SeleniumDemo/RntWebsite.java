package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RntWebsite {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testClickEachLink() {
        // Navigate to the webpage
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");
        //driver.manage().window().maximize();

        clickAllLinksOnPage();

        // Click on the "Generative AI" element
        driver.findElement(By.xpath("//div[@class='text-uppercase icon-label py-1'][normalize-space()='Generative AI']")).click();

        clickAllLinksOnPage();
    }

    private void clickAllLinksOnPage() {
        // Find all the links on the webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Store the URLs of the links in a separate list
        List<String> linkUrls = new ArrayList<>();
        for (WebElement link : links) {
            linkUrls.add(link.getAttribute("href"));
        }

        // Print the total number of links
        System.out.println("Total number of links: " + linkUrls.size());

        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Click on each link by navigating to its URL
        for (int k = 0; k < linkUrls.size(); k++) {
            // Print the link URL
            System.out.println((k + 1) + ". Clicking on link: " + linkUrls.get(k));

            // Navigate to the link URL
            driver.get(linkUrls.get(k));

            // Optionally, wait for some time or perform other actions after navigating to the link
            // For example, you can use Thread.sleep or WebDriverWait

            // Navigate back to the original page (assuming you want to go back)
            driver.navigate().back();
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
