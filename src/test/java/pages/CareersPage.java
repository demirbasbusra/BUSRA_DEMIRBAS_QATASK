package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By seeAllTeamsButton = By.xpath("//a[contains(text(),'See all teams')]");
    private By qaSection = By.xpath("//h3[contains(text(),'Quality Assurance')]");

    public CareersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openSeeAllTeams() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 10; i++) { // maksimum 10 kez dene
            try {
                WebElement button = driver.findElement(seeAllTeamsButton);
                if (button.isDisplayed()) {
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", button);
                    wait.until(ExpectedConditions.elementToBeClickable(button));
                    button.click();
                    return;
                }
            } catch (Exception e) {
                // görünene kadar aşağı kaydır
                js.executeScript("window.scrollBy(0, 400);");
            }
            try {
                Thread.sleep(700); // küçük bekleme süresi
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        throw new RuntimeException("See All Teams butonu yok!");
    }


    public void openQAPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 15; i++) { // maksimum 15 deneme
            try {
                WebElement qa = driver.findElement(qaSection);
                if (qa.isDisplayed()) {
                    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", qa);
                    wait.until(ExpectedConditions.elementToBeClickable(qa)).click();
                    return;
                }
            } catch (Exception e) {
                // QA bölümü henüz yüklenmediyse biraz daha aşağı kaydır
                js.executeScript("window.scrollBy(0, 400);");
            }

            try {
                Thread.sleep(700); // sayfanın yeni içerik yüklemesi için kısa bekleme
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("Quality Assurance bölümü yok!");
    }

}
