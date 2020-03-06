package steps;

import enums.PersonalDetailsItems;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;
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
        PersonalDetailsItems personalDetailsItems = PersonalDetailsItems.getPersonalDetailsItems(genderButtonName);
        switch (personalDetailsItems) {
            case MALE:
                personalDetailsPage.clickOnMaleRadioButton();
                break;
            case FEMALE:
                personalDetailsPage.clickOnFemaleRadioButton();
                break;
            default:
                throw new IllegalStateException("Unexpected value " + genderButtonName + "\n Should be either 'Male' or 'Female'");
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
