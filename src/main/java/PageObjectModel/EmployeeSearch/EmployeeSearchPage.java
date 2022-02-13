package PageObjectModel.EmployeeSearch;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchPage extends Functions {

    public EmployeeSearchPage(WebDriver driver) {
        super(driver);
    }

    SoftAssert softAssert = new SoftAssert();

    private final By keywordInputField = By.id("searchTerm");
    private final By searchCandidatedButton = By.xpath("//button[@type='submit']");

    private final By keywordsLocator = By.xpath("//div[@class='font-weight-bold tal-skill-highlighted']");
    private final By listEmployees = By.xpath("//a[@class='d-block px-4 py-3 tal-candidate-result-list-item bg-white text-black']");

    private final By errorMessage = By.xpath("//div[@class='card-body']");

    private final By advancedSearch = By.xpath("//div[@class='col-12 mt-4 px-3']");
    private final By skillInput = By.xpath("//input[@id='skill']/following-sibling::tal-ontology-type-ahead//input[@role='combobox']");
    private final By skillStar = By.xpath("//div[@class='col-12 col-lg-6 ng-star-inserted']//div[@class='tal-ontology-level border ng-star-inserted'][2]//i[@class='pe-7s-star']");
    private final By skillAdd = By.xpath("//div[@class='col-12 col-lg-6 ng-star-inserted']//button[@class='form-control btn btn-secondary']");
    private final By skillDropDown = By.xpath("//div[@class='ng-option ng-star-inserted']");

    private final By languageInput = By.xpath("//input[@id='language']/following-sibling::tal-ontology-select//input[@role='combobox']");
    private final By languageStar = By.xpath("//div[@class='col-12 col-lg-6 mt-3 mt-lg-0 ng-star-inserted']//div[@class='tal-ontology-level border ng-star-inserted'][2]");
    private final By languageAdd = By.xpath("//div[@class='col-12 col-lg-6 mt-3 mt-lg-0 ng-star-inserted']//button[@class='form-control btn btn-secondary']");
    private final By languageDropDown = By.xpath("//div[@class='ng-option ng-option-child ng-star-inserted ng-option-marked']");

    private final By degreeAdd = By.xpath("//div[@class='col-12 col-lg-6 mt-3 ng-star-inserted']//button[@class='form-control btn btn-secondary']");
    private final By degreeInput = By.xpath("//input[@id='education']/following-sibling::tal-ontology-select//div[@class='ng-input']");
    private final By degreeDropDown = By.xpath("//input[@id='education']/following-sibling::tal-ontology-select//div[@role='option'][2]");

    private final By worklocationSearch = By.xpath("//button[@class='btn btn-primary tal-tree-btn']");
    private final By worklocationInput = By.xpath("//input[@class='form-control tal-tree-input']");
    private final By worklocationNoviSad = By.xpath("//li[@class='list-group-item tal-tree-node ng-star-inserted'][4]");
    private final By worklocationOKBtn = By.xpath("//button[@class='btn btn-save']");

    private final By certificateAdd = By.xpath("//div[@class='col-12 col-lg-6 mt-lg-3 ng-star-inserted'][2]//button[@class='form-control btn btn-secondary']");
    private final By certificateInput = By.xpath("//input[@id='certificate']/following-sibling::tal-ontology-select//div[@class='ng-input']");
    private final By certificateDropdownAE1 = By.xpath("//input[@id='certificate']/following-sibling::tal-ontology-select//div[@role='option'][1]");
    private final By certificateDropdownAS1 = By.xpath("//input[@id='certificate']/following-sibling::tal-ontology-select//div[@role='option'][2]");

    private final By tagAdd = By.xpath("//div[@class='col-12 col-lg-6 mt-lg-3 ng-star-inserted'][3]//button[@class='form-control btn btn-secondary']");
    private final By tagInput = By.xpath("//input[@id='tag']/following-sibling::tal-ontology-select//div[@class='ng-input']");
    private final By tagDropdown = By.xpath("//input[@id='tag']/following-sibling::tal-ontology-select//div[@class='ng-option ng-option-marked ng-star-inserted']");

    private final By languageLabel = By.xpath("//label[@for='language']");


    public void inputKeywordAndSearch(String keyword){
        waitForElementToBeVisible(keywordInputField);
        sendKeys(keywordInputField, keyword, "keywordInputField");
        click(searchCandidatedButton);
    }


    public void verifyErrorMessage(String errorMessDE, String errorMessEN){
        waitForElementToBeVisible(errorMessage);
        errorMessageVerification(errorMessage, errorMessDE, errorMessEN);
    }

    public void validateKeywordsSearching(String keyword){

        waitForElementToBeVisible(listEmployees);
        List<WebElement> all = driver.findElements(listEmployees);
        for (WebElement one : all) {
                one.isDisplayed();
                one.click();
                String actualResult = getText(keywordsLocator);
                softAssert.assertEquals(actualResult, keyword, "Keyword is not searched.");
                System.out.println(actualResult);
                System.out.println(one.getText());
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,350)", "");

        }
        softAssert.assertAll();

    }

    public void advancedSearch(List<String> skill, List<String> languageEN, List<String> languageDE) {
        clickWithWait(advancedSearch,"advancedSearch" );

        for(String one:skill){
            click(skillInput);
            sendKeys(skillInput, one, "skillInput" );
            clickWithWait(skillDropDown,"skillDropDown");
            clickWithWait(skillStar,"skillStar");
            click(skillAdd);
        }

        System.out.println(getText(languageLabel));
        if(getText(languageLabel).equals("Sprache")) {
            for(String one:languageDE){
                click(languageInput);
                sendKeys(languageInput,one, "languageInput" );
                clickWithWait(languageDropDown, "languageDropDown");
                clickWithWait(languageStar, "languageStar");
                clickWithWait(languageAdd, "languageAdd");
            }
        } else
            for(String one:languageEN){
                click(languageInput);
                sendKeys(languageInput,one, "languageInput" );
                clickWithWait(languageDropDown, "languageDropDown");
                clickWithWait(languageStar, "languageStar");
                clickWithWait(languageAdd, "languageAdd");
            }


        click(degreeInput);
        clickWithWait(degreeDropDown, "degreeDropDown");
        clickWithWait(degreeAdd, "degreeAdd");

        click(worklocationSearch);
        click(worklocationNoviSad);
        clickWithWait(worklocationOKBtn, "worklocationOKBtn");

        JavascriptExecutor js = (JavascriptExecutor) driver;
       // js.executeScript("arguments[0].click();", element);

        js.executeScript("window.scrollBy(0,350)", "");


        List<By> certificateXpath = new ArrayList<>();
        certificateXpath.add(certificateDropdownAE1);
        certificateXpath.add(certificateDropdownAS1);

        for(By one:certificateXpath) {
            click(certificateInput);
            //clickWithWait(certificateXpath.get(1));
            clickWithWait(one, "one");
            click(certificateAdd);
        }

        click(tagInput);
        clickWithWait(tagDropdown, "tagDropdown");
        click(tagAdd);

        click(searchCandidatedButton);

    }

}
