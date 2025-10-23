package tests;


import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import base.BaseTest;
import pages.OpenPositionsPage;
import pages.QAPage;

public class InsiderCareersTest extends BaseTest {

    @Test
    public void verifyInsiderCareerFlow() throws InterruptedException {
        HomePage home = new HomePage(driver, wait);

        CareersPage careers = new CareersPage(driver, wait);
        QAPage qaPage = new QAPage(driver, wait);
        OpenPositionsPage positions = new OpenPositionsPage(driver, wait);


        home.open();
        positions.acceptCookiesIfPresent();
        home.navigateToCareers();

        careers.openSeeAllTeams();
        careers.openQAPage();

        qaPage.clickSeeAllQAJobs();

        positions.filterJobs();
        positions.openFirstJob();
    }
}
