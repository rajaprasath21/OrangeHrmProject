package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class TimePage {
	
	private ActionDriver actionDriver;
	
	private By punchStatus=By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-attendance-card-state']");
	private By attendanceCardActionButton=By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main orangehrm-attendance-card-action']");
	private By textArea=By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");
	private By punchInOutButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	
	
	public TimePage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver();
	}
	
	public String getPunchStatus() {
		return actionDriver.getText(punchStatus);
	}
	
	public void clickCardActionButton() {
		actionDriver.click(attendanceCardActionButton);
	}
	
	public void enterTextArea(String value) {
		actionDriver.enterText(textArea, value);
	}
	
	public void clickPunchInOutButton() {
		actionDriver.click(punchInOutButton);
	}

}
