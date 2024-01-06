package seleniumDemo.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class WebPageNode {
    String data;
    List<WebPageNode> children;

    public WebPageNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(WebPageNode childNode) {
        this.children.add(childNode);
    }

    public List<WebPageNode> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }
}

class WebPageTree {
    WebPageNode root;

    public WebPageTree(String data) {
        this.root = new WebPageNode(data);
    }

    public WebPageNode getRoot() {
        return root;
    }

    public void display() {
        WebPageTreePrinter treePrinter = new WebPageTreePrinter();
        treePrinter.printTree(this);
    }
}

class WebPageTreePrinter {

    public void printTree(WebPageTree tree) {
        printTree(tree.getRoot(), "", true);
    }

    private void printTree(WebPageNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node);

            List<WebPageNode> children = node.getChildren();
            for (int i = 0; i < children.size(); i++) {
                WebPageNode child = children.get(i);
                boolean isLast = (i == children.size() - 1);
                printTree(child, prefix + (isLeft ? "│   " : "    ") + (isLast ? "    " : "│   "), false);
            }
        }
    }
}

public class WebPageLinkExplorer {
    public static WebDriver driver;
    public static String originalWindowHandle;
    public static Set<String> openTabs;

    @BeforeMethod
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openTabs = new HashSet<>();
    }

    @AfterMethod
    public void closeDriver() {
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
    public void exploreWebPageLinks() {
        WebPageTree webPageTree = new WebPageTree("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");

        // Navigate to the specified URL
        driver.get(webPageTree.getRoot().getData());

        // Initialize the originalWindowHandle
        originalWindowHandle = driver.getWindowHandle();
        openTabs.add(originalWindowHandle);

        // Create a root node for the tree
        WebPageNode rootNode = new WebPageNode(webPageTree.getRoot().getData());
        webPageTree.getRoot().addChild(rootNode);

        // Explore links on the root page
        exploreLinks(rootNode);

        // Display the tree structure
        webPageTree.display();
    }

    private void exploreLinks(WebPageNode parentNode) {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        int linkCount = 1;
        for (WebElement link : links) {
            try {
                // Attempt to re-find the link element to handle StaleElementReferenceException
            	// Skip if link is not clickable
            	if (!link.isEnabled() || !link.isDisplayed()) {
            	    System.out.println(linkCount + ". Skipping link: " + link.getAttribute("href"));
            	    continue; // Skip to the next iteration
            	}

            	String linkUrl = link.getAttribute("href");

                // Skip if linkUrl is null
                if (linkUrl == null) {
                    System.out.println(linkCount + ". Skipping link with null URL");
                    continue; // Skip to the next iteration
                }

                // Add your condition to skip links here
                if (shouldSkipLink(linkUrl)) {
                    System.out.println(linkCount + ". Skipping link: " + linkUrl);
                    continue; // Skip to the next iteration
                }

                // Create a tree node for the current link
                WebPageNode currentNode = new WebPageNode(linkUrl);

                // Add the current node to the parent node
                parentNode.addChild(currentNode);

                // Print the link count and URL
                System.out.println(linkCount + ". Adding link to tree: " + linkUrl);

                // Open link in a new tab
                ((JavascriptExecutor) driver).executeScript("window.open()");
                switchToNewTab();

                // Navigate to the link URL
                driver.get(linkUrl);

                // Find sub-nodes on the new webpage and process them

                // Close the current tab
                closeCurrentTab();

                linkCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean shouldSkipLink(String linkUrl) {
        // Add your conditions to skip links based on the URL
        // Example: Skip links containing "mailto:info@rnt.ai", "example.com", or "example2.com"
        return linkUrl != null && (linkUrl.contains("mailto:info@rnt.ai") || linkUrl.contains("about:blank"));
    }

    private void switchToNewTab() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!openTabs.contains(handle)) {
                try {
                    driver.switchTo().window(handle);
                    openTabs.add(handle);
                } catch (NoSuchWindowException e) {
                    System.out.println("Window with handle " + handle + " is already closed.");
                }
                break;
            }
        }
    }


    private void closeCurrentTab() {
        try {
            if (openTabs.size() > 1 && driver.getWindowHandles().size() > 0) {
                driver.close();
                openTabs.remove(driver.getWindowHandle());
                switchToNewTab(); 
            }
        } 
        catch (NoSuchWindowException e) {
            System.out.println("Current window is already closed.");
        }
    }

}