package steps;

import emuns.ItemsContainer;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DashboardPageSteps extends DefaultStepsData {

    @Step
    public String getDashBoardPageTitle() {
        return dashboardPage.getTitle();
    }

    @Step
    public String getAccountNameFromDashboard() {
        return dashboardPage.getAccountNameLabel().getText();
    }

    @Step
    public void clickOnHideMenuButton() {
        dashboardPage.clickOnHideMenuButton();
    }

    @Step
    public void expandContainerClickingOnThreeDots(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                dashboardPage.getThreeDotsButtonEmployee().waitUntilEnabled().click();
                break;
            case LEAVE_TAKEN:
                dashboardPage.getThreeDotsButtonLeaves().waitUntilEnabled().click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    public boolean checkThatLegendAppearsIn(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                return dashboardPage.getEmployeeLegend().isVisible();
            case LEAVE_TAKEN:
                return dashboardPage.getLeavesLegend().isVisible();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

//    public boolean verifySectionIsPresent(String sectionName) {
//        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
//        switch (itemsContainer) {
//            case NEWS:
//                return
//        }
//    }


    //
//    @Step
//    public boolean isNewsSectionPresent() {
//        return dashboardPage.getNewsContainer().isPresent();
//    }
//
//    @Step
//    public String getNewsSectionHeaderName() {
//        return dashboardPage.getNewsHeaderText().getText();
//    }

    @Step
    public int getTotalNumberOfSectionItems(String sectionName) {
        switch (sectionName) {
            case "News":
                return getTotalNumberOfNewsItems();
            case "Documents":
                return  getTotalNumberOfDocumentItems();
            default:
                throw new IllegalStateException("Unexpected value: " + sectionName);
        }
    }

    private int getTotalNumberOfNewsItems() {
        return dashboardPage.getNewsItems().size();
    }

    private int getTotalNumberOfDocumentItems() {
        return dashboardPage.getDocumentsItems().size();
    }

    @Step
    public int trimCounterString(String stringToTrim) {
        return Integer.parseInt(StringUtils.substringBetween(stringToTrim, ": ", " /"));
    }


}
