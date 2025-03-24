package com.techSoft.utils;

import com.techSoft.CommonConstants;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class AppiumServerUtils {
    private static AppiumDriverLocalService server;
    public static void startServer()
    {
        if (!isAppiumServerRunning(Integer.parseInt(CommonConstants.APPIUM_SERVER_PORT_NUMBER))) {
            server = new AppiumServiceBuilder()
                    .withAppiumJS(new File(CommonConstants.MAINJS_PATH))
                    .withIPAddress(CommonConstants.SERVER_IP)
                    .usingPort(Integer.parseInt(CommonConstants.APPIUM_SERVER_PORT_NUMBER))
                    .build();
            server.start();
            System.out.println("========= [Appium server started successfully] ========");
        } else {
            System.out.println("========= [Appium server is already running] =========");
        }
    }

    public static void stopServer()
    {
        if (server != null) {
            System.out.println("======== [Stopping Appium server...] =========");
            server.stop();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("======== [Appium server stopped!] =========");
        } else {
            System.out.println("No Active Appium server to stop");
        }
    }

    private static boolean isAppiumServerRunning(int port)
    {
        try (Socket socket = new Socket(CommonConstants.SERVER_IP, port))
        {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
