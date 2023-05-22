package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarPageComponent extends BasePage {

    @FindBy(css = "a[href*='launches']")
    WebElement launchesLink;

    @FindBy(css = "a[href*='filters']")
    WebElement filtersLink;

    public SideBarPageComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FiltersPage goToFilters() {
        filtersLink.click();
        return new FiltersPage(driver);
    }

    public LaunchesPage goToLaunches() {
        launchesLink.click();
        return new LaunchesPage(driver);
    }
}
