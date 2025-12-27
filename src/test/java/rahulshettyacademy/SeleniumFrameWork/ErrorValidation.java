package rahulshettyacademy.SeleniumFrameWork;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.BaseCompenent.BaseTest;

public class ErrorValidation extends BaseTest{
	
	@Test
	public void loginerrorvalidation() throws IOException, InterruptedException
	{
		String productname = "ZARA COAT 3";	
		ProductCatelogue prod= LandingPage.LoginApplication("pratik7@gmail.com", "Pratik@");
		Assert.assertEquals("Incorrect email password.", LandingPage.geterrormessage());
	}
	
	@Test(groups = {"ErrorHandling"})
	public void producterrorvalidation() throws IOException, InterruptedException
	{
		String productname = "ZARA COAT 33";	
		ProductCatelogue prod= LandingPage.LoginApplication("pratik7@gmail.com", "Pratik@123");
		ProductCatelogue prodCatelogue = new ProductCatelogue(driver);
		List<WebElement> listofprod = prodCatelogue.getproductlist();
		prodCatelogue.addproducttocart(productname);
		CartPage cartpage = prodCatelogue.gotocartpage();
		Boolean match = cartpage.verifyproductdisplay(productname);
		Assert.assertFalse(match);
	}
}