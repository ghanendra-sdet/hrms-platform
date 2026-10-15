package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object for the ESS Personal/Contact Details (MyInfo) form.
 */
public class PersonalDetailsPage {

    private final WebDriver driver;

    private final By employeeIdField = By.id("employeeId");
    private final By nickNameField = By.id("nickName");
    private final By editButton = By.id("editButton");
    private final By saveButton = By.id("saveButton");
    private final By confirmationMessage = By.id("confirmationMessage");

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEmployeeIdFieldEnabled() {
        return driver.findElement(employeeIdField).isEnabled();
    }

    public boolean isNickNameFieldEnabled() {
        return driver.findElement(nickNameField).isEnabled();
    }

    public void clickEdit() {
        driver.findElement(editButton).click();
    }

    public void setNickName(String value) {
        WebElement field = driver.findElement(nickNameField);
        field.clear();
        field.sendKeys(value);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
