package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PimTabPage;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.RandomGeneration;

public class AddEmployeeTest extends BaseClass{

	private LoginPage loginPage;
	private PimTabPage pimTabPage;

	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		pimTabPage=new PimTabPage(getDriver());
	}
	@Test
	public void addEmployeeTest() {
		
		SoftAssert softAssert=getSoftAssert(); 

		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

		ExtentManager.logStep("Click on PIM Tab");
		pimTabPage.ClickOnPIMTab();
		
		ExtentManager.logStep("Click the Add Button");
		pimTabPage.clickAdd();
		
		staticWait(3);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("/pim/addEmployee"),"Add Employee tab not loaded");
		
		if(getDriver().getCurrentUrl().contains("/pim/addEmployee")) {
			//ExtentManager.logStep("Add Employee tab loaded");
			ExtentManager.logStepWithScreenshot(getDriver(), "Add Employee tab loaded", "Employee tab");
		}
		
		pimTabPage.enterEmpUsername(RandomGeneration.generateString(10));
		pimTabPage.enterEmpLastname(RandomGeneration.generateString(10));
		pimTabPage.clickSave();
		staticWait(5);
		
		softAssert.assertTrue(getDriver().getCurrentUrl().contains("/pim/viewPersonalDetails/empNumber"),"Employee ID not created");
		if(getDriver().getCurrentUrl().contains("/pim/viewPersonalDetails/empNumber")) {
			ExtentManager.logStepWithScreenshot(getDriver(), "Employee created successfully", "Employee ID Created");
		}
		
		String empId = pimTabPage.getEmpId();
		softAssert.assertTrue(empId!=null && !empId.isEmpty(),"The employee id was not populated");
		if(empId!=null && !empId.isEmpty()) {
			ExtentManager.logStep("The Employee ID is : "+pimTabPage.getEmpId());
		}
		
		softAssert.assertAll();
	}


}
