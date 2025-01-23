package testCases;

//import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	
	{
		
		logger.info("...Starting TC001_AccountRegistrationTest...");
		
		try// we need to do this if Assert fails, then we need to catch the error log
		{
		
		//we need to create an object to access the homepage action methods
		 HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("...CLicked on myaccount link...");
		hp.clickRegister();
		logger.info("...CLicked on register link...");
		
		//we need to create an object to access the AccountRegistration page action methods
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details..");
		
		//regpage.setFirstName("John");
		regpage.setFirstName(randomeString().toUpperCase());
		//regpage.setLastName("David");
		
		regpage.setLastName(randomeString().toUpperCase());
		//regpage.setEmail("abcjohndavif@gmail.com");
		
		regpage.setEmail(randomeString()+"@gmail.com");//randomly generated email
		
		//regpage.setTelephone("23123123");
		regpage.setTelephone(randomeNumber());
		
		
		// we need to capture the password in String so it can be the same for confirm passsword
		String password=randomeAlphaNumberic();
		
		//regpage.setPassword("xyz12356");
		regpage.setPassword(password);
		
		//regpage.setConfirmPassword("xyz12356");
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
			
		{
			
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		
		
			
		catch(Exception e)
		{
			
			Assert.fail();// if catch block executed then we need to do assert fail
		
			
		}
		
		logger.info("...Finished TC001_AccountRegistrationTest....");
		
	}
	
}
