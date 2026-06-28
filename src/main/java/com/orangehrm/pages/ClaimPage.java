package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class ClaimPage {
	private ActionDriver actionDriver;
	
	private By ClaimTab=By.xpath("//span[text()='Claim']");
	private By assignClaim=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By employeeName=By.xpath("//div[@class='oxd-autocomplete-text-input--before']/following-sibling::input");
	
	private By event=By.xpath("(//div[@class='oxd-select-text-input'])[1]");
	private By currency=By.xpath("(//div[@class='oxd-select-text-input'])[2]");
	private By createBtn=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By addExpense=By.xpath("(//button[@class='oxd-button oxd-button--medium oxd-button--text'])[1]");
	private By selectExpenseType=By.xpath("//div[@class='oxd-select-text-input']");
	private By date=By.xpath("(//input[@class='oxd-input oxd-input--active'])[7]");
	private By amount=By.xpath("//label[normalize-space()='Amount']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By saveBtn=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By submitBtn=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-sm-button']");
	private By backBtn=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-sm-button']");
	
	private By gridEmployeeName=By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
	
	private By message=By.id("oxd-toaster_1");
	
	public ClaimPage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver(); 
	}
	
	public void clickClaimTab() {
		 actionDriver.click(ClaimTab);
	}
	
	public void clickAssignClaim() {
		actionDriver.click(assignClaim);
	}
	
	public void enterEmployeeName(String firstName,String lastName) {
		String empName=firstName+" "+lastName;
		actionDriver.enterText(employeeName, empName);
	}
	
	public void selectTheEmployeeName(String fullName) {
		By selEmpName=By.xpath("//div[@role='listbox']//div[normalize-space()='"+fullName+"']");
		actionDriver.click(selEmpName);
	}
	//div[@role='listbox']//div[normalize-space()='John Smith']
	
	public void createClaim(String fullName,String eventName, String currencyName) {
		actionDriver.enterText(employeeName, fullName);
		selectTheEmployeeName(fullName);
		actionDriver.selectListBoxOptions(event, eventName);
		actionDriver.selectListBoxOptions(currency, currencyName);
		actionDriver.click(createBtn);
	}
	
	public void clickAddExpense() {
		actionDriver.click(addExpense);
	}
	
	public void enterExpenses(String ExpenseType,String currentDate, long enterAmount) {
		actionDriver.selectListBoxOptions(selectExpenseType, ExpenseType);
		actionDriver.enterText(date, currentDate);
		actionDriver.enterText(amount, String.valueOf(enterAmount));
		actionDriver.click(saveBtn);
	}
	
	public void clickSubmitBtn() {
		actionDriver.click(submitBtn);
	}
	
	public void clickBackBtn() {
		actionDriver.click(backBtn);
	}
	
	public String getMessage() {
		return actionDriver.getText(message);
	}
	
	public boolean VerifyTheGirdEmployeeName(String fullName) {
		return actionDriver.compareText(gridEmployeeName, fullName);
	}

}
