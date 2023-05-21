package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Launches extends MainPage{
    @FindBy(css = "#app div.launchFiltersToolbar__add-filter-button--GqSO3 button")
    private WebElement addFilterButton;
    public Launches(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getAddFilterButton() {
        return getTextOfElement(addFilterButton);
    }
}
