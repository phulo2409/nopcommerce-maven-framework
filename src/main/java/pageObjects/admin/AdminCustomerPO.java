package pageObjects.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminCustomerPUI;

import java.util.List;

public class AdminCustomerPO extends BasePage {
    private WebDriver driver;

    public AdminCustomerPO(WebDriver driver){
        this.driver = driver;
    }

    public AdminAddCustomerPO clickOnAddNewButton() {
        waitForElementClickable(driver, AdminCustomerPUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminCustomerPUI.ADD_NEW_BUTTON);
        return PageGenerator.getPageGenerator().getAdminAddCustomer(driver);
    }

    public void selectCustomerRolesDropdown(String value) {
        List<WebElement> select_item = getListElement(driver, AdminCustomerPUI.SELECTED_CUSTOMER_ROLE);
        if(!(select_item.size() == 0)){
            for (WebElement item : select_item){
                item.click();
            }
        } else {
            waitForElementClickable(driver, AdminCustomerPUI.CUSTOMER_ROLE_SELECT);
            clickToElement(driver, AdminCustomerPUI.CUSTOMER_ROLE_SELECT);
        }
        waitForElementClickable(driver, AdminCustomerPUI.CUSTOMER_ROLE_ITEM, value);
        clickToElement(driver, AdminCustomerPUI.CUSTOMER_ROLE_ITEM, value);
    }

    public void clickOnSearchButton() {
        waitForElementClickable(driver, AdminCustomerPUI.SEARCH_BUTTON);
        clickToElement(driver, AdminCustomerPUI.SEARCH_BUTTON);
        waitAllLoadingIconInvisibleAdmin(driver);
    }

    public boolean isDataRowDisplayed(String customerName, String role, String comapny) {
        waitForElementVisible(driver, AdminCustomerPUI.DYNAMIC_DATA_ROW, customerName, role, comapny);
        return isElementDisplayed(driver, AdminCustomerPUI.DYNAMIC_DATA_ROW, customerName, role, comapny);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, AdminCustomerPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.EMAIL_TEXTBOX, email);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AdminCustomerPUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AdminCustomerPUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToComapnyTextbox(String company) {
        waitForElementVisible(driver, AdminCustomerPUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, AdminCustomerPUI.COMPANY_TEXTBOX, company);
    }

    public AdminEditCustomerPO clickOnEditButton(String name) {
        waitForElementClickable(driver, AdminCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        clickToElement(driver, AdminCustomerPUI.DYNAMIC_EDIT_BUTTON_BY_NAME, name);
        sleepInSecond(30);
        return PageGenerator.getPageGenerator().getAdminEditCustomer(driver);
    }

}
