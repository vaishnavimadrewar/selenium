package seleniumDemo.SeleniumDemo;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class vaishnavi {

	
		public static void main(String[] args) throws InterruptedException, AWTException {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Vaishnavi Madrewar\\Downloads\\chromedriver-win32 (2).zip\\chromedriver-win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/index.html");
			System.out.println("Navigating to next URL");
			driver.get("https://www.rabbitandtortoise.com/4-Dec-2023/service-automationsolution.html");

	}

}
