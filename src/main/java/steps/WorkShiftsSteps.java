package steps;

import grids.WorkShiftGrid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import pageComponents.AddWorkShiftModalWindow;
import pageComponents.TimePicker;
import utils.Converter;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class WorkShiftsSteps extends DefaultStepsData {

    @Step
    public List<WorkShiftGrid> getWorkShiftGrid() {
        log.info("Getting Work Shift table from Work Shifts page");
        return new WorkShiftGrid().getAllItems(getDriver());
    }

    @Step
    public void clickOnAddWorkShiftButton() {
        log.info("Clicking on the [Add work shift] button");
        workShiftPage.getAddWorkShiftButton().waitUntilClickable().click();
    }

    @Step
    private AddWorkShiftModalWindow getAddWorkShiftModalWindow() {
        return new AddWorkShiftModalWindow(workShiftPage.getAddWorkShiftWindow());
    }

    private TimePicker getTimePickerElement() {
        return new TimePicker(workShiftPage.getTimePickerLocator());
    }

    private void pickTimeInsideTimePicker(List<WebElementFacade> timePicker, String timeToPick) {
        for (WebElementFacade time : timePicker) {
            if (time.getText().equals(timeToPick)) {
                time.click();
                return;
            }
        }
        throw new NoSuchElementException("Could not pick selected time (" + timeToPick +
                ". Make sure minutes are within 0-60 range and can be equally divided by 5 and " +
                "hours have 24-hour format");
    }

    private void selectTimeGeneral(String time) {
        pickTimeInsideTimePicker(getTimePickerElement().getHoursBoard(), Converter.getHoursFromTime(time));
        pickTimeInsideTimePicker(getTimePickerElement().getMinutesBoard(), Converter.getMinutesFromTime(time));
    }

    public List<String> getWorkShiftTypes() {
        return getWorkShiftGrid().stream().map(WorkShiftGrid::getWorkShift).collect(Collectors.toList());
    }

    @Step
    public void selectFromTime(String time) {
        getAddWorkShiftModalWindow().getFromClockIcon().click();
        selectTimeGeneral(time);
        getTimePickerElement().getOkButton().click();
    }

    @Step
    public void selectToTime(String time) {
        getAddWorkShiftModalWindow().getToClockIcon().click();
        selectTimeGeneral(time);
        getTimePickerElement().getOkButton().click();
    }

    @Step
    public String getHoursPerDay() {
        return getAddWorkShiftModalWindow().getHoursPerDayInputField().getValue();
    }

    @Step
    public void saveAddedWorkShift() {
        getAddWorkShiftModalWindow().getSaveButton().click();
    }

    @Step
    public String getWarningUnderWorkShiftNameText() {
        return getAddWorkShiftModalWindow().getWarningUnderWorkShiftName().getText();
    }
}
