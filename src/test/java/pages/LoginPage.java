package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private By txtUserName = By.xpath("//input[@id='emailAddress']");
    private By txtPassword = By.xpath("//input[@id='password']");
    private By btnSignIn = By.xpath("//h6[contains(.,'Sign In')]");
    private By companyLogo = By.xpath("//div[@ng-show='companyLogo']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Launch base Url")
    public void launchBaseUrl(String baseUrl) {
        driver.get(baseUrl);
    }

    @Step("Enter userName")
    public void enterUserName(String userName) {
        writeText(txtUserName, userName);
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        writeText(txtPassword, password);
    }

    @Step("Click SignIn button")
    public void clickSignIn() {
        click(btnSignIn);
        assertTrue(companyLogo);
    }

    @Step("Navigate to Dashboard page")
    public void navigateToDashboard(String dashboardUrl) {
        driver.navigate().to(dashboardUrl);
        assertTrue(driver.getCurrentUrl(), "dashboards");
    }
}
