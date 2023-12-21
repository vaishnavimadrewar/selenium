package seleniumDemo.SeleniumDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class TreeNode {
    String data;
    Set<TreeNode> children;

    public TreeNode(String data) {
        this.data = data;
        this.children = new HashSet<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }
}

public class Usecase10 {
    private WebDriver driver;
    private PrintStream console;
    private File outputFile;
    private TreeNode root;

    @BeforeMethod
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        // Redirect console output to a file
        try {
            // LocalDateTime date = LocalDateTime.now();
            outputFile = new File("C:\\Users\\Vaishnavi Madrewar\\Desktop\\console_WebsiteOutput.txt");
            console = new PrintStream(new FileOutputStream(outputFile));
            System.setOut(console);
            System.setErr(console);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void testClickEachLink() {
        // Navigate to the webpage
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Find all the links on the webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Use a Set to store unique URLs of the links
        Set<String> uniqueLinks = new HashSet<>();

        // Store the unique URLs of the links in the set
        for (WebElement link : links) {
            uniqueLinks.add(link.getAttribute("href"));
        }

        // Print the total number of unique links
        System.out.println("Total number of unique links on the main page: " + uniqueLinks.size());

        // Click on each unique link
        int linkCount = 1;
        for (String linkUrl : uniqueLinks) {
            // Add your condition to skip links here
            if (shouldSkipLink(linkUrl)) {
                System.out.println(linkCount + ". Skipping link: " + linkUrl);
                continue; // Skip to the next iteration
            }

            System.out.println(linkCount + ". Clicking on link: " + linkUrl);

            // Open link in a new tab or window
            ((JavascriptExecutor) driver).executeScript("window.open()");

            // Switch to the newly opened tab or window
            String originalHandle = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            try {
                // Navigate to the link URL
                driver.get(linkUrl);

                // Check if the page has loaded properly
                if (isAboutBlank()) {
                    System.err.println("Error: about:blank encountered. Capturing screenshot.");
                    captureScreenshot("about_blank_screenshot.png");
                } else if (is404Error()) {
                    System.err.println("404 Error on link: " + linkUrl);

                    // Capture screenshot for 404 error
                    captureScreenshot("404_error_screenshot.png");
                } else {
                    TreeNode currentNode = new TreeNode(linkUrl);
                    if (root == null) {
                        root = currentNode;
                    } else {
                        root.addChild(currentNode);
                    }

                    List<WebElement> nextLinks = driver.findElements(By.tagName("a"));
                    System.out.println("Total Links on this webpage (" + nextLinks.size() + " links) and Print only the unique links:");
                    Set<String> uniqueNextLinks = new HashSet<>();

                    for (WebElement nextLink : nextLinks) {
                        String nextLinkUrl = nextLink.getAttribute("href");

                        // Check if the URL is valid and not empty
                        if (isValidLink(nextLinkUrl) && uniqueNextLinks.add(nextLinkUrl)) {
                            System.out.println("- " + nextLinkUrl);
                            TreeNode nextNode = new TreeNode(nextLinkUrl);
                            currentNode.addChild(nextNode);
                        }
                    }
                }
            } catch (Exception e) {
                // Handle the exception (print an error message, log it, etc.)
                System.err.println("Error while clicking on the link: " + e.getMessage());

                // Introduce a delay before capturing the screenshot
                try {
                    Thread.sleep(2000); // You can adjust the delay as needed
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Capture screenshot on error
                captureScreenshot("error_screenshot.png");
            } finally {
                // Optionally, wait for some time or perform other actions after navigating to the link

                // Close the current tab or window
                driver.close();
            }
            // Switch back to the original tab or window
            driver.switchTo().window(originalHandle);

            linkCount++;
        }

        printTree(root, 0);
    }

    private void printTree(TreeNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.getData());

        for (TreeNode child : node.getChildren()) {
            printTree(child, depth + 1);
        }
    }

    private boolean shouldSkipLink(String linkUrl) {
        // Add your condition to skip links based on the URL
        // Example: Skip links containing "mailto:info@rnt.ai"
        return linkUrl.contains("mailto:info@rnt.ai");
    }

    private boolean isValidLink(String linkUrl) {
        // Check if the URL is valid and not empty
        return linkUrl != null && !linkUrl.trim().isEmpty() && !linkUrl.equals("#");
    }

    private boolean isAboutBlank() {
        // Check if the current page has a title of "about:blank"
        return driver.getTitle().equals("about:blank");
    }

    private boolean is404Error() {
        // Check if the current page has a 404 error
        // You can customize this method based on how the 404 error is represented in your application
        return driver.getTitle().contains("File Not Found");
    }

    // Method to capture screenshot
    private void captureScreenshot(String fileName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);

            File trg = new File("C:\\Users\\Vaishnavi Madrewar\\Desktop\\WebsiteErrorSS\\" + fileName);

            FileUtils.copyFile(src, trg);
            System.out.println("Screenshot captured: " + fileName);
        } catch (IOException e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
        // Close the console output file
        if (console != null) {
            console.close();
        }
    }
}
