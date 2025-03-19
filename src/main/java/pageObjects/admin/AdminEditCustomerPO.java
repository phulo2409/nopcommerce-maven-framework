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
}
