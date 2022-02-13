package parallel;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import PageObjectModel.Header.Header;
import PageObjectModel.LoginPage.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    LoginPage loginPage;
    Header header;
    DataReader dataReader = new DataReader();


    @When("User enters valid credentials for login form")
    public void userEntersValidCredentialsForLoginForm() {
        loginPage = new LoginPage(BrowserManager.getDriver());
        loginPage.fillAllFieldsAndLogin(dataReader.getPropertyValue("staffminerUsername"), dataReader.getPropertyValue("staffminerPassword"));
    }

    @Then("Check that user is logged in successfully")
    public void checkThatUserIsLoggedInSuccessfully() {
        header = new Header(BrowserManager.getDriver());
        System.out.println("Login successfully");
        header.logoutisDisplayed();
    }

    @When("User enters {string} email and {string} password for login form")
    public void userEntersEmailAndPasswordForLoginForm(String email, String password) {
        loginPage = new LoginPage(BrowserManager.getDriver());
        switch (email) {
            case "invalid":
                loginPage.fillAllFieldsAndLogin(dataReader.getPropertyValue("staffminerInvalidEmailFormat"), dataReader.getPropertyValue("staffminerPassword"));
                break;
            case "empty":
                loginPage.fillAllFieldsAndLogin(dataReader.getPropertyValue("emptyInput"), dataReader.getPropertyValue("emptyInput"));
                break;
            default:
                break;
        }

    }


    @Then("Check that appropriate error message is shown for {string} email")
    public void checkThatAppropriateErrorMessageIsShownForEmail(String email) {
        switch (email) {
            case "invalid":
                loginPage.errorMssgWrongEmail(dataReader.getPropertyValue("errorMessInvalidFormatDE"), dataReader.getPropertyValue("errorMessInvalidFormatEN"));
                break;
            case "empty":
                loginPage.errorMssgEmptyCredentials(dataReader.getPropertyValue("errorMessEmptyEmailDE"), dataReader.getPropertyValue("errorMessEmptyEmailEN"), dataReader.getPropertyValue("errorMessEmptyPassDE"), dataReader.getPropertyValue("errorMessEmptyPassEN"));
                break;
            default:
                break;
        }
    }


}
