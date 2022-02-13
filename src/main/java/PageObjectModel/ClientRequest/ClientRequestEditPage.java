package PageObjectModel.ClientRequest;

import BaseUtilities.DataReader;
import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class ClientRequestEditPage extends Functions {

    public ClientRequestEditPage(WebDriver driver) {
        super(driver);
        locatorsMap();
        editValueLocator();
    }

    DataReader dataReader = new DataReader();
    SoftAssert softAssert = new SoftAssert();

    HashMap<String, By> mapOfClientRequestEdit = new HashMap<>();

    public void locatorsMap() {
        mapOfClientRequestEdit.put("overviewEditTab", By.xpath("//a[@class='nav-link active']"));
        mapOfClientRequestEdit.put("findStaffTab", By.xpath("//li[@class='nav-item ng-star-inserted'][1]//a[@class='nav-link']"));
        mapOfClientRequestEdit.put("viewStaff", By.xpath("//li[@class='nav-item ng-star-inserted'][2]//a[@class='nav-link']"));
        mapOfClientRequestEdit.put("back", By.xpath("//a[@class='tal-all-pointer-events flex-grow-0 flex-shrink-0']"));
        mapOfClientRequestEdit.put("clientName", By.xpath("//h5[@class='mb-0 pl-3 d-none d-lg-block d-xl-block text-truncate mw-100 tal-all-pointer-events']"));
        mapOfClientRequestEdit.put("editButton", By.xpath("//button[@class='btn btn-primary']"));
        mapOfClientRequestEdit.put("deleteButton", By.xpath("//button[@class='btn btn-danger ml-3']"));
    }

    HashMap<String, By> mapOfClientRequestValueEdit = new HashMap<>();

    public void editValueLocator() {
        mapOfClientRequestValueEdit.put("clientNameValue", By.xpath("//div[1][@class='col-12 col-md-6 mt-3']//div[2]"));
        mapOfClientRequestValueEdit.put("numberValue", By.xpath("//div[2][@class='col-12 col-md-6 mt-3']//div[2]"));
        mapOfClientRequestValueEdit.put("requestNameValue", By.xpath("//div[@class='col-6']//div[2]"));
        mapOfClientRequestValueEdit.put("locationValue", By.xpath("//div[1][@class='col-12 col-md-6 mt-4']//div[2]"));
        mapOfClientRequestValueEdit.put("numberOfResourcesValue", By.xpath("//div[2][@class='col-12 col-md-6 mt-4']//div[2]"));
        mapOfClientRequestValueEdit.put("languageValue", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][1]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("certificateValue", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][2]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("professionalSkill1Value", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][3]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("professionalSkill2Value", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][4]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("professionalSkill3Value", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][5]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("professionalSkill4Value", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][6]/div[@class='col-12 col-md-6 mt-3']"));
        mapOfClientRequestValueEdit.put("professionalSkill5Value", By.xpath("//div[@class='ng-star-inserted']//div[@class='row ng-star-inserted'][7]/div[@class='col-12 col-md-6 mt-3']"));
    }

    public By differentTextLocator(String text) {
        return By.xpath("//div[@class='col-12 col-md-6 mt-3'][text()='" + text + "']");
    }

    public void validateElementsOfEditClientRequestPage() {
        validateAllElementsOfPage(mapOfClientRequestEdit);
        validateAllElementsOfPage(mapOfClientRequestValueEdit);
    }

    public void validateElementValueIsGood() {
        softAssert.assertEquals(getText(mapOfClientRequestValueEdit.get("clientNameValue")), dataReader.getPropertyValue("clientName"));
        softAssert.assertEquals(getText(mapOfClientRequestValueEdit.get("locationValue")), dataReader.getPropertyValue("location"));
        softAssert.assertEquals(getText(mapOfClientRequestValueEdit.get("certificateValue")), dataReader.getPropertyValue("certificate"));
        softAssert.assertEquals(getText(differentTextLocator("CD, CI tools (Jenkins, GitLab)")), "CD, CI tools (Jenkins, GitLab)");
        softAssert.assertEquals(getText(differentTextLocator("Estimation")), "Estimation");
        softAssert.assertEquals(getText(differentTextLocator("Selenium")), "Selenium");
        softAssert.assertEquals(getText(differentTextLocator("Business Analysis")), "Business Analysis");
        softAssert.assertEquals(getText(differentTextLocator("Test Automation")), "Test Automation");

        softAssert.assertAll();
    }

    public void validateElementValue(String number, String numberOfResources) {
        softAssert.assertEquals(getText(mapOfClientRequestValueEdit.get("numberValue")), number);
        softAssert.assertEquals(getText(mapOfClientRequestValueEdit.get("numberOfResourcesValue")), numberOfResources);

        softAssert.assertAll();
    }
}
