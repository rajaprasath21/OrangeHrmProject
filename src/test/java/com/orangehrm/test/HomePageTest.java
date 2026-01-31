package com.orangehrm.test;
 
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class HomePageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(3);
		homePage=new HomePage(getDriver());
	}
	
	@Test(dataProvider = "validLoginData",dataProviderClass = DataProviders.class)
	public void verifyOrangeHRMLogo(String username, String password) {
	//public void verifyOrangeHRMLogo() {
		//ExtentManager.startTest("Home Page verify Logo Test"); // This has been implemented in TestListner class. Method -> onTestStart(ITestResult result)
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginPage.login(username, password);
		//loginPage.login("Admin", "admin123");
		ExtentManager.logStep("Verifying Logo is visible or not");
		Assert.assertTrue(homePage.verifyOrangeHRMlogo(), "Logo is not visible");
		ExtentManager.logStep("Validation Successful");
	}
	
	
}
