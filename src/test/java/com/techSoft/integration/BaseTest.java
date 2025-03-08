package com.techSoft.integration;

import com.techSoft.CommonConstants;
import com.techSoft.driverUtils.AndroidDriverUtils;
import com.techSoft.driverUtils.WebBrowser;
import com.techSoft.utils.ServerUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import java.time.Duration;

import static com.techSoft.utils.FileUtil.getProperty;

public class BaseTest {
    private final WebBrowser browser = new WebBrowser();
    private static AndroidDriverUtils androidUtils;

    public WebDriver getWebDriver()
    {
        WebDriver driver = null;
        String env = getProperty(CommonConstants.TECHSOFT, CommonConstants.EXECUTION_ENV);

        if (env.equals(CommonConstants.LOCAL)) {
            driver = browser.getBrowserDriver(
                    getProperty(CommonConstants.TECHSOFT, CommonConstants.BROWSER),
                    Boolean.parseBoolean(getProperty(CommonConstants.TECHSOFT, CommonConstants.RUNMODE_IS_HEADLESS))
            );

            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
                driver.manage().window().maximize();
            }

            System.out.println("Launching the Local " +
                    (Boolean.parseBoolean(getProperty(CommonConstants.TECHSOFT, CommonConstants.RUNMODE_IS_HEADLESS)) ? "Headless " : "") +
                    getProperty(CommonConstants.TECHSOFT, CommonConstants.BROWSER) + " browser");
        } else {
            System.out.println("Remote execution not implemented.");
        }
        return driver;
     }


    public static WebDriver getDriver()
     {
        return WebBrowser.getDriver();
     }


    public synchronized AndroidDriver getAndroidDriver(){
        AndroidDriver androidDriver = null;
        androidUtils = new AndroidDriverUtils();
        try {
            androidDriver =  androidUtils.getAndroidDriver();

            if (androidDriver != null && androidDriver.getSessionId() != null) {
                System.out.println("======== App launched successfully! ========");
            } else {
                System.out.println("App launch failed!");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to start Appium Server or Initialize Driver", e);
        }
        return androidDriver;
    }

    public static AndroidDriver getAndroidDriverInstance() {
        return androidUtils != null ? androidUtils.getAndroidDriverInstance() : null;
    }

    @BeforeAll
    public static void globalSetup()
    {
        if (getProperty(CommonConstants.APP, CommonConstants.MOBILE_PLATFORM).equalsIgnoreCase("Android"))
         {
             ServerUtils.startServer();
         } else if ( getProperty(CommonConstants.APP, CommonConstants.MOBILE_PLATFORM).equalsIgnoreCase("iOS"))
         {
             ServerUtils.startServer();
         }
        System.out.println("====== Test Execution Started ======");
    }

    @AfterAll
    public static void globalTearDown() {
       if (getProperty(CommonConstants.APP, CommonConstants.MOBILE_PLATFORM).equalsIgnoreCase("Android"))
        {
            ServerUtils.stopServer();
        } else if ( getProperty(CommonConstants.APP, CommonConstants.MOBILE_PLATFORM).equalsIgnoreCase("iOS"))
        {
            ServerUtils.stopServer();
        }
        System.out.println("====== Test Execution Completed ======");
    }


    @After
    public void tearDown(Scenario scenario)
    {
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }
        WebBrowser.quitDriver();
        AndroidDriver androidDriver = getAndroidDriverInstance();
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }


    private void captureScreenshot(Scenario scenario)
    {
       if (getDriver() instanceof TakesScreenshot driver)
        {
            scenario.attach(driver.getScreenshotAs(OutputType.BYTES), "image/png", "Failure Screenshot (Web)");
        } else if (getAndroidDriverInstance() != null)
        {
            scenario.attach(getAndroidDriverInstance().getScreenshotAs(OutputType.BYTES), "image/png", "Failure Screenshot (Android)");
        } else
        {
            System.out.println("No active driver found for screenshot capture.");
        }
    }

}
