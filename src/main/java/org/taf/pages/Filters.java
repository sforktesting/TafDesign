package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Filters extends MainPage{
    @FindBy(css = "#app ul li span")
    private WebElement headerText;
    public Filters(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getHeaderText() {
        return getTextOfElement(headerText);
    }
}
