package com.auomation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

//import com.automation.utilities.ExcelReader;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;

public class TestBase {


	public static Properties config = new Properties();
	public static Properties objRepo = new Properties();
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Logger log= Logger.getLogger("devpinoyLogger");
	//public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	
	//public static Logger log= Logger.getLogger(TestBase.class.getName());
	

	/*
	 * Properties file WebDriver initializing
	 */
	
  @BeforeSuite
  public void setUp()
  {
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config Property loaded !!!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				objRepo.load(fis);
				log.debug("OR Property loaded !!!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Launching chrome browser !!!!");
			} else if (config.getProperty("browser").equals("firefox")) {

				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			driver.get( config.getProperty("testurl"));
			log.debug("Test URL loaded !!!!");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("impliciteWait")),TimeUnit.SECONDS);
			
		}
  }
  
   public boolean isElementPresent(By by) {
	   try {
		   driver.findElement(by);
		   return true;
		   
		   
	   }catch(NoSuchElementException e) {
		   return false;
	   }
   }
  @AfterSuite
	public void setDown() {
		if(driver!= null) {
			driver.quit();
		}
		
		log.debug("Browser closed successfully !!!!");
	}
	

}
