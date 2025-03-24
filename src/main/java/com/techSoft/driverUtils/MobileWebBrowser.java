package com.techSoft.driverUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileWebBrowser {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    WebDriver driver ;
    public synchronized WebDriver getSeleniumMobileWebDriverInstance(String deviceName, boolean isHeadless)
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        addCommonArguments(options, isHeadless);
        setupMobileEmulation(options, deviceName);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));

        webDriver.set(driver);
        return driver;
    }

    public static WebDriver getDriver()
    {
        return webDriver.get();
    }

    public static void quitDriver()
    {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

    private void setupMobileEmulation(ChromeOptions options, String deviceName)
    {
        Map<String, Object> mobileEmulation = new HashMap<>();
        if (deviceName != null && !deviceName.isEmpty()) {
            mobileEmulation.put("deviceName", deviceName);
        } else {
            Map<String, Object> deviceMetrics = Map.of(
                    "width", 360,
                    "height", 800,
                    "pixelRatio", 3.0
            );
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Mobile Safari/537.36");
        }
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
    }

    private void addCommonArguments(ChromeOptions options, boolean isHeadless)
    {
        List<String> commonArguments = Arrays.asList(
                "--no-sandbox", "--disable-dev-shm-usage", "--disable-extensions",
                "--dns-prefetch-disable", "--disable-gpu", "--disable-web-security",
                "--no-proxy-server", "--ignore-certificate-errors", "--disable-popup-blocking"
        );
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (isHeadless) {
            options.addArguments("--headless", "--window-size=412,915");
        }
        options.addArguments(commonArguments);
        Map<String, Object> prefs = Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false
        );
        options.setExperimentalOption("prefs", prefs);
    }

}
