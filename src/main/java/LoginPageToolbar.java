import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LoginPageToolbar extends BasePage {

    public final WebElement toMailRu = driver.findElement(By.xpath("//a[text()=\"Mail.ru\"]"));
    public final WebElement toMail = driver.findElement(By.xpath("//a[text()=\"Почта\"]"));
    public final WebElement cloud = driver.findElement(By.xpath("//a[text()=\"Облако\"]"));
    public final WebElement classmates = driver.findElement(By.xpath("//a[text()=\"Одноклассники\"]"));
    public final WebElement inContact = driver.findElement(By.xpath("//a[text()=\"ВКонтакте\"]"));
    public final WebElement news = driver.findElement(By.xpath("//a[text()=\"Новости\"]"));
    public final WebElement acquaintances = driver.findElement(By.xpath("//a[text()=\"Знакомства\"]"));
    public final WebElement myWorld = driver.findElement(By.xpath("//a[text()=\"Мой мир\"]"));

    public Select allProjects = new Select(driver.findElement(By.xpath("//div[@class=\"ph-project svelte-a9o3e5\"]")));

    public Select languages = new Select(driver.findElement(By.xpath("//div[contains(@class, \"ph-lang-select\")]")));

    public WebElement help = driver.findElement(By.xpath("//a[contains(@class, \"ph-help\")]"));

    LoginPageToolbar() {
    }
}