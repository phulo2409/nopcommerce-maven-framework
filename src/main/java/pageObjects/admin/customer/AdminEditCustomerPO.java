package pageObjects.admin.customer;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.customer.AdminEditCustomerPUI;

public class AdminEditCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminEditCustomerPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter to Email textbox with: {0}")
    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminEditCustomerPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.EMAIL_TEXTBOX, email);
    }

    @Step("Enter to First name textbox with: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AdminEditCustomerPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Last Name textbox with: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AdminEditCustomerPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Enter to Company textbox with: {0}")
    public void enterToCompanyTextbox(String company) {
        waitForElementVisible(driver, AdminEditCustomerPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.COMPANY_TEXTBOX, company);
    }

    @Step("Enter to Admin Comment textare with: {0}")
    public void enterToAdminCommentTextArea(String text) {
        waitForElementVisible(driver, AdminEditCustomerPUI.ADMIN_COMMENT_AREA);
        sendkeyToElement(driver, AdminEditCustomerPUI.ADMIN_COMMENT_AREA, text);
    }

    @Step("Click on Save button")
    public AdminCustomerPO clickSaveButton() {
        waitForElementClickable(driver, AdminEditCustomerPUI.SAVE_BUTTON);
        clickToElement(driver, AdminEditCustomerPUI.SAVE_BUTTON);
        return PageGenerator.getPageGenerator().getAdminCustomer(driver);
    }

    @Step("Click on Address tab")
    public void clickOnAddressTab() {
        waitForElementClickable(driver, AdminEditCustomerPUI.ADDRESS_TAB);
        clickToElement(driver, AdminEditCustomerPUI.ADDRESS_TAB);
    }

    @Step("Click on Add New Address button")
    public AdminAddNewAddressPO clickOnAddNewAddressButton() {
        waitForElementClickable(driver, AdminEditCustomerPUI.ADD_NEW_ADDRESS_BUTTON);
        clickToElement(driver, AdminEditCustomerPUI.ADD_NEW_ADDRESS_BUTTON);
        return PageGenerator.getPageGenerator().getAdminAddNewAddress(driver);
    }

    @Step("Verify: Get text of Address data row")
    public String getTextAddressDataRow(String firstName, String lastName, String email, String phone, String fax) {
        return getTextElement(driver, AdminEditCustomerPUI.ADDRESS_DATA_ROW_TABLE, firstName, lastName, email, phone, fax);
    }

    @Step("Verify: Data Address row is displayed")
    public boolean isDataAddressRowUndisplayed(String firstName, String lastName, String email, String phone, String fax) {
        return isElementUndisplayed(driver, AdminEditCustomerPUI.ADDRESS_DATA_ROW_TABLE, firstName, lastName, email, phone, fax);
    }

    @Step("Click on Edit button in table by {0} name")
    public AdminEditAddressPO clickOnEditButtonInTableByName(String name) {
        waitForElementClickable(driver, AdminEditCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminEditCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        return PageGenerator.getPageGenerator().getAdminEditAddress(driver);
    }

    @Step("Click on Delete button in Address table")
    public void clickOnDeleteButtonInAddressTable(String name) {
        waitForElementClickable(driver, AdminEditCustomerPUI.DYNAMIC_DELETE_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminEditCustomerPUI.DYNAMIC_DELETE_BUTTON_BY_NAME, name);
        acceptToAlert(driver);
        waitAllLoadingIconInvisibleAdmin(driver);
    }

    @Step("Verify: No data is displayed in table")
    public boolean getTextMessageNoDataInTable() {
        waitForElementVisible(driver, AdminEditCustomerPUI.NO_DATA_MESSAGE_TABLE);
        return isElementDisplayed(driver, AdminEditCustomerPUI.NO_DATA_MESSAGE_TABLE);
    }
}
