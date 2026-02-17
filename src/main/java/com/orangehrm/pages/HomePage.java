package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	//Define locators uing by class
	private By adminTab=By.xpath("//span[text()='Admin']");
	private By PIMTab=By.xpath("//span[text()='PIM']");
	private By LeaveTab=By.xpath("//span[text()='Leave']");
	private By TimeTab=By.xpath("//span[text()='Time']");
	private By RecruitmentTab=By.xpath("//span[text()='Recruitment']");
	private By PerformanceTab=By.xpath("//span[text()='Performance']");
	private By MyInfoTab=By.xpath("//span[text()='My Info']");
	private By DashboardTab=By.xpath("//span[text()='Dashboard']");
	private By DirectoryTab=By.xpath("//span[text()='Directory']");
	private By MaintenanceTab=By.xpath("//span[text()='Maintenance']");
	private By ClaimTab=By.xpath("//span[text()='Claim']");
	private By BuzzTab=By.xpath("//span[text()='Buzz']");

	private By UserIDButton=By.className("oxd-userdropdown-name");
	private By logoutButton=By.xpath("//a[text()='Logout']");
	private By OrangeHRMlogo=By.xpath("//div[@class='oxd-brand-banner']//img");


	//Initialize the ActionDriver object by passing Webdriver instance
	/*public HomePage(WebDriver driver) {
		this.actionDriver=new ActionDriver(driver);
	}*/

	//Initialize the ActionDriver object by passing Webdriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver(); 
	}

	//Method to verify if admin tab is visible
	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}

	//Method to verify if orangeHRM log is visible
	public boolean verifyOrangeHRMlogo() {
		return actionDriver.isDisplayed(OrangeHRMlogo);
	}

	//Method to perform logou peration
	public void logout() {
		actionDriver.click(UserIDButton);
		actionDriver.click(logoutButton);
	}

	//Method to verify if PIM tab is visible
	public boolean isPIMTabVisible() {
		return actionDriver.isDisplayed(PIMTab);
	}

	//Method to verify if Leave tab is visible
	public boolean isLeaveTabVisible() {
		return actionDriver.isDisplayed(LeaveTab);
	}

	//Method to verify if Time tab is visible
	public boolean isTimeTabVisible() {
		return actionDriver.isDisplayed(TimeTab);
	}

	//Method to verify if Recruitment tab is visible
	public boolean isRecruitmentTabVisible() {
		return actionDriver.isDisplayed(RecruitmentTab);
	}

	//Method to verify if Performance tab is visible
	public boolean isPerformanceTabVisible() {
		return actionDriver.isDisplayed(PerformanceTab);
	}

	//Method to verify if My Info tab is visible
	public boolean isMyInfoTabVisible() {
		return actionDriver.isDisplayed(MyInfoTab);
	}

	//Method to verify if Dashboard tab is visible
	public boolean isDashboardTabVisible() {
		return actionDriver.isDisplayed(DashboardTab);
	}

	//Method to verify if Directory tab is visible
	public boolean isDirectoryTabVisible() {
		return actionDriver.isDisplayed(DirectoryTab);
	}

	//Method to verify if Maintenance tab is visible
	public boolean isMaintenanceTabVisible() {
		return actionDriver.isDisplayed(MaintenanceTab);
	}

	//Method to verify if Claim tab is visible
	public boolean isClaimTabVisible() {
		return actionDriver.isDisplayed(ClaimTab);
	}

	//Method to verify if Buzz tab is visible
	public boolean isBuzzTabVisible() {
		return actionDriver.isDisplayed(BuzzTab);
	}
}
