package ProjectDemo.com.step_definitions;

import com.utilities.ExcelProcessManager;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * This class is designed for Cucumber run option
 * If we run in Junit, we don't need this class
 *
 */

@Log4j2
@AllArgsConstructor
public class ExcelStepDefinition {

    @When("User reads data from excel file")
    public void userReadsDataFromExcelFile() {
        ExcelProcessManager.getExcelRowData();
    }


}
