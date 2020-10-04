package com.petclinic.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.petclinic.base.TestBase;


public class AddOwnerFormPage extends TestBase
{
	
	/*Initialize the PageObjects*/
	public AddOwnerFormPage() {
		PageFactory.initElements(driver, this);
		
	}
	
	/* Identify the Elements */
	
	@FindBys(@FindBy(xpath = "//*[@class = 'btn btn-default']"))
	List<WebElement> ownersButton;
	
	@FindBy(xpath = "//table[@id = 'owners']")
	WebElement OwnersTable;
	
	@FindBy(id = "firstName")
	WebElement firstName;
	
	@FindBy(id = "lastName")
	WebElement lastName;
	
	@FindBy(id = "address")
	WebElement addressDet;
	
	@FindBy(id = "city")
	WebElement cityName;
	
	@FindBy(id = "telephone")
	WebElement telephoneNo;
	
	@FindBy(className = "btn btn-default")
	WebElement addOwnerBtn;
	
	@FindBys(@FindBy(xpath = "//table[@class = 'table table-striped']/tbody/tr/td"))
	List<WebElement> addedOwnerDetails;
	
	public boolean verifyOwnersFormIsDisplayed() {
		return OwnersTable.isDisplayed();
	}
	
	public void fillAddOwnersForm(String fName, String lName, String address, String city, String telephone) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		addressDet.sendKeys(address);
		cityName.sendKeys(city);
		telephoneNo.sendKeys(telephone);
		addOwnerBtn.click();
	}
	
		
	public boolean verifyName(String fName, String lName) {
		String name = fName+" "+lName;
		return addedOwnerDetails.get(0).getText().equals(name);
	}
	
	public boolean verifyAddress(String address) {
		return addedOwnerDetails.get(1).getText().equals(address);
	}
	
	public boolean verifyCity(String city) {
		return addedOwnerDetails.get(2).getText().equals(city);
	}
	
	public boolean verifyTelephone(String telephone) {
		return addedOwnerDetails.get(1).getText().equals(telephone);
	}
	
}


