package com.techSoft.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineUtility {
    ProcessBuilder builder;
    Process p;

    public String runCommand(String command) throws Exception {

        p = Runtime.getRuntime().exec(command);

        BufferedReader r = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        String line = "";
        String allLine = "";
        int i = 1;
        while ((line = r.readLine()) != null) {
            allLine = allLine + "" + line + "\n";
            i++;
        }
        return allLine;
    }

}
