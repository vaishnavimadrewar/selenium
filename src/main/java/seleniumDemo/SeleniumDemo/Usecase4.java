package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Usecase4 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    @Test
    public void testClickEachLink() {
        // Navigate to the main webpage
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Find all the links on the main webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Use a Set to store unique URLs of the links on the main webpage
        Set<String> uniqueLinksOnMainPage = new HashSet<>();

        // Store the unique URLs of the links on the main webpage in the set
        for (WebElement link : links) {
            uniqueLinksOnMainPage.add(link.getAttribute("href"));
        }

        // Print the total number of unique links on the main webpage
        System.out.println("Total number of unique links on main page: " + uniqueLinksOnMainPage.size());

        // Print and click on each unique link on the main webpage
        int linkCount = 1;
        for (String linkUrl : uniqueLinksOnMainPage) {
            System.out.println(linkCount + ". Clicking on link: " + linkUrl);

            // Navigate to the link URL
            driver.get(linkUrl);

            // Optionally, wait for some time or perform other actions after navigating to the link

            // Navigate back to the main webpage (assuming you want to go back to the main webpage)
            driver.navigate().back();

            linkCount++;
        }

        // Click on a specific link using its index (e.g., the first link - index 0)
        int specificLinkIndex = 3;

        if (specificLinkIndex >= 3 && specificLinkIndex < links.size()) {
            WebElement specificLink = links.get(specificLinkIndex);
            System.out.println("Clicking on specific link at index " + specificLinkIndex);
            specificLink.click();

            // Find all the links on the opened webpage
            List<WebElement> linksOnSpecificPage = driver.findElements(By.tagName("a"));

            // Use a Set to store unique URLs of the links on the opened webpage
            Set<String> uniqueLinksOnSpecificPage = new HashSet<>();

            // Store the unique URLs of the links on the opened webpage in the set
            for (WebElement link : linksOnSpecificPage) {
                uniqueLinksOnSpecificPage.add(link.getAttribute("href"));
            }

            // Print the total number of unique links on the opened webpage
            System.out.println("Total number of unique links on the specific page: " + uniqueLinksOnSpecificPage.size());

            // Print and click on each unique link on the opened webpage
            linkCount = 1;
            for (String linkUrl : uniqueLinksOnSpecificPage) {
                System.out.println(linkCount + ". Clicking on link: " + linkUrl);

                // Navigate to the link URL
                driver.get(linkUrl);

                // Optionally, wait for some time or perform other actions after navigating to the link

                // Navigate back to the specific page (assuming you want to go back to the specific page)
                driver.navigate().back();

                linkCount++;
            }
        } else {
            System.out.println("Invalid specific link index.");
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}

