package pageObjects.admin.customer;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.customer.AdminAddNewAddressPUI;

public class AdminAddNewAddressPO extends BasePage {
    private WebDriver driver;

    public AdminAddNewAddressPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter to First Name textbox with: {0}")
    public void enterToFirstNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.FIRST_NAME_TEXTBOX, value);
    }

    @Step("Enter to Last Name textbox with: {0}")
    public void enterToLastNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.LAST_NAME_TEXTBOX, value);
    }

    @Step("Enter to Email textbox with: {0}")
    public void enterToEmailTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.EMAIL_TEXTBOX, value);
    }

    @Step("Enter to Comapny textbox with: {0}")
    public void enterToCompanyTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.COMPANY_TEXTBOX, value);
    }

    @Step("Select item in Country dropdown with: {0}")
    public void selectCountryDropdown(String value) {
        waitForElementClickable(driver, AdminAddNewAddressPUI.COUNTRY_DROPDOWN);
        selectItemInDropdown(driver, AdminAddNewAddressPUI.COUNTRY_DROPDOWN, value);
    }

    @Step("Select item in State with: {0}")
    public void selectStateDropdown(String value) {
        waitForElementClickable(driver, AdminAddNewAddressPUI.STATE_DROPDOWN);
        selectItemInDropdown(driver, AdminAddNewAddressPUI.STATE_DROPDOWN, value);
    }

    @Step("Enter to City textbox with: {0}")
    public void enterToCityTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.CITY_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.CITY_TEXTBOX, value);
    }

    @Step("Enter to Address1 textbox with: {0}")
    public void enterToAddress1Textbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ADDRESS1_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ADDRESS1_TEXTBOX, value);
    }

    @Step("Enter to Address2 textbox with: {0}")
    public void enterToAddress2Textbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ADDRESS2_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ADDRESS2_TEXTBOX, value);
    }

    @Step("Enter to Zip textbox with: {0}")
    public void enterToZipTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ZIP_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ZIP_TEXTBOX, value);
    }

    @Step("Enter to Phone Number textbox with: {0}")
    public void enterToPhoneTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.PHONE_NUMBER_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.PHONE_NUMBER_TEXTBOX, value);
    }

    @Step("Enter to Fax Number textbox with: {0}")
    public void enterToFaxTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.FAX_NUMBER_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.FAX_NUMBER_TEXTBOX, value);
    }

    @Step("Click on Save button")
    public void clickOnSaveButton() {
        waitForElementClickable(driver, AdminAddNewAddressPUI.SAVE_BUTTON);
        clickToElement(driver, AdminAddNewAddressPUI.SAVE_BUTTON);
    }

    @Step("Click on Back link")
    public AdminEditCustomerPO clickOnBackLink() {
        waitForElementClickable(driver, AdminAddNewAddressPUI.BACK_LINK);
        clickToElement(driver, AdminAddNewAddressPUI.BACK_LINK);
        return PageGenerator.getPageGenerator().getAdminEditCustomer(driver);
    }
}
