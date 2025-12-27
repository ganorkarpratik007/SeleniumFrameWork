package rahulshettyacademy.SeleniumFrameWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Abstractpackage.AbstractClass;

public class LandingPage extends AbstractClass {

	WebDriver driver;
	
	//Intitialization of the driver.
	public LandingPage(WebDriver driver) 
	{
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

	//WebElement UserEmailInput = driver.findElement(By.id("userEmail"));
	
	//Pagefactory
	@FindBy(id = "userEmail")
	WebElement UserEmailInput;
	
	@FindBy(id = "userPassword")
	WebElement UserPasswordInput;
	
	@FindBy(id = "login")
	WebElement LoginBtn;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;
		
	public ProductCatelogue LoginApplication(String email, String password)
	{
		UserEmailInput.sendKeys(email);
		
		UserPasswordInput.sendKeys(password);
		
		LoginBtn.click();
		
		ProductCatelogue prod = new ProductCatelogue(driver);
		return prod;
	}
	
	public void Goto()
	{
		driver.get("https://www.rahulshettyacademy.com/client/");
	}
	
	public String geterrormessage()
	{
		waitforelementtoappear(errormessage);
	   return (errormessage.getText());
	}
	
	
}
