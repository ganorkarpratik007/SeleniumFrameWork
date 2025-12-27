package rahulshettyacademy.SeleniumFrameWork;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Abstractpackage.AbstractClass;

public class CartPage extends AbstractClass{

	WebDriver driver;
	
	//Intitialization of the driver.
	public CartPage(WebDriver driver) 
	{
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

	
	 @FindBy(css = ".cartSection h3")
	 List<WebElement> CartProducts;
	 
	 @FindBy(xpath = "//button[normalize-space()='Checkout']")
	 WebElement CheckoutBtn;
	 
	 public Boolean verifyproductdisplay(String ProductName)
	 {
		 Boolean PrdMatch = CartProducts.stream().anyMatch(CProd->CProd.getText().equalsIgnoreCase(ProductName));
			
			return PrdMatch;
	 }
	 
	 public CheckOutPage gotoCheckOut()
	 {
		 CheckoutBtn.click();
		return new CheckOutPage(driver);
	 }
	 
	
}
