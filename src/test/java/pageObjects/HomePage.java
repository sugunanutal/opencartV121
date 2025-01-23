package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
//WebDriver driver; not required, because it is already in the BasePage
	
public HomePage(WebDriver driver)
	{
	
	super(driver);//passing the driver to the parent class constructor
	
	
	}


@FindBy(xpath="//a//span[normalize-space()='My Account']")
WebElement lnkMyaccount;

@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
WebElement lnkRegister;

@FindBy(linkText="Login")
WebElement linkLogin;

public void clickMyAccount()
{
	lnkMyaccount.click();
}

public void clickRegister()
{
	lnkRegister.click();

}



public void clickLogin()
{
linkLogin.click();

	}
}

