package pageObjects.users;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserCustomerInfoPUI;

public class UserCustomerInfoPO extends UserSideBarPO {
    private WebDriver driver;

    public UserCustomerInfoPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Check on Male radiobutton")
    public void checkOnMaleRadio() {
        waitForElementClickable(driver, UserCustomerInfoPUI.MALE_RADIO);
        checkToRadioButton(driver, UserCustomerInfoPUI.MALE_RADIO);
    }

    @Step("Enter to First Name textbox with {0}")
    public void enterToFirstNameTextbox(String value) {
        waitForElementVisible(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX, value);
    }

    @Step("Enter to Last Name textbox with {0}")
    public void enterToLastNameTextbox(String value) {
        waitForElementVisible(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX, value);

    }

    @Step("Enter to Email Name textbox with {0}")
    public void enterToEmailTextbox(String value) {
        waitForElementVisible(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX, value);
    }

    @Step("Enter to Comapny Name textbox with {0}")
    public void enterToCompanyTextbox(String value) {
        waitForElementVisible(driver, UserCustomerInfoPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, UserCustomerInfoPUI.COMPANY_TEXTBOX, value);
    }

    @Step("Click the Save button")
    public void clickTheSaveButton() {
        waitForElementClickable(driver, UserCustomerInfoPUI.SAVE_BUTTON);
        clickToElement(driver, UserCustomerInfoPUI.SAVE_BUTTON);
    }

    @Step("Verify: Male radiobutton is selected")
    public boolean isMaleRadioSelected() {
        waitForElementSelected(driver, UserCustomerInfoPUI.MALE_RADIO);
        return isElementSelected(driver, UserCustomerInfoPUI.MALE_RADIO);
    }

    @Step("Verify: Get value of First Name textbox")
    public String getValueFirstNameTextbox() {
        waitForElementVisible(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX);
        return getTextboxValue(driver, UserCustomerInfoPUI.FIRST_NAME_TEXTBOX);
    }

    @Step("Verify: Get value of Last Name textbox")
    public String getValueLastNameTextbox() {
        waitForElementVisible(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX);
        return getTextboxValue(driver, UserCustomerInfoPUI.LAST_NAME_TEXTBOX);
    }

    @Step("Verify: Get value of Email textbox")
    public String getValueEmailTextbox() {
        waitForElementVisible(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX);
        return getTextboxValue(driver, UserCustomerInfoPUI.EMAIL_TEXTBOX);
    }

    @Step("Verify: Get value of Company textbox")
    public String getValueCompanyTextbox() {
        waitForElementVisible(driver, UserCustomerInfoPUI.COMPANY_TEXTBOX);
        return getTextboxValue(driver, UserCustomerInfoPUI.COMPANY_TEXTBOX);
    }
}
