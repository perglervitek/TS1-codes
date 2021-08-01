package cz.cvut.fel.ts1.selenium;

import cz.cvut.fel.ts1.pages.AdvancedSearch;
import cz.cvut.fel.ts1.pages.Article;
import cz.cvut.fel.ts1.pages.Home;
import cz.cvut.fel.ts1.pages.SearchResults;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckSavedArticlesTest {
    private static WebDriver driver;
    private static Home home;
    @BeforeAll
    private static void prepareAll(){

        try {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\vitek\\Documents\\ts1Projekty\\perglvit\\ts1-hw03-selenium\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            Dimension dimension = new Dimension(1920,1080);
            driver.manage().window().setSize(dimension);
            driver.get("https://link.springer.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        home = new Home(driver);
        home.clickOnCookies();

        String loginEmail = "johnytestacc@gmail.com";
        String loginPassword = "Pa$$w0rd!";
        home.navigateToLogin().loginUser(loginEmail, loginPassword);
    }
    @BeforeEach
    private void driverInitialLocation(){
        driver.get("https://link.springer.com/");
    }

    @ParameterizedTest
    @CsvFileSource(files = "articles.csv")
    public void checkArticlesData_Valid_Success(String expectedArticleTitle, String expectedArticleDoi, String expectedArticlePublicationDate){
        SearchResults searchResults = home.navigateToAdvancedSearch().searchWithFiltersAndTitleSingleYear(expectedArticleTitle,"Page Object Model","Sellenium Testing","2021");

        Article article = searchResults.navigateToArticle(0);
        String receivedTitle = article.getTitle();
        String receivedPublicationDate = article.getPublishedDate();
        String receivedDoi = article.getDoi();

        assertEquals(expectedArticleTitle, receivedTitle);
        assertEquals(expectedArticlePublicationDate, receivedPublicationDate);
        assertEquals(expectedArticleDoi, receivedDoi);
    }


    @AfterAll
    private static void cleanAll() {
        driver.close();
    }
}
