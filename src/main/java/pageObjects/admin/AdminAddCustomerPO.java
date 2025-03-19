package pageObjects.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminAddCustomerPUI;

import java.util.List;

public class AdminAddCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminAddCustomerPO(WebDriver driver){
        this.driver = driver;
    }


    public void enterToEmailTextbox(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminAddCustomerPUI.EMAIL_TEXTBOX, value);
    }

    public void enterToPasswordTextbox(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminAddCustomerPUI.PASSWORD_TEXTBOX, value);
    }

    public void enterToFirstNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddCustomerPUI.FIRST_NAME_TEXTBOX, value);
    }

    public void enterToLastNameTextbox(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminAddCustomerPUI.LAST_NAME_TEXTBOX, value);
    }

    public void checkOnGenderRadio(String gender) {
        if (gender.toUpperCase().equals("MALE")) {
            waitForElementClickable(driver, AdminAddCustomerPUI.MALE_GENDER_RADIO);
            checkToRadioButton(driver, AdminAddCustomerPUI.MALE_GENDER_RADIO);
        } else if (gender.toUpperCase().equals("FEMALE")) {
            waitForElementClickable(driver, AdminAddCustomerPUI.FEMALE_GENDER_RADIO);
            checkToRadioButton(driver, AdminAddCustomerPUI.FEMALE_GENDER_RADIO);
        }
    }

    public void enterToCompanyTextbox(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminAddCustomerPUI.COMPANY_TEXTBOX, value);
    }

    public void selectCustomerRolesDropdown(String value) {
        List<WebElement> select_item = getListElement(driver, AdminAddCustomerPUI.SELECTED_CUSTOMER_ROLE);
        if(!(select_item.size() == 0)){
            for (WebElement item : select_item){
                item.click();
            }
        } else {
            waitForElementClickable(driver, AdminAddCustomerPUI.CUSTOMER_ROLE_SELECT);
            clickToElement(driver, AdminAddCustomerPUI.CUSTOMER_ROLE_SELECT);
        }
        waitForElementClickable(driver, AdminAddCustomerPUI.CUSTOMER_ROLE_ITEM, value);
        clickToElement(driver, AdminAddCustomerPUI.CUSTOMER_ROLE_ITEM, value);
    }

    public void checkOnActiveCheckbox() {
        waitForElementClickable(driver, AdminAddCustomerPUI.ACTIVE_CHECKBOX);
        checkToCheckbox(driver, AdminAddCustomerPUI.ACTIVE_CHECKBOX);
    }

    public void enterToAdminComment(String value) {
        waitForElementVisible(driver, AdminAddCustomerPUI.ADMIN_COMMENT_AREA);
        sendkeyToElement(driver, AdminAddCustomerPUI.ADMIN_COMMENT_AREA, value);
    }

    public void clickOnSaveAndContinueEditButton() {
        waitForElementClickable(driver, AdminAddCustomerPUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
        clickToElement(driver, AdminAddCustomerPUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
    }

    public String getSuccessMessage() {
        waitForElementVisible(driver, AdminAddCustomerPUI.SUCCESS_MESSAGE);
        return getTextElement(driver, AdminAddCustomerPUI.SUCCESS_MESSAGE);
    }

    public AdminCustomerPO clickOnBackLink(){
        waitForElementClickable(driver, AdminAddCustomerPUI.BACK_LINK);
        clickToElement(driver, AdminAddCustomerPUI.BACK_LINK);
        return PageGenerator.getPageGenerator().getAdminCustomer(driver);
    }
}
