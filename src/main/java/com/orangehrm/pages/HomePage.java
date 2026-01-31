package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {
	
	private ActionDriver actionDriver;
	
	//Define locators uing by class
	private By adminTab=By.xpath("//span[text()='Admin']");
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
}
