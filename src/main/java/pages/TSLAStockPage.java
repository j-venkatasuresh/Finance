package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonUtilities;

public class TSLAStockPage {
WebDriver driver;
	
	
	public  TSLAStockPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy (xpath="//span[@data-testid=\"qsp-price\"]")
	private WebElement stockprice;
	
	@FindBy(xpath="//fin-streamer[@data-field=\"regularMarketVolume\"]")
	private WebElement volume;
	
	@FindBy(xpath="//fin-streamer[@data-field=\"regularMarketPreviousClose\"]")
	private WebElement previousClose;
	
	public double getstockprice() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(stockprice));
		return  Double.parseDouble(stockprice.getText());
		
		
	}
	public boolean verifystockprice() throws IOException {
		Properties prop;
		prop = CommonUtilities.loadProperiesFile();
		double expected =Double.parseDouble( prop.getProperty("stockcompareprice"));
		return getstockprice()>expected;
	}
	
	public int getvolume() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(volume));
		return  Integer.parseInt(volume.getText().replaceAll(",", "").trim());
		
		
	}
	public double getpreviousClose() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(previousClose));
		return  Double.parseDouble(previousClose.getText());	
	}
	
		
	}
