package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExtentManager;

public class WorkTimingTest extends BaseClass{
	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		homePage=new HomePage(getDriver());
	}
	
	@Test()
	public void workTimingTest() {
		
		SoftAssert softAssert =getSoftAssert();
		
		ExtentManager.logStep("Logging with Admin credentials");
		loginPage.login(getProperties().getProperty("username"), getProperties().getProperty("password"));
		
		ExtentManager.logStep("The Punch status : "+homePage.getPunchStatusText());
		softAssert.assertNotNull(homePage.getPunchStatusText(), "Punch status is not visible");
		
		ExtentManager.logStep("The Punch time for Today : "+homePage.getPunchTimeForToday());
		softAssert.assertNotNull(homePage.getPunchTimeForToday(), "Today punch time is not visible");
		
		ExtentManager.logStep("The Last Week Date Duration : "+homePage.getLastWeekDateDuration());
		softAssert.assertNotNull(homePage.getLastWeekDateDuration(), "Week Duration is not visible");
		
		ExtentManager.logStep("The Punch time for last week : "+homePage.getPunchTimeForLastWeek());
		softAssert.assertNotNull(homePage.getPunchTimeForLastWeek(), "Last week punch time is not visible");
		
		softAssert.assertAll();
	}
}
