package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void assertUrlContains(String text) {
        assert driver.getCurrentUrl().contains(text) : "Expected URL to contain: " + text;
    }

    private final By acceptCookies = By.id("wt-cli-accept-all-btn");

    public void acceptCookiesIfPresent() {
        try {
            WebElement btn = driver.findElement(acceptCookies);
            btn.click();
        } catch (Exception ignored) {}
    }

}
