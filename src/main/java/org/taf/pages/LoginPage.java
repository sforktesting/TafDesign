package org.taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public static final String USERNAME_LOCATOR = "input[type=\"text\"]";
    public static final String PASSWORD_LOCATOR = "input[type=\"password\"]";
    public static final String SUBMIT_LOCATOR = "button[type=\"submit\"]";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        String url = repo.getBy("url");
        driver.navigate().to(url);
        return this;
    }

    public void setUserName(String userName) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(repo.getBy("time.wait.locator"))));
        wait.until(ExpectedConditions
                        .elementToBeClickable(driver.findElement(By.cssSelector(USERNAME_LOCATOR))))
                .isDisplayed();
        driver.findElement(By.cssSelector(USERNAME_LOCATOR)).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(By.cssSelector(PASSWORD_LOCATOR)).sendKeys(password);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.cssSelector(SUBMIT_LOCATOR));
    }

    public MainPage submit() {
        getSubmitButton().submit();
        return new MainPage(driver);
    }

    public MainPage login(String username, String password) {
        setUserName(username);
        setPassword(password);
        return submit();
    }

    public Boolean isSubmitButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(Long.parseLong(repo.getBy("time.wait.locator"))));
        return wait.until(ExpectedConditions.elementToBeClickable(getSubmitButton())).isDisplayed();
    }
}
