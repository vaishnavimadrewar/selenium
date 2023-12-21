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
import java.util.concurrent.TimeUnit;

public class Website {
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
        // Navigate to the webpage
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html"); // Replace with your target URL
        //driver.manage().window().maximize();


        // Find all the links on the webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Store the URLs of the links in a separate list
        List<String> linkUrls = new ArrayList<>();
        for (WebElement link : links) {
            linkUrls.add(link.getAttribute("href"));
        }

        // Print the total number of links
        System.out.println("Total number of links: " + linkUrls.size());
        for (int i = 0; i < linkUrls.size(); i++) {
            System.out.println((i + 1) + ". " + linkUrls.get(i));
        }

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
		
		/*
		 * driver.findElement(By.
		  xpath("//div[@class='text-uppercase icon-label py-1'][normalize-space()='Generative AI']"
		 * )).click(); List<WebElement> links2 = driver.findElements(By.tagName("a"));
		 * 
		 * // Store the URLs of the links in a separate list List<String> linkUrls2 =
		 * new ArrayList<>(); for (WebElement link : links2) {
		 * linkUrls2.add(link.getAttribute("href")); }
		 * 
		 * // Print the total number of links
		 * System.out.println("Total number of links: " + linkUrls2.size()); for (int i
		 * = 0; i < linkUrls2.size(); i++) { System.out.println((i + 1) + ". " +
		 * linkUrls2.get(i)); }
		 * 
		 * // Create a JavascriptExecutor instance JavascriptExecutor je =
		 * (JavascriptExecutor) driver;
		 * 
		 * // Click on each link by navigating to its URL for (int k = 0; k <
		 * linkUrls2.size(); k++) { // Print the link URL System.out.println((k + 1) +
		 * ". Clicking on link: " + linkUrls2.get(k));
		 * 
		 * // Navigate to the link URL driver.get(linkUrls2.get(k));
		 * 
		 * // Optionally, wait for some time or perform other actions after navigating
		 * to the link // For example, you can use Thread.sleep or WebDriverWait
		 * 
		 * // Navigate back to the original page (assuming you want to go back)
		 * driver.navigate().back();
		 */
        
     // Create a JavascriptExecutor instance
        JavascriptExecutor jse = (JavascriptExecutor) driver;


        String targetHrefValue1 = "https://www.rabbitandtortoise.com/4-Dec-2023/index.html";
       // String targetHrefValue2 = "http://3.13.184.127:8080/rnt/newApplicationPage.do";

        WebElement[] allLinks = null;
		// Loop through each link on the initial page
        for (WebElement link : allLinks) {
            // Get the href attribute value of the link
            String hrefValue = link.getAttribute("href");

            // Check if the href attribute value matches the target href value
            if (hrefValue != null && (hrefValue.equals(targetHrefValue1) || hrefValue.equals(targetHrefValue1))) {
                // Close the footer if it exists
                closeFooterIfPresent();

                // Click on the link to navigate to the new page
                link.click();
                System.out.println("Clicked on the link with href value: " + hrefValue);

                // Perform any additional operations on the new page

                // Find all links on the new page
                List<WebElement> linksOnNewPage = driver.findElements(By.tagName("a"));
                System.out.println("Total number of links on the Home page: " + linksOnNewPage.size());

                // Print each link on the new page
                for (int i = 0; i < linksOnNewPage.size(); i++) {
                    System.out.println((i + 1) + ". " + linksOnNewPage.get(i).getAttribute("href"));
                }

                // Find all buttons on the new page
                List<WebElement> buttonsOnNewPage = driver.findElements(By.tagName("button"));
                System.out.println("Total number of buttons on the new webpage: " + buttonsOnNewPage.size());

                // Print each button on the new page
                for (int i = 0; i < buttonsOnNewPage.size(); i++) {
                    System.out.println((i + 1) + ". " + buttonsOnNewPage.get(i).getText());
                }
                

                break; // Exit the loop after finding and clicking the desired link
            }
        }
       
    }

    private void closeFooterIfPresent() {
        // Check if the footer element is present
        List<WebElement> footerElements = driver.findElements(By.className("footerNewUi"));
        if (!footerElements.isEmpty()) {
            // Close the footer using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", footerElements.get(0));
            System.out.println("Closed the footer");
        }
    }



    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}