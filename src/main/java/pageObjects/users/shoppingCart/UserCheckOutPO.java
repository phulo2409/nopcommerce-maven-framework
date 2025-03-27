package pageObjects.users.shoppingCart;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.shoppingCart.UserCheckOutPUI;

public class UserCheckOutPO extends BasePage {
    private WebDriver driver;

    public UserCheckOutPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Uncheck on Ship On Same Address checkbox")
    public void uncheckOnShipOnSameAddressCheckbox() {
        waitForElementClickable(driver, UserCheckOutPUI.SHIPPING_SAME_ADDRESS_CHECKBOX);
        uncheckToCheckbox(driver, UserCheckOutPUI.SHIPPING_SAME_ADDRESS_CHECKBOX);
    }

    @Step("Add an new {0} Address")
    public void addNewAddressBillingShipping(String tabName,String firstName, String lastName, String email, String company, String country,
                                     String state, String city, String address1, String address2, String zip, String phoneNumber, String faxNumber) {
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_FIRST_NAME_TEXTBOX, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_FIRST_NAME_TEXTBOX, firstName, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_LAST_NAME_TEXTBOX, lastName, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_EMAIL_TEXTBOX, email, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_COMPANY_TEXTBOX, company, tabName);
        selectItemInDropdown(driver, UserCheckOutPUI.DYNAMIC_COUNTRY_TEXTBOX, country, tabName);
        selectItemInDropdown(driver, UserCheckOutPUI.DYNAMIC_STATE_TEXTBOX, state, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_CITY_TEXTBOX, city, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_ADDRESS1_TEXTBOX, address1, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_ADDRESS2_TEXTBOX, address2, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_ZIP_TEXTBOX, zip, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_PHONE_NUMBER_TEXTBOX, phoneNumber, tabName);
        sendkeyToElement(driver, UserCheckOutPUI.DYNAMIC_FAX_NUMBER_TEXTBOX, faxNumber, tabName);
        clickToElement(driver, UserCheckOutPUI.DYNAMIC_CONTINUE_BUTTON, tabName.toLowerCase());
    }

    @Step("Select {0} item in Shipping Address dropdown ")
    public void selectShippingAddressDropdown(String value) {
        waitForElementClickable(driver, UserCheckOutPUI.SHIPPING_ADDRESS_DROPDOWN);
        selectItemInDropdown(driver, UserCheckOutPUI.SHIPPING_ADDRESS_DROPDOWN, value);
    }

    @Step("Select {0} radiobutton in Shipping Method ")
    public void selectShippingMethod(String method) {
        waitForElementClickable(driver, UserCheckOutPUI.DYNAMIC_SHIPPING_METHOD_NAME, method);
        checkToRadioButton(driver, UserCheckOutPUI.DYNAMIC_SHIPPING_METHOD_NAME, method);
        clickToElement(driver, UserCheckOutPUI.SHIPPING_METHOD_CONTINUE_BUTTON);
    }

    @Step("Select {0} radiobutton in Payment Method")
    public void selectPaymentMethod(String method) {
        waitForElementClickable(driver, UserCheckOutPUI.DYNAMIC_PAYMENT_METHOD_NAME, method);
        checkToRadioButton(driver, UserCheckOutPUI.DYNAMIC_PAYMENT_METHOD_NAME, method);
        clickToElement(driver, UserCheckOutPUI.PAYMENT_METHOD_CONTINUE_BUTTON);
    }

    @Step("Verify: Get text of Payment information")
    public String getPaymentInformation() {
        waitForElementVisible(driver, UserCheckOutPUI.PAYMENT_INFORMATION_TEXT);
        return getTextElement(driver, UserCheckOutPUI.PAYMENT_INFORMATION_TEXT);
    }

    @Step("Click Continue button in Payment Information tab")
    public void clickContinueButtonInPaymentInformation() {
        waitForElementClickable(driver, UserCheckOutPUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
        clickToElement(driver, UserCheckOutPUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
    }

    @Step("Verify: Get text of {0} Name")
    public String getDynamicNameText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_NAME_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_NAME_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Email")
    public String getDynamicEmailText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_EMAIL_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_EMAIL_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Phone Number")
    public String getDynamicPhoneText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_PHONE_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_PHONE_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Fax Number")
    public String getDynamicFaxText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_FAX_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_FAX_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Comapny")
    public String getDynamicCompanyText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_COMPANY_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_COMPANY_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Country")
    public String getDynamicCountryText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_COUNTRY_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_COUNTRY_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} State")
    public String getDynamicStateText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_STATE_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_STATE_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Address 1")
    public String getDynamicAddress1Text(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_ADDRESS1_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_ADDRESS1_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Address 2")
    public String getDynamicAddress2Text(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_ADDRESS2_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_ADDRESS2_TEXT, tabName);
    }

    @Step("Verify: Get text of {0} Zip Postal")
    public String getDynamicZipText(String tabName){
        waitForElementVisible(driver, UserCheckOutPUI.DYNAMIC_ZIP_TEXT, tabName);
        return getTextElement(driver, UserCheckOutPUI.DYNAMIC_ZIP_TEXT, tabName);
    }

    @Step("Verify: Data are displayed in table")
    public boolean isDataRowDisplayed(String productName, String price, String quantity, String totalPrice) {
        waitForElementVisible(driver, UserCheckOutPUI.DATA_ROW_TABLE, productName, price, quantity, totalPrice);
        return isElementDisplayed(driver, UserCheckOutPUI.DATA_ROW_TABLE, productName, price, quantity, totalPrice);
    }

    @Step("Verify: Get text of Sub-Total price")
    public String getSubTotalPriceText() {
        waitForElementVisible(driver, UserCheckOutPUI.SUB_TOTAL_TEXT);
        return getTextElement(driver, UserCheckOutPUI.SUB_TOTAL_TEXT);
    }

    @Step("Verify: Get text of Shipping price")
    public String getShippingPriceText() {
        waitForElementVisible(driver, UserCheckOutPUI.SHIPPING_COST_TEXT);
        return getTextElement(driver, UserCheckOutPUI.SHIPPING_COST_TEXT);
    }

    @Step("Verify: Get text of Tax price")
    public String getTaxPriceText() {
        waitForElementVisible(driver, UserCheckOutPUI.TAX_COST_TEXT);
        return getTextElement(driver, UserCheckOutPUI.TAX_COST_TEXT);
    }

    @Step("Verify: Get text of Total price")
    public String getTotalPriceText() {
        waitForElementVisible(driver, UserCheckOutPUI.TOTAL_COST_TEXT);
        sleepInSecond(10);
        return getTextElement(driver, UserCheckOutPUI.TOTAL_COST_TEXT);
    }

    @Step("Click Confirm Button")
    public void clickConfirmButton() {
        waitForElementClickable(driver, UserCheckOutPUI.CONFIRM_BUTTON);
        clickToElement(driver, UserCheckOutPUI.CONFIRM_BUTTON);
    }

    @Step("Verify: Get text of Successful title")
    public String getSuccessfulTitle() {
        waitForElementVisible(driver, UserCheckOutPUI.SUCCESS_TITLE);
        return getTextElement(driver, UserCheckOutPUI.SUCCESS_TITLE);
    }

    @Step("Verify: Get text of Successful message")
    public String getSuccessfulMessage() {
        waitForElementVisible(driver, UserCheckOutPUI.SUCCESS_MESSAGE);
        return getTextElement(driver, UserCheckOutPUI.SUCCESS_MESSAGE);
    }

    @Step("Verify: Get text of Order number")
    public String getOrderNumber() {
        waitForElementVisible(driver, UserCheckOutPUI.ORDER_NUMBER);
        return getTextElement(driver, UserCheckOutPUI.ORDER_NUMBER);
    }
}
