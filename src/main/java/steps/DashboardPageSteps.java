package steps;

import enums.ItemsContainer;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.DocumentsContainer;
import pageComponents.NewsContainer;

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

    @Step
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

    private DocumentsContainer documentsContainer() {
        return new DocumentsContainer(dashboardPage.getDocumentsContainer());
    }

    private NewsContainer newsContainer(){
        return new NewsContainer(dashboardPage.getNewsContainer());
    }



    @Step
    public int getTotalNumberOfSectionItems(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return newsContainer().getContainerItems().size();
            case DOCUMENTS:
                return documentsContainer().getContainerItems().size();
            default:
                throw new IllegalStateException("Unexpected value: " + sectionName);
        }
    }

    @Step
    public String getSectionHeaderText(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return newsContainer().getContainerHeaderText();
            case DOCUMENTS:
                return documentsContainer().getContainerHeaderText();
            default:
                throw new IllegalStateException("Unexpected value: " + sectionName);
        }
    }

    @Step
    public String getSectionItemsCounterText(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return newsContainer().getContainerItemsCounter();
            case DOCUMENTS:
                return documentsContainer().getContainerItemsCounter();
            default:
                throw new IllegalStateException("Unexpected value: " + sectionName);
        }
    }

    public boolean isSectionPresent(String sectionName) {
        return dashboardPage.getDashboardContainer()
                .then(By.cssSelector("#dashboard__view" + sectionName +"OnDashboard")).isVisible();
    }


}
