package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class,data will be taken from the dataprovider, we need to give another parameter if the dataprovider is in another class
	public void verify_loginDDT(String email, String pwd, String exp ) throws InterruptedException
	
	{
		logger.info("**** Starting TC003_LoginDDT test***");
		try
		{
		//Homepage
		HomePage hp= new HomePage(driver);//creating an object for homepage
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);//p is the properties we declared in baseclass
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//my Account
		
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		//if target page is true, then login is successful. false then login is not successful
		
		//validations
		/* Data is valid - Login Successful- test pass- Logout
		                   Login failed - test fail
		  
		  Data is invalid - Login Successful- test fail- Logout
		                     Login failed - test pass
		  		 */
		
		
		if(exp.equalsIgnoreCase("valid"))//data is valid, valid word is taken from excel column
		 {
			
			if(targetPage==true)//login successful
			{
				
				macc.clicklogout();//we need to click logout once successful
				Assert.assertTrue(true);//test passed, Assert always have to be after the action
				
			}
			
			else //data is valid but login is not successful
			
			{
				
				Assert.assertTrue(false);//test failed
			}
			
		}
		//negative test cases
		
		if (exp.equalsIgnoreCase("Invalid"))//data is invalid
		  {
			
			if(targetPage==true) 
			{
				macc.clicklogout();//we need to click logout once successful
			    Assert.assertTrue(false);//test failed because login successful with invalid data
			}
		  
		      else
		        {
			
			Assert.assertTrue(true);//test passed because of invalid data
		         }
		
		}//try ends
			
		 }
		catch(Exception e)
		  {
			
			Assert.fail();
		  }
		Thread.sleep(10);
		logger.info("**** FinishedTC003_LoginDDT test***");
		
		}
		
		
			
	
}
	
	
	
	

