package parallel;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/resources/parallel",
        glue = "parallel",
    //    tags = "@EmployeeSearch and @TC2",
        tags = "@Regression",
        plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }




}
