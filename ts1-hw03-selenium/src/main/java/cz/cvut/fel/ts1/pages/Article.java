package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Article {
    private WebDriver driver;

    @FindBy(css = "h1")
    private WebElement articleTitleText;

    @FindBy(css = "header time")
    private WebElement publishedDateText;

    @FindBy(css = ".c-bibliographic-information__value > a")
    private WebElement doiAnchor;

    private static final String articleUrl = "https://link.springer.com/article";

    public Article(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains(articleUrl)) {
            throw new IllegalStateException("Invalid url address expected: " + articleUrl + " got: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return articleTitleText.getText();
    }

    public String getDoi(){
        return doiAnchor.getAttribute("href");
    }

    public String getPublishedDate(){
        return publishedDateText.getText();
    }
}
