package rahulshettyacademy.SeleniumFrameWork;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import rahulshettyacademy.BaseCompenent.Retry;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.BaseCompenent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "PurchaseOrder", retryAnalyzer =Retry.class)
	public void Submitorder(HashMap<String, String> input) throws IOException, InterruptedException 
	{
		ProductCatelogue prod = LandingPage.LoginApplication(input.get("email"), input.get("password"));
		ProductCatelogue prodCatelogue = new ProductCatelogue(driver);
		List<WebElement> listofprod = prodCatelogue.getproductlist();
		prodCatelogue.addproducttocart(input.get("ProductName"));
		CartPage cartpage = prodCatelogue.gotocartpage();
		Boolean match = cartpage.verifyproductdisplay(input.get("ProductName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.gotoCheckOut();
		checkoutpage.selectcountry("India");
		confirmationPage cnfpage = checkoutpage.submitorder();
		String cnfmsg = cnfpage.getconfirmationmessage();
		AssertJUnit.assertTrue(cnfmsg.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "Submitorder" })
	public void OrderHistoryTest() throws InterruptedException 
	{
		ProductCatelogue prod = LandingPage.LoginApplication("pratik7@gmail.com", "Pratik@123");
		Thread.sleep(Duration.ofSeconds(2));
		OrderPage orderspage = prod.GotOrdersPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productname));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

	    String jsonPath = System.getProperty("user.dir") +
	            "\\src\\test\\java\\DataPackage\\PurchaseOrder.json";

	    List<HashMap<String, String>> data = GetJsonDataToMap(jsonPath);

	    return new Object[][] {
	        { data.get(0) },
	        { data.get(1) }
	    };
}
}