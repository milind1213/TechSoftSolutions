package com.techSoft.utils;
import com.techSoft.CommonConstants;
import java.util.HashMap;
import java.util.Map;

public class IOSDeviceManager {
    private final CommandLineUtility cmd = new CommandLineUtility();
    private final Map<Integer, String> deviceId = new HashMap<>();

    public Map<Integer, String> getConnectedDevice() throws Exception {
        String output = cmd.runCommand("instruments -s devices"); // Get list of connected devices
        String[] outputStrings = output.split("\n");
        if (outputStrings.length == 1) {
            System.out.println("No device connected");
        } else {
            for (int i = 1; i < outputStrings.length; i++) {
                outputStrings[i] = outputStrings[i].replaceAll("\\s+", "");
                if (outputStrings[i].contains("(Simulator)")) {
                    deviceId.put(i, outputStrings[i].split(" \\(")[0]);
                } else {
                    deviceId.put(i, outputStrings[i].split(" \\(")[0]);  // Extract device name
                }
            }
        }
        return deviceId;
    }

    public String getDeviceModel(String deviceId) throws Exception {
        String model = null;
        if (deviceId != null) {
            model = cmd.runCommand("ideviceinfo -u " + deviceId + " | grep Model").replaceAll("\\s+", "");
        }
        return model;
    }

    // Get iOS version of the device
    public String getDeviceOS(String deviceId) throws Exception {
        String iosVersion = null;

        if (deviceId != null) {
            iosVersion = cmd.runCommand("ideviceinfo -u " + deviceId + " | grep ProductVersion")
                    .replaceAll("\\s+", "");
        }
        return iosVersion;
    }

    public void unlockDevice(String deviceId) {
        try {
            if (deviceId.contains("simulator")) return; // No need to unlock simulator
            cmd.runCommand("ideviceunlock " + deviceId); // Unlock the device
        } catch (Exception e) {
            System.err.println("Failed to unlock the screen of device id :" + deviceId);
        }
    }

    public void clearData(String deviceID) {
        String clearAppCmd;
        try {
            clearAppCmd = "ideviceinstaller -u " + deviceID + " --uninstall " + System.getProperty(CommonConstants.APP_PACKAGE);
            cmd.runCommand(clearAppCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
