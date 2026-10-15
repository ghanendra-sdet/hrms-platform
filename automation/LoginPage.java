package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object for the ESS login page. Locators are illustrative, matching the
 * dummy field/element names used throughout this repo's documentation.
 */
public class LoginPage {

    private final WebDriver driver;

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginButton");
    private final By errorMessage = By.id("loginErrorMessage");
    private final By personalDetailsHeading = By.id("personalDetailsHeading");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("/login");
    }

    public void loginAs(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isOnPersonalDetailsPage() {
        return driver.findElement(personalDetailsHeading).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
