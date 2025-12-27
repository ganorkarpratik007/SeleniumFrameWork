package Abstractpackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.SeleniumFrameWork.CartPage;
import rahulshettyacademy.SeleniumFrameWork.OrderPage;

public class AbstractClass {
	WebDriver driver;

	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitforelementmethod(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	public void waitforelementtoappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitforinvisibilityOfElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement CartID;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersXpath;

	public CartPage gotocartpage() {

		CartID.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage GotOrdersPage() {

		OrdersXpath.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
}
