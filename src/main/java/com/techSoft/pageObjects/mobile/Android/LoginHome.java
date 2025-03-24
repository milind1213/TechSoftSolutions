package com.techSoft.pageObjects.mobile.Android;

import com.techSoft.commonPlatformUtils.CommonAndroid;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;

import java.util.List;

public class LoginHome extends CommonAndroid {
    public final AppiumDriver driver;

    public LoginHome(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // WIFI
    @AndroidFindBy(accessibility = "Preference")
    public WebElement preference;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='3. Preference dependencies']")
    private WebElement preferenceDependency;

    @AndroidFindBy(id = "android:id/checkbox")
    private WebElement wifiCheckBox;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
    private WebElement wifiSettings;

    @AndroidFindBy(id = "android:id/edit")
    private WebElement editField;

    @AndroidFindBy(className = "android.widget.Button")
    public List<WebElement> buttonList;

    public void clickPreference() {
        preference.click();
    }

    public void clickPreferenceDependency() {
        preferenceDependency.click();
    }

    public void clickWifiCheckBox() {
        wifiCheckBox.click();
    }

    public void wifiSettings() {
        wifiSettings.click();
    }



    public void clickButtonByIndex(int index) {
        buttonList.get(index).click();
    }

    public void mns() {
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
    }

//LONGPRESS

    @AndroidFindBy(accessibility = "Views")
    private WebElement views;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expandable Lists']")
    private WebElement expandableLists;

    @AndroidFindBy(accessibility = "1. Custom Adapter")
    private WebElement customAdapter;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"People Names\"]")
    private WebElement peopleNames;

    @AndroidFindBy(id = "android:id/title")
    private WebElement title;

    public void longPress() {
        views.click();
        expandableLists.click();
        customAdapter.click();
        longPressAction(peopleNames);
    }

    public void validatelongpressResult() {
        String menuText = title.getText();
        AssertJUnit.assertEquals(menuText, "Sample menu");
        AssertJUnit.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
    }

// 	SWIPE ACTION
    @AndroidFindBy(accessibility = "Gallery")
    private WebElement gallery;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1. Photos']")
    private WebElement photos;

    @AndroidFindBy(xpath = "(//android.widget.ImageView)[1]")
    private WebElement firstImage;

    public void performGalleryActions() {
        views.click();
        gallery.click();
        photos.click();
    }

    public void validateFocusImate() {
        AssertJUnit.assertEquals(firstImage.getAttribute("focusable"), "true");
        swipeAction(firstImage, "left");
        AssertJUnit.assertEquals(firstImage.getAttribute("focusable"), "false");
    }
}
