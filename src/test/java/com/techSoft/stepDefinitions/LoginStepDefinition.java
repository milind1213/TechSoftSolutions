package com.techSoft.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
       System.out.println("Login");
    }

    @When("I enter a valid username and password")
    public void i_enter_a_valid_username_and_password() {
        System.out.println("Entering User ID");
        System.out.println("Entering Password");
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
        System.out.println("Clicking on Login");
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        System.out.println("Verifying the HomePage");
    }
}
