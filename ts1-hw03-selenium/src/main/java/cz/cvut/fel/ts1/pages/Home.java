package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    private WebDriver driver;
    private String homeUrl = "https://link.springer.com/";

    @FindBy(css = "a.register-link.flyout-caption")
    private WebElement loginButton;

    @FindBy(id = "search-options")
    private WebElement searchOptionsButton;

    @FindBy(id="advanced-search-link")
    private WebElement advancedSearchButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement allowCookiesButton;

    public Home(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(homeUrl)) {
            throw new IllegalStateException("Invalid url address expected: " + homeUrl + " got: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public void clickOnCookies() {
        allowCookiesButton.click();
    }

    public Login navigateToLogin(){
        loginButton.click();
        return new Login(driver);
    }

    public AdvancedSearch navigateToAdvancedSearch(){
        searchOptionsButton.click();
        advancedSearchButton.click();
        return new AdvancedSearch(driver);
    }

}
