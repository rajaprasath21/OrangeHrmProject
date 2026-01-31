package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class LoginPageTest extends BaseClass {
	
	private LoginPage loginpage;
	private HomePage homePage;
	
	@BeforeMethod
	public  void SetupPages() {
		loginpage=new LoginPage(getDriver());
		homePage=new HomePage(getDriver());
	}
	@Test (dataProvider = "validLoginData",dataProviderClass = DataProviders.class)
	public void verifyValidLoginTest(String username, String password) {
	//public void verifyValidLoginTest() {
		//ExtentManager.startTest("Valid Login Test"); // This has been implemented in TestListner class. Method -> onTestStart(ITestResult result)
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginpage.login(username, password);
		//loginpage.login("Admin", "admin123");
		ExtentManager.logStep("Verifying Admin tab is visible or not");
		Assert.assertTrue(homePage.isAdminTabVisible(), "Admin tab should be visible after succesful login");
		ExtentManager.logStep("Validation Successful");
		homePage.logout();
		ExtentManager.logStep("Logged out Successfully!");
		staticWait(2);
	}
	@Test  (dataProvider = "inValidLoginData",dataProviderClass = DataProviders.class)
	public void inValidLoginTest(String username, String password) {
	//public void inValidLoginTest() {
		//ExtentManager.startTest("In valid Login Test"); // This has been implemented in TestListner class. Method -> onTestStart(ITestResult result)
		ExtentManager.logStep("Navigating to Login Page entering username and password");
		loginpage.login(username, password);
		//loginpage.login("admin", "admin");
		String expectedErrorMessage="Invalid credentials";
		Assert.assertTrue(loginpage.verifyErrorMessage(expectedErrorMessage),"Test Failed : invalid error message");
		ExtentManager.logStep("Validation Successful");
	}
	
}
