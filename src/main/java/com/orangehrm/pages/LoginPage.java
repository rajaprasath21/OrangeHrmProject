package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class LoginPage {
	
	private ActionDriver actionDriver;
	
	//Define locators 
	private By userNameField=By.name("username");
	private By passwordField=By.cssSelector("input[type='password']");
	private By loginButton=By.xpath("//button[normalize-space()='Login']");
	private By errorMessage=By.xpath("//p[normalize-space()='Invalid credentials']");
	
	//Initialize the ActionDriver object by passing Webdriver instance
	/*
	public LoginPage(WebDriver driver) {
		this.actionDriver=new ActionDriver(driver);
	}*/
	
	//Initialize the ActionDriver object by passing Webdriver instance
	public LoginPage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver(); 
	}
	
	//Method to perform login 
	public void login(String username,String password) {
		actionDriver.enterText(userNameField, username);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);
		//actionDriver.clickUsingJS(loginButton);
	}
	
	//Method to check if error message is displyed
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}
	
	//Method to get the text from Error message
	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}
	
	//Verify if error is correct ot not
	
	public boolean  verifyErrorMessage(String expectedError) {
		 return actionDriver.compareText(errorMessage, expectedError); //Doubt
	}
	
	
	
	
	
}
