package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By companyMenu = By.xpath("//a[contains(text(),'Company')]");
    private By careersLink = By.xpath("//a[contains(text(),'Careers')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get("https://useinsider.com/");
        wait.until(ExpectedConditions.titleContains("Insider"));
    }

    public void navigateToCareers() {
        Actions actions = new Actions(driver);
        WebElement company = wait.until(ExpectedConditions.visibilityOfElementLocated(companyMenu));
        actions.moveToElement(company).perform();
        WebElement careers = wait.until(ExpectedConditions.elementToBeClickable(careersLink));
        careers.click();
    }
}
