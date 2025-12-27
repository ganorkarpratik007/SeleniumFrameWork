package rahulshettyacademy.SeleniumFrameWork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Abstractpackage.AbstractClass;

public class ProductCatelogue extends AbstractClass {

    WebDriver driver;

    public ProductCatelogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Updated locators
    @FindBy(css = ".mb-3")   // Correct container for product tiles
    List<WebElement> products;

    @FindBy(css = "ngx-spinner")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By productNameBy = By.cssSelector("b");    
    By addToCartBtn = By.cssSelector("button.btn.w-10.rounded");

    By toastcontainer = By.id("toast-container");

    public List<WebElement> getproductlist() {
        waitforelementmethod(productsBy);
        return products;
    }

    public WebElement getproductbyname(String productname) {
        return getproductlist().stream()
                .filter(product -> product.findElement(productNameBy)
                .getText().trim().equalsIgnoreCase(productname))
                .findFirst()
                .orElse(null);
    }

    public boolean addproducttocart(String productname) {

        WebElement prod = getproductbyname(productname);

        if (prod == null) {
            System.out.println("[ERROR] Product not found: " + productname);
            return false;   // prevent NullPointerException
        }

        prod.findElement(addToCartBtn).click(); 
        waitforelementmethod(toastcontainer);
        waitforinvisibilityOfElement(spinner);

        return true;
    }
}
