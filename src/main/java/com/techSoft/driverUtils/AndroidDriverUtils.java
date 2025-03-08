package com.techSoft.driverUtils;

import com.techSoft.CommonConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class AndroidDriverUtils {
    private static final ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();
    public AndroidDriver getAndroidDriver() throws MalformedURLException, URISyntaxException
    {
        if (androidDriver.get() == null)
        {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(CommonConstants.DEVICE_NAME);
            options.setPlatformName(CommonConstants.ANDROID);
            options.setPlatformVersion("12");
            options.setApp(CommonConstants.CURRENT_DIRECTORY +"/apps/ApiDemos-debug.apk");
            options.setAutomationName(CommonConstants.AUTOMATION_NAME);
            options.setNoReset(false);
            options.setFullReset(false);
            options.setCapability("autoGrantPermissions", true);

            AndroidDriver driver = new AndroidDriver(new URI(CommonConstants.APPIUM_SERVER_URL).toURL(), options);
            androidDriver.set(driver);
        }

        return androidDriver.get();
    }

    // Get current AndroidDriver instance
    public AndroidDriver getAndroidDriverInstance()
    {
        return androidDriver.get();
    }



}
