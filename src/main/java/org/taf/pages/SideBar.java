package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBar extends BasePage {

    @FindBy(css = "a[href*='launches']")
    WebElement launchesLink;

    @FindBy(css = "a[href*='filters']")
    WebElement filtersLink;

    public SideBar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Filters goToFilters() {
        filtersLink.click();
        return new Filters(driver);
    }

    public Launches goToLaunches() {
        launchesLink.click();
        return new Launches(driver);
    }
}
