package pageObjects.admin.customer;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageGenerator;
import pageUIs.admin.customer.AdminCustomerPUI;

import java.util.List;

public class AdminCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminCustomerPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click on Add New Button")
    public AdminAddCustomerPO clickOnAddNewButton() {
        waitForElementClickable(driver, AdminCustomerPUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminCustomerPUI.ADD_NEW_BUTTON);
        return PageGenerator.getPageGenerator().getAdminAddCustomer(driver);
    }

    @Step("Select Customer Roles dropdown with: {0}")
    public void selectCustomerRolesDropdown(String value) {
        List<WebElement> select_item = getListElement(driver, AdminCustomerPUI.SELECTED_CUSTOMER_ROLE);
        if(!(select_item.size() == 0)){
            for (WebElement item : select_item){
                item.click();
            }
        } else {
            waitForElementClickable(driver, AdminCustomerPUI.CUSTOMER_ROLE_SELECT);
            clickToElement(driver, AdminCustomerPUI.CUSTOMER_ROLE_SELECT);
        }
        waitForElementClickable(driver, AdminCustomerPUI.CUSTOMER_ROLE_ITEM, value);
        clickToElement(driver, AdminCustomerPUI.CUSTOMER_ROLE_ITEM, value);
    }

    @Step("Click on Search Button")
    public void clickOnSearchButton() {
        waitForElementClickable(driver, AdminCustomerPUI.SEARCH_BUTTON);
        clickToElement(driver, AdminCustomerPUI.SEARCH_BUTTON);
        waitAllLoadingIconInvisibleAdmin(driver);
    }

    @Step("Verify: Data row is displayed in table")
    public boolean isDataRowDisplayed(String customerName, String role, String comapny) {
        waitForElementVisible(driver, AdminCustomerPUI.DYNAMIC_DATA_ROW, customerName, role, comapny);
        return isElementDisplayed(driver, AdminCustomerPUI.DYNAMIC_DATA_ROW, customerName, role, comapny);
    }

    @Step("Enter to Email textbox with: {0}")
    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminCustomerPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.EMAIL_TEXTBOX, email);
    }

    @Step("Enter to First Name textbox with: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AdminCustomerPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Last Name textbox with: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AdminCustomerPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Enter to Company textbox with: {0}")
    public void enterToComapnyTextbox(String company) {
        waitForElementVisible(driver, AdminCustomerPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.COMPANY_TEXTBOX, company);
    }

    @Step("Click on Edit button of {0} customer name")
    public AdminEditCustomerPO clickOnEditButton(String name) {
        waitForElementClickable(driver, AdminCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        waitAllLoadingIconInvisibleAdmin(driver);
        return PageGenerator.getPageGenerator().getAdminEditCustomer(driver);
    }

}
