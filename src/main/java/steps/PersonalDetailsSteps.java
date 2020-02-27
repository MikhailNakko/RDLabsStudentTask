package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PersonalDetailsSteps extends DefaultStepsData {

    @Step
    public String getValueFromDateOfBirthField() {
        return personalDetailsPage.getDateOfBirthInputField().getAttribute("value");
    }

    @Step
    public void enterDateIntoDateBirthField(String date) {
        personalDetailsPage.getDateOfBirthInputField().clear();
        personalDetailsPage.enterDateOfBirth(date);
    }

    @Step
    public List<String> getOptionsFromNationalitySelect() {
        return personalDetailsPage.getNationalitySelect().thenFindAll(By.xpath("./..//li//span"))
                .stream().map(we -> we.getAttribute("innerText")).collect(Collectors.toList());
    }

    @Step
    public void checkGenderRadioButton(String genderButtonName) {
        if (genderButtonName.equals("Male")) {
            personalDetailsPage.clickOnMaleRadioButton();
        } else if (genderButtonName.equals("Female")) {
            personalDetailsPage.clickOnFemaleRadioButton();
        } else {
            throw new NoSuchElementException("No button with that name found");
        }
    }

    @Step
    public String checkDateOfBirthErrorText() {
        return personalDetailsPage.getDateOfBirthErrorMessage().getText();
    }

    @Step
    public String getEEORaceDropDownDefaultValue() {
        return personalDetailsPage.getEeoRaceAndEthnicitySelect().getAttribute("value");
    }

    @Step
    public String getEEORaceWarningIfNotSelectedText() {
        return personalDetailsPage.getEeoRaceAndEthnicityWarningIfNotSelected().getText();
    }


}
