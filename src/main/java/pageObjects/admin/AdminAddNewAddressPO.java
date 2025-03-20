package pageObjects.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminAddCustomerPUI;
import pageUIs.admin.AdminAddNewAddressPUI;

import java.util.Collection;

public class AdminAddNewAddressPO extends BasePage {
    private WebDriver driver;

    public AdminAddNewAddressPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.FIRST_NAME_TEXTBOX, value);
    }

    public void enterToLastNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.LAST_NAME_TEXTBOX, value);
    }

    public void enterToEmailTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.EMAIL_TEXTBOX, value);
    }

    public void enterToCompanyTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.COMPANY_TEXTBOX, value);
    }

    public void selectCountryDropdown(String value) {
        waitForElementClickable(driver, AdminAddNewAddressPUI.COUNTRY_DROPDOWN);
        selectItemInDropdown(driver, AdminAddNewAddressPUI.COUNTRY_DROPDOWN, value);
    }

    public void selectStateDropdown(String value) {
        waitForElementClickable(driver, AdminAddNewAddressPUI.STATE_DROPDOWN);
        selectItemInDropdown(driver, AdminAddNewAddressPUI.STATE_DROPDOWN, value);
    }

    public void enterToCityTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.CITY_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.CITY_TEXTBOX, value);
    }

    public void enterToAddress1Textbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ADDRESS1_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ADDRESS1_TEXTBOX, value);
    }

    public void enterToAddress2Textbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ADDRESS2_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ADDRESS2_TEXTBOX, value);
    }

    public void enterToZipTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.ZIP_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.ZIP_TEXTBOX, value);
    }

    public void enterToPhoneTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.PHONE_NUMBER_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.PHONE_NUMBER_TEXTBOX, value);
    }

    public void enterToFaxTextbox(String value) {
        waitForElementVisible(driver, AdminAddNewAddressPUI.FAX_NUMBER_TEXTBOX);
        sendkeyToElement(driver, AdminAddNewAddressPUI.FAX_NUMBER_TEXTBOX, value);
    }

    public void clickOnSaveButton() {
        waitForElementClickable(driver, AdminAddNewAddressPUI.SAVE_BUTTON);
        clickToElement(driver, AdminAddNewAddressPUI.SAVE_BUTTON);
    }

    public AdminEditCustomerPO clickOnBackLink() {
        waitForElementClickable(driver, AdminAddNewAddressPUI.BACK_LINK);
        clickToElement(driver, AdminAddNewAddressPUI.BACK_LINK);
        return PageGenerator.getPageGenerator().getAdminEditCustomer(driver);
    }
}
