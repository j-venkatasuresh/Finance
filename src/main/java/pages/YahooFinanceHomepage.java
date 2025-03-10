package pages;



import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonUtilities;

public class YahooFinanceHomepage {

	WebDriver driver;



	public  YahooFinanceHomepage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver,this);

	}
	@FindBy (xpath="//input[@name=\"p\"]")
	private WebElement searchfield;

	@FindBy(xpath = "//ul[@role=\"listbox\"]/li[1]//div[contains(@class,'modules-module_quoteCompanyName')]")
	private WebElement firstsuggestion;



	public void EnterdataInSearchfield(String text) throws IOException {

		searchfield.sendKeys(text);
	}
	public boolean verifyAutosuggest() throws IOException {
		Properties prop;
		prop = CommonUtilities.loadProperiesFile();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement suggestion = wait.until(ExpectedConditions.visibilityOf(firstsuggestion));
		return suggestion.getText().equals(prop.getProperty("expectedfirstautosuggestion"));
	}
	public TSLAStockPage clickonfirstsuggestion() throws IOException {
		if(verifyAutosuggest()) {
			firstsuggestion.click();
			System.out.println("Clicked on First Suggestion");
		}

		return new TSLAStockPage(driver);

	}
	public String printfirstsuggestion() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(firstsuggestion));
		return firstsuggestion.getText();
	}
}
