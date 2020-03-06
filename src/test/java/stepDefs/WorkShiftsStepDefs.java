package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.WorkShiftsSteps;

public class WorkShiftsStepDefs extends DefaultStepsData {

    @Steps
    private WorkShiftsSteps workShiftsSteps;

    @When("I click on Add Work Shift button")
    public void clickOnAddWorkShiftButton() {
        workShiftsSteps.clickOnAddWorkShiftButton();
    }

    @Then("I see $valueToCheckIfPresent in the Workshift column")
    public void verifyColumnHas(String valueToCheckIfPresent) {
        softly.assertThat(workShiftsSteps.getWorkShiftTypes())
                .as("The column does NOT contain desired value").contains(valueToCheckIfPresent);
    }


    @When("I set working shift hours $from $time $to $time")
    public void setWorkingHoursForShift(String from, String time1, String to, String time2) {
        workShiftsSteps.selectTime(from, time1);
        workShiftsSteps.selectTime(to, time2);
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
