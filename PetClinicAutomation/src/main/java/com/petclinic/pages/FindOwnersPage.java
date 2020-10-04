package com.petclinic.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.petclinic.base.TestBase;


public class FindOwnersPage extends TestBase
{
	
	/*Initialize the PageObjects*/
	public FindOwnersPage() {
		PageFactory.initElements(driver, this);
		
	}
	
	/* Identify the Elements */
	
	@FindBys(@FindBy(xpath = "//*[@class = 'btn btn-default']"))
	List<WebElement> ownersButton;
	
	@FindBy(xpath = "//table[@id = 'owners']")
	WebElement OwnersTable;
	
	public void clickOnButton(String Button) {
		for(int i=0;i<ownersButton.size();i++) {
			if(ownersButton.get(i).getText().equalsIgnoreCase(Button)) {
				ownersButton.get(i).click();
				break;
			}
		}
	}
	
	public boolean verifyOwnersTableIsDisplayed() {
		return OwnersTable.isDisplayed();
	}
	
	public AddOwnerFormPage clickOnAddOwnerBtn() {
		clickOnButton("Add Owner");
		return new AddOwnerFormPage();
	}
}


