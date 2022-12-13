import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPageToolbar {

    private static final By TOOLBAR_PANEL_LOCATOR = By.id("ph-whiteline");
    private static final By TO_MAIL_RU = By.xpath("//a[text()=\"Mail.ru\"]");
    private static final By TO_MAIL = By.xpath("//a[text()=\"Почта\"]");
    private static final By CLOUD = By.xpath("//a[text()=\"Облако\"]");
    private static final By CLASSMATES = By.xpath("//a[text()=\"Одноклассники\"]");
    private static final By IN_CONTACT = By.xpath("//a[text()=\"ВКонтакте\"]");
    private static final By NEWS = By.xpath("//a[text()=\"Новости\"]");
    private static final By ACQUAINTANCES = By.xpath("//a[text()=\"Знакомства\"]");
    //private static final By MY_WORLD = By.xpath("//a[text()=\"Мой мир\"]");
    private static final By ALL_PROJECTS = By.xpath("//div[@class=\"ph-project svelte-a9o3e5\"]");
    private static final By LANGUAGES = By.xpath("//div[contains(@class, \"ph-lang-select\")]");
    private static final By HELP = By.xpath("//a[contains(@class, \"ph-help\")]");
    private static final By ALL_LANGUAGES_LOCATOR = By.xpath("//div[contains(@class, \"ph-lang-item\")]/span");

    private static final By[] allIconsLocators = new By[]{TO_MAIL_RU, TO_MAIL, CLOUD, CLASSMATES, IN_CONTACT,
            NEWS, ACQUAINTANCES, ALL_PROJECTS, LANGUAGES, HELP};

    private WebDriver driver;

    LoginPageToolbar(WebDriver driver) {
        this.driver = driver;
    }

    public MailPage goToMail() {
        WebElement toMail = driver.findElement(TO_MAIL);
        toMail.click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new MailPage(driver);
    }

    public boolean isDisplayed() {

        for (By locator : allIconsLocators) {
            if (!driver.findElement(locator).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean changeLanguageTo(String language) {
        WebElement languages = driver.findElement(LANGUAGES);
        if (languages.isDisplayed()) {
            languages.click();
            List<WebElement> allLanguages = driver.findElements(ALL_LANGUAGES_LOCATOR);
            for (WebElement lang : allLanguages) {
                if (lang.getText().equals(language)) {
                    lang.click();
                    return true;
                }
            }
        }
        return false;
    }
}