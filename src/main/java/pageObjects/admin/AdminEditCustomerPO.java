package pageObjects.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminEditCustomerPUI;

public class AdminEditCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminEditCustomerPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminEditCustomerPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.EMAIL_TEXTBOX, email);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AdminEditCustomerPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AdminEditCustomerPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToCompanyTextbox(String company) {
        waitForElementVisible(driver, AdminEditCustomerPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminEditCustomerPUI.COMPANY_TEXTBOX, company);
    }

    public void enterToAdminCommentTextArea(String text) {
        waitForElementVisible(driver, AdminEditCustomerPUI.ADMIN_COMMENT_AREA);
        sendkeyToElement(driver, AdminEditCustomerPUI.ADMIN_COMMENT_AREA, text);
    }

    public AdminCustomerPO clickSaveButton() {
        waitForElementClickable(driver, AdminEditCustomerPUI.SAVE_BUTTON);
        clickToElement(driver, AdminEditCustomerPUI.SAVE_BUTTON);
        return PageGenerator.getPageGenerator().getAdminCustomer(driver);
    }

    public void clickOnAddressTab() {
        waitForElementClickable(driver, AdminEditCustomerPUI.ADDRESS_TAB);
        clickToElement(driver, AdminEditCustomerPUI.ADDRESS_TAB);
    }

    public AdminAddNewAddressPO clickOnAddNewAddressButton() {
        waitForElementClickable(driver, AdminEditCustomerPUI.ADD_NEW_ADDRESS_BUTTON);
        clickToElement(driver, AdminEditCustomerPUI.ADD_NEW_ADDRESS_BUTTON);
        return PageGenerator.getPageGenerator().getAdminAddNewAddress(driver);
    }

    public String getTextAddressDataRow(String firstName, String lastName, String email, String phone, String fax) {
        return getTextElement(driver, AdminEditCustomerPUI.ADDRESS_DATA_ROW_TABLE, firstName, lastName, email, phone, fax);
    }

    public boolean isDataAddressRowUndisplayed(String firstName, String lastName, String email, String phone, String fax) {
        return isElementUndisplayed(driver, AdminEditCustomerPUI.ADDRESS_DATA_ROW_TABLE, firstName, lastName, email, phone, fax);
    }


    public AdminEditAddressPO clickOnEditButtonInTableByName(String name) {
        waitForElementClickable(driver, AdminEditCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminEditCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        return PageGenerator.getPageGenerator().getAdminEditAddress(driver);
    }

    public void clickOnDeleteButtonInAddressTable(String name) {
        waitForElementClickable(driver, AdminEditCustomerPUI.DYNAMIC_DELETE_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminEditCustomerPUI.DYNAMIC_DELETE_BUTTON_BY_NAME, name);
        acceptToAlert(driver);
        waitAllLoadingIconInvisibleAdmin(driver);
    }

    public boolean noDataInTable() {
        waitForElementVisible(driver, AdminEditCustomerPUI.NO_DATA_MESSAGE_TABLE);
        return isElementDisplayed(driver, AdminEditCustomerPUI.NO_DATA_MESSAGE_TABLE);
    }
}
