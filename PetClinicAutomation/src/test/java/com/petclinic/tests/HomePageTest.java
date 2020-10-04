package com.petclinic.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petclinic.base.TestBase;
import com.petclinic.pages.HomePage;

public class HomePageTest extends TestBase 
{
		HomePage homePage;
		
        @BeforeClass
        public void setUp()
        {
        	homePage = new HomePage();
        }
        
        @Test
        public void verifyHomePageImageTest()throws Exception
        {
			try {
				Assert.assertTrue(homePage.homePageImageIsDisplayed(), "The HomePage Image is not being displayed.");
			}catch(Exception e) {
				
			}
				
        }
        
        @Test
        public void verifyMenuItemTest() throws Exception{
        	try {
        		homePage.clickOnVeterinarianMenu();
        		
        	}catch(Exception e) {
        		
        	}
        }
        
       
}