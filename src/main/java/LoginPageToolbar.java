import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPageToolbar extends BasePage {

    public static ChromeDriver driver;

    LoginPageToolbar() {
    }

    public WebElement toMailRu = driver.findElement(By.xpath("//a[text()=\"Mail.ru\"]"));
    public WebElement toMail = driver.findElement(By.xpath("//a[text()=\"Почта\"]"));
    public WebElement cloud = driver.findElement(By.xpath("//a[text()=\"Облако\"]"));
    public WebElement classmates = driver.findElement(By.xpath("//a[text()=\"Одноклассники\"]"));
    public WebElement inContact = driver.findElement(By.xpath("//a[text()=\"ВКонтакте\"]"));
    public WebElement news = driver.findElement(By.xpath("//a[text()=\"Новости\"]"));
    public WebElement acquaintances = driver.findElement(By.xpath("//a[text()=\"Знакомства\"]"));
    public WebElement myWorld = driver.findElement(By.xpath("//a[text()=\"Мой мир\"]"));

    public Select allProjects = new Select(driver.findElement(
            By.xpath("//div[@class=\"ph-project svelte-a9o3e5\"]")));

    public Select languages = new Select(driver.findElement(
            By.xpath("//div[contains(@class, \"ph-lang-select\")]")));

    public WebElement help = driver.findElement(By.xpath("//a[contains(@class, \"ph-help\")]"));
}
//a[contains(@class, "button-pro") and text()="Зарегистрироваться"]