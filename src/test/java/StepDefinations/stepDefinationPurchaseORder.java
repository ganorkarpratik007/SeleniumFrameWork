package StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.BaseCompenent.BaseTest;
import rahulshettyacademy.SeleniumFrameWork.CartPage;
import rahulshettyacademy.SeleniumFrameWork.CheckOutPage;
import rahulshettyacademy.SeleniumFrameWork.LandingPage;
import rahulshettyacademy.SeleniumFrameWork.ProductCatelogue;
import rahulshettyacademy.SeleniumFrameWork.SubmitOrderTest;
import rahulshettyacademy.SeleniumFrameWork.confirmationPage;

public class stepDefinationPurchaseORder extends BaseTest {

    public LandingPage landingPage;   // ✅ correct variable
    public ProductCatelogue prodCatelogue;
    public confirmationPage cnfpage;

    @Given("I have landed on Ecommerce Page")
    public void I_have_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();   // ✅ correct usage
    }

    @Given("^Logged in with username (.*) and password (.*)$")
    public void logged_in_with_username_name_and_password_pratik(String username, String password) {
    	prodCatelogue = landingPage.LoginApplication(username, password);
    }

    @When("I add product {string} to cart")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> listofprod = prodCatelogue.getproductlist();
        prodCatelogue.addproducttocart(productName);
    }

    @When("Checkout {string} and submit the order")
    public void checkout_product_and_submit_the_order(String productName) {
        CartPage cartpage = prodCatelogue.gotocartpage();
        Boolean match = cartpage.verifyproductdisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkoutpage = cartpage.gotoCheckOut();
        checkoutpage.selectcountry("India");
        cnfpage = checkoutpage.submitorder();
    }

    @Then("\"{string}\" message is displayed on confirmation page")
    public void message_is_displayed_on_confirmation_page(String expectedMessage) {
        String ActualMessage = cnfpage.getconfirmationmessage();
        Assert.assertTrue(ActualMessage.equalsIgnoreCase(expectedMessage));
    }
}
