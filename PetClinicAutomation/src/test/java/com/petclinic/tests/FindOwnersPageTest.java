package com.petclinic.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petclinic.base.TestBase;
import com.petclinic.pages.AddOwnerFormPage;
import com.petclinic.pages.FindOwnersPage;
import com.petclinic.pages.HomePage;
import com.petclinic.pages.VeterinariansPage;

public class FindOwnersPageTest extends TestBase 
{
		VeterinariansPage veterinariansPage;
		HomePage homePage;
		FindOwnersPage findOwnersPage;
		AddOwnerFormPage addOwnerFormPage;
		
        @BeforeClass
        public void setUp()
        {
        	veterinariansPage = new VeterinariansPage();
        	homePage = new HomePage();
        	findOwnersPage = new FindOwnersPage();
        	addOwnerFormPage = new AddOwnerFormPage();
        }
        
        
       @Test
       public void verifyFindOwnersTest() throws Exception
       {
    	   try {
    		  homePage.clickOnFindOwnersMenu();
    		  findOwnersPage.clickOnButton("Find Owner");
    		  Assert.assertTrue(findOwnersPage.verifyOwnersTableIsDisplayed(), "The OwnersPage is not being displayed.");
    	   }catch(Exception e) {
    		   
    	   }
       }
       
       @Test
       public void verifyAddOwnersTest() throws Exception
       {
    	   try {
    		   findOwnersPage.clickOnAddOwnerBtn();
    		   Assert.assertTrue(addOwnerFormPage.verifyOwnersFormIsDisplayed());
    	   }catch(Exception e) {
    		   
    	   }
       }
}