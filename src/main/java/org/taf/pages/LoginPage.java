package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(css = "#app input[type='text']")
    private WebElement userNameField;
    @FindBy(css = "#app input[type='password']")
    private WebElement passwordField;
    @FindBy(css = "#app button[type='submit']")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public MainPage login(String username, String password) {
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
        return new MainPage(driver);
    }

    public String getSignedOutSuccessfullyText() {
        return getTextOfElement(notificationMessageElement);
    }
}
