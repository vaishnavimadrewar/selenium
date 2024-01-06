package seleniumDemo.SeleniumDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class TreeNode1 {
    String data;
    Set<TreeNode1> children;

    public TreeNode1(String data) {
        this.data = data;
        this.children = new HashSet<>();
    }

    public void addChild(TreeNode1 child) {
        this.children.add(child);
    }

    public Set<TreeNode1> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }
}

public class Usecase12 {
    private WebDriver driver;
    private PrintStream console;
    private File outputFile;
    private Set<String> visitedLinks;

    @BeforeMethod
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        // Redirect console output to a file
		/*
		 * try { outputFile = new
		 * File("C:\\Users\\Vaishnavi Madrewar\\Desktop\\console_WebsiteOutput2.txt");
		 * console = new PrintStream(new FileOutputStream(outputFile));
		 * System.setOut(console); System.setErr(console); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

        visitedLinks = new HashSet<>();
    }

    @Test(priority = 1)
    public void testClickEachLink() {
        // Navigate to the main webpage
        driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Initialize root to null
        TreeNode1 root = new TreeNode1("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Create a stack for DFS
        Stack<TreeNode1> stack = new Stack<>();
        stack.push(root);

        // DFS to click on each link and print the tree
        while (!stack.isEmpty()) {
            TreeNode1 currentNode = stack.pop();
            String currentUrl = currentNode.getData();

            // Add your condition to skip links here
            if (shouldSkipLink(currentUrl) || visitedLinks.contains(currentUrl)) {
                System.out.println("Skipping link: " + currentUrl);
                continue;
            }

            System.out.println("Clicking on link: " + currentUrl);

            try {
                // Open link in a new tab
                ((JavascriptExecutor) driver).executeScript("window.open()");
                switchToTab(2); // Switch to the newly opened tab

                // Navigate to the link URL
                driver.get(currentUrl);

                // Check if the page has loaded properly
                if (isAboutBlank()) {
                    System.err.println("Error: about:blank encountered. Capturing screenshot.");
                    captureScreenshot("about_blank_screenshot.png");
                } else if (is404Error()) {
                    System.err.println("404 Error on link: " + currentUrl);

                    // Capture screenshot for 404 error
                    captureScreenshot("404_error_screenshot.png");
                } else {
                    // Mark the link as visited
                    visitedLinks.add(currentUrl);

                    // Print links on the current webpage
                    List<WebElement> currentLinks = driver.findElements(By.tagName("a"));
                    System.out.println("Total Links on this webpage (" + currentLinks.size()
                            + " links) and Print only the unique links:");
                    Set<String> uniqueCurrentLinks = new HashSet<>();

                    for (WebElement currentLink : currentLinks) {
                        String nextLinkUrl = currentLink.getAttribute("href");

                        // Check if the URL is valid and not empty
                        if (isValidLink(nextLinkUrl) && uniqueCurrentLinks.add(nextLinkUrl)) {
                            System.out.println("- " + nextLinkUrl);
                            TreeNode1 nextNode = new TreeNode1(nextLinkUrl);
                            currentNode.addChild(nextNode);
                            stack.push(nextNode);
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
                // Close the current tab
                closeCurrentTab();
            }
        }

        // Print the tree structure
        printTree(root, 0);
    }

    private void switchToTab(int tabIndex) {
        // Switch to the specified tab
        Set<String> handles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(handles);

        if (handlesList.size() >= tabIndex) {
            driver.switchTo().window(handlesList.get(tabIndex - 1));
        } else {
            System.err.println("Error: Unable to switch to tab with index " + tabIndex);
        }
    }

    private void closeCurrentTab() {
        // Close the current tab
        String currentHandle = driver.getWindowHandle();
        driver.close();

        // Switch back to the original tab
        Set<String> handles = driver.getWindowHandles();
        handles.remove(currentHandle);

        if (!handles.isEmpty()) {
            driver.switchTo().window(handles.iterator().next());
        } else {
            System.err.println("Error: No more tabs to switch back to.");
        }
    }

    private void printTree(TreeNode1 node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.getData());

        for (TreeNode1 child : node.getChildren()) {
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
		/*
		 * // Close the console output file if (console != null) { console.close(); }
		 */
    }
}
