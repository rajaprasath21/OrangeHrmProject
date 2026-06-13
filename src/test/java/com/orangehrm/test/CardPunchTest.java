package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.TimePage;
import com.orangehrm.utilities.ExtentManager;

public class CardPunchTest extends BaseClass{
	
	
	private LoginPage loginPage;
	private TimePage timePage;
	private SoftAssert softAssert;
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		timePage=new TimePage(getDriver());
	}
	
	@Test
	public void cardPunchTest() {
		
		softAssert=getSoftAssert(); 

		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		staticWait(2);
		ExtentManager.logStep("Checking the current punch status");
		
		String punchStatus = timePage.getPunchStatus().trim();
		softAssert.assertTrue(punchStatus!=null && !punchStatus.isEmpty());
		
		switch(punchStatus) {
		
		case "Punched In":
			punchedIn();
			break;
		case "Punched Out":
			punchedOut();
			break;
			
		default: 
			throw new IllegalArgumentException("Invalid Punch Status" + punchStatus);
		}
		softAssert.assertAll();
	}
	
	private void punchedOut() {
		
		timePage.clickCardActionButton();
		staticWait(1);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("/attendance/punchIn"),"Attendance screen not loaded");
		if(getDriver().getCurrentUrl().contains("/attendance/punchIn")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Attendance screen loaded with In", "Attendance screen loaded");
		}
		
		ExtentManager.logStep("Entering the text area for PunchIn");
		timePage.enterTextArea("The PunchIn Text entered");
		
		timePage.clickPunchInOutButton();
		
		staticWait(6);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("attendance/punchOut"),"Punch In not updated");
		if(getDriver().getCurrentUrl().contains("attendance/punchOut")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Punch In updated successfully", "Punched In");
		}
		
		timePage.enterTextArea("The PunchOut Text entered");
		timePage.clickPunchInOutButton();
		
		staticWait(6);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("attendance/punchIn"),"Punch Out not updated");
		if(getDriver().getCurrentUrl().contains("attendance/punchIn")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Punch Out updated successfully", "Punched Out");
		}
	}

	private void punchedIn() {
		
		timePage.clickCardActionButton();
		staticWait(1);
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("/attendance/punchOut"),"Attendance screen not loaded");
		if(getDriver().getCurrentUrl().contains("/attendance/punchOut")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Attendance screen loaded with Out", "Attendance screen loaded");
		}
		
		ExtentManager.logStep("Entering the text area for PunchOut");
		timePage.clickPunchInOutButton();
		
		staticWait(6);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("attendance/punchIn"),"Punch Out not updated");
		if(getDriver().getCurrentUrl().contains("attendance/punchIn")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Punch Out updated successfully", "Punched Out");
		}
		
		timePage.enterTextArea("The PunchIn Text entered");
		
		timePage.clickPunchInOutButton();
		
		staticWait(6);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("attendance/punchOut"),"Punch In not updated");
		if(getDriver().getCurrentUrl().contains("attendance/punchOut")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Punch In updated successfully", "Punched In");
		}
		timePage.enterTextArea("The PunchOut Text entered");
		timePage.clickPunchInOutButton();
		
		staticWait(6);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("attendance/punchIn"),"Punch Out not updated");
		if(getDriver().getCurrentUrl().contains("attendance/punchIn")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Punch Out updated successfully", "Punched Out");
		}	
	}
}
