package org.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.taf.pages.LoginPage;
import org.taf.pages.MainPage;
import org.taf.utils.RepositoryParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class FilterTest {

    private static RepositoryParser repo;
    private static Logger LOGGER = null;
    WebDriver driver;

    @BeforeAll
    public static void setLogger() {
        LOGGER = LogManager.getLogger();
        repo = new RepositoryParser("src/main/resources/Locators.properties");
    }

    @BeforeEach
    void beforeEach() {
        LOGGER.info("Starting...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        LOGGER.info("Started");
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Ending...");
        driver.quit();
        driver = null;
        LOGGER.info("The End");
    }

    @Test
    @ExtendWith(ReportPortalExtension.class)
    void successLogin() {
        LoginPage loginPage;
        loginPage = new LoginPage(driver).open();
        assertTrue(loginPage.isSubmitButtonVisible());
        MainPage loginResultsPage = loginPage.login(
                repo.getBy("default.user.name"),
                repo.getBy("default.user.password"));
        assertEquals(repo.getBy("signed.in.successfully"), loginResultsPage.signedInSuccessfullyText());
    }
}
