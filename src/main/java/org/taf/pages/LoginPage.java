package org.taf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.taf.utils.RepositoryParser;

import java.time.Duration;

public class LoginPage {

    public static final String USERNAME_LOCATOR = "input[type=\"text\"]";
    public static final String PASSWORD_LOCATOR = "input[type=\"password\"]";
    public static final String SUBMIT_LOCATOR = "button[type=\"submit\"]";
    private final WebDriver driver;
    private final RepositoryParser repo;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        repo = new RepositoryParser("src/main/resources/Locators.properties");
    }

    public LoginPage open()
    {
        String url = repo.getBy("url");
        driver.navigate().to(url);
        return this;
    }

    public void setUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions
                        .elementToBeClickable(driver.findElement(By.cssSelector(USERNAME_LOCATOR))))
                .isDisplayed();
        WebElement usernameElement = driver.findElement(By.cssSelector(USERNAME_LOCATOR));
        usernameElement.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordElement = driver.findElement(By.cssSelector(PASSWORD_LOCATOR));
        passwordElement.sendKeys(password);
    }

    public WebElement getSubmitButton(){
        return driver.findElement(By.cssSelector(SUBMIT_LOCATOR));
    }

    public MainPage submit()
    {
        getSubmitButton().submit();
        return new MainPage(driver);
    }

    public MainPage login(String username, String password)
    {
        setUsername(username);
        setPassword(password);
        return submit();
    }

    public Boolean isVisible()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(getSubmitButton())).isDisplayed();
    }

}
