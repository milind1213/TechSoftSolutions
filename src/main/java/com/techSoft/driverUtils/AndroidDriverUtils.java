package com.techSoft.driverUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class AndroidDriverUtils {

    private static final ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();
    private AppiumDriverLocalService server;

    public AndroidDriver getAndroidDriver(String deviceName, String platformVersion, String appPath) throws MalformedURLException, MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformName("Android");
        options.setPlatformVersion(platformVersion);
        options.setApp(appPath);
        options.setAutomationName("UiAutomator2");
        options.setNoReset(false);
        options.setFullReset(false);
        options.setCapability("autoGrantPermissions", true); // Automatically grant app permissions

        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723"), options);
        androidDriver.set(driver);
        return driver;
    }

    public static AndroidDriver getDriver() {
        return androidDriver.get();
    }

    public void startServer() {
        if (!isAppiumServerRunning(Integer.parseInt("portNum"))) {
            server = new AppiumServiceBuilder().usingDriverExecutable(new File(("nodePath")))
                    .withAppiumJS(new File(("mainJs")))
                    .withIPAddress(("ip"))
                    .usingPort(Integer.parseInt(("portNum"))).build();
            server.start();
            System.out.println("***** Appium server started ******");
        } else {
            System.out.println("***** Appium server is already running ******");
        }
    }

    public void stopServer()
    {
        if (server != null && server.isRunning()) {
            server.stop();
            System.out.println("***** Appium server Stopped ******");
        }
    }

    private boolean isAppiumServerRunning(int port) {
        try (Socket socket = new Socket("127.0.0.1", port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
















}
