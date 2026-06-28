package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PimTabPage;
import com.orangehrm.utilities.ExcelWriterReaderUtility;
import com.orangehrm.utilities.ExtentManager;

public class UpdateEmployeeDetailTest extends BaseClass {
	
	private LoginPage loginPage;
	private PimTabPage pimTabPage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		pimTabPage=new PimTabPage(getDriver());
	}
	
	@Test
	public void updateEmployeeDetail() {
		SoftAssert softAssert=getSoftAssert(); 
		
		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		
		ExtentManager.logStep("Click on PIM Tab");
		pimTabPage.ClickOnPIMTab();
		
		ExtentManager.logStep("Search for Employee");
		pimTabPage.employeeIdSearch(ExcelWriterReaderUtility.getValueByKey("EmpId"));
		staticWait(2);
		
		pimTabPage.clickGirdRecord();
		staticWait(3);
		
		pimTabPage.selectNationality("Indian");
		pimTabPage.selectMaritalStatus("Single");
		pimTabPage.clearDateOfBirth();
		pimTabPage.selectGender("male");
		pimTabPage.enterDateOfBirth("2000-01-01");
		pimTabPage.clickSave();
		String message = pimTabPage.getMessage();
		if (message.contains("Successfully Updated")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Message : "+message, "Message: "+message);
			softAssert.assertTrue(true,"Empolyee details are updated");
		}else {
			ExtentManager.logFailure(getDriver(), "Message not matched with actual", "Message not matched with actual");
			softAssert.assertTrue(false,"Message not matched with actual");
		}
		staticWait(3);
		softAssert.assertAll();
		
	}
	
}
