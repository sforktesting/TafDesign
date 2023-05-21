package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.taf.utils.PropertiesReader;

import java.time.Duration;

public class BasePage {
    protected static final PropertiesReader repo = new PropertiesReader("src/main/resources/Locators.properties");
    private static final String TIME_TO_WAIT = repo.getBy("time.wait.locator");
    protected final WebDriver driver;
    @FindBy(css = "#notification-root p")
    protected WebElement notificationMessageElement;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(TIME_TO_WAIT)));
        return wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }

    public boolean isElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(TIME_TO_WAIT)));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public String getTextOfElement(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(TIME_TO_WAIT)));
        return wait.until(ExpectedConditions.visibilityOf(locator)).getText();
    }
}
