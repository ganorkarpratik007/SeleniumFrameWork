package rahulshettyacademy.SeleniumFrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Abstractpackage.AbstractClass;

public class confirmationPage extends AbstractClass {
	
        WebDriver driver;
	
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	By ConfirmationMessage= By.cssSelector(".hero-primary");
	
	public String getconfirmationmessage()
	{
		waitforelementmethod(ConfirmationMessage);
		 return driver.findElement(ConfirmationMessage).getText();
	}

}
