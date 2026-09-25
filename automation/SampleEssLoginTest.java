package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

/**
 * Sample Selenium + TestNG regression test for the HRMS ESS Login and
 * Personal Details flows. All data below is DUMMY/SAMPLE data for
 * portfolio demonstration only — no real employee credentials.
 */
public class SampleEssLoginTest {

    private WebDriver driver;

    private static final String VALID_ESS_USER = "demo.ess.user";
    private static final String VALID_ESS_PASSWORD = "Demo@Passw0rd";
    private static final String INVALID_ESS_USER = "not.a.real.user";
    private static final String INVALID_ESS_PASSWORD = "WrongPassword123";

    @BeforeMethod
    public void setUp() {
        // driver = new ChromeDriver(); // omitted: driver setup is environment-specific
    }

    @Test(description = "TC_MYINFO_LOGIN_01 - valid user, valid password")
    public void loginWithValidCredentialsShowsPersonalDetailsPage() {
        LoginPage login = new LoginPage(driver);
        login.goTo();
        login.loginAs(VALID_ESS_USER, VALID_ESS_PASSWORD);

        Assert.assertTrue(login.isOnPersonalDetailsPage(), "Expected redirect to Personal Details page");
    }

    @DataProvider(name = "invalidLoginCombinations")
    public Object[][] invalidLoginCombinations() {
        return new Object[][] {
            // TC_MYINFO_LOGIN_02 - valid user, invalid password
            { VALID_ESS_USER, INVALID_ESS_PASSWORD },
            // TC_MYINFO_LOGIN_03 - invalid user, valid password
            { INVALID_ESS_USER, VALID_ESS_PASSWORD },
            // TC_MYINFO_LOGIN_04 - invalid user, invalid password
            { INVALID_ESS_USER, INVALID_ESS_PASSWORD },
        };
    }

    @Test(dataProvider = "invalidLoginCombinations",
          description = "TC_MYINFO_LOGIN_02-04 - negative login combinations")
    public void invalidCredentialsShowCredentialsNotValidError(String username, String password) {
        LoginPage login = new LoginPage(driver);
        login.goTo();
        login.loginAs(username, password);

        Assert.assertEquals(login.getErrorMessage(), "Credentials not valid");
    }

    @Test(description = "TC_MYINFO_PERSDETAILS_01 - field enabled/disabled state")
    public void employeeIdFieldIsDisabledOnPersonalDetailsForm() {
        LoginPage login = new LoginPage(driver);
        login.goTo();
        login.loginAs(VALID_ESS_USER, VALID_ESS_PASSWORD);

        PersonalDetailsPage details = new PersonalDetailsPage(driver);
        Assert.assertFalse(details.isEmployeeIdFieldEnabled(), "Employee ID must be HR-controlled (disabled)");
        Assert.assertTrue(details.isNickNameFieldEnabled(), "Nick Name must be employee-editable (enabled)");
    }

    @Test(description = "TC_MYINFO_PERSDETAILS_03 - save/update persistence")
    public void savingPersonalDetailsShowsConfirmationMessage() {
        LoginPage login = new LoginPage(driver);
        login.goTo();
        login.loginAs(VALID_ESS_USER, VALID_ESS_PASSWORD);

        PersonalDetailsPage details = new PersonalDetailsPage(driver);
        details.clickEdit();
        details.setNickName("Demo Nick");
        details.clickSave();

        Assert.assertEquals(details.getConfirmationMessage(), "details are saved");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
