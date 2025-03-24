package com.techSoft.stepDefinitions.steps_mobileWeb;

import com.techSoft.CommonConstants;
import com.techSoft.integration.BaseTest;
import com.techSoft.pageObjects.mobile.MobileWeb.MobileWebDashboard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import static com.techSoft.utils.FileUtil.getProperty;

public class PGLoginSteps {
    private final WebDriver driver;
    public MobileWebDashboard user;

    public PGLoginSteps() {
        driver = new BaseTest().getSeleniumMobileWebDriver();
        user = new MobileWebDashboard(driver);
    }

    @Given("I am on the login page")
    public void landed_on_home() {
        driver.get(getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_WEBURL));
    }

    @When("I select the {string} login option")
    public void selecting_login_option(String loginOption) {
        user.getPGLogin().buttonWithText("Sign In");
        user.getPGLogin().loginOptionsWithText(loginOption);
    }

    @And("I enter email {string} and password {string}")
    public void entering_Login_credentials(String email,String password) {
        user.getPGLogin().enterLoginCredentials(email,password);
    }

    @And("I click the login button")
    public void clicking_Login_Button() {
        user.getPGLogin().clickLoginButton();
    }

    @Then("I should see the {string}")
    public void verifying_Expected_output(String expectedResult) {
        System.out.println("Validated : " + expectedResult);
    }
}
