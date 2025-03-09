package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserRegisterPUI;

public class UserRegisterPO extends BasePage {
    private WebDriver driver;

    public UserRegisterPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.FIRST_NAME_TEXTBOX, value);
    }

    public void enterToLastNameTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.LAST_NAME_TEXTBOX, value);
    }

    public void enterToEmailTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.EMAIL_TEXTBOX, value);
    }

    public void enterToCompanyTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.COMPANY_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.COMPANY_NAME_TEXTBOX, value);
    }

    public void enterToPasswordTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.PASSWORD_TEXTBOX, value);
    }

    public void enterToConfirmPasswordTextbox(String value) {
        waitForElementVisible(driver, UserRegisterPUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPUI.CONFIRM_PASSWORD_TEXTBOX, value);
    }

    public UserHomePO clickToLogoutLink(){
        waitForElementClickable(driver, UserRegisterPUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPUI.LOGOUT_LINK);
        return PageGenerator.getUserHomePage(driver);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPUI.SUCCESS_MESSAGE);
        return getTextElement(driver, UserRegisterPUI.SUCCESS_MESSAGE);
    }

    public String getRegisterErrorMessage(){
        waitForElementVisible(driver,UserRegisterPUI.REGISTER_ERROR_MESSAGE);
        return getTextElement(driver, UserRegisterPUI.REGISTER_ERROR_MESSAGE);
    }

    public String getFirstNameValidationMessage() {
        waitForElementVisible(driver, UserRegisterPUI.FIRST_NAME_VALIDATION);
        return getTextElement(driver, UserRegisterPUI.FIRST_NAME_VALIDATION);
    }

    public String getLastNameValidationMessage() {
        waitForElementVisible(driver, UserRegisterPUI.LAST_NAME_VALIDATION);
        return getTextElement(driver, UserRegisterPUI.LAST_NAME_VALIDATION);
    }

    public String getEmailValidationMessage() {
        waitForElementVisible(driver, UserRegisterPUI.EMAIL_VALIDATION);
        return getTextElement(driver, UserRegisterPUI.EMAIL_VALIDATION);
    }

    public String getPasswordValidationMessage() {
        waitForElementVisible(driver, UserRegisterPUI.PASSWORD_VALIDATION);
        return getTextElement(driver, UserRegisterPUI.PASSWORD_VALIDATION);
    }

    public String getConfirmPasswordValidationMessage() {
        waitForElementVisible(driver, UserRegisterPUI.CONFIRM_PASSWORD_VALIDATION);
        return getTextElement(driver, UserRegisterPUI.CONFIRM_PASSWORD_VALIDATION);
    }

    public void clickTheRegisterButton() {
        waitForElementClickable(driver, UserRegisterPUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPUI.REGISTER_BUTTON);
    }
}
