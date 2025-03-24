package com.techSoft.driverUtils;
import com.techSoft.CommonConstants;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Set;

import static com.techSoft.CommonConstants.CHROME;


public class AndroidDriverUtils {
    private static final ThreadLocal<AndroidDriver> androidAppDriver = new ThreadLocal<>();
    private static final ThreadLocal<AndroidDriver> androidWebDriver = new ThreadLocal<>();

    // Get AndroidDriver
    public AndroidDriver getAndroidDriver() throws MalformedURLException, URISyntaxException {
        if (androidAppDriver.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(CommonConstants.DEVICE_NAME);
            options.setPlatformName(CommonConstants.ANDROID);
            options.setPlatformVersion("10");
            options.setApp(CommonConstants.CURRENT_DIRECTORY + "/apps/ApiDemos-debug.apk");
            options.setAutomationName(CommonConstants.AUTOMATION_NAME);
            options.setNoReset(false);
            options.setFullReset(false);
            AndroidDriver driver = new AndroidDriver(new URI(CommonConstants.APPIUM_SERVER_URL).toURL(), options);

            androidAppDriver.set(driver);
        }
        return androidAppDriver.get();
    }

    public AndroidDriver getAndroidDriverInstance() {
        return androidAppDriver.get();
    }


    // Get Android MobileWebDriver
    public AndroidDriver getAndroidMobileWebDriver() throws MalformedURLException, URISyntaxException {
        if (androidWebDriver.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(CommonConstants.DEVICE_NAME);
            options.setPlatformName(CommonConstants.ANDROID);
            options.setPlatformVersion("10"); // Changed to match emulator-5554
            options.setAutomationName(CommonConstants.AUTOMATION_NAME);
            options.setCapability("browserName", CHROME);
            options.setNoReset(true);
            options.setFullReset(false);

            // Optional: Specify ChromeDriver if needed
            options.setChromedriverExecutable(CommonConstants.CURRENT_DIRECTORY + CommonConstants.CHROME_DRIVER_PATH);
            AndroidDriver driver = new AndroidDriver(new URI(CommonConstants.APPIUM_SERVER_URL).toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            androidWebDriver.set(driver);
        }
        return androidWebDriver.get();
    }

    public static AndroidDriver getAndroidWebDriverInstance() {
        return androidWebDriver.get();
    }


    @AfterTest
    public void quitDrivers() {
        if (androidAppDriver.get() != null) {
            androidAppDriver.get().quit();
            androidAppDriver.remove();
        }
        if (androidWebDriver.get() != null) {
            androidWebDriver.get().quit();
            androidWebDriver.remove();
        }
    }


    // Test Method to Verify Mobile Web Driver
    @Test
    public void checkMobileWeb() throws MalformedURLException, URISyntaxException {
        AndroidDriver driver = getAndroidMobileWebDriver();
        driver.get("https://github.com/");
        System.out.println("Page Title: " + driver.getTitle());
        // Optional: Check available contexts (should only be WEBVIEW_chrome for pure browser)
        Set<String> contexts = driver.getContextHandles();
        System.out.println("Available Contexts: " + contexts);

        // No need to switch contexts for pure browser automation, but included for debugging
        for (String context : contexts) {
            if (context.toLowerCase().contains("webview")) {
                driver.context(context);
                System.out.println("Switched to Context: " + context);
                break;
            }
        }
    }



}
