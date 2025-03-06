package com.techSoft.driverUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import java.net.MalformedURLException;
import java.util.*;

public class WebBrowser {
    protected WebDriver driver;
    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<>());

    public WebDriver getBrowserDriver(String browserType, boolean isHeadless) throws MalformedURLException
    {
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                addCommonArguments(chromeOptions, isHeadless);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                addCommonArguments(firefoxOptions, isHeadless);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                addCommonArguments(edgeOptions, isHeadless);
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
        if (driver != null) {
            webDriver.set(driver);
            webDriverList.add(driver);
        }
        return driver;
    }

    private void addCommonArguments(Object options, boolean isHeadless) {
        List<String> commonArguments = Arrays.asList(
                "--no-sandbox", "--disable-dev-shm-usage", "--disable-extensions",
                "--dns-prefetch-disable", "--disable-gpu", "--start-maximized",
                "--disable-web-security", "--no-proxy-server"
        );

        if (options instanceof ChromeOptions chromeOptions) {
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            if (isHeadless) {
                chromeOptions.addArguments("--headless", "--window-size=1920,1080");
            }
            chromeOptions.addArguments(commonArguments);
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

            Map<String, Object> prefs = Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false
            );
            chromeOptions.setExperimentalOption("prefs", prefs);

        } else if (options instanceof FirefoxOptions firefoxOptions) {
            firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            if (isHeadless) {
                firefoxOptions.addArguments("--headless", "--window-size=1920,1080");
            }
            firefoxOptions.addArguments(commonArguments);
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        } else if (options instanceof EdgeOptions edgeOptions) {
            edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            edgeOptions.addArguments(commonArguments);
            if (isHeadless) {
                edgeOptions.addArguments("--headless", "--window-size=1920,1080");
            }
        }
    }
}