package com.petclinic.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petclinic.base.TestBase;
import com.petclinic.pages.AddOwnerFormPage;
import com.petclinic.pages.FindOwnersPage;
import com.petclinic.pages.HomePage;
import com.petclinic.pages.VeterinariansPage;

public class AddOwnersFormPageTest extends TestBase 
{
		VeterinariansPage veterinariansPage;
		HomePage homePage;
		FindOwnersPage findOwnersPage;
		AddOwnerFormPage addOwnerFormPage;
		public String fName = "Muthukumaran";
		public String lName = "M";
		public String address = "1st Cross";
		public String city = "New York";
		public String telephone = "+1 987 123 4567";
		
		
        @BeforeClass
        public void setUp()
        {
        	veterinariansPage = new VeterinariansPage();
        	homePage = new HomePage();
        	findOwnersPage = new FindOwnersPage();
        	addOwnerFormPage = new AddOwnerFormPage();
        }
        
        
       @Test
       public void verifyAddOwnersFormPageTest() throws Exception
       {
    	   try {
    		   addOwnerFormPage.fillAddOwnersForm(fName,lName, address, city, telephone);
    		   homePage.clickOnFindOwnersMenu();
    		   Assert.assertTrue(addOwnerFormPage.verifyName(fName, lName));
    		   Assert.assertTrue(addOwnerFormPage.verifyAddress(address));
    		   Assert.assertTrue(addOwnerFormPage.verifyCity(city));
    		   Assert.assertTrue(addOwnerFormPage.verifyTelephone(telephone));
    		   
    	   }catch(Exception e) {
    		   
    	   }
       }
       
      
}