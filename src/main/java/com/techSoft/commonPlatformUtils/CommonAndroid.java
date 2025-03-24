package com.techSoft.commonPlatformUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CommonAndroid extends CommonSelenium{
    AndroidDriver driver;

    public CommonAndroid(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void wait(int seconds) {
        waitForProgressBarDisappear();
        int milliseconds = seconds * 1000;
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            Assert.fail("Failed to wait for [" + seconds + "] seconds... ");
        }
    }

    public void longPressAction(WebElement ele) {
        try {
            ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                    ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to perform long press action on element: [" + ele + "]");
        }
    }

    public void swipeAction(WebElement ele, String direction) {
        try {
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
                    ((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.75));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to perform swipe action on element: [" + ele + "] with direction: [" + direction + "]");
        }
    }

    public void sendKeys(WebElement locators, String text) {
        try {
            wait(1);
            locators.sendKeys(text);
            wait(1);
            hideKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
            hideKeyboard();
            Assert.fail("Failed to send text: [" + text + "]");
        }
    }

    public void hideKeyboard() {
        wait(1);
        try {
            if (driver.isKeyboardShown()) {
                driver.hideKeyboard();
                wait(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to hide keyboard..");
        }
    }

    public void rotateDevice(DeviceRotation deviceRotation) {
        try {
            driver.rotate(deviceRotation);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to rotate device with rotation: [" + deviceRotation + "]");
        }
    }

    public void rotateToPortrait() {
        try {
            DeviceRotation portrait = new DeviceRotation(0, 0, 0);
            driver.rotate(portrait);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to rotate device to portrait mode");
        }
    }

    public void waitForWebElementToBeClickable(WebElement element, Duration seconds) {
        try {
            new WebDriverWait(driver, seconds).pollingEvery(Duration.ofMillis(250))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to find element [" + element + "]..even waited for [" + seconds + "] ");
        }
    }


    public WebElement waitForWebElementToBeVisible(WebElement element, Duration seconds) {
        return new WebDriverWait(driver, seconds).pollingEvery(Duration.ofMillis(250))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public int getElementSize(List<WebElement> webElement) {
        wait(1);
        return webElement.size();
    }

    public void click(WebElement element) {
        try {
            wait(1);
            element.click();
            wait(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click: [" + element + "]");
        }
    }

    public void clickWithoutWait(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click : [" + element + "]");
        }
    }



    public boolean isElementDisplayed(WebElement webelement) {
        wait(2);
        boolean result = false;
        try {
            if (webelement.isDisplayed()) {
                wait(1);
                result = true;
            }
        } catch (Exception ex) {
            wait(1);
            result = false;
        }
        return result;
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }

    public String waitForProgressBarDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.app.nobrokerhood:id/progressBar")));
            Thread.sleep(2);
            return "Done";
        } catch (Exception e) {
            return "Progress bar/loading icon is still appearing even after waiting for 120 sec.";
        }
    }

    public void pressDeviceHomeButton() {
        try {
            wait(1);
            KeyEvent keyEvent = new KeyEvent();
            driver.pressKey(keyEvent.withKey(AndroidKey.HOME));
            wait(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to press home button of device.");
        }
    }

    public void pressBackKey(int times) {
        try {
            for (int i = 1; i <= times; i++) {
                KeyEvent keyEvent = new KeyEvent();
                wait(1);
                driver.pressKey(keyEvent.withKey(AndroidKey.BACK));
                wait(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to back..");
        }
    }



    public String getText(WebElement element) {
        try {
            wait(1);
            return element.getText();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to get text from element: [" + element + "]");
            throw e;
        }
    }


}