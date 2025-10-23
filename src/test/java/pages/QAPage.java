package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QAPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By seeAllQAJobs = By.xpath("//a[contains(text(),'See all QA jobs')]");

    public QAPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSeeAllQAJobs() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(seeAllQAJobs));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}
