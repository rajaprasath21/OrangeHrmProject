package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class DummyTest2 extends BaseClass{
	@Test
	public void dummyTest() {
		//ExtentManager.startTest("DummyTest2 Test"); // This has been implemented in TestListner class. Method -> onTestStart(ITestResult result)
		String title=getDriver().getTitle();
		ExtentManager.logStep("Verifying the title");
		assert title.equals("OrangeHRM"):"Test Failed";
		System.out.println(title);
		ExtentManager.logStep("Validation Successful");
	}
}
