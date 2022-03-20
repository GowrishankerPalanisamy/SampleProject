package pages;
import io.qameta.allure.Step;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Locale;
public class DashBoardPage extends BasePage {

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    private By lnkDateTime = By.xpath("//button[@title='DATE/TIME']");
    private By tabHistoric = By.xpath("//a[contains(.,'Historic')]");
    private By drpFromChevron = By.xpath("//div[@selection='from']//i[contains(@class,'fa fa-chevron-down')]");
    private By fromCalMonthYear = By.xpath("//div[@selection='from']//span[@class='month']");
    private By fromLeftChevron = By.xpath("//div[@selection='from']//i[@class='fa fa-chevron-left']");
    private By fromRightChevron = By.xpath("//div[@selection='from']//i[@class='fa fa-chevron-right']");
    private String selectFromDay = "//div[@selection='from']//div[@class='day' and contains(.,'${date}')]";
    private String selectFromhour = "//div[@selection='from']//select[@name='hours']";
    private String selectFromMins = "//div[@selection='from']//select[@name='minutes']";
    private By drpToChevron = By.xpath("//div[@selection='to']//i[contains(@class,'fa fa-chevron-down')]");
    private By toCalMonthYear = By.xpath("//div[@selection='to']//span[@class='month']");
    private By toLeftChevron = By.xpath("//div[@selection='to']//i[@class='fa fa-chevron-left']");
    private By toRightChevron = By.xpath("//div[@selection='to']//i[@class='fa fa-chevron-right']");
    private String selectToDay = "//div[@selection='to']//div[@class='day' and contains(.,'${date}')]";
    private String selectTohour = "//div[@selection='to']//select[@name='hours']";
    private String selectToMins = "//div[@selection='to']//select[@name='minutes']";
    private By clickSubmit = By.xpath("//form[@id='date-range-selector']//button[text()='Submit']");
    private By dashboardTitle = By.xpath("//h1[text()='Test Project']");
    private By dashboardTab1 = By.xpath("//span[text()='Singular']");
    private By dashboardTab2 = By.xpath("//span[text()='Interval']");
    private By dashboardTab3 = By.xpath("//span[text()='Constant']");
    private By getFromDate = By.xpath("//div[@class='pull-right date-range-group top-10']/span[1]");
    private By getToDate = By.xpath("//div[@class='pull-right date-range-group top-10']/span[3]");

    private By clickAdd = By.xpath("(//tbody[@class='MuiTableBody-root'])[1]/tr[1]/td[6]/div");
    private By addValue = By.xpath("//input[@id='value']");
    private By btnSubmit = By.xpath("//span[text()='Submit']");
    private By clickEdit = By.xpath("(//tbody[@class='MuiTableBody-root'])[1]/tr[1]/td[7]/div");
    private By getValue = By.xpath("(//tbody[@class='MuiTableBody-root'])[3]/tr[1]/td[1]");

    @Step("Click DateTime link from header")
    public void clickDateTime() {
        click(lnkDateTime);
        click(tabHistoric);
    }

    @Step("Click Historic tab from Date time window")
    public void clickHistoricTab() {
        click(tabHistoric);
    }

    @Step("Click from calendar chevron")
    public void clickFromChevron() {
        click(drpFromChevron);
    }

    @Step("click From calendar")
    public void selectFromDate(String fromDate) {
        String strDate[] = fromDate.split(",");
        String strMonthDay[] = strDate[0].split(" ");
        String strYearTime[] = strDate[1].split(" ");
        String strTime[] = strYearTime[2].split(":");
        selectFromYearMonth(strMonthDay, strYearTime);
        selectFromDay(strMonthDay[1]);
        selectFromTime(strTime[0], strTime[1]);
    }
    @Step("Click to calendar chevron")
    public void clickToChevron() {
        click(drpToChevron);
    }
    @Step("click To calendar")
    public void selectToDate(String toDate) {
        String strDate[] = toDate.split(",");
        String strMonthDay[] = strDate[0].split(" ");
        String strYearTime[] = strDate[1].split(" ");
        String strTime[] = strYearTime[2].split(":");
        selectToYearMonth(strMonthDay, strYearTime);
        selectToDay(strMonthDay[1]);
        selectToTime(strTime[0], strTime[1]);
    }
    @Step("click submit button")
    public void clickSubmit() {
        click(clickSubmit);
    }

    public void selectFromYearMonth(String[] strMonthDay, String[] strYearTime) {
        String strActYearMonth[] = readText(fromCalMonthYear).split(" ");
        int expectedMonthVal = monthvalue(strMonthDay[0]);
        if (Integer.parseInt(strYearTime[1]) > Integer.parseInt(strActYearMonth[1])) {
            do {
                driver.findElement(fromRightChevron).click();
            } while (!readText(fromCalMonthYear).contains(strYearTime[1]));
            clickFromMonth(expectedMonthVal);
        } else if (Integer.parseInt(strYearTime[1]) < Integer.parseInt(strActYearMonth[1])) {
            do {
                driver.findElement(fromLeftChevron).click();
            } while (!readText(fromCalMonthYear).contains(strYearTime[1]));
            clickFromMonth(expectedMonthVal);
        } else if (Integer.parseInt(strYearTime[1]) == Integer.parseInt(strActYearMonth[1])) {
            clickFromMonth(expectedMonthVal);
        }

    }

    public void clickFromMonth(int expectedMonthVal) {
        int currentMonthVal;
        String[] monthName = readText(fromCalMonthYear).split(" ");
        currentMonthVal = monthvalue(monthName[0]);
        while (!(currentMonthVal == expectedMonthVal)) {
            if (expectedMonthVal > currentMonthVal) {
                driver.findElement(fromRightChevron).click();
            } else {
                driver.findElement(fromLeftChevron).click();
            }
            monthName = readText(fromCalMonthYear).split(" ");
            currentMonthVal = monthvalue(monthName[0]);
        }
    }
    public void selectFromDay(String strDay) {
        driver.findElement(By.xpath(selectFromDay.replace("${date}", strDay))).click();
    }

    public void selectFromTime(String hour, String mins) {
        selectValue(selectFromhour, hour);
        selectValue(selectFromMins, mins);
    }

    public void selectToYearMonth(String[] strMonthDay, String[] strYearTime) {
        String strActYearMonth[] = readText(toCalMonthYear).split(" ");
        int expectedMonthVal = monthvalue(strMonthDay[0]);
        if (Integer.parseInt(strYearTime[1]) > Integer.parseInt(strActYearMonth[1])) {
            do {
                driver.findElement(toRightChevron).click();
            } while (!readText(toCalMonthYear).contains(strYearTime[1]));
            clickToMonth(expectedMonthVal);
        } else if (Integer.parseInt(strYearTime[1]) < Integer.parseInt(strActYearMonth[1])) {
            do {
                driver.findElement(toLeftChevron).click();
            } while (!readText(toCalMonthYear).contains(strYearTime[1]));
            clickToMonth(expectedMonthVal);
        } else if (Integer.parseInt(strYearTime[1]) == Integer.parseInt(strActYearMonth[1])) {
            clickToMonth(expectedMonthVal);
        }

    }

    public void clickToMonth(int expectedMonthVal) {
        int currentMonthVal;
        String[] monthName = readText(toCalMonthYear).split(" ");
        currentMonthVal = monthvalue(monthName[0]);
        while (!(currentMonthVal == expectedMonthVal)) {
            if (expectedMonthVal > currentMonthVal) {
                driver.findElement(toRightChevron).click();
            } else {
                driver.findElement(toLeftChevron).click();
            }
            monthName = readText(toCalMonthYear).split(" ");
            currentMonthVal = monthvalue(monthName[0]);
        }
    }

    public void selectToDay(String strDay) {
        driver.findElement(By.xpath(selectToDay.replace("${date}", strDay))).click();
    }
    public void selectToTime(String hour, String mins) {
        selectValue(selectTohour, hour);
        selectValue(selectToMins, mins);
    }

    public Integer monthvalue(String strMonth) {
        DateTimeFormatter format = DateTimeFormat.forPattern("MMM");
        DateTime instance = format.withLocale(Locale.ENGLISH).parseDateTime(strMonth);
        int month_number = instance.getMonthOfYear();
        return month_number;
    }
    @Step("Validate title Dashboard page")
    public void validateDashboardPage(){
        assertTrue(dashboardTitle);
        assertTrue(dashboardTab1);
        assertTrue(dashboardTab2);
        assertTrue(dashboardTab3);
    }
    @Step("Validate date in Dashboard page")
    public void validateDateDashboardPage(String calendarFrom, String calendarTo){
        assertTrue(readText(getFromDate),calendarFrom);
        assertTrue(readText(getToDate),calendarTo);
    }
    @Step("Adding value to Singular data")
    public void addValueField(String val){
        click(clickAdd);
        writeText(addValue,val);
        click(btnSubmit);
    }
    @Step("Validate the added value in Edit Singular data page ")
    public void editValueField(String val){
        click(clickEdit);
        assertTrue(readText(getValue),val);
    }
}