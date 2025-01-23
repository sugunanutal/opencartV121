package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//only constructor , it extends into every page object. this is the parent of all the page objects classes
public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver driver)
	
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
		
	}
	
}
