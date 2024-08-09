package automation.mavenJava;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserLunchingTest {

	static WebDriver driver;

	@BeforeClass
	@Parameters("browserName")
	void lunchingBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--disable-notifications");
			driver = new ChromeDriver(op);
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

	}
	
	@Test (priority = 0)
	void verifyingTitleOfPage() {
		System.out.println("**** " + driver.getTitle() + " ****");
		System.out.println("**** " + driver.getCurrentUrl() + " ****");
	}
	
	@Test (priority = 1)
	void handleChildWindow() {
		driver.findElement(By.id("openwindow")).sendKeys(Keys.ENTER);
		Set <String> ids = driver.getWindowHandles();
			
		Iterator <String> it = ids.iterator();
		String parentId = it.next();
		String childId = it.next();
		
		driver.switchTo().window(childId);
		driver.manage().window().maximize();
		
		System.out.println("**** "+driver.getTitle()+" ****");
		System.out.println("**** "+driver.getCurrentUrl()+" ****");
		driver.close();
		driver.switchTo().window(parentId);
		
		
		
		
	}
	
	
	
	@AfterTest
	void closingBrowser() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}
	
}
