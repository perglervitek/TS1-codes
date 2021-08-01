package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResults {
    private WebDriver driver;

    @FindBy(partialLinkText = "Article")
    private WebElement articleOnlyLink;

    @FindBy(css = "ol#results-list > li > h2 > a.title")
    private List<WebElement> articleLinks;

    private static final String searchResultsUrl = "https://link.springer.com/search";


    public SearchResults(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains(searchResultsUrl)) {
            throw new IllegalStateException("Invalid url address expected: " + searchResultsUrl + " got: " + driver.getCurrentUrl().substring(0, driver.getCurrentUrl().indexOf("?")));
        }
        PageFactory.initElements(driver, this);
    }

    public void selectOnlyArticles(){
        articleOnlyLink.click();
    }

    public Article navigateToArticle(int articleId){
        articleLinks.get(articleId).click();
        return new Article(driver);
    }
}
