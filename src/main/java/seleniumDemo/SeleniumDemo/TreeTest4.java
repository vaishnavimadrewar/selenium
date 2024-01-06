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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class MyTreeNode5 {
    String data;
    List<MyTreeNode5> children;

    public MyTreeNode5(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(MyTreeNode5 child) {
        this.children.add(child);
    }

    public List<MyTreeNode5> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }
}

class Tree6 {
    private MyTreeNode5 root;

    public Tree6(String rootData) {
        this.root = new MyTreeNode5(rootData);
    }

    public MyTreeNode5 getRoot() {
        return root;
    }

    public void show() {
        display(root, 0);
    }

    private void display(MyTreeNode5 node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.getData());

        for (MyTreeNode5 child : node.getChildren()) {
            display(child, depth + 1);
        }
    }
}

public class TreeTest4 {
    public static WebDriver driver;
    public static String originalWindowHandle;
    public static Set<String> openTabs;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openTabs = new HashSet<>();
    }

    @AfterClass
    public void tearDown() {
        // Close all remaining tabs
        for (String handle : openTabs) {
            try {
                driver.switchTo().window(handle);
                driver.close();
            } catch (Exception e) {
                // Ignore any exceptions during closing
            }
        }
    }

    @Test(priority = 1)
    public void testCase1() {
        Tree6 tree = new Tree6("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Navigate to the specified URL
        driver.get(tree.getRoot().getData());

        // Initialize the originalWindowHandle
        originalWindowHandle = driver.getWindowHandle();
        openTabs.add(originalWindowHandle);

        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Use a Set to store unique URLs of the links
        Set<String> uniqueLinks = new HashSet<>();

        // Store the unique URLs of the links in the set
        for (WebElement link : links) {
            uniqueLinks.add(link.getAttribute("href"));
        }

        int linkCount = 1;
        for (String linkUrl : uniqueLinks) {
            // Add your condition to skip links here
            if (shouldSkipLink(linkUrl)) {
                System.out.println(linkCount + ". Skipping link: " + linkUrl);
                continue; // Skip to the next iteration
            }

            // Create a tree node for the current link
            MyTreeNode5 currentNode = new MyTreeNode5(linkUrl);

            // Add the current node to the tree
            tree.getRoot().addChild(currentNode);

            // Print the link count and URL
            System.out.println(linkCount + ". Adding link to tree: " + linkUrl);

            // Open link in a new tab
            ((JavascriptExecutor) driver).executeScript("window.open()");
            switchToNewTab();

            // Navigate to the link URL
            driver.get(linkUrl);

            // Additional wait to ensure the page is loaded
            new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("some expected title"));

            // Find sub-nodes on the new webpage
            List<WebElement> subLinks = driver.findElements(By.tagName("a"));
            Set<String> uniqueSubLinks = new HashSet<>();
            for (WebElement subLink : subLinks) {
                uniqueSubLinks.add(subLink.getAttribute("href"));
            }

            int subLinkCount = 1;
            for (String subLinkUrl : uniqueSubLinks) {
                // Add your condition to skip sub-links here
                if (shouldSkipLink(subLinkUrl)) {
                    System.out.println("  " + subLinkCount + ". Skipping sub-link: " + subLinkUrl);
                    continue; // Skip to the next sub-link
                }

                // Create a sub-node for the current sub-link
                MyTreeNode5 subNode = new MyTreeNode5(subLinkUrl);

                // Add the sub-node to the current node
                currentNode.addChild(subNode);

                // Print the sub-link count and URL
                System.out.println("  " + subLinkCount + ". Adding sub-link to tree: " + subLinkUrl);

                subLinkCount++;
            }

            // Close the new tab
            closeCurrentTab();

            linkCount++;
        }

        // Display the tree structure
        tree.show();
    }

    private boolean shouldSkipLink(String linkUrl) {
        // Add your condition to skip links based on the URL
        return linkUrl == null || linkUrl.contains("mailto:info@rnt.ai");
    }

    private void switchToNewTab() {
        // Switch to the newly opened tab or window
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            try {
                driver.switchTo().window(handle);
                openTabs.add(handle);
                break;
            } catch (Exception e) {
                System.err.println("Error switching to window: " + e.getMessage());
            }
        }
    }

    private void closeCurrentTab() {
        if (openTabs.size() > 1) {
            driver.close();
            openTabs.remove(driver.getWindowHandle());
            switchToNewTab();
        }
    }
}
