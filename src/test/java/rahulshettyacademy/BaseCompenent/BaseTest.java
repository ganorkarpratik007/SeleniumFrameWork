package rahulshettyacademy.BaseCompenent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.SeleniumFrameWork.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LandingPage;

	public WebDriver IntializeDriver() throws IOException {

	    Properties prop = new Properties();
	    FileInputStream fis = new FileInputStream(
	            "D:\\AUTOMATION\\Zing\\SeleniumFrameWork\\src\\test\\java\\resources\\GlobalData.properties");
	    prop.load(fis);

	    String browserName = System.getProperty("browser") != null
	            ? System.getProperty("browser")
	            : prop.getProperty("browser");

	    System.out.println("Browser picked: " + browserName);

	    browserName = browserName.toLowerCase();   // 🔥 IMPORTANT FIX

	    if (browserName.contains("chrome")) {

	        ChromeOptions options = new ChromeOptions();

	        if (browserName.contains("headless")) {
	            options.addArguments("--headless=new");
	            options.addArguments("--disable-gpu");
	            options.addArguments("--window-size=1440,900");
	        }

	        driver = new ChromeDriver(options);
	    } 
	    else if (browserName.contains("firefox")) {
	        driver = new FirefoxDriver();
	    } 
	    else if (browserName.contains("edge")) {
	        driver = new EdgeDriver();
	    } 
	    else if (browserName.contains("safari")) {
	        driver = new SafariDriver();
	    }

	    driver.manage().deleteAllCookies();
	    return driver;
	}

	public List<HashMap<String, String>> GetJsonDataToMap(String file) throws IOException {

		// Read json to string
		String jsoncontent = FileUtils.readFileToString(
				new File(file),
				StandardCharsets.UTF_8);
		// Read String to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshot(String testcasename, WebDriver driver) throws IOException
    {
		 TakesScreenshot ts = (TakesScreenshot) driver;
		    File source = ts.getScreenshotAs(OutputType.FILE);

		    String path = "D:\\AUTOMATION\\Screenshot Folder\\" + testcasename + ".png";
		    File filedest = new File(path);

		    FileUtils.copyFile(source, filedest);

		    return path;   // return String, not File
    	
    }
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = IntializeDriver();
		LandingPage = new LandingPage(driver); // FIXED
		LandingPage.Goto();
		return LandingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}

	}
}