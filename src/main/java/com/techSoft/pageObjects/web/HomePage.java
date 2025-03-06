package com.techSoft.pageObjects.web;
import com.techSoft.commonPlatformUtils.CommonSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends CommonSelenium {
    protected WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    By connectionsList = By.xpath("//p[@class='listHeading']");
    By sortOptions = By.xpath("//li[@class='dropdown-menu__item__2cQ8']");
    By sortResults = By.xpath("//a[@class='user-name']//h3");
    By sortByChar = By.xpath("//div[contains(text(),'Name (A-Z)')]");
    By connectBtn = By.xpath("//button[normalize-space()='Connect']//*[name()='svg']");

    public void moveOnConnect()
    {
        waitForElementToBeClickable(connectBtn,10);
        moveToElement(connectBtn);
    }

    public void selectConnectionType(String type)
    {
        List<WebElement> elements = driver.findElements(connectionsList);
        for (WebElement element : elements)
        {
            if (element.getText().equalsIgnoreCase(type))
            {
                element.click();
                break;
            }
        }
    }

    public void clickSortButton()
    {
        click(By.xpath("//span[@class='btnLabel__aEhVM']"));
    }

    public void clickedOnA2ZDropdowns()
    {
        click(sortByChar);
    }

    public List<WebElement> getConnectionOptions()
    {
        return driver.findElements(sortOptions);
    }

    public List<WebElement> getSortResults()
    {
        waitForElementClickable(driver.findElement(sortResults), 5);
        return driver.findElements(sortResults);
    }
}
