package org.taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {
    public static final String SIGNED_IN_SUCCESSFULLY = "#notification-root p";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String signedInSuccessfullyText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(repo.getBy("time.wait.locator"))));
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SIGNED_IN_SUCCESSFULLY))).getText();
    }
}
