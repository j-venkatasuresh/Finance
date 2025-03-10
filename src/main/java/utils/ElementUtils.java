package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ElementUtils {
    WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
            System.out.println("Clicked element: " + element);
        } catch (Exception e) {
            System.err.println("Unable to click element: " + e.getMessage());
        }
    }

    public void sendKeysToElement(WebElement element, String value) {
        try {
            element.clear();
            element.sendKeys(value);
            System.out.println("Entered value: " + value);
        } catch (Exception e) {
            System.err.println("Unable to send keys to element: " + e.getMessage());
        }
    }

    public void waitForElementToBeVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
