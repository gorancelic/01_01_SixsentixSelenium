package PageObjectModel.ClientRequest;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import BaseUtilities.Functions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientRequestCreatePage extends Functions {

    public ClientRequestCreatePage(WebDriver driver) {
        super(driver);
        locatorsMap();
    }

    SoftAssert softAssert = new SoftAssert();
    DataReader dataReader = new DataReader();
    HashMap<String, By> mapOfCreateClientRequestPage = new HashMap<>();

    public void locatorsMap() {
        mapOfCreateClientRequestPage.put("clientNameInputField", By.id("clientName"));
        mapOfCreateClientRequestPage.put("numberInputField", By.id("requestIdentifier"));
        mapOfCreateClientRequestPage.put("requestNameInputField", By.id("requestName"));
        mapOfCreateClientRequestPage.put("locationInputField", By.id("location"));
        mapOfCreateClientRequestPage.put("numberOfResourceInputField", By.id("vacantSlots"));
        mapOfCreateClientRequestPage.put("languagesPlusButtonField", By.xpath("//div[@class='col-12 tal-language-label d-flex justify-content-between align-items-center']//i[@class='pe-7s-plus pe-2x']"));
        mapOfCreateClientRequestPage.put("certificatesAndCoursesPlusButtonField", By.xpath("//div[@class='col-12 tal-language-label d-flex justify-content-between align-items-center mt-2'][1]//i[@class='pe-7s-plus pe-2x']"));
        mapOfCreateClientRequestPage.put("professionalSkillsPlusButtonField", By.xpath("//div[@class='col-12 tal-language-label d-flex justify-content-between align-items-center mt-2'][2]//i[@class='pe-7s-plus pe-2x']"));
        mapOfCreateClientRequestPage.put("addPredefinedProfessionalSkillButtonField", By.xpath("//button[@class='btn btn-primary w-100']"));
        mapOfCreateClientRequestPage.put("tagPlusButtonField", By.xpath("//div[@class='col-12 tal-language-label d-flex justify-content-between align-items-center mt-3 ng-star-inserted']//i[@class='pe-7s-plus pe-2x']"));
        mapOfCreateClientRequestPage.put("cancelButtonField", By.xpath("//button[@class='btn btn-cancel']"));
        mapOfCreateClientRequestPage.put("saveButtonField", By.xpath("//button[@class='btn btn-save ml-3']"));
    }


    public String number = RandomStringUtils.randomNumeric(2);
    public String numberOfResource = RandomStringUtils.randomNumeric(1);

    public void allInputFieldValid() {
        sendKeys(mapOfCreateClientRequestPage.get("clientNameInputField"), dataReader.getPropertyValue("clientName"),"clientNameInputField" );
        sendKeys(mapOfCreateClientRequestPage.get("requestNameInputField"), dataReader.getPropertyValue("requestName"), "requestNameInputField");
        sendKeys(mapOfCreateClientRequestPage.get("numberInputField"), number, "numberInputField");
        sendKeys(mapOfCreateClientRequestPage.get("locationInputField"), dataReader.getPropertyValue("location"), "locationInputField");
    }

    public void invalidClientName() {
        allInputFieldValid();
        sendKeys(mapOfCreateClientRequestPage.get("clientNameInputField"), dataReader.getPropertyValue("invalidTextInput"), "clientNameInputField");
    }

    public void invalidRequestName() {
        allInputFieldValid();
        sendKeys(mapOfCreateClientRequestPage.get("requestNameInputField"), dataReader.getPropertyValue("invalidTextInput"), "requestNameInputField");
    }

    public void invalidNumber() {
        allInputFieldValid();
        sendKeys(mapOfCreateClientRequestPage.get("numberInputField"), dataReader.getPropertyValue("invalidNumberInput"), "numberInputField");
    }

    public void invalidLocation() {
        allInputFieldValid();
        sendKeys(mapOfCreateClientRequestPage.get("locationInputField"), dataReader.getPropertyValue("invalidTextInput"), "locationInputField");
    }

    public void allInputFieldsEmpty() {
        sendKeys(mapOfCreateClientRequestPage.get("clientNameInputField"), "","clientNameInputField" );
        sendKeys(mapOfCreateClientRequestPage.get("requestNameInputField"), "", "requestNameInputField");
        sendKeys(mapOfCreateClientRequestPage.get("numberInputField"), "","numberInputField" );
        sendKeys(mapOfCreateClientRequestPage.get("locationInputField"), "", "locationInputField");
    }


    public void selectLocationAndCertificates() {
        click(mapOfCreateClientRequestPage.get("languagesPlusButtonField"));
        selectOptionFromDropdown(languageLocator, allOptionsLocator, dataReader.getPropertyValue("languageEnglish"), dataReader.getPropertyValue("languageGerman"));

        click(mapOfCreateClientRequestPage.get("certificatesAndCoursesPlusButtonField"));
        selectOptionFromDropdown(certificationLocator, allOptionsLocator, dataReader.getPropertyValue("certificate"), dataReader.getPropertyValue("certificate"));
    }

    public void selectProfessionalSkill() {
        click(mapOfCreateClientRequestPage.get("addPredefinedProfessionalSkillButtonField"));

        clickSkills(generalITSkillDropdown, ciCdiCheckbox, ciCdiStar);
        clickSkills(agileDropdown, estimationCheckbox, estimationStar);
        clickSkills(testingDropdown, seleniumCheckbox, seleniumStar);
        clickSkills(consultancyDropdown, baCheckbox, baStar);
        clickSkills(automationDropdown, testAutomationCheckbox, testAutomationStar);

        click(okButton);
    }


    public void fillAllCreateClientRequestFields() {
        allInputFieldValid();
        sendKeys(mapOfCreateClientRequestPage.get("numberOfResourceInputField"), numberOfResource, "numberOfResourceInputField");

        selectLocationAndCertificates();
        selectProfessionalSkill();
    }

    public void selectOptionFromDropdown(By locator, By allElementsLocator, String englishLanguage, String germanLanguage) {
        click(locator);
        List<WebElement> allOptions = driver.findElements(allElementsLocator);
        for (int i = 0; i <= allOptions.size() - 1; i++) {
            if (allOptions.get(i).getText().contains(englishLanguage) || allOptions.get(i).getText().contains(germanLanguage)) {
                allOptions.get(i).click();
                break;
            }
        }
    }

    private final By languageLocator = By.xpath("//tal-ontology-select[@formcontrolname='language']//div[@class='ng-input']");
    private final By certificationLocator = By.xpath("//tal-ontology-select[@formcontrolname='certificate']//div[@class='ng-input']");
    private final By allOptionsLocator = By.xpath("//div[@role='option']");

    private final By generalITSkillDropdown = By.xpath("//div[@class='px-4 mb-2 ng-star-inserted'][1]//span[@class='tal-skillgroup-title']");
    private final By agileDropdown = By.xpath("//div[@class='px-4 mb-2 ng-star-inserted'][2]//span[@class='tal-skillgroup-title']");
    private final By testingDropdown = By.xpath("//div[@class='px-4 mb-2 ng-star-inserted'][3]//span[@class='tal-skillgroup-title']");
    private final By consultancyDropdown = By.xpath("//div[@class='px-4 mb-2 ng-star-inserted'][4]//span[@class='tal-skillgroup-title']");
    private final By automationDropdown = By.xpath("//div[@class='px-4 mb-2 ng-star-inserted'][5]//span[@class='tal-skillgroup-title']");

    private final By ciCdiCheckbox = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][1]//input[@type='checkbox']");
    private final By ciCdiStar = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][1]//div[@class='tal-ontology-level border tal-clickable ng-star-inserted'][2]");

    private final By estimationCheckbox = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][3]//input[@type='checkbox']");
    private final By estimationStar = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][3]//div[@class='tal-ontology-level border tal-clickable ng-star-inserted'][3]");

    private final By seleniumCheckbox = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][8]//input[@type='checkbox']");
    private final By seleniumStar = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][8]//div[@class='tal-ontology-level border tal-clickable ng-star-inserted'][1]");

    private final By baCheckbox = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][4]//input[@type='checkbox']");
    private final By baStar = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][4]//div[@class='tal-ontology-level border tal-clickable ng-star-inserted'][1]");

    private final By testAutomationCheckbox = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][11]//input[@type='checkbox']");
    private final By testAutomationStar = By.xpath("//div[@class='col-12 col-md-6 col-lg-6 ng-star-inserted'][11]//div[@class='tal-ontology-level border tal-clickable ng-star-inserted'][2]");

    private final By okButton = By.xpath("//button[@class='btn btn-save']");

    public ClientRequestEditPage clickSaveButton() {
        clickWithWait(mapOfCreateClientRequestPage.get("saveButtonField"),"saveButtonField" );
        return new ClientRequestEditPage(BrowserManager.getDriver());
    }

    public void clickSkills(By dropdownLocator, By checkboxLocator, By starLocator) {
        click(dropdownLocator);
        click(checkboxLocator);
        click(starLocator);
        click(dropdownLocator);
    }

    public ClientRequestPage clickCancelButton() {
        clickWithWait(mapOfCreateClientRequestPage.get("cancelButtonField"), "cancelButtonField");
        return new ClientRequestPage(BrowserManager.getDriver());
    }

    HashMap<String, By> mapOfWebElementAndString = new HashMap<>();

    public void validateAllFieldsError() {
        List<WebElement> allError = driver.findElements(By.xpath("//input/following-sibling::bfv-messages/span[@class='invalid-feedback ng-star-inserted']"));
        List<String> allMessagesEnglish = new ArrayList<>();
        allMessagesEnglish.add("Client name may not be empty");
        allMessagesEnglish.add("Request name may not be empty");

        List<String> allMessagesGerman = new ArrayList<>();
        allMessagesGerman.add("Kundenname darf nicht leer sein");
        allMessagesGerman.add("Profilname darf nicht leer sein");

        checkDifferentErrorMessageList(allError, allMessagesEnglish, allMessagesGerman);
    }

    public void checkSaveButtonIsDisplayed() {
        //  waitForElementToBeVisible(mapOfCreateClientRequestPage.get("saveButtonField"));
        sleep(1000);
        elementIsDisplayed(mapOfCreateClientRequestPage.get("saveButtonField"),"saveButtonField");
    }


}
