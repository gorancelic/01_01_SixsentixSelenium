package PageObjectModel.YourProfile;

import BaseUtilities.DataReader;
import BaseUtilities.Functions;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

public class YourProfilePage extends Functions {
    public YourProfilePage(WebDriver driver) {
        super(driver);
    }

    DataReader dataReader = new DataReader();
    SoftAssert softAssert = new SoftAssert();


    //Edit profile info locators
    final By editProfileInfoBtn = By.className("ng-fa-icon");
    final By firstName = By.id("firstName");
    final By lastName = By.id("lastName");
    final By phoneField = By.xpath("//div[@class='form-group col-12 col-md-6 ng-star-inserted']//input[@type='text']");
    final By dateOfBirth = By.className("tal-datepicker-input");
    /*needs better locator, works for now!!*/ final By nationality = By.cssSelector("body > modal-container > div > div > tal-profile-info-editor > div > form > div:nth-child(2) > div:nth-child(3) > tal-custom-field-input > input");
    final By workLocation = By.xpath("//i[@class='pe-7s-search']");
    final By workLocation_NoviSad = By.xpath("//span[normalize-space()='Novi Sad']");
    final By btnSave = By.xpath("//button[@class='btn btn-save']");
    final By btnOK = By.xpath("//button[contains(text(),'OK')]");
    final By aboutMe = By.xpath("//div[@contenteditable='true']/p");
 //   final By aboutMe = By.className("ql-editor");


    //Check profile info locators
    final By editWorkHistoryBtn = By.xpath("//div[@class='row mt-4 ng-star-inserted'][1]//button[@class='btn btn-primary px-5']");
    final By jobTitleInput = By.id("title");
    final By jobCompanyInput = By.id("company");
    final By addJobBtn = By.xpath("(//i[@class='pe-7s-plus pe-2x'])[1]");
    final By currentlyWorkingChb = By.id("currentJob");
    final By fromYearCmb = By.xpath("//ng-select[@role='listbox']");
    final By january = By.xpath("(//div[@role='option'])[2]");
    final By fromYearInput = By.cssSelector("div.col-4.pl-0 > input");
    final By workHistorySection = By.xpath("//tal-work-history-card//div[@class='card-body']");


    //Delete job section
    final By editWorkHistory = By.xpath("(//fa-icon[@class='ng-fa-icon'])[3]");
    final By deleteBtn = By.xpath("/html/body/modal-container/div/div/tal-work-history-editor/div/form/div[7]/button[2]");
    final By confirmDeleteBtn = (By.xpath("//tal-delete-confirmation//button[2]"));




    public void editAndFillProfileInfo() {
        clickWithWait(editProfileInfoBtn, "editProfileInfoBtn");
        waitForElementToBeVisible(firstName);
        sendKeys(firstName, dataReader.getPropertyValue("accountFirstName"), "accountFirstName");
        sendKeys(lastName, dataReader.getPropertyValue("accountLastName"), "accountLastName" );
        sendKeys(phoneField, dataReader.getPropertyValue("accountPhone"), "accountPhone");
        sendKeys(dateOfBirth, dataReader.getPropertyValue("accountDate"), "accountDate");
        sendKeys(nationality, dataReader.getPropertyValue("accountNationality"), "accountNationality");
        click(workLocation);
        clickWithWait(workLocation_NoviSad, "workLocation_NoviSad");
        click(btnOK);
        sendKeys(aboutMe, dataReader.getPropertyValue("accountAboutMe"), "accountAboutMe");
        click(btnSave);
    }

    public void checkProfileInfo() {
        final String profileInfoSection = driver.findElement(By.xpath("(//div[@class='card-body'])[1]")).getText();
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountFirstName")), "Account first name don't match!");
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountLastName")), "Account last name don't match!");
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountPhone")), "Account phone don't match!");
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountDate")), "Account date don't match!");
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountNationality")), "Account nationality don't match!");
        softAssert.assertTrue(profileInfoSection.contains(dataReader.getPropertyValue("accountAboutMe")), "Account about me don't match!");
        softAssert.assertAll();
    }

    public void editWorkHistory() {
        clickWithWait(addJobBtn, "addJobBtn");
        sendKeys(jobTitleInput, dataReader.getPropertyValue("jobTitle"), "jobTitle");
        sendKeys(jobCompanyInput, dataReader.getPropertyValue("jobCompany"), "jobCompany");
        click(currentlyWorkingChb);
        click(fromYearCmb);
        clickWithWait(january, "january");
        sendKeys(fromYearInput, dataReader.getPropertyValue("jobYear"), "jobYear");
        click(btnSave);

    }

    public void checkWorkHistory()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String checkWorkHistory = getText(workHistorySection);
        System.out.println(checkWorkHistory);
        softAssert.assertTrue(checkWorkHistory.contains(dataReader.getPropertyValue("jobTitle")), "Work history job title don't match!");
        softAssert.assertTrue(checkWorkHistory.contains(dataReader.getPropertyValue("jobCompany")), "Work history job company don't match!");
        softAssert.assertTrue(checkWorkHistory.contains(dataReader.getPropertyValue("jobYear")), "Work history job year don't match!");
        softAssert.assertAll();

    }

    public void deleteJob()
    {

     click(editWorkHistory);
     click(deleteBtn);
     click(confirmDeleteBtn);

    }


}
