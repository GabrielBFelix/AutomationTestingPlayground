package base;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader configFr;
	public static FileReader locatorsFr;
	public static WebDriverWait wait;

	public static Logger log = LogManager.getLogger();
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			String userDir = System.getProperty("user.dir");

			try {
				configFr = new FileReader(userDir + "\\src\\test\\resources\\configFiles\\config.properties");
				locatorsFr = new FileReader(userDir + "\\src\\test\\resources\\configFiles\\locators.properties");
				prop.load(configFr);
				log.debug("Config file loaded");
				loc.load(locatorsFr);
				log.debug("Locators file loaded");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("Chrome Launched");
		}

		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.debug("Firefox Launched");
		}

		else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.debug("Edge Launched");
		}

		driver.get(prop.getProperty("testurl"));
		log.debug("Navigated to: " + prop.getProperty("testurl"));
		driver.manage().window().maximize();
		log.debug("Browser window maximized");
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit.wait"))));
		log.debug("Implicit wait time set");
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		log.debug("WebDriverWait with 5 seconds set");
	}

	@AfterMethod
	public void reset() {
		driver.get(prop.getProperty("testurl"));
		log.debug("Test Reset successfully executed");
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null)
			driver.quit();

		log.debug("Teardown successfully executed");
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			log.debug("Element " + by + " was present");
			return true;
		} catch (NoSuchElementException e) {
			log.debug("Element " + by + " wasn't present");
			return false;
		}
	}

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(loc.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(loc.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(loc.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Clicking on : " + locator);
		log.debug("Clicking on : " + locator);
	}

	public void type(String locator, String text) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(loc.getProperty(locator))).sendKeys(text);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(loc.getProperty(locator))).sendKeys(text);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(loc.getProperty(locator))).sendKeys(text);
		}

		test.log(LogStatus.INFO, "Typing on : " + locator + "entered value as " + text);
		log.debug("Typing on : " + locator + "entered value as " + text);
	}
}
