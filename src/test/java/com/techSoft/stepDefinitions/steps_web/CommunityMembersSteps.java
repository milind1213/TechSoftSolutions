package com.techSoft.stepDefinitions.steps_web;

import com.techSoft.CommonConstants;
import com.techSoft.integration.BaseTest;
import com.techSoft.pageObjects.web.LoginPage;
import com.techSoft.pageObjects.web.WebDashboard;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import static com.techSoft.utils.FileUtil.getProperty;

public class CommunityMembersSteps {
    private final WebDriver driver;
    private WebDashboard user;

    public CommunityMembersSteps() {
        driver = new BaseTest().getWebDriver();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver.get(getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_WEBURL));
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        user = loginPage.webLogin(
               getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_USERNAME),
               getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_PASSWORD)
        );
    }

    @Then("the user should be redirected to the dashboard")
    public void theUserShouldBeRedirectedToTheDashboard() {
        String title = user.getLoginPage().getTitle();
        Assert.assertEquals(title, "PeopleGrove for Career & Alumni", "Dashboard title does not match.");
    }

    @When("the user navigates to the {string} section")
    public void theUserNavigatesToTheSection(String section) {
        if (section == null || section.trim().isEmpty()) {
            Assert.fail("Section name cannot be empty");
        }
        user.getHomePage().moveOnConnect();
    }

    @And("the user selects {string} as the connection type")
    public void theUserSelectsConnectionType(String connectionType) {
        if (connectionType == null || connectionType.trim().isEmpty()) {
            Assert.fail("Connection type cannot be empty");
        } else if (!connectionType.equals("Community")) {
            Assert.fail("Invalid connection type selected: " + connectionType);
        }
        user.getHomePage().selectConnectionType(connectionType);
    }

    @And("the user clicks on the sort button")
    public void theUserClicksOnTheSortButton() {
        user.getHomePage().clickSortButton();
    }

    @And("the user selects {string} sorting")
    public void theUserSelectsSorting(String sortingType) {
        if (sortingType == null || sortingType.trim().isEmpty()) {
            Assert.fail("Sorting type cannot be empty");
        } else if ("A-Z".equalsIgnoreCase(sortingType)) {
            user.getHomePage().clickedOnA2ZDropdowns();
        } else {
            Assert.fail("Unsupported sorting type: " + sortingType);
        }
    }

    @Then("the user should see the sorted community members list")
    public void theUserShouldSeeTheSortedCommunityMembersList() {
        List<WebElement> userList = user.getHomePage().getSortResults();
        Assert.assertFalse(userList.isEmpty(), "The sorted community members list is empty.");
    }

    @And("the list should not be empty")
    public void theListShouldNotBeEmpty() {
        List<WebElement> userList = user.getHomePage().getSortResults();
        Assert.assertFalse(userList.isEmpty(), "The community members list should not be empty.");
    }

}
