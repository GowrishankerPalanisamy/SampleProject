package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    //Wait Wrapper Method
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    //Click Method
    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    //Write Text
    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //Read Text
    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    //Assert Equals
    public void assertEquals(By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);

    }

//    Assert True
    public void assertTrue(By elementBy) {
        waitVisibility(elementBy);
        Assert.assertTrue(driver.findElement(elementBy).isDisplayed());
    }

    //    Assert True
    public void assertTrue(String actual, String expected) {
        Assert.assertTrue(actual.contains(expected));
    }

    //    Assert True
    public boolean isDisplayed(By elementBy) {
        return driver.findElement(elementBy).isDisplayed();
    }

    public void selectValue(String element, String value) {
        Select select = new Select(driver.findElement(By.xpath(element)));
        select.selectByValue(value);
    }


}
