package parallel;

import BaseUtilities.BrowserManager;
import PageObjectModel.YourProfile.YourProfilePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class YourProfilePageSteps {
    YourProfilePage yourProfilePage;


    @When("User enters valid information for all fields")
    public void userEntersValidInformationForAllFields() {
        yourProfilePage = new YourProfilePage(BrowserManager.getDriver());
        yourProfilePage.editAndFillProfileInfo();
    }

    @Then("Check that correct information are visible")
    public void checkThatCorrectInformationAreVisible() {
        yourProfilePage.checkProfileInfo();
    }

    @When("User opens edit work section and enter valid skills")
    public void userOpensEditWorkSectionAndEnterValidSkills() {
        yourProfilePage = new YourProfilePage(BrowserManager.getDriver());
        yourProfilePage.editWorkHistory();
    }

    @Then("Check that correct information are visible in work section")
    public void checkThatCorrectInformationAreVisibleInWorkSection() {

        yourProfilePage.checkWorkHistory();
    }

    @And("User should delete the job after verification")
    public void userShouldDeleteTheJobAfterVerification() {
        yourProfilePage.deleteJob();

    }
}
