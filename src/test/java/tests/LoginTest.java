package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;

    @BeforeClass
    public void initiateSetup() throws Exception{
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
    }


    @BeforeClass
    @Description("This test is to validate the login page")
    @Parameters({"baseUrl", "userName", "password"})
    public void loginToApplication(String baseUrl, String userName, String password) {
        loginPage.launchBaseUrl(baseUrl);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();

    }

    @Test(priority = 0)
    @Description("This test is to validate the login page")
    @Parameters({"dashboardUrl","CalendarFrom","CalendarTo"})
    public void navigateToDashboard(String dashboardUrl, String calendarFrom, String CalendarTo) {
        loginPage.navigateToDashboard(dashboardUrl);
        dashBoardPage.clickDateTime();
        dashBoardPage.clickHistoricTab();
        dashBoardPage.clickFromChevron();
        dashBoardPage.selectFromDate(calendarFrom);
        dashBoardPage.clickToChevron();
        dashBoardPage.selectToDate(CalendarTo);
        dashBoardPage.clickSubmit();
        dashBoardPage.validateDateDashboardPage(calendarFrom,CalendarTo);
    }
    @Test(priority = 1)
    @Description("This test is to validate the Dashboard page")
    public void validateDashboard() {
        dashBoardPage.validateDashboardPage();
    }

    @Test(priority = 2)
    @Description("This test is to add value to Singular data")
    @Parameters({"addValue"})
    public void addingSingularData(String addValue) {
        dashBoardPage.addValueField(addValue);
    }

    @Test(priority = 3)
    @Description("This test is to add value to Singular data")
    @Parameters({"addValue"})
    public void validateAddedData(String addValue) {
        dashBoardPage.editValueField(addValue);
    }

}
