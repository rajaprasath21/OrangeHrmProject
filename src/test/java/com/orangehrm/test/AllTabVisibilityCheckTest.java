package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class AllTabVisibilityCheckTest extends BaseClass{
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		homePage=new HomePage(getDriver());
	}
	
	@Test (dataProvider = "emplVerification",dataProviderClass = DataProviders.class)
	public void verifyVisibilityOfAllTabs(String empId, String empName) {
		
		SoftAssert softAssert=getSoftAssert();
		
		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		
		ExtentManager.logStep("Verifying Logo is visible or not");
		softAssert.assertTrue(homePage.verifyOrangeHRMlogo(), "Logo is not visible");
		
		ExtentManager.logStep("Verifying Admin Tab is visible or not");
		softAssert.assertTrue(homePage.isAdminTabVisible(), "Admin Tab is not visible");
		
		ExtentManager.logStep("Verifying PIM Tab is visible or not");
		softAssert.assertTrue(homePage.isPIMTabVisible(),"PIM Tab is not visible");
		
		ExtentManager.logStep("Verifying Leave Tab is visible or not");
		softAssert.assertTrue(homePage.isLeaveTabVisible(),"Leave Tab is not visible");
		
		ExtentManager.logStep("Verifying Time Tab is visible or not");
		softAssert.assertTrue(homePage.isTimeTabVisible(),"Time Tab is not visible");
		
		ExtentManager.logStep("Verifying Recruitment Tab is visible or not");
		softAssert.assertTrue(homePage.isRecruitmentTabVisible(),"Recruitment Tab is not visible");
		
		ExtentManager.logStep("Verifying Performance Tab is visible or not");
		softAssert.assertTrue(homePage.isPerformanceTabVisible(),"Performance Tab is not visible");
		
		ExtentManager.logStep("Verifying My Info Tab is visible or not");
		softAssert.assertTrue(homePage.isMyInfoTabVisible(),"My Info Tab is not visible");
		
		ExtentManager.logStep("Verifying Dashboard Tab is visible or not");
		softAssert.assertTrue(homePage.isDashboardTabVisible(),"Dashboard Tab is not visible");
		
		ExtentManager.logStep("Verifying Directory Tab is visible or not");
		softAssert.assertTrue(homePage.isDirectoryTabVisible(),"Directory Tab is not visible");
		
		ExtentManager.logStep("Verifying Maintenance Tab is visible or not");
		softAssert.assertTrue(homePage.isMaintenanceTabVisible(),"Maintenance Tab is not visible");
		
		ExtentManager.logStep("Verifying Claim Tab is visible or not");
		softAssert.assertTrue(homePage.isClaimTabVisible(),"Claim Tab is not visible");
		
		ExtentManager.logStep("Verifying Buzz Tab is visible or not");
		softAssert.assertTrue(homePage.isBuzzTabVisible(),"Buzz Tab is not visible");
		
		softAssert.assertAll();
		
	}

}