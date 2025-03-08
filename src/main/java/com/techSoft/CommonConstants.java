package com.techSoft;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class CommonConstants {
    public static final String PLATFORM = "platform";
    public static final String COMMON = "common";
    public static final String TECHSOFT = "techSoft";
    public static final String APP = "app";
    public static final String LOCAL ="local";
    public static final String REMOTE ="remote";
    public static final String SELENIUM_GRID ="selenium grid";
    public static final String LAMBDATEST ="lambdatest";
    public static final String BROWSERSTACK ="browserstack";
    public static final String JENKINS ="jenkins";
    public static final String PROD ="prod";
    public static final String STAGE ="stage";
    public static final String UAT ="uat";
    //ANDROID/IOS CONFIGURATIONS
    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String DEVICE_NAME = "Pixel 8 Pro";
    public static final String AUTOMATION_NAME ="UiAutomator2";
    public static final String ANDROID ="Android";
    public static final String IOS ="iOS";
    public static final String APPIUM_SERVER_PORT_NUMBER ="4723";
    public static final String SERVER_IP ="127.0.0.1";
    public static final String MAINJS_PATH = "C:/Users/milind.ghongade/AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
    public static final String MOBILE_PLATFORM ="app.mobile.platforms";
    public static final String APP_PACKAGE = "app.base.pkg";
    public static final String APP_ACTIVITY = "app.activity";
    public static final String ANDROID_PLATFORM = "app.android.platform";
    public static final String IOS_PLATFORM = "app.ios.platform";
    public static final String PLATFORM_VERSION = "app.platform.version";
    public static final String APK_VERSION = "app.android.version";
    public static final String APP_PATH = "app.path";
    // WEB CONFIGURATIONS
    public static final String EXECUTION_ENV ="techSoft.execution_env";
    public static final String GRID_HUB_URL ="techSoft.hubUrl";
    public static final String GRID_PLATFORM ="techSoft.platform";
    public static final String GRID_OS ="techSoft.platform";
    public static final String TECHSOFT_USERNAME = "techSoft.username";
    public static final String TECHSOFT_PASSWORD = "techSoft.password";
    public static final String TECHSOFT_WEBURL = "techSoft.webUrl";
    public static final String TECHSOFT_STAGE = "techSoft.webUrl";
    public static final String TECHSOFT_PRO = "techSoft.webUrl";
    public static final String TECHSOFT_PREPROD = "techSoft.webUrl";
    public static final String BROWSER = "techSoft.browser";
    public static final String RUNMODE_IS_HEADLESS = "techSoft.headless";
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String GMAIL_USERNAME = "common.username";
    public static final String GMAIL_PASSWORD = "common.password";
    public static final String GMAIL_TO ="common.to";
    public static final String GMAIL_FROM ="common.from";
    public static final String GMAIL_SUBJECT ="common.subject";
    public static final String GMAIL_TEXT = "common.body";
    public static final String SLACK_CHANNEL = "common.slackChannel";
    public static final String SLACK_TOKEN = "common.slackToken";

    public static String generateRandomText(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
    public static String generateRandomEmail(int length) {
        String randomText = RandomStringUtils.randomAlphanumeric(length);
        return  randomText + "@example.com";

    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
