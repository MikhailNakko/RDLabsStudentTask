package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.reflections.Reflections.log;

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
        List<String> nationalityOptions = personalDetailsPage.getNationalitySelect().thenFindAll(By.xpath("./..//li//span"))
                .stream().map(we -> we.getAttribute("innerText")).collect(Collectors.toList());
        return nationalityOptions;
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
}
