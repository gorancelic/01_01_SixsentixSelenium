# FRAMEWORK STRUCTURE!

## First separation:

* main
    * main should contain everything regarding the structure of the project and all page object models that reflect
      pages of the project.
* test
    * test should contain all data regarding test cases such as feature files, step classes, runner class...etc.

## Secondary separation:

### main/java:

* BaseUtilities -> should contain files for browser handling, data reading and common functions used in project.

* PageObjectModel -> should contain structure of packages/classes that reflect structure of the application that you are working
  on. You will find the separate pages of Staffminer application, as well as elements that can be found for all pages separately.

### test/java:

* parallel package -> This package should contain:
    * steps for UI test cases -> contains mapped steps from cucumber feature files for UI test.
    * Application Hooks class -> which handles everything that we be done before and after test.
    * Runner class -> which is used to target specific tests and set plugins that you wish to use.

### test/resources:

* parallel package should contain files that match structure of UI from application side which will contain
      feature files for UI tests
	  
* configs package should contain data properties file where we will store necessary information that can be reused later.


# WRITING,RUNNING AND REPORTING!

### STEPS:

1. Create feature file for desired tests in:
    1. `src/test/resources/parallel`
2. Fill the feature file with:
    1. `Scenario `
    2. `Scenario Outline`
        1. **Separation of tests for Scenario Outline can be done with annotations ex. @ Smoke / @Regression. Check login.feature.**
3. Create steps that will map those scenarios/steps in:
    1. `src/test/java/parallel`
4. Create page class for the page you wish to test in: `src/main/java/PageObjectModel`
5. Fill the page class with the elements of that page using `By` library.
7. Use functions created in page class in the steps by creating object of the page class.

### EXECUTION:

**There are multiple ways to execute test cases**

1. Directly from feature file by **right-clicking** on specific Scenario or Scenario Outline and then choosing **Run**.
   That way you will run desired test in single thread.
2. From Runner class in our case **TestRunner**. Right-click on the class and choose **Run**. If you choose to run tests
   like this, the tests will run in the multithreading creating 3 instances and will target tests that carry annotation set
   inside the class under **@CucumberOptions** -> **tags**
3. From XML file testng.xml in root folder of the project -> those xml files will target runner class in our case
   TestRunner and in them, you can set number of threads for execution.

### REPORTING:

1. `project/allure-report` - should contain files from Allure report tool after the `allure generate target/allure-results --clean` command is ran.

* For detailed explanation for sharing report result please click [HERE](https://sixsentix-my.sharepoint.com/:b:/p/milos_janousek/EYcNAIA5ga1Nmigt1IQsmxIBIIXBcz_QwA2L9MirbHFvaQ?e=CpCAUZ "Allure reporting documentation").

