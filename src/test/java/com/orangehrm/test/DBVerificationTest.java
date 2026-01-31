package com.orangehrm.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.orangehrm.base.BaseClass;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PimTabPage;
import com.orangehrm.utilities.DBConnection;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;

public class DBVerificationTest extends BaseClass{
	
	private LoginPage loginPage;
	private PimTabPage pimTabPage;
	
	@BeforeMethod
	public void setupPages() {
		loginPage=new LoginPage(getDriver());
		//staticWait(5);
		pimTabPage=new PimTabPage(getDriver());
	}
	
	//@Test (dataProvider = "validLoginData",dataProviderClass = DataProviders.class)
	//public void verifyEmployeeNameFromDB(String username, String password ) {
	@Test (dataProvider = "emplVerification",dataProviderClass = DataProviders.class)
	public void verifyEmployeeNameFromDB(String empId, String empName) {
		
		SoftAssert softAssert=getSoftAssert(); 
		
		ExtentManager.logStep("Logging with Admin Credentials");
		loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		
		ExtentManager.logStep("Click on PIM Tab");
		pimTabPage.ClickOnPIMTab();
		
		ExtentManager.logStep("Search for Employee");
		//pimTabPage.employeeSearch("Jaya");
		pimTabPage.employeeSearch(empName);

		
		ExtentManager.logStep("Get the Employee Name from DB");
		//String employee_id="0003";
		
		//Fetch the data into a map
		//Map<String, String> employeeDetails = DBConnection.getEmployeeDetails(employee_id);
		Map<String, String> employeeDetails = DBConnection.getEmployeeDetails(empId);
		
		String emplFirstName = employeeDetails.get("firstName");
		String emplMiddleName = employeeDetails.get("middleName");
		String emplLastName = employeeDetails.get("lastName");
		
		String  emplFirstAndMiddleName=(emplFirstName+" "+emplMiddleName).trim();
		
		//validation for first and middle name
		ExtentManager.logStep("Verify the employee first and middle name");
		softAssert.assertTrue(pimTabPage.verifyEmpFirstAndMiddleName(emplFirstAndMiddleName),"First and Middle name not Matching");
		
		//validation for last name
		ExtentManager.logStep("Verify the employee Last name");
		softAssert.assertTrue(pimTabPage.verifyEmpLastName(emplLastName),"Last name not Matching");
		
		ExtentManager.logStep("DataBase Validation completed");
		
		softAssert.assertAll();
		
	}

}
