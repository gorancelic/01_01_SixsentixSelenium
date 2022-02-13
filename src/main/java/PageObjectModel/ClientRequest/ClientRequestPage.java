package PageObjectModel.ClientRequest;

import BaseUtilities.BrowserManager;
import BaseUtilities.DataReader;
import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ClientRequestPage extends Functions {

    public ClientRequestPage(WebDriver driver) {
        super(driver);
        locatorsMap();
    }

    HashMap<String, By> mapOfClientRequestPage = new HashMap<>();
    DataReader dataReader = new DataReader();

    public void locatorsMap() {
        mapOfClientRequestPage.put("headerClientRequest", By.xpath("//h5[@class='text-nowrap']"));
        mapOfClientRequestPage.put("inputSearchField", By.xpath("//div[@class='position-relative']//input"));
        mapOfClientRequestPage.put("searchField", By.className("input-group-append"));
        mapOfClientRequestPage.put("addButton", By.xpath("//button[@class='btn btn-primary']"));
    }

    private final By noMatchingClientRequest = By.xpath("//div[@class='card-body']");
    private final By clientValue = By.xpath("//div[@class='font-weight-bold']");
    private final By requestNameValue = By.xpath("//span[@class='font-weight-bold']");
    private final By clientRequestOutsideBorders = By.xpath("//tal-client-request-card[@class='col-4 ng-star-inserted']");

    public void validateAllElementsOfClientsRequestPage() {
        validateAllElementsOfPage(mapOfClientRequestPage);
    }

    public void enterSearchInput(String text) {
        sendKeys(mapOfClientRequestPage.get("inputSearchField"), text, "inputSearchField");
        click(mapOfClientRequestPage.get("searchField"));
    }

    public void wrongSearch() {
        errorMessageVerification(noMatchingClientRequest, dataReader.getPropertyValue("errorMessClientDE"), dataReader.getPropertyValue("errorMessClientEN"));
    }

    public void validateClientName() {
        validateName(clientValue, dataReader.getPropertyValue("validClientName"));
    }

    public void validateRequestName() {
        validateName(requestNameValue, dataReader.getPropertyValue("validRequestName"));
    }

    public void clickClientRequestOutsideBorder() {
        clickWithWait(clientRequestOutsideBorders, "clientRequestOutsideBorders");
    }

    public ClientRequestCreatePage clickAddButton() {
        clickWithWait(mapOfClientRequestPage.get("addButton"), "addButton");
        return new ClientRequestCreatePage(BrowserManager.getDriver());
    }

}
