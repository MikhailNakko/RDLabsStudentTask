package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
@Slf4j
public class DashboardPage extends BasePage {

    public static final String PAGE_TITLE = "OrangeHRM";

    @FindBy(css = ".card-content .material-icons")
    private WebElementFacade threeDotsButtonEmployee;

    @FindBy(css = ".l6:nth-of-type(3) .card-content .material-icons")
    private WebElementFacade threeDotsButtonLeaves;

    @FindBy(css = ".dashboard-outline")
    private WebElementFacade dashboardContainer;

    @FindBy(css = "#account-name")
    private WebElementFacade accountNameLabel;

    @FindBy(xpath = "//a[@id='side-menu-hamburger']")
    private WebElementFacade hideMenuButton;

    @FindBy(css = ".l6:nth-of-type(5)")
    private WebElementFacade newsContainer;

    @FindBy(css = ".l6:nth-of-type(4)")
    private WebElementFacade documentsContainer;

    @FindBy(css = ".card-content .material-icons")
    private WebElementFacade threeDotsButton;

    @FindBy(css = "#task-list-group-panel-menu_holder-legend")
    private WebElementFacade employeeLegend;

    @FindBy(css = "#legend")

    private WebElementFacade leavesLegend;

    public void clickOnHideMenuButton() {
        log.info("Clicking on the [Hide menu] button");
        hideMenuButton.waitUntilVisible().waitUntilClickable().click();
    }


}
