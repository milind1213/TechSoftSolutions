package com.techSoft.stepDefinitions;

import com.techSoft.integration.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AndroidApiDemoSettings {
    private final AndroidDriver driver;

    public AndroidApiDemoSettings() {
        if (BaseTest.getAndroidDriverInstance() == null) {
            try {
                new BaseTest().getAndroidDriver();  // Initialize if null
            } catch (Exception e) {
                throw new RuntimeException("Error initializing Android driver", e);
            }
        }
        this.driver = BaseTest.getAndroidDriverInstance();
        Assert.assertNotNull(driver, "Android driver is not initialized!");
    }

    @Given("I launch the API Demos app")
    public void launchApiDemosApp() {
        System.out.println("API Demos app is successfully launched.");
    }

    @When("I tap on {string}")
    public void tapOnOption(String option) {
        try {
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + option + "']"));
            element.click();
            System.out.println("Tapped on option: " + option);
        } catch (Exception e) {
            throw new RuntimeException("Failed to tap on option: " + option, e);
        }
    }

    @Then("I should see the {string} option")
    public void verifyOptionVisible(String option) {
        try {
            WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='" + option + "']"));
            Assert.assertTrue(element.isDisplayed(), option + " option is not visible!");
            System.out.println("Verified option is visible: " + option);
        } catch (Exception e) {
            throw new RuntimeException("Option not found or not visible: " + option, e);
        }
    }
}
