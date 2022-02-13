package BaseUtilities;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.*;

public class Functions {

    protected WebDriver driver;
    protected WebDriverWait waitForElement;
    SoftAssert softAssert = new SoftAssert();

    public Functions(WebDriver driver) {
        this.driver = driver;
        this.waitForElement = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    protected void clickWithWait(By locator, String elementName) {
        try {
            waitForElementToBeClickable(locator);
            click(locator);
        } catch (NoSuchElementException e) {
            Assert.fail("Element " + elementName + " with locator " + locator + " is not visible on page.");
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void waitForElementToBeVisible(By locator) {
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeClickable(By locator) {
        waitForElement.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForElementToBePresent(By locator) {
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void sendKeys(By locator, String text, String elementName) {
        try {
            waitForElementToBePresent(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        } catch (NoSuchElementException e) {
            Assert.fail("Could not find " + elementName + " with locator " + locator);
        }
    }

    protected void elementIsEnabled(By locator, String elementName) {
        WebElement test = driver.findElement(locator);
        if (test.isEnabled()) {
            clickWithWait(locator, elementName);
        } else {
            Assert.fail("Could not find " + elementName + " with locator " + locator);
        }
    }

    protected Boolean elementIsDisplayed(By locator, String elementName) {
        try {
            try {
                waitForElementToBePresent(locator);
            } catch (TimeoutException e) {
                Assert.fail("Could not find " + elementName + " with locator " + locator);
            }
            return driver.findElement(locator).isDisplayed();

        } catch (NoSuchElementException e) {
            Assert.fail("Could not find " + elementName + " with locator " + locator);
            return null;
        }
    }

    protected String getText(By locator) {
        waitForElementToBePresent(locator);
        return driver.findElement(locator).getText();
    }


    protected void switchToFrame(String id) {
        driver.switchTo().frame(id);
    }


    protected Boolean countElements(By locator) {
        boolean elementCount = driver.findElements(locator).size() > 0;
        return elementCount;
    }

    protected void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("No alert present!");
        }
        driver.switchTo().defaultContent();
    }

    protected void switchFocusToSecondTab() {
        //String newWindow = driver.getWindowHandle();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
    }

    protected void errorMessageVerification(By locator, String errorMessDE, String errorMessEN) {
        String actualErrorMessage = getText(locator);
        System.out.println(actualErrorMessage);

        Assert.assertTrue(actualErrorMessage.contains(errorMessDE) || actualErrorMessage.contains(errorMessEN), "Actual error message does not contain expected. \nActual: "
                + actualErrorMessage + "\nExpected: "
                + errorMessEN + "OR" + errorMessDE);
        System.out.println(actualErrorMessage);
    }

    protected void clickByJS(By locator) {

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        try {
            WebElement element = driver.findElement(locator);
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("Element " + locator + "is not found");
        }
    }


    protected void validateAllElementsOfPage(HashMap<String, By> map) {
        for (Map.Entry<String, By> oneLocator : map.entrySet()) {
            try {
                waitForElementToBeVisible(oneLocator.getValue());
            } catch (Exception e) {
                softAssert.fail("Element " + oneLocator.getKey() + " with locator " + oneLocator.getValue() + " is not present");
            }
        }
        softAssert.assertAll();


      /*  Set mapSet = map.entrySet();
        Iterator it = mapSet.iterator();
        while (it.hasNext()) {
            Map.Entry mp = (Map.Entry) it.next();
            try {
                waitForElement.until(ExpectedConditions.visibilityOfElementLocated((By) mp.getValue()));
            } catch (Exception e) {
                softAssert.fail("Locator " + mp.getKey() + " is not present");
            }
        }
        softAssert.assertAll();
        */
    }

    protected void validateName(By locator, String text) {

     /*   waitForElement.until(ExpectedConditions.presenceOfElementLocated(locator));
        List<WebElement> allClient = driver.findElements(locator);
        for (int i=0; i<= allClient.size()-1;i++){
            System.out.println(allClient.size());
           if( allClient.get(i).getText().equals(text)){
               System.out.println("This text is one that we searched for");
           }
        }
        softAssert.assertAll();*/

        waitForElementToBeVisible(locator);
        List<WebElement> all = driver.findElements(locator);
        for (WebElement one : all) {
            if (one.getText().equals(text)) {
                System.out.println("This text is one that we searched for");
            } /*else System.out.println("This text is one that we searched for");*/
        }
        softAssert.assertAll();
    }

    protected void checkDifferentErrorMessageList(List<WebElement> allError, List<String> allMessagesEnglish, List<String> allMessagesGerman) {
        for (int i = 0; i < allError.size(); i++) {
           /*if (allError.get(i).getText().equals(allMessagesEnglish.get(i))|| allError.get(i).getText().equals(allMessagesGerman.get(i)))
               System.out.println("Error message is the same");
           else
               softAssert.fail("Error messages are different. Actual result is "+allError.get(i).getText());*/

            softAssert.assertTrue(allError.get(i).getText().equals(allMessagesEnglish.get(i)) || allError.get(i).getText().equals(allMessagesGerman.get(i)), "Actual error message is not equal to expected. \nActual: "
                    + allError.get(i).getText() + "\nExpected: "
                    + allMessagesEnglish.get(i) + " OR " + allMessagesGerman.get(i));
        }
        softAssert.assertAll();
    }

    protected void elementIsNotDisplayed(By locator, String elementName) {
        if (!driver.findElement(locator).isDisplayed()) {
            Assert.fail("Element " + elementName + " with locator " + locator + "is displayed");
        }
    }

    protected void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
