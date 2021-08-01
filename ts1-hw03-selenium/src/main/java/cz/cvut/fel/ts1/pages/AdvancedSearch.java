package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedSearch {
    private WebDriver driver;
    private String advancedSearchUrl = "https://link.springer.com/advanced-search";

    @FindBy(id = "all-words")
    private WebElement allWordsInput;
    @FindBy(id = "least-words")
    private WebElement withAtLeastInput;
    @FindBy(id = "date-facet-mode")
    private WebElement timeRangeSelect;
    @FindBy(id = "title-is")
    private WebElement titleContainsInput;
    @FindBy(id = "facet-start-year")
    private WebElement startPublishedDateInput;
    @FindBy(id = "facet-end-year")
    private WebElement endPublishedDateInput;
    @FindBy(id = "submit-advanced-search")
    private WebElement submitAdvancedSearchButton;

    public AdvancedSearch(WebDriver driver) {
        this.driver = driver;
//        if (!driver.getCurrentUrl().equals(advancedSearchUrl)) {
//            throw new IllegalStateException("Invalid url address expected: " + advancedSearchUrl + " got: " + driver.getCurrentUrl());
//        }
        PageFactory.initElements(driver, this);
    }

    public SearchResults searchWithFiltersSingleYear(String allWords, String withAtlEastWords, String publishedDate){
        allWordsInput.sendKeys(allWords);
        withAtLeastInput.sendKeys(withAtlEastWords);
        new Select(timeRangeSelect).selectByValue("in");
        startPublishedDateInput.sendKeys(publishedDate);
        submitAdvancedSearchButton.click();
        return new SearchResults(driver);
    }

    public SearchResults searchWithFiltersAndTitleSingleYear(String title, String allWords, String withAtlEastWords, String publishedDate){
        allWordsInput.sendKeys(allWords);
        withAtLeastInput.sendKeys(withAtlEastWords);
        new Select(timeRangeSelect).selectByValue("in");
        startPublishedDateInput.sendKeys(publishedDate);
        titleContainsInput.sendKeys(title);
        submitAdvancedSearchButton.click();
        return new SearchResults(driver);
    }

}
