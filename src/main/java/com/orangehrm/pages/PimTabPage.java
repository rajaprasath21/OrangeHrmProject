package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class PimTabPage {
	private ActionDriver actionDriver;
	
	
	private By UserIDButton=By.className("oxd-userdropdown-name");
	private By logoutButton=By.xpath("//a[text()='Logout']");
    //private By pimTab=By.xpath("//a[@href='/orangehrm/orangehrm-5.8/web/index.php/pim/viewPimModule']");
	//private By pimTab=By.xpath("//a[@href='/orangehrm/orangehrm-5.8/web/index.php/pim/viewPimModule']//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']");
	//private By employeeName=By.xpath("//input[@placeholder='Type for hints...'])[1]");
	//private By searchButton=By.xpath("//button[@type='submit']");
	//private By empFirstAndMiddleName=By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[3]");
	//private By empLastName=By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[4]");

	/*
	private By pimTab=By.xpath("//span[text()='PIM']");
	private By employeeName=By.xpath("(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active'])[1]//input[@placeholder='Type for hints...']");
	private By searchButton=By.xpath("//button[@type='submit']");
	private By empFirstAndMiddleName=By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[3]");
	private By empLastName=By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[4]");
	*/

	private By pimTab = By.xpath("//span[text()='PIM']");
	private By employeeName = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input");
	private By searchButton = By.xpath("//button[@type='submit']");
	private By empGridFirstAndMiddleName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
	private By empGridLastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]");

	private By addButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By empFirstName=By.xpath("//input[@name='firstName']");
	private By empLastName=By.xpath("//input[@name='lastName']");
	private By saveButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By empId=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	
	//Initialize the ActionDriver object by passing Webdriver instance
	public PimTabPage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver(); 
	}
	
	//Method to Navigate the PIM Tab
	public void ClickOnPIMTab() {
		//actionDriver.click(pimTab);
		actionDriver.clickUsingJS(pimTab);
	}
	
	//Employee Search
	public void employeeSearch(String value) {
		actionDriver.enterText(employeeName, value);
		actionDriver.click(searchButton);
		//BaseClass baseClass=new BaseClass();
		//baseClass.staticWait(3);
		actionDriver.scrollToElement(empGridFirstAndMiddleName);
	}
	//Verify the employee first and middle name
	public boolean verifyEmpFirstAndMiddleName(String empFirstAndMiddleNameFromDB) {
		return actionDriver.compareText(empGridFirstAndMiddleName, empFirstAndMiddleNameFromDB);
	}
	
	//Verify the employee Last name
	public boolean verifyEmpLastName(String empLastNameFromDB) {
		return actionDriver.compareText(empGridLastName, empLastNameFromDB);
	}
	
	//Click the add button
	public void clickAdd() {
		actionDriver.click(addButton);
		
	}
	
	public void enterEmpUsername(String value) {
		actionDriver.enterText(empFirstName, value);
	}
	
	public void enterEmpLastname(String value) {
		actionDriver.enterText(empLastName, value);
	}
	
	public String getEmpId() {
		return actionDriver.getText(empId);
	}
	
	//Click the save button
	public void clickSave() {
		actionDriver.click(saveButton);
		
	}
	
	//Method to perform logout peration
	public void logout() {
		actionDriver.click(UserIDButton);
		actionDriver.click(logoutButton);
	}

}
