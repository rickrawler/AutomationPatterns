package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverWrapper {

    private final WebDriver driver;


    public DriverWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilVisible(By locator, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        locator
                ));
    }

    public void waitUntilVisible(WebElement element, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.visibilityOf(
                        element
                ));
    }

    public void waitUntilVisible(List<WebElement> elements, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.visibilityOfAllElements(
                        elements
                ));
    }

    public void waitUntilClickable(By locator, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.elementToBeClickable(
                        locator
                ));
    }

    public void waitLocatedElementWithText(By locator, String text, int duration) {
        new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        locator, text
                ));
    }

}
