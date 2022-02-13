package parallel;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import PageObjectModel.ClientRequest.ClientRequestCreatePage;
import PageObjectModel.ClientRequest.ClientRequestEditPage;
import PageObjectModel.ClientRequest.ClientRequestPage;
import PageObjectModel.Header.Header;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ClientRequestSteps {

    ClientRequestPage clientRequestPage;
    ClientRequestCreatePage clientRequestCreatePage;
    ClientRequestEditPage clientRequestEditPage;
    private String checkNumber;
    private String checkNumberOfResources;
    DataReader dataReader = new DataReader();
    Header header = new Header(BrowserManager.getDriver());


    @And("User navigates to client request page")
    public void userNavigatesToClientRequestPage() {
        clientRequestPage = header.goToClientRequestPage();
    }

    @Then("Check elements on client request page")
    public void checkElementsOnClientRequestPage() {
        clientRequestPage.validateAllElementsOfClientsRequestPage();
    }


    @And("User clicks on add button")
    public void userClicksOnAddButton() {
        clientRequestCreatePage = clientRequestPage.clickAddButton();
    }

    @And("User fills client request with valid data and save it")
    public void userFillsClientRequestWithValidDataAndSaveIt() {
        clientRequestCreatePage.fillAllCreateClientRequestFields();
        checkNumber = clientRequestCreatePage.number;
        checkNumberOfResources = clientRequestCreatePage.numberOfResource;
        clientRequestEditPage = clientRequestCreatePage.clickSaveButton();

    }

    @Then("Check client is saved")
    public void checkClientIsSaved() {
        clientRequestEditPage.validateElementsOfEditClientRequestPage();
        clientRequestEditPage.validateElementValueIsGood();
        clientRequestEditPage.validateElementValue(checkNumber,checkNumberOfResources);
    }

    @And("User search valid requests according client or name {string} input")
    public void userSearchValidRequestsAccordingClientOrNameInput(String input) {
        switch (input) {
            case "client" -> clientRequestPage.enterSearchInput(dataReader.getPropertyValue("validClientName"));
            case "name" -> clientRequestPage.enterSearchInput(dataReader.getPropertyValue("validRequestName"));
            default -> throw new IllegalArgumentException("Unexpected value: " + input);
        }
    }

    @Then("Check client request is shown according to {string} input")
    public void checkClientRequestIsShownAccordingToInput(String input) {
        switch (input) {
            case "client" -> clientRequestPage.validateClientName();
            case "name" -> clientRequestPage.validateRequestName();
            default -> throw new IllegalArgumentException("Unexpected value: " + input);

        }
    }

    @And("User search invalid requests according client or name {string} input")
    public void userSearchInvalidRequestsAccordingClientOrNameInput(String input) {
        switch (input) {
            case "client", "name" -> clientRequestPage.enterSearchInput("asdfgg");
            default-> throw new IllegalArgumentException("Unexpected value: " + input);
        }
    }

    @Then("Check error message is shown")
    public void checkErrorMessageIsShown() {
        clientRequestPage.wrongSearch();
    }

    @And("User fills client request with valid data and cancel it")
    public void userFillsClientRequestWithValidDataAndCancelIt() {
        clientRequestCreatePage.fillAllCreateClientRequestFields();
        clientRequestPage = clientRequestCreatePage.clickCancelButton();
    }

    @Then("Check client request page is present")
    public void checkClientRequestPageIsPresent() {
        clientRequestPage.validateAllElementsOfClientsRequestPage();
    }

    @And("User clicks outside of border for client request")
    public void userClicksOutsideOfBorderForClientRequest() {
        clientRequestPage.clickClientRequestOutsideBorder();
    }

    @And("User fills data {string} combination")
    public void userFillsDataCombination(String combination) {
        switch(combination){
            case "allFieldsEmpty" -> clientRequestCreatePage.allInputFieldsEmpty();
            case "invalidClientName" -> clientRequestCreatePage.invalidClientName();
            case "invalidNumber" -> clientRequestCreatePage.invalidNumber();
            case "invalidRequestName" -> clientRequestCreatePage.invalidRequestName();
            case "invalidLocation" -> clientRequestCreatePage.invalidLocation();
            default-> throw new IllegalArgumentException("Unexpected value: " + combination);
        }
    }

    @And("Save client request")
    public void saveClientRequest() {
        clientRequestCreatePage.clickSaveButton();
    }


    @Then("Check following {string} error appears")
    public void checkFollowingAppears(String error) {
        switch (error){
            case "requiredFieldsError" -> clientRequestCreatePage.validateAllFieldsError();
            case "wrongEntryError" -> clientRequestCreatePage.checkSaveButtonIsDisplayed();
            default -> throw new IllegalArgumentException("Unexpected value: " + error);
        }
    }
}
