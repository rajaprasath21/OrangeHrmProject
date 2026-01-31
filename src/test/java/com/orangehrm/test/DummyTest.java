package com.orangehrm.test;

//import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class DummyTest extends BaseClass{
	@Test
	public void dummyTest() {
		//ExtentManager.startTest("DummyTest1 Test"); // This has been implemented in TestListner class. Method -> onTestStart(ITestResult result)
		String title=getDriver().getTitle();
		ExtentManager.logStep("Verifying the title");
		assert title.equals("OrangeHRM"):"Test Failed";
		System.out.println(title);
		//ExtentManager.logSkip("This case is skipped");
		//throw new SkipException("Skipping the test as part of Testting");
		ExtentManager.logStep("Validation Successful");
	}
}
