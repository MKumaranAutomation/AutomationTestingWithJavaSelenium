package com.petclinic.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petclinic.base.TestBase;
import com.petclinic.pages.FindOwnersPage;
import com.petclinic.pages.HomePage;
import com.petclinic.pages.VeterinariansPage;

public class VeterinariansPageTest extends TestBase 
{
		VeterinariansPage veterinariansPage;
		HomePage homePage;
		FindOwnersPage findOwnersPage;
		
        @BeforeClass
        public void setUp()
        {
        	veterinariansPage = new VeterinariansPage();
        	homePage = new HomePage();
        	findOwnersPage = new FindOwnersPage();
        }
        
        @Test
        public void verifyVeterinariansTest()throws Exception
        {
			try {
				Assert.assertTrue(veterinariansPage.VeterinariansTable());
			}catch(Exception e) {
			}
        }
        
       @Test
       public void verifyFindOwnersTest() throws Exception
       {
    	   try {
    		  homePage.clickOnFindOwnersMenu();
    	   }catch(Exception e) {
    		   
    	   }
       }
}