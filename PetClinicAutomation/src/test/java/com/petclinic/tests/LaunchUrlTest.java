package com.petclinic.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.petclinic.base.TestBase;


public class LaunchUrlTest extends TestBase {
	
	public LaunchUrlTest(){
		super();
	}
	
	@BeforeClass
	@Parameters({"browser","url"})
	public void setup(String browser, String url) throws InterruptedException{
		initialization(browser,url);
	}
	

	/*Launch URL Test*/	
	@Test
	@Parameters({"browser","url"})
	public void launchUrlTest(String browser, String url) throws NullPointerException, InterruptedException{
		try {
			System.out.println("Launching the Application in "+url+" Environment, On "+browser+" browser.");
		}catch(NullPointerException e) {
			e.getMessage();
		}
	}

}
