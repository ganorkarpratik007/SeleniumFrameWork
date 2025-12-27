package rahulshettyacademy.SeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname = "ZARA COAT 3";
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable-blink-features=AutomationControlled");
		
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		
		options.setExperimentalOption("useAutomationExtension", false);
		
		WebDriver driver = new ChromeDriver();
		
		LandingPage LandingPage = new LandingPage(driver);
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://www.rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("pratik7@gmail.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Pratik@123");
		
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(Duration.ofSeconds(4));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> Items = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement prod = Items.stream().filter(product->product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(productname)).findFirst().orElse(null);
		
		System.out.println(prod);
		
		prod.findElement(By.cssSelector("[class='btn w-10 rounded']")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']"))).click();
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean matchtest = cartproducts.stream().anyMatch(CProd->CProd.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(matchtest);
		
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String cnfmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(cnfmsg.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();
	}

}