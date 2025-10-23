package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OpenPositionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locationDropdown = By.id("select2-filter-by-location-container");
    private By departmentDropdown = By.id("select2-filter-by-department-container");

    private By istanbulOption = By.xpath("//li[contains(text(),'Istanbul, Turkiye')]");
    private By qaOption = By.xpath("//li[contains(text(),'Quality Assurance')]");

    private By firstJobCard = By.cssSelector(".position-list-item");

    public OpenPositionsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By acceptCookiesBtn = By.xpath("//a[contains(text(),'Accept All') or contains(text(),'Accept all')]");
            WebElement cookieButton = wait.until(ExpectedConditions.presenceOfElementLocated(acceptCookiesBtn));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
                System.out.println("Cookies accepted.");
            }
        } catch (Exception e) {
            System.out.println("No cookie banner appeared.");
        }
    }

    public void filterJobs() throws InterruptedException {
        acceptCookiesIfPresent();
        Thread.sleep(7000);

        wait.until(ExpectedConditions.elementToBeClickable(locationDropdown)).click();

        scrollToAndClick(istanbulOption);

        wait.until(ExpectedConditions.elementToBeClickable(departmentDropdown)).click();

        // QA seçeneğini tıkla (burası genelde görünür, istersen aynı scroll metodunu kullanabilirsin)
        wait.until(ExpectedConditions.elementToBeClickable(qaOption)).click();

        Thread.sleep(2000);
    }
    private void scrollToAndClick(By optionLocator) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 3; i++) { // max 10 kez dene
            try {
                WebElement option = driver.findElement(optionLocator);
                if (option.isDisplayed()) {
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", option);
                    wait.until(ExpectedConditions.elementToBeClickable(option));
                    option.click();
                    return;
                }
            } catch (NoSuchElementException e) {
                // eleman görünmüyorsa scroll yap
                js.executeScript("document.querySelector('.select2-results__options').scrollBy(0, 100);");
            }
            Thread.sleep(500);
        }
        throw new RuntimeException(optionLocator + " seçeneği bulunamadı veya tıklanamadı!");
    }


    public void openFirstJob() {
        WebElement jobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(firstJobCard));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobCard);

        Actions actions = new Actions(driver);
        actions.moveToElement(jobCard).perform();

        WebElement viewRoleButton = jobCard.findElement(By.xpath(".//a[contains(text(),'View Role')]"));
        wait.until(ExpectedConditions.elementToBeClickable(viewRoleButton)).click();

        // Yeni sekme açıldı mı?
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() > 1, "Yeni sekme açılmadı!");
        driver.switchTo().window(tabs.get(1));
        System.out.println("Yeni sekme URL: " + driver.getCurrentUrl());
    }
}
