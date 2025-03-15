package ProjectDemo.com.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-report.html",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",

        },
        glue = "ProjectDemo/com/step_definitions",//path from source root
        features = "@target/rerun.txt"




)
public class FailedTestRunner {


}
