package com.techSoft.pageObjects.mobile.Android;

import com.techSoft.commonPlatformUtils.CommonAndroid;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDashboard extends CommonAndroid {
    AndroidDriver driver;
    public final LoginHome loginhome;

    public AndroidDashboard(AndroidDriver driver)
    {
        super(driver);
        this.driver = driver;
        loginhome = new LoginHome(driver);

    }

    public LoginHome getloginHome() {
        return loginhome;
    }

}
