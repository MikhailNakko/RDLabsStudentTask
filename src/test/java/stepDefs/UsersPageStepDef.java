package stepDefs;

import grids.UsersGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import steps.DefaultStepsData;
import steps.UsersSteps;

import java.util.List;
import java.util.Map;

public class UsersPageStepDef extends DefaultStepsData {

    @Steps
    private UsersSteps usersSteps;

    @When("filter users by Employee Name $userName")
    public void filterUsersByEmployeeName(String employeeName) {
        usersSteps.filterUsersByEmployeeName(employeeName);
    }

    @Then("record is shown with following parameters:$table")
    public void checkResultOfFiltering(ExamplesTable examplesTable) {
        Map<String, String> row = examplesTable.getRow(0);
        List<UsersGrid> allItems = usersSteps.getUsersGrid();
        softly.assertThat(allItems).as("Wrong search result size is shown").hasSize(1);
        softly.assertThat(allItems.get(0).getUserName()).as("Wrong [Username] is shown").isEqualTo(row.get("Username"));
        softly.assertThat(allItems.get(0).getUserRole()).as("Wrong [User Role(s)] is shown").isEqualTo(row.get("User Role(s)"));
        softly.assertThat(allItems.get(0).getEmployeeName()).as("Wrong [Employee Name] is shown").isEqualTo(row.get("Employee Name"));
        softly.assertThat(allItems.get(0).getStatus()).as("Wrong [Status] is shown").isEqualTo(row.get("Status"));
        softly.assertThat(allItems.get(0).getRegions()).as("Region field is not empty").isEqualTo(row.get("Region"));
    }

    @When("I open filter users window")
    public void openFilterUsersWindow() {
        usersSteps.openFilterWindow();
    }

    @When("I click on the Search button in Filter Users window")
    public void clickOnTheSearchButtonInFilterUsersWindow() {
        usersSteps.clickOnTheSearchButton();
    }

    @When("filter users by $filterName with option $filterValue")
    public void selectFilters(String filterName, String filterValue) {
        usersSteps.filterBy(filterName, filterValue);
    }

    @Then("$userToSearch is $condition in the results")
    public void validateSearchResult(String searchResultToTest, String condition) {
        if (condition.contains("not")) {
            softly.assertThat(usersSteps.getAllUserNamesFromUsersGrid()).as("The expected result was found in " +
                    "the actual result page which is NOT expected").doesNotContain(searchResultToTest);
        } else {
            softly.assertThat(usersSteps.getAllUserNamesFromUsersGrid()).as("The expected result was NOT found in " +
                    "the actual result page").contains(searchResultToTest);
        }
    }


    @When("I click on Cancel button in Filter Users window")
    public void cancelSearch() {
        usersSteps.clickOnCancelButton();
    }

    @Then("I see filters $filterName - $filterValue retained")
    public void verifyFiltersAreRetainedAfterCancellingSearch(String filterName, String filterValue) {
        softly.assertThat(usersSteps.getDropdownSelectedValueText(filterName))
                .as("filter" + filterName + " is NOT retained")
                .isEqualTo(filterValue);
    }
}
