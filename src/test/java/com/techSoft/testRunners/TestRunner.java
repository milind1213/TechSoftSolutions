package com.techSoft.testRunners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = {"com.techSoft.stepDefinitions"},
        plugin = {
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
                "pretty",
                "html:reports/cucumber-report.html"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests
{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


    @org.testng.annotations.AfterClass
    public void launchReport()
    {
        try {
            File reportFile = new File("reports/chaintest-report.html");
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            } else {
                System.out.println("Report file not found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
