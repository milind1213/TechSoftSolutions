package com.techSoft.utils;

import com.techSoft.CommonConstants;

import java.util.HashMap;
import java.util.Map;

public class AndroidDeviceManager {
    private final CommandLineUtility cmd = new CommandLineUtility();
    private final Map<Integer, String> deviceId = new HashMap<Integer, String>();

    public Map<Integer, String> getConnectedDevice() throws Exception {
        CommandLineUtility cmd = new CommandLineUtility();
        String output = cmd.runCommand("adb devices");
        String[] outputStrings = output.split("\n");
        if (outputStrings.length == 1) {
            System.out.println("No device connected");
            //System.exit(1);
        } else {
            for (int i = 1; i < outputStrings.length; i++) {
                outputStrings[i] = outputStrings[i].replaceAll("\\s+", "");
                deviceId.put(i, outputStrings[i].replace("device", ""));
            }
        }
        return deviceId;
    }

    public String getDeviceModel(String deviceId) throws Exception {
        String model = null;
        String brand = null;
        if (deviceId != null) {
            model =
                    cmd.runCommand("adb -s " + deviceId
                                    + " shell getprop ro.product.model")
                            .replaceAll("\\s+", "");
            brand =
                    cmd.runCommand("adb -s " + deviceId
                                    + " shell getprop ro.product.brand")
                            .replaceAll("\\s+", "");
        }
        return brand.concat(" " + model).trim();
    }

    public String getDeviceOS(String deviceId) throws Exception {
        String AndroidOSversion = null;

        if (deviceId != null) {
            AndroidOSversion = cmd.runCommand(
                            "adb -s " + deviceId + " shell getprop ro.build.version.release")
                    .replaceAll("\\s+", "");
        }
        return AndroidOSversion;
    }

    public void unlockDevice(String deviceId) {
        try {
            if (deviceId.contains("emulator"))
                return;
            cmd.runCommand("adb -s " + deviceId + " shell input keyevent 82");
            cmd.runCommand("adb -s " + deviceId + " shell input keyevent 82");
        } catch (Exception e) {
            System.err.println("Failed to unlock the screen of device id :" + deviceId);
        }
    }

    public void clearData(String deviceID) {
        String guardUninstallCmd;
        try {
            //adb shell pm clear com.app.guarddoor
            guardUninstallCmd = "adb -s " + deviceID + " shell pm clear " + System.getProperty(CommonConstants.APP_PACKAGE);
            cmd.runCommand(guardUninstallCmd);
        } catch (AssertionError | Exception e) {
            // e.printStackTrace();
        }
    }
}

