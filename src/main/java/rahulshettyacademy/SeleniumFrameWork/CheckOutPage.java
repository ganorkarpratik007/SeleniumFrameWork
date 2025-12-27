package rahulshettyacademy.SeleniumFrameWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Abstractpackage.AbstractClass;

public class CheckOutPage extends AbstractClass{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement submit;
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectIndia;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectcountry(String CountryName)
	{
Actions a = new Actions(driver);
		
		a.sendKeys(country, CountryName).build().perform();	
		
		waitforelementmethod(By.cssSelector(".ta-results"));
		
		selectIndia.click();
		
	}
	public confirmationPage submitorder()
	{
		submit.click();
		return new confirmationPage(driver);
	}
}
