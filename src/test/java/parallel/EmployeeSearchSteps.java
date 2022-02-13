package parallel;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import PageObjectModel.EmployeeSearch.EmployeeSearchPage;
import PageObjectModel.Header.Header;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;


public class EmployeeSearchSteps {

    Header header;
    EmployeeSearchPage employeeSearchPage;
    DataReader dataReader = new DataReader();


    @And("User navigates to employee search page")
    public void userNavigatesToEmployeeSearchPage() {
        header = new Header(BrowserManager.getDriver());
        employeeSearchPage = header.navigateToEmployeeSearch();
    }

    @When("User enters valid keyword and search candidates")
    public void userEntersValidKeywordAndSearchCandidates(){
        employeeSearchPage.inputKeywordAndSearch(dataReader.getPropertyValue("keywordValid"));
    }

    @Then("Check that appropriate profile is shown")
    public void checkThatAppropriateProfileIsShown(){
        employeeSearchPage.validateKeywordsSearching(dataReader.getPropertyValue("keywordValid"));
    }

    @When("User enters invalid keyword and search candidates")
    public void userEntersInvalidKeywordAndSearchCandidates(){
        employeeSearchPage.inputKeywordAndSearch("lala");
    }

    @Then("Check that appropriate error message is shown for invalid keyword")
    public void checkThatAppropriateErrorMessageIsShownForInvalidKeyword(){
        employeeSearchPage.verifyErrorMessage(dataReader.getPropertyValue("errorMessEmployeeDE"), dataReader.getPropertyValue("errorMessEmployeeEN"));
    }


    @When("User enters valid data and search candidates")
    public void userEntersValidDataAndSearchCandidates() {

        List<String> skill = new ArrayList<>();
        skill.add(dataReader.getPropertyValue("keywordValid"));
        skill.add(dataReader.getPropertyValue("skill1"));
        skill.add(dataReader.getPropertyValue("skill2"));

        List<String> languageDE = new ArrayList<>();
        languageDE.add(dataReader.getPropertyValue("languageGerman"));
        languageDE.add(dataReader.getPropertyValue("englishDE"));
        languageDE.add(dataReader.getPropertyValue("russianDE"));
        languageDE.add(dataReader.getPropertyValue("frenchDE"));

        List<String> languageEN = new ArrayList<>();
        languageEN.add(dataReader.getPropertyValue("languageEnglish"));
        languageEN.add(dataReader.getPropertyValue("englishEN"));
        languageEN.add(dataReader.getPropertyValue("russianEN"));
        languageEN.add(dataReader.getPropertyValue("frenchEN"));

        employeeSearchPage.advancedSearch(skill,languageEN,languageDE);
        
    }

    @Then("Check that appropriate profiles are shown")
    public void checkThatAppropriateProfilesAreShown() {
    }
}
