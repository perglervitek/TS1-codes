package cz.cvut.fel.ts1;

import cz.cvut.fel.ts1.pages.Article;
import cz.cvut.fel.ts1.pages.Home;
import cz.cvut.fel.ts1.pages.SearchResults;
import cz.cvut.fel.ts1.utils.ArticleInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static WebDriver driver;
    private static List<ArticleInfo> articlesInfo = new ArrayList<>();

    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver","C:/Users/vitek/Documents/ts1Projekty/perglvit/ts1-hw03-selenium/src/main/java/resources/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver","C:\\Users\\vitek\\Documents\\ts1Projekty\\perglvit\\ts1-hw03-selenium\\driver\\chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("http://34.89.128.102/");

        try {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\vitek\\Documents\\ts1Projekty\\perglvit\\ts1-hw03-selenium\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://link.springer.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Home home = new Home(driver);
        home.clickOnCookies();

        SearchResults searchResults = home.navigateToAdvancedSearch().searchWithFiltersSingleYear("Page Object Model","Sellenium Testing","2021");
        searchResults.selectOnlyArticles();
        for(int i =0; i<4; i++){
            Article article = searchResults.navigateToArticle(i);
            ArticleInfo articleInfo = new ArticleInfo(article.getTitle(), article.getDoi(), article.getPublishedDate());
            articlesInfo.add(articleInfo);
            driver.navigate().back();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("articles.csv");
            for (int i = 0; i < articlesInfo.size(); i++) {
                writer.println(articlesInfo.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
