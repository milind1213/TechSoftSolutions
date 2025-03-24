package com.techSoft.pageObjects.mobile.MobileWeb;

import com.techSoft.commonPlatformUtils.CommonSelenium;
import org.openqa.selenium.WebDriver;

public class MobileWebDashboard extends CommonSelenium {
    WebDriver driver;
    PGLoginPage pgLoginPage;

    public MobileWebDashboard(WebDriver driver) {
        super(driver);
        this.driver = driver;
        pgLoginPage = new PGLoginPage(driver);
    }
    public PGLoginPage getPGLogin() {
        return pgLoginPage;
    }

}
