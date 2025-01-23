package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login()
	
	{
	   logger.info("....Starting TC002_LoginTest....");	
	try {
	//Homepage
	HomePage hp= new HomePage(driver);//creating an object for homepage
	hp.clickMyAccount();
	hp.clickLogin();
	
	//Login Page
	LoginPage lp= new LoginPage(driver);
	lp.setEmail(p.getProperty("email"));//p is the properties we declared in baseclass
	lp.setPassword(p.getProperty("password"));
	lp.clickLogin();
	
	//my Account
	
	MyAccountPage macc= new MyAccountPage(driver);
	boolean targetPage = macc.isMyAccountPageExists();
	
	//Assert.assertEquals(targetPage, true,"Login Failed..");
	Assert.assertTrue(targetPage);
	}
	
	catch(Exception e)
	{
		
		Assert.fail();
	}
	logger.info("Finished TC002_LoginTest....");
		
	}
	
	
	
	
}
