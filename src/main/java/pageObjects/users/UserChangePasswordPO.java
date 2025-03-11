package pageObjects.users;

import org.openqa.selenium.WebDriver;
import pageUIs.users.UserChangePasswordPUI;

public class UserChangePasswordPO extends UserSideBarPO{
    private WebDriver driver;

    public UserChangePasswordPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void enterToOldPassword(String value) {
        waitForElementVisible(driver, UserChangePasswordPUI.OLD_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserChangePasswordPUI.OLD_PASSWORD_TEXTBOX, value);
    }

    public void enterToNewPassword(String value) {
        waitForElementVisible(driver, UserChangePasswordPUI.NEW_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserChangePasswordPUI.NEW_PASSWORD_TEXTBOX, value);
    }

    public void enterToConfirmPassword(String value) {
        waitForElementVisible(driver, UserChangePasswordPUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserChangePasswordPUI.CONFIRM_PASSWORD_TEXTBOX, value);
    }

    public void clickTheChangePasswordButton() {
        waitForElementClickable(driver, UserChangePasswordPUI.CHANGE_PASSWORD_BUTTON);
        clickToElement(driver, UserChangePasswordPUI.CHANGE_PASSWORD_BUTTON);
    }

    public void changePassword(String oldPass, String newPass){
        enterToOldPassword(oldPass);
        enterToNewPassword(newPass);
        enterToConfirmPassword(newPass);
        clickTheChangePasswordButton();
    }
}
