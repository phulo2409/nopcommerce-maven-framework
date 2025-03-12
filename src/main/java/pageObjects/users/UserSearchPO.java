package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserSearchPUI;

public class UserSearchPO extends BasePage {
    private WebDriver driver;

    public UserSearchPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToSearchKeywordTextbox(String value) {
        waitForElementVisible(driver, UserSearchPUI.SEARCH_KEYWORD_TEXTBOX);
        sendkeyToElement(driver, UserSearchPUI.SEARCH_KEYWORD_TEXTBOX, value);
    }

    public String getWarningValidation() {
        waitForElementVisible(driver, UserSearchPUI.WARNING_VALIDATION);
        return getTextElement(driver, UserSearchPUI.WARNING_VALIDATION);
    }

    public String getNoResultValidation() {
        waitForElementVisible(driver, UserSearchPUI.NO_RESULT_VALIDATION);
        return getTextElement(driver, UserSearchPUI.NO_RESULT_VALIDATION);
    }

    public int getTotalProductDisplayed() {
        waitForElementVisible(driver, UserSearchPUI.PRODUCT_ITEM);
        return getListElementNumber(driver, UserSearchPUI.PRODUCT_ITEM);
    }

    public boolean isProductDisplayed(String value) {
        waitForElementVisible(driver, UserSearchPUI.PRODUCT_ITEM_TITLE,value);
        return isElementDisplayed(driver, UserSearchPUI.PRODUCT_ITEM_TITLE, value);
    }

    public void checkOnAdvancedSearchCheckbox() {
        waitForElementClickable(driver, UserSearchPUI.ADVANCED_SEARCH_CHECKBOX);
        checkToCheckbox(driver, UserSearchPUI.ADVANCED_SEARCH_CHECKBOX);
    }

    public void selectItemInCategoryDropDown(String value) {
        waitForElementClickable(driver, UserSearchPUI.CATEGORY_DROPDOWN);
        selectItemInDropdown(driver, UserSearchPUI.CATEGORY_DROPDOWN, value);
    }

    public void uncheckCategoryCheckbox() {
        waitForElementClickable(driver, UserSearchPUI.CATEGORY_CHECKBOX);
        uncheckToCheckbox(driver, UserSearchPUI.CATEGORY_CHECKBOX);
    }

    public void checkOnCategoryCheckbox() {
        waitForElementClickable(driver, UserSearchPUI.CATEGORY_CHECKBOX);
        checkToCheckbox(driver, UserSearchPUI.CATEGORY_CHECKBOX);
    }

    public void selectItemInManuFacturerDropdown(String value) {
        waitForElementClickable(driver, UserSearchPUI.MANUFACTURER_DROPDOWN);
        selectItemInDropdown(driver, UserSearchPUI.MANUFACTURER_DROPDOWN, value);
    }
    public void clickTheSearchButton(){
        waitForElementClickable(driver, UserSearchPUI.SEARCH_BUTTON);
        clickToElement(driver, UserSearchPUI.SEARCH_BUTTON);
    }
}
