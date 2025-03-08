package com.techSoft.pageObjects.web;

import com.techSoft.commonPlatformUtils.CommonSelenium;
import org.openqa.selenium.WebDriver;

public class WebDashboard extends CommonSelenium {

    WebDriver driver;
    public final HomePage homepage;
    public final LoginPage loginpage;

    public WebDashboard(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
    }

    public HomePage getHomePage()
    {
        return homepage;
    }

    public LoginPage getLoginPage()
    {
        return loginpage;
    }
}
