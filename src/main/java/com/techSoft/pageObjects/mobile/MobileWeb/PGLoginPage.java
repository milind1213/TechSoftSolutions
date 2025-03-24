package com.techSoft.pageObjects.mobile.MobileWeb;
import com.techSoft.commonPlatformUtils.CommonSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PGLoginPage extends CommonSelenium {
   WebDriver driver;
    public PGLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected String loginOptions = "//div[contains(@class, 'sso-action-btn')]//span[contains(text(), '%s')]";

    public void buttonWithText(String text){
        By buttonText = By.xpath(String.format("//button[contains(text(), '%s')]", text));
        waitForElementClickable(buttonText,10);
        click(buttonText);
    }

    public void loginOptionsWithText(String text){
        By dynamicLoginOption = By.xpath(String.format(loginOptions, text));
        waitForElementClickable(dynamicLoginOption,10);
        click(dynamicLoginOption);
    }

    public void enterLoginCredentials(String email, String password){
        By emailField = By.id("email");
        By passwordField = By.id("password");

        if (!isElementDisplayed(emailField)) {
            waitForElementDisplay(emailField, 10);
        }
        sendKeys(emailField, email);
        sendKeys(passwordField, password);
    }


    public void clickLoginButton(){
        click(By.xpath("//button[contains(@type,'submit')]"));
    }

}

