/*
package com.techSoft.Integrations;

import com.techSoft.driverFactory.WebBrowser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {
    private WebBrowser webBrowser;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @BeforeMethod
    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @BeforeMethod
    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        webBrowser = new WebBrowser();
        driver = webBrowser.init_driver(browserName);
    }

    @AfterMethod
    @After(order = 0)
    public void quitBrowser() {
        if (driver != null)
            driver.quit();
    }

    @AfterMethod
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(sourcePath, "image/png", screenshotName);
            } catch (Exception e) {
                System.err.println("Failed to capture a screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}*/
