package com.petclinic.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.petclinic.base.TestBase;


public class HomePage extends TestBase
{
	
	/*Initialize the PageObjects*/
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}
	
	/* Identify the Elements */
	
	@FindBy(css = ".col-md-12 .img-responsive")
	WebElement homePageImg;
	
	@FindBys(@FindBy(xpath = "//ul[@class = 'nav navbar-nav navbar-right']//a"))
	List<WebElement> menuItems;
	
	public boolean homePageImageIsDisplayed() {
		return homePageImg.isDisplayed();
	}
	
	
	public int getMenuList() {
		return menuItems.size();
	}
	
	public void clickOnMenu(String menuName) {
		for(int i = 0; i<menuItems.size(); i++) {
			if(menuItems.get(i).getText().equalsIgnoreCase(menuName)) {
				menuItems.get(i).click();
				break;
			}
		}
	}
	
	public VeterinariansPage clickOnVeterinarianMenu() {
		clickOnMenu("Veterinarians");
		return new VeterinariansPage();
	}
	
	public FindOwnersPage clickOnFindOwnersMenu() {
		clickOnMenu("Find Owners");
		return new FindOwnersPage();
	}
	
	
	
	
}


