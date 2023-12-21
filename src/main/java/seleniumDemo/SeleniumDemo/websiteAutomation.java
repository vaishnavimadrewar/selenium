package seleniumDemo.SeleniumDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class websiteAutomation {

	
		

		
		    public static void main(String[] args) {
		        // Set the path to the ChromeDriver executable
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
                 WebDriver driver = new ChromeDriver();
		        // Create a pool of execution agents
		        ExecutorService executorService = Executors.newFixedThreadPool(5);

		        // Define the root website URL
		       driver.get("http://3.13.184.127:8080/rnt/rntlogin.do");
		        driver.findElement(By.className("form-control")).sendKeys("VM1494");// Username

				driver.findElement(By.id("password-field")).sendKeys("Vaishnavi@2000");// Password
				driver.findElement(By.xpath("/html/body/div/div/form/button")).click();// Click on login

		        // Define relevant input data combinations
		        String[] inputData = {"vm1494", "vaishnavi@2000"};

		        // Loop through each input data combination
		        for (String data : inputData) {
		            // Submit a task for each input data combination to the executor service
		           // executorService.submit(() -> executeScenario(rootWebsite, data));
		        }

		        // Shutdown the executor service
		        executorService.shutdown();
		    }

		    // Method to execute a scenario for a given input data on the website
		    private static void executeScenario(String rootWebsite, String inputData) {
		        // Create a new instance of the ChromeDriver
		        WebDriver driver = new ChromeDriver();

		        try {
		            // Navigate to the root website
		            driver.get(rootWebsite);

		            // Perform actions on the website based on the specific scenario and input data
		            // For example, click buttons, fill forms, navigate through pages, etc.

		            // Add logic to report any issues, save evidence, and calculate a score

		            // Simulate a delay to represent the execution time
		            Thread.sleep(2000);

		            // Print a message indicating successful execution
		            System.out.println("Scenario executed successfully for input: " + inputData);
		        } catch (Exception e) {
		            // Handle exceptions, report issues, and save evidence
		            e.printStackTrace();
		        } finally {
		            // Close the browser window
		            driver.quit();
		        }
		    }
		    
		
	}


