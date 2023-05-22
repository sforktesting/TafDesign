package org.taf;

import com.epam.reportportal.junit5.ReportPortalExtension;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.taf.pages.FiltersPage;
import org.taf.pages.LaunchesPage;
import org.taf.pages.LoginPage;
import org.taf.pages.MainPage;
import org.taf.user.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
@ExtendWith(ReportPortalExtension.class)
class FilterTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static UserInfo defaultUser;
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeAll
    public static void setLogger() {
        defaultUser = new UserInfo();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach(WebDriver driver) {
        LOGGER.info("Starting...");
        driver.manage().window().maximize();
        driver.navigate().to(defaultUser.getBaseUrl());
        LOGGER.info("Started");

        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(defaultUser.getUserName(), defaultUser.getPassword());
        assertEquals(defaultUser.getSignedInText(), mainPage.getSignedInSuccessfullyText());
        LOGGER.info("Logged In");
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Ending...");
        loginPage = mainPage.logOut();
        assertEquals(defaultUser.getSignedOutText(), loginPage.getSignedOutSuccessfullyText());
        LOGGER.info("Logged Out");
    }

    @Test
    @DisplayName("Verify Header text on Filter page")
    void verifyHeaderTextOnFilterPage() {
        LOGGER.info("Filter");
        FiltersPage filters = mainPage.getSideBar().goToFilters();
        assertEquals("FILTERS", filters.getHeaderText());
    }

    @Test
    @DisplayName("Verify Launches page")
    void verifyLaunchesPage() {
        LOGGER.info("Launches");
        LaunchesPage launches = mainPage.getSideBar().goToLaunches();
        assertEquals("Add filter", launches.getAddFilterButton());
    }
}
