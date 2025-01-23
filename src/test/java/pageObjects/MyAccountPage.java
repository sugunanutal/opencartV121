package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

public MyAccountPage(WebDriver driver) {
		super(driver);
		
}
@FindBy(xpath="//h2[text()='My Account']")//My account page heading
WebElement msgHeading;

@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")//added in step 6
WebElement linklogout;

public boolean isMyAccountPageExists()//validation
{
	try
	{
	return(msgHeading.isDisplayed());
	}
	catch (Exception e)
	{
		return false;
	}
	
}
public void clicklogout()
{
	linklogout.click();
}
	
}
