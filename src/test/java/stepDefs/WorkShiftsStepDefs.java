package stepDefs;

import grids.WorkShiftGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.yecht.Data;
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



    @When("I set working shift hours from $fromTime to $toTime")
    public void setWorkingHoursForShift(String fromTime, String toTime) {
        workShiftsSteps.selectFromTime(fromTime);
        workShiftsSteps.selectToTime(toTime);
    }

    @Then("the Hours per Day field shows $value")
    public void verifyTime(String expectedText) {
        softly.assertThat(workShiftsSteps.getHoursPerDay()).isEqualTo(expectedText);
    }

    @When("I try saving new work shift")
    public void saveWorkShift() {
        workShiftsSteps.saveAddedWorkShift();
    }

    @Then("I see $Required warning under Work Shift field")
    public void verifyWarningMessageAfterTryingToSaveWhenRequiredFieldsAreLeftEmpty(String expectedWarningMessage) {
        softly.assertThat(workShiftsSteps.getWarningUnderWorkShiftNameText()).as("Warning message text" +
                "does not match the expected one").isEqualTo(expectedWarningMessage);
    }
}
