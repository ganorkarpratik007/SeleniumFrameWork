package rahulshettyacademy.SeleniumFrameWork;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Abstractpackage.AbstractClass;

public class OrderPage extends AbstractClass{

	WebDriver driver;
	
	//Intitialization of the driver.
	public OrderPage(WebDriver driver) 
	{
		super(driver);
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

	
	 @FindBy(css = "tr td:nth-child(3)")
	 List<WebElement> OrdersProducts;
	 
	 @FindBy(xpath = "//button[normalize-space()='Checkout']")
	 WebElement CheckoutBtn;
	 
	 public Boolean VerifyOrderDisplay(String ProductName)
	 {
		 Boolean PrdMatch = OrdersProducts.stream().anyMatch(CProd->CProd.getText().equalsIgnoreCase(ProductName));
			
			return PrdMatch;
	 }	
}
