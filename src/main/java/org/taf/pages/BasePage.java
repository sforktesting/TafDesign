package org.taf.pages;

import org.openqa.selenium.WebDriver;
import org.taf.utils.RepositoryParser;

public class BasePage {
    protected final WebDriver driver;
    protected final RepositoryParser repo;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        repo = new RepositoryParser("src/main/resources/Locators.properties");
    }
}
