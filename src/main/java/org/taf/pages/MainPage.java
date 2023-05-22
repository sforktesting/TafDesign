package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @FindBy(css = "img[alt='avatar']")
    protected WebElement userBlockImage;

    @FindBy(css = "div.userBlock__menu--2Y0xl > div.userBlock__menu-item--3VBsZ")
    protected WebElement userBlockItemLogout;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getSignedInSuccessfullyText() {
        return getTextOfElement(notificationMessageElement);
    }

    public LoginPage logOut() {
        isElementInvisible(notificationMessageElement);
        userBlockImage.click();
        isElementDisplayed(userBlockItemLogout);
        userBlockItemLogout.click();
        return new LoginPage(driver);
    }

    public SideBarPageComponent getSideBar() {
        return new SideBarPageComponent(driver);
    }
}
