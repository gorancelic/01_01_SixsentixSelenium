package PageObjectModel.Header;

import BaseUtilities.BrowserManager;
import BaseUtilities.Functions;
import PageObjectModel.ClientRequest.ClientRequestPage;
import PageObjectModel.EmployeeSearch.EmployeeSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends Functions {
    public Header(WebDriver driver) {
        super(driver);
    }

    private final By navigationMenu = By.className("tal-navigation-menu-toggle");
    private final By navigationMenuClientRequest = By.xpath("//a[@href='/ui/manager/clientrequest']//div[@class='tal-navigation-item-icon']");
    private final By logoutButton = By.xpath("//a[@class='ng-tns-c99-0']");

    private final By employeeSearchButton = By.xpath("//a[@href='/ui/manager/candidatesearch?resetsearch=true']//div[@class='tal-navigation-item-icon']");

    public void logoutisDisplayed(){
        elementIsDisplayed(logoutButton, "logoutButton");
    }

    public void openMainManu(){
        //waitForElementToBeVisible(navigationMenu);
        clickWithWait(navigationMenu, "navigationMenu");
    }

    public EmployeeSearchPage navigateToEmployeeSearch(){
        openMainManu();
        clickWithWait(employeeSearchButton, "employeeSearchButton");
        return new EmployeeSearchPage(BrowserManager.getDriver());
    }

    public ClientRequestPage goToClientRequestPage(){
        openMainManu();
        clickWithWait(navigationMenuClientRequest, "navigationMenuClientRequest");
        return new ClientRequestPage(BrowserManager.getDriver());
    }


}
