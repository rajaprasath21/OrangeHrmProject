package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.LoggerManager;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	//private static final Logger logger=LogManager.getFormatterLogger(BaseClass.class);
	public static final Logger logger=LoggerManager.getLogger(BaseClass.class);
	protected static Properties properties;
	//protected static WebDriver driver;
	//private static ActionDriver actionDriver;
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static ThreadLocal<ActionDriver> actionDriver=new ThreadLocal<>();
	
	protected ThreadLocal<SoftAssert> softAssert= ThreadLocal.withInitial(SoftAssert::new);
	
	//Getter Method for SoftAsset
	public SoftAssert getSoftAssert() {
		return softAssert.get();
	}

	//Load the configuration file -> F:\Downloads\eclipse-workspace\orangeHRMProject\src\main\resources\config.properties
	@BeforeSuite
	public void loadConfig() throws IOException {

		properties=new Properties();
		//FileInputStream fis=new FileInputStream("src\\main\\resources\\config.properties");
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		properties.load(fis);
		logger.info("Config.properties file loaded");
		
		//Start the Extent Report  --> This has been implemented in TestListner class. Method -> onStart(ITestContext context)
		//ExtentManager.getReporter();
	}
	
	public static Properties getProperties() {
		return properties;
	}
	/*
	public WebDriver getDriver() {
		return driver;
	}
	
	*/
	
	//Getter method for WebDriver
	public static WebDriver getDriver() {
		if(driver.get()==null) {
			logger.info("WebDriver is not initialized");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		return driver.get();
	}
	
	//Getter method for ActionDriver
	public static ActionDriver getActionDriver() {
		if(actionDriver.get()==null) {
			logger.info("WebDriver is not initialized");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		return actionDriver.get();
	}
	
	//Setter method for WebDriver
	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver=driver;
	}
	
	//This method is called the Driver methods : launchBrowser, maximize & 
	@BeforeMethod
	public synchronized void setup()  {
		logger.info("Setting up WebDriver for:"+this.getClass().getSimpleName());
		launchBrowser();
		configureBrower();
		staticWait(2);
		logger.info("WebDriver Initialized and Browser maximaized.");
		
		/*
		//Initialize the actionDriver only once
		if(actionDriver==null) {
			actionDriver=new ActionDriver(driver);
			logger.info("ActionDriver instance is created."+Thread.currentThread().getId());
		}
		*/
		
		//Initialize ActionDriver for the current thread
		actionDriver.set(new ActionDriver(getDriver()));
		logger.info("ActionDriver Initialized for thread: "+Thread.currentThread().getId());

	}

	//Initialize the WebDriver based on browser defined in config.properties file
	private synchronized void launchBrowser() {
		String browser=properties.getProperty("browser");

		switch (browser.toLowerCase()) {
		case "chrome": {
			ChromeOptions options=new ChromeOptions();
			/*
			options.addArguments("--headless"); //Run in chrome headless mode
			options.addArguments("--disable-gpu"); //Disable GPU for headless mode
			options.addArguments("window-size=1980,1080"); //Set window size
			options.addArguments("--disable-notifications"); //Disable browser notifications
			options.addArguments("--no-sandbox"); //Required for some CI/CD environments
			options.addArguments("--disable-dev-shm-usage"); //Resolve issues in resources
			*/
			
			// ✅ Use new headless mode (VERY IMPORTANT)
			//options.addArguments("--headless");
			//options.addArguments("--guest");

			// ✅ Explicitly set window size
			//options.addArguments("--window-size=1920,1080");

			// ✅ Stability flags
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-notifications");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			
			//WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver();
			driver.set(new ChromeDriver());  //New changes as per Threadlocal
			//Register WebDiver for current Thread for ExtentReporting
			ExtentManager.registerDriver(getDriver());
			logger.info("ChromeDriver Instance created.");
			break;
		}
		case "edge": {
			EdgeOptions options=new EdgeOptions();
			options.addArguments("--headless"); //Run in chrome headless mode
			options.addArguments("--disable-gpu"); //Disable GPU for headless mode
			//options.addArguments("window-size=1980,1080"); //Set window size
			options.addArguments("--disable-notifications"); //Disable popup notifications
			options.addArguments("--no-sandbox"); //Required for some CI/CD environments
			options.addArguments("--disable-dev-shm-usage"); //Resolve issues in resources
			
			//WebDriverManager.edgedriver().setup();
			//driver=new EdgeDriver();
			driver.set(new EdgeDriver(options));  //New changes as per Threadlocal
			//Register WebDiver for current Thread for ExtentReporting
			ExtentManager.registerDriver(getDriver());
			logger.info("EdgeDriver Instance created.");
			break;
		}
		case "firefox": {
			FirefoxOptions options=new FirefoxOptions();
			options.addArguments("--headless"); //Run in chrome headless mode
			options.addArguments("--disable-gpu"); //Disable GPU for headless mode
			//options.addArguments("window-size=1980,1080"); //Set window size
			options.addArguments("--width=1920"); //Set browser width
			options.addArguments("--height=1080"); //Set browser height
			options.addArguments("--disable-notifications"); //Disable browser notifications
			options.addArguments("--no-sandbox"); //Required for some CI/CD environments
			options.addArguments("--disable-dev-shm-usage"); //Resolve issues in resources
			
			//WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();
			driver.set(new FirefoxDriver(options));  //New changes as per Threadlocal
			//Register WebDiver for current Thread for ExtentReporting
			ExtentManager.registerDriver(getDriver());
			logger.info("FirefoxDriver Instance created.");
			break;
		}
		default:
			throw new IllegalArgumentException("Browser not supported"+browser);
		}
	}

	//Configure the Browser settings such as Implicit wait, Maximize the browser and loading the URL

	private void configureBrower() {
		//Implicit wait
		int implicitWait=Integer.parseInt(properties.getProperty("implicitWait"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		//Maximize the browser
		getDriver().manage().window().maximize();

		//Navigate to URL
		String url;
		try {
			url = properties.getProperty("url");
			getDriver().get(url);
		} catch (Exception e) {
			logger.error("Failed to navigate the URL"+e.getMessage());
		}

	}

	//close the all browser windows
	/*
	@AfterMethod
	public synchronized void tearDown() {
		if(getDriver()!=null) {
			try {
				getDriver().quit();
			} catch (Exception e) {
				logger.error("Failed to navigate the URL"+e.getMessage());
			}
		}
		logger.info("WebDriver Instance is closed.");
		//driver=null;
		//actionDriver=null;
		driver.remove();
		actionDriver.remove();
	}
	*/
	
	//close the all browser windows
	@AfterMethod(alwaysRun = true)
	public synchronized void tearDown() 
	{
	    try {
	        if (driver.get() != null) {
	        	driver.get().quit();
	            logger.info("WebDriver Instance is closed.");
	        }
	    } catch (Exception e) {
	        logger.error("Error during driver quit", e);
	    } finally {
	        driver.remove();
	        actionDriver.remove();
	        //ExtentManager.logStep("Browser closed");
	        //end the Extent Report
	        //ExtentManager.endTest();  --> This has been implemented in TestListner class. Method -> onFinish(ITestResult result)
	    }
	}
	
	//It will wait for given time (seconds)
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

}
