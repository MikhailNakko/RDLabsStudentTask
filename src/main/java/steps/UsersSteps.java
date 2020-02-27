package steps;

import grids.UsersGrid;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import pageComponents.FilterUsersModalWindow;

import java.util.List;
import java.util.stream.Collectors;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Slf4j
public class UsersSteps extends DefaultStepsData {

    @Step
    public void openFilterWindow() {
        log.info("Opens Filter Users window");
        usersPage.clickOnFilterButton();
        FILTER_USERS_WINDOW.put(new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow()));
    }

    @Step
    public FilterUsersModalWindow getFilterUsersWindow() {
        return new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow());
    }

    @Step
    public void clickOnTheSearchButton() {
        log.info("Clicking on the Search button");
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        filterUsersModalWindow.clickOnSearchButton();
    }

    @Step
    public void filterUsersByEmployeeName(String employeeName) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Employee Name: " + employeeName);
        log.info("Typing employee name into [Employee Name] input field");
        filterUsersModalWindow.getEmployeeNameField().waitUntilEnabled().sendKeys(employeeName);
        WebElementFacade employeeDropDown = filterUsersModalWindow.getEmployeeNameField().find(By.xpath("./..//div[contains(@class,'angucomplete-row')]"));
        log.info("Clicking on the autocomplete search result");
        employeeDropDown.waitUntilVisible().waitUntilClickable().click();
        employeeDropDown.waitUntilNotVisible();
    }

    @Step
    public List<UsersGrid> getUsersGrid() {
        log.info("Getting [Users] grid");
        return new UsersGrid().getAllItems(usersPage.getDriver());
    }

    //should this one be more generalized?
    @Step
    public List<String> getAllUserNamesFromUsersGrid() {
        return getUsersGrid().stream().map(UsersGrid::getEmployeeName).collect(Collectors.toList());
    }


    //check
    @Step
    public void filterBy(String dropdownName, String buttonInsideDropdown) {
        getDriver().findElement(By.xpath("//div[@id='" +
                removeWhiteSpacesAndConvertToLower(dropdownName) + "_inputfileddiv']")).click();
        getDriver().findElement(By.xpath("//span[.='" + buttonInsideDropdown + "']")).click();
    }

    private String removeWhiteSpacesAndConvertToLower(String textToModify) {
        return StringUtils.deleteWhitespace(textToModify).toLowerCase();
    }

    @Step
    public void clickOnCancelButton() {
        getFilterUsersWindow().clickOnCancelButton();
    }

    @Step
    public String getDropdownSelectedValueText(String dropdownName) {
        return getDriver()
                .findElement(By.cssSelector("div#" + removeWhiteSpacesAndConvertToLower(dropdownName)
                        + "_inputfileddiv  .select-dropdown"))
                .getAttribute("value");
    }
}
