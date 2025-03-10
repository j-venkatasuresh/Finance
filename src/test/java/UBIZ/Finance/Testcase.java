package UBIZ.Finance;



import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.TSLAStockPage;
import pages.YahooFinanceHomepage;
import utils.CommonUtilities;
import utils.ElementUtils;

public class Testcase  {

	private WebDriver driver;

	private YahooFinanceHomepage yahooFinanceHomepage;
	private ElementUtils elementitils;
	private TSLAStockPage tslaStockPage;
	Properties prop;

	@BeforeMethod
	public void setup() throws IOException {
		
		prop = CommonUtilities.loadProperiesFile();
		String browserName = prop.getProperty("browserName");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("internetexplorer")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		driver.get("https://finance.yahoo.com/");
		driver.manage().window().maximize();
		yahooFinanceHomepage = new YahooFinanceHomepage(driver);
		

	}
	@Test
	public void validtest() throws IOException, InterruptedException {
		prop = CommonUtilities.loadProperiesFile();
		yahooFinanceHomepage.EnterdataInSearchfield(prop.getProperty("validdata"));
		System.out.println("entered data");
		System.out.println(yahooFinanceHomepage.printfirstsuggestion());
		Assert.assertTrue(yahooFinanceHomepage.verifyAutosuggest(), "Test Execution stopped");
		tslaStockPage = yahooFinanceHomepage.clickonfirstsuggestion();
		System.out.println("Current Stock Price:"+tslaStockPage.getstockprice());
		Assert.assertTrue(tslaStockPage.verifystockprice(),"Stock Price is not greater than expected price");
		System.out.println("Volume is :"+tslaStockPage.getvolume());
		System.out.println("Previous Close Amount is:"+tslaStockPage.getpreviousClose());

	}
	@AfterMethod
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}




}
