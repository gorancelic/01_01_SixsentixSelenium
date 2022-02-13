package PageObjectModel.LoginPage;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;


public class LoginPage extends Functions {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By emailInputField = By.id("userId");
    private final By passwordInputField = By.id("password");
    private final By loginButton = By.xpath("//button[@class='btn btn-primary btn-block mt-4']");

    private final By errorMessageEmail = By.xpath("//form[@role='form']/div[1]/bfv-messages/span[@class='invalid-feedback']");
    private final By errorMessagePass = By.xpath("//form[@role='form']/div[2]/bfv-messages/span[@class='invalid-feedback']");

    public void fillAllFieldsAndLogin(String email, String password) {

          waitForElementToBeVisible(emailInputField);
            sendKeys(emailInputField, email, "emailInputField");
            sendKeys(passwordInputField, password, "passwordInputField");
            click(loginButton);

        System.out.println("Login is finished.");
    }


    public void errorMssgEmptyCredentials(String errorMessEmailDE, String errorMessEmailEN, String errorMssgPassDE, String errorMessgPassEN){
        String actualErrorMssgEmail = getText(errorMessageEmail);
        String actualErrorMssgPass = getText(errorMessagePass);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualErrorMssgEmail.contains(errorMessEmailDE) || actualErrorMssgEmail.contains(errorMessEmailEN), "Actual error message does not contain expected. \nActual: "
                + actualErrorMssgEmail + "\nExpected: "
                + errorMessEmailDE + "OR" + errorMessEmailEN);
        softAssert.assertTrue(actualErrorMssgPass.contains(errorMssgPassDE) || actualErrorMssgPass.contains(errorMessgPassEN), "Actual error message does not contain expected. \nActual: "
                + actualErrorMssgPass + "\nExpected: "
                + errorMssgPassDE + "OR" + errorMessgPassEN);
        softAssert.assertAll();
    }

    public void errorMssgWrongEmail(String errorMessDE, String errorMessEN){
        errorMessageVerification(errorMessageEmail, errorMessDE, errorMessEN);
    }


}
