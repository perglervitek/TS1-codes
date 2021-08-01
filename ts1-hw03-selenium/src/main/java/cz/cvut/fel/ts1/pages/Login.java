package cz.cvut.fel.ts1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login {
    private WebDriver driver;

    private static final String loginUrl = "https://link.springer.com/signup-login";


    @FindBy(id = "login-box-email")
    private WebElement loginEmailInput;
    @FindBy(id = "login-box-pw")
    private WebElement loginPasswordInput;
    @FindBy(css = "button[title='Log in']")
    private WebElement loginSubmitButton;

    public Login(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains(loginUrl)) {
            throw new IllegalStateException("Invalid url address expected: " + loginUrl + " got: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

     public Home loginUser(String email, String password) {
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginSubmitButton.click();
         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

         return new Home(driver);
    }
}
