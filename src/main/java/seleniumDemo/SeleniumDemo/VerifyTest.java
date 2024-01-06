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
import java.util.concurrent.TimeUnit;

public class VerifyTest {
    public static WebDriver driver;
    public static String originalWindowHandle;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/careers.html");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
       // driver.manage().window().maximize();

        originalWindowHandle = driver.getWindowHandle();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void clickOnSpecificLink() throws InterruptedException {
        // Find all links on the page
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links on the main webpage: " + allLinks.size());

        // Print each link
        for (int i = 0; i < allLinks.size(); i++) {
            System.out.println((i + 1) + ". " + allLinks.get(i).getAttribute("href"));
        }
     // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) driver;


        String targetHrefValue1 = "https://www.rabbitandtortoise.com/4-Dec-2023/index.html";
       // String targetHrefValue2 = "http://3.13.184.127:8080/rnt/newApplicationPage.do";

        // Loop through each link on the initial page
        for (WebElement link : allLinks) {
            // Get the href attribute value of the link
            String hrefValue = link.getAttribute("href");

           

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

