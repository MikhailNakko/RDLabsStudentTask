package stepDefs;

import com.google.common.collect.Ordering;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.DATEPATTERN_US;
import static utils.DateUtils.getDateInFutureOrPastFromNow;
import static utils.SessionVariables.DATE_OF_BIRTH;
import static resources.Constants.DROPDOWN_DEFAULT_EMPTY_VALUE;

public class PersonalDetailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @Then("I save current Date of Birth to session")
    public void saveCurentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_US, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }
    @When("I check $radioButtonName button")
    public void checkRadioButton(String radionButtonName) {
        personalDetailsSteps.checkGenderRadioButton(radionButtonName);
    }

    @When("I set birthday as $1 day from today")
    public void changeDateOfBirth(int daysFromToday) {
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_US, daysFromToday);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @When("I try saving my Personal Details")
    public void savePersonalDetails() {
        personalDetailsPage.clickOnSavePersonalDetailsButton();
    }

    @Then("I see '$errorText' error message")
    public void validateErrorMessageAfterEnteringInvalidBirthDate(String errorText){
        softly.assertThat(personalDetailsSteps.checkDateOfBirthErrorText()).as("No error text or text itself" +
                "doesn't match expected").isEqualTo(errorText);
    }

    @Then("EEO Race and Ethnicity dropdown has no value by default")
    public void validateEEODropdownHasNoValueByDefault() {
        softly.assertThat(personalDetailsSteps.getEEORaceDropDownDefaultValue()).as("The default value is not " +
                "empty").isEqualTo(DROPDOWN_DEFAULT_EMPTY_VALUE);
    }

    @Then("I see '$Required' error message")
    public void validateErrorMessageAfterLeavingEEODropdownWithNoValue(String errorText) {
        softly.assertThat(personalDetailsSteps.getEEORaceWarningIfNotSelectedText()).as("Warning message text" +
                "does not match the expected one").isEqualTo(errorText);
    }
}
