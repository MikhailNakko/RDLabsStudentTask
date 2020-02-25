package stepDefs;

import grids.WorkShiftGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import steps.DefaultStepsData;
import steps.WorkShiftsSteps;

import java.util.List;
import java.util.stream.Collectors;

public class WorkShiftsStepDefs extends DefaultStepsData {

    @Steps
    private WorkShiftsSteps workShiftsSteps;

    @When("I click on Add Work Shift button")
    public void clickOnAddWorkShiftButton() {
        workShiftsSteps.clickOnAddWorkShiftButton();
    }

    @Then("I see $valueToCheckIfPresent in the $columnName column")
    public void verifyColumnHas(String columnName, String valueToCheckIfPresent) {
        List<WorkShiftGrid> workShiftGridItems = workShiftsSteps.getWorkShiftGrid();
        List<String> workShiftItems = workShiftGridItems.stream().map(WorkShiftGrid::getWorkShift).collect(Collectors.toList());
        softly.assertThat(workShiftItems).as("The column does NOT contain desired value").contains(valueToCheckIfPresent);
    }

    @When("I select $10 hours")
    public void selectHour(String hourToSelect) throws InterruptedException {
        workShiftsSteps.clickOnAddWorkShiftButton();
        workShiftPage.getSelectFromTimepickerContainer().then(By.xpath("//i[.='access_time']")).click();
        workShiftsSteps.timeSelector(hourToSelect);
        Thread.sleep(10000);
    }
}
