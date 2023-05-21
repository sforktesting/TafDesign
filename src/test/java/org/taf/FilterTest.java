package org.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.taf.pages.Filters;
import org.taf.pages.Launches;
import org.taf.pages.LoginPage;
import org.taf.pages.MainPage;
import org.taf.utils.PropertiesReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class FilterTest {

    private static PropertiesReader repo;
    private static Logger LOGGER = null;

    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeAll
    public static void setLogger() {
        LOGGER = LogManager.getLogger();
        repo = new PropertiesReader("src/main/resources/Locators.properties");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach(WebDriver driver) {
        LOGGER.info("Starting...");
        String url = repo.getBy("url");
        driver.manage().window().maximize();
        driver.navigate().to(url);
        LOGGER.info("Started");

        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(
                repo.getBy("default.user.name"),
                repo.getBy("default.user.password"));
        assertEquals(repo.getBy("signed.in.successfully"), mainPage.getSignedInSuccessfullyText());
        LOGGER.info("Logged In");
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Ending...");
        loginPage = mainPage.logOut();
        assertEquals(repo.getBy("logged.out.successfully"), loginPage.getSignedOutSuccessfullyText());
        LOGGER.info("Logged Out");
    }

    @Test
    @ExtendWith(ReportPortalExtension.class)
    @DisplayName("Verify Header text on Filter page")
    void verifyHeaderTextOnFilterPage() {
        LOGGER.info("Filter");
        Filters filters = mainPage.getSideBar().goToFilters();
        assertEquals("FILTERS", filters.getHeaderText());

    }
    @Test
    @ExtendWith(ReportPortalExtension.class)
    @DisplayName("Verify Launches page")
    void verifyLaunchesPage() {
        LOGGER.info("Launches");
        Launches launches = mainPage.getSideBar().goToLaunches();
        assertEquals("Add filter", launches.getAddFilterButton());

    }
}
