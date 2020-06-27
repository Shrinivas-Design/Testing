package com.automation.testCases;

import org.junit.Assert;
import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.annotations.Test;

import com.auomation.base.TestBase;

public class ManagerLoginTest  extends TestBase{

	@Test
	public void bankManagerLogin() throws InterruptedException {
	 log.info("inside the Login Test !!!!");
		driver.findElement(By.xpath(objRepo.getProperty("managerLogin"))).click();
		Thread.sleep(2000);
		Assert.assertTrue("Login not successful",isElementPresent(By.xpath(objRepo.getProperty("addCustomer"))));
	   
		log.info("Login Test Executed..!!!!");
		
	}
	
}
