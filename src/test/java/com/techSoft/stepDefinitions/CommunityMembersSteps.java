package com.techSoft.stepDefinitions;

import com.techSoft.CommonConstants;
import com.techSoft.integration.TestHooks;
import com.techSoft.pageObjects.web.Loginpage;
import com.techSoft.pageObjects.web.WebDashboard;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import static com.techSoft.utils.FileUtil.getProperty;

public class CommunityMembersSteps {
    private WebDriver driver;
    private WebDashboard userDashboard;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        TestHooks hooks = new TestHooks();
        driver = hooks.getWebDriver();
        driver.get(getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_WEBURL));
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() throws Exception {
        Loginpage loginpage = new Loginpage(driver);
        userDashboard = loginpage.webLogin(
                getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_USERNAME),
                getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_PASSWORD)
        );
    }

    @Then("the user should be redirected to the dashboard")
    public void theUserShouldBeRedirectedToTheDashboard() {
        String title = userDashboard.loginpage.getTitle();
        Assert.assertEquals(title, "PeopleGrove for Career & Alumni");
    }

    @When("the user navigates to the {string} section")
    public void theUserNavigatesToTheSection(String section) {
        userDashboard.getHomePage().moveOnConnect();
    }

    @And("the user selects {string} as the connection type")
    public void theUserSelectsConnectionType(String connectionType) {
        userDashboard.getHomePage().selectConnectionType(connectionType);
    }

    @And("the user clicks on the sort button")
    public void theUserClicksOnTheSortButton() {
        userDashboard.getHomePage().clickSortButton();
    }

    @And("the user selects {string} sorting")
    public void theUserSelectsSorting(String sortingType) {
        if (sortingType.equals("A-Z")) {
            userDashboard.getHomePage().clickedOnA2ZDropdowns();
        }
    }

    @Then("the user should see the sorted community members list")
    public void theUserShouldSeeTheSortedCommunityMembersList() {
        List<WebElement> userList = userDashboard.getHomePage().getSortResults();
        Assert.assertFalse(userList.isEmpty(), "The user list is empty.");
    }

    @And("the list should not be empty")
    public void theListShouldNotBeEmpty() {
        List<WebElement> userList = userDashboard.getHomePage().getSortResults();
        Assert.assertFalse(userList.isEmpty(), "The user list is empty.");
    }
}
