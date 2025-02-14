package com.techSoft;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class CommonConstants {
    public static final String EXECUTION_ENV ="techSoft.execution_env";
    public static final String EXECUTION_PLATFORM = "techSoft.platform";
    public static final String HUB_URL ="techSoft.hubUrl";
    public static final String TECHSOFT_USERNAME = "techSoft.username";
    public static final String TECHSOFT_PASSWORD = "techSoft.password";
    public static final String TECHSOFT_WEBURL = "techSoft.webUrl";
    public static final String REST_URL = "techSoft.restUrl";
    public static final String REST_URL1 = "techSoft.restUrl1";
    public static final String FD_URL = "techSoft.webFdUrl";
    public static final String BROWSER = "techSoft.browser";
    public static final String RUNMODE_IS_HEADLESS = "techSoft.headless";
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String COMMON = "common";
    public static final String TECHSOFT = "techSoft";
    public static final String GMAIL_USERNAME = "common.username";
    public static final String GMAIL_PASSWORD = "common.password";
    public static final String GMAIL_TO ="common.to";
    public static final String GMAIL_FROM ="common.from";
    public static final String GMAIL_SUBJECT ="common.subject";
    public static final String GMAIL_TEXT ="common.text";
    public static final String LOCAL ="local";
    public static final String REMOTE ="remote";
    public static final String PROD ="prod";
    public static final String SLACK_CHANNEL = "common.slackChannel";
    public static final String SLACK_TOKEN = "common.slackToken";
    public static final String CURRENT_WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final String APP_PACKAGE = "techsoft.properties";


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
