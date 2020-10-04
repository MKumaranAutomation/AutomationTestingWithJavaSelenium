package com.petclinic.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.petclinic.base.TestBase;


public class VeterinariansPage extends TestBase
{
	
	/*Initialize the PageObjects*/
	public VeterinariansPage() {
		PageFactory.initElements(driver, this);
		
	}
	
	/* Identify the Elements */
	
	@FindBy(id = "vets")
	WebElement vetsTable;
	
	public boolean VeterinariansTable() {
		return vetsTable.isDisplayed();
	}
	
		
}


