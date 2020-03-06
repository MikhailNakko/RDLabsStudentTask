package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import steps.CommonSteps;
import steps.DashboardPageSteps;
import steps.DefaultStepsData;
import utils.Converter;

public class DashboardPageStepDef extends DefaultStepsData {

    @Steps
    private DashboardPageSteps dashboardPageSteps;
    @Steps
    private CommonSteps commonSteps;

    @Then("dashboard page opens with account name $userName")
    public void checkThatDashboardPageOpens(String userName) {
        softly.assertThat(dashboardPageSteps.getDashBoardPageTitle())
                .as("Wrong page title")
                .isEqualTo(dashboardPage.getTitle());
        softly.assertThat(dashboardPageSteps.getAccountNameFromDashboard())
                .as("Wrong account name is shown on page")
                .isEqualTo(userName);
    }

    //https://jbehave.org/reference/latest/aliases.html

    @When("I click on $hide menu button")
    public void whenClickOnTheHideMenuButton() {
        dashboardPageSteps.clickOnHideMenuButton();
    }

    @Then("main menu $condition")
    public void mainMenuCondition(String condition) {
        String warningMessage = "Menu not " + condition + " after clicking on the hide/show menu button";
        if (condition.equals("disappear")) {
            softly.assertThat(commonSteps.isMenuAvatarVisibleNow()).as(warningMessage).isFalse();
        }
    }

    @When("I click on the three dots button inside $sectionName section")
    public void clickiOnThreeDotsButton(String sectionName) {
        dashboardPageSteps.expandContainerClickingOnThreeDots(sectionName);
    }

    @Then("Legend component appears in $sectionName section")
    public void checkThatLegendAppears(String sectionName) {
        softly.assertThat(dashboardPageSteps.checkThatLegendAppearsIn(sectionName))
                .as("Legend component not appears").isTrue();
    }

    @Then("$News section is present")
    public void checkSectionPresent(String sectionName) {
        softly.assertThat(dashboardPageSteps.isSectionPresent(sectionName))
                .as(sectionName + " section is not present")
                .isTrue();
    }

    @Then("section name is $News")
    public void verifyNewsSectionHeaderMatchesExpected(String expectedHeaderText){
        softly.assertThat(dashboardPageSteps.getSectionHeaderText(expectedHeaderText))
                .as("Wrong or missing text")
                .isEqualTo(expectedHeaderText);
    }

    @Then("$section counter correspond to the actual number of items in the section")
    public void verifyNewsCounterMatchesActualNumberOfNewsDisplayed(String sectionName){
        softly.assertThat(dashboardPageSteps.getTotalNumberOfSectionItems(sectionName))
                .as("Section counter and " +
                "actual number of section items do not match")
                .isEqualTo(Converter.getContainerCounterValue(dashboardPageSteps.getSectionItemsCounterText(sectionName)));
    }

}
