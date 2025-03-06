package com.techSoft.integration;
import com.techSoft.CommonConstants;
import com.techSoft.driverUtils.AndroidDriverUtils;
import com.techSoft.driverUtils.WebBrowser;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Base64;
import static com.techSoft.utils.FileUtil.getProperty;

public class TestHooks {
    private static WebDriver driver;
    private static AndroidDriver androidDriver;
    private static AndroidDriverUtils androidUtils;
    WebBrowser browser;

    public WebDriver getWebDriver() {
        browser = new WebBrowser();
        try {
            if (getProperty(CommonConstants.TECHSOFT, CommonConstants.EXECUTION_ENV).equalsIgnoreCase(CommonConstants.LOCAL)) {
                driver = browser.getBrowserDriver(getProperty(CommonConstants.TECHSOFT, CommonConstants.BROWSER),
                        Boolean.parseBoolean(getProperty(CommonConstants.TECHSOFT, CommonConstants.RUNMODE_IS_HEADLESS)));
                System.out.println("Launching the Local " + (Boolean.parseBoolean(getProperty(CommonConstants.TECHSOFT, CommonConstants.RUNMODE_IS_HEADLESS)) ? "Headless " : "") +
                        getProperty(CommonConstants.TECHSOFT, CommonConstants.BROWSER) + " browser");

            } else if (getProperty(CommonConstants.TECHSOFT, CommonConstants.EXECUTION_ENV).equalsIgnoreCase(CommonConstants.REMOTE)) {

                System.out.println("Launching the Remote " + (Boolean.parseBoolean(getProperty(CommonConstants.TECHSOFT,
                        CommonConstants.RUNMODE_IS_HEADLESS)) ? "Headless " : "") + getProperty(CommonConstants.TECHSOFT, CommonConstants.BROWSER) + " browser");
            }
            if (driver != null) {
                setDriverTimeouts(driver);
            } else {
                throw new RuntimeException("Failed to initialize WebDriver");
            }

         } catch (Exception e) {
             System.err.println("Failed to launch the Browser: " + e.getMessage());
         }
           return driver;
    }


    public AndroidDriver getAndroidDriver() throws MalformedURLException {
        try {
            androidUtils = new AndroidDriverUtils();
            androidUtils.startServer();
        } catch (Exception e) {
             throw new RuntimeException("Failed to Start Appium Server");
        }
            androidDriver = new AndroidDriverUtils().getAndroidDriver(getProperty(CommonConstants.APP,CommonConstants.DEVICE_NAME),
                    getProperty(CommonConstants.APP,CommonConstants.PLATFORM_VERSION),
                    getProperty(CommonConstants.APP,CommonConstants.APP_PATH));
            System.out.println("Launching the Android Application");

        if (androidDriver != null)
        {
                setDriverTimeouts(androidDriver);
            } else {
                throw new RuntimeException("Failed to initialize AndroidDriver");
        }
          return androidDriver;
    }


    @After
    public static void afterScenario(Scenario scenario)
    {
     if (scenario.isFailed()) {
         System.out.println("Scenario Failed - Taking Screenshot");
         if (driver != null) {
             String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
             byte[] decodedScreenshot = Base64.getDecoder().decode(base64Screenshot);
             scenario.attach(decodedScreenshot, "image/png", "Failure Screenshot");
             System.out.println("Screenshot captured and attached as Base64!");
         } else {
             System.err.println("Driver is NULL");
         }
       }
    }

    @After
    public static void tearDown()
    {
        if (driver != null ) {
            driver.quit();
        }
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }

    @AfterAll
    public static void stopAppiumServer()
    {
        try {
            androidUtils = new AndroidDriverUtils();
            androidUtils.stopServer();
        } catch (Exception e) {
            throw new RuntimeException("Failed to Stop Server");
        }
    }

    private void setDriverTimeouts(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));

        if (!(driver instanceof AndroidDriver))
        {
            driver.manage().window().maximize();
        }
    }
}
