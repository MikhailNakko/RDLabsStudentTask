package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.Duration;

@Getter
@Slf4j
public class UsersPage extends BasePage {

    @FindBy(xpath = "//a[@data-tooltip='Filter']")
    private WebElementFacade filterButton;

    @FindBy(xpath = "//div[@id='systemUser_list_search_modal']/div[@class='modal-footer']/a[2]")
    private WebElementFacade cancelButton;

    @FindBy(css = ".ng-valid-parse-add [readonly]")
    private WebElementFacade adminRolesDropdownContainer;

    @FindBy(css = "#status_inputfileddiv [readonly]")
    private WebElementFacade statusDropdownContainer;

    public void clickOnFilterButton() {
        log.info("Clicking on the [Filter button]");
        filterButton.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible();
        waitUntilSpinnerGone(3);
        filterButton.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible().waitUntilEnabled().waitUntilClickable().click();
    }
}
