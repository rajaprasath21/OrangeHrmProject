package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.ClaimPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExcelWriterReaderUtility;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.RandomGeneration;

public class CreateClaimTest extends BaseClass {

	private LoginPage loginPage;
	private ClaimPage claimPage;

	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		claimPage=new ClaimPage(getDriver());
	}
	
	@Test
	public void createClaimTest() {
		SoftAssert softAssert=getSoftAssert(); 
		
		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		
		ExtentManager.logStep("Click on Claim Tab");
		claimPage.clickClaimTab();
		
		claimPage.clickAssignClaim();
		
		String firstName = ExcelWriterReaderUtility.getValueByKey("EmpFirstname");
		String lastName = ExcelWriterReaderUtility.getValueByKey("EmpLastname");
		String EmpFullName=firstName+" "+lastName;
		
		ExtentManager.logStep("Enter the Employee Name: "+EmpFullName);
		claimPage.createClaim(EmpFullName, "Event1", "Indian Rupee");
		
		String message = claimPage.getMessage();
		
		if (message.contains("Success")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Claim Request created : "+message, "Claim Request created: "+message);
			softAssert.assertTrue(true,"Claim request created");
		}else {
			ExtentManager.logFailure(getDriver(), "Message not matched with actual", "Message not matched with actual");
			softAssert.assertTrue(false,"Claim request not created");
		}
		
		claimPage.clickAddExpense();
		staticWait(2);
		claimPage.enterExpenses("Expense1", RandomGeneration.getCurrentDate(), RandomGeneration.generateNumeric(5));
		
		if (message.contains("Success")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Expense updated : "+message, "Expense updated: "+message);
			softAssert.assertTrue(true,"Expense updated");
		}else {
			ExtentManager.logFailure(getDriver(), "Message not matched with actual", "Message not matched with actual");
			softAssert.assertTrue(false,"Expense not created");
		}
		
		staticWait(5);
		
		claimPage.clickSubmitBtn();
		
		if (message.contains("Success")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Claim assigned : "+message, "Claim assigned: "+message);
			softAssert.assertTrue(true,"Expense updated");
		}else {
			ExtentManager.logFailure(getDriver(), "Message not matched with actual", "Message not matched with actual");
			softAssert.assertTrue(false,"Claim not assigned");
		}
		
		staticWait(2);
		claimPage.clickBackBtn();
		
		staticWait(3);
		
		ExtentManager.logStep("Verify the employee name");
		if (claimPage.VerifyTheGirdEmployeeName(EmpFullName)) {
			softAssert.assertTrue(true,"Grid Employee name not matched");
		}else {
			ExtentManager.logFailure(getDriver(), "Grid Employee name not matched", "Grid Employee name not matched");
			softAssert.assertTrue(false,"Grid Employee name not matched");
		}	
		softAssert.assertAll();
	}

}
