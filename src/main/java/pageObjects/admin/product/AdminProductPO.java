package pageObjects.admin.product;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.product.AdminProductPUI;

public class AdminProductPO extends BasePage {
    private WebDriver driver;

    public AdminProductPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterProductNameTextbox(String name) {
        waitForElementVisible(driver, AdminProductPUI.PRODUCT_NAME_TEXTBOX);
        sendkeyToElement(driver, AdminProductPUI.PRODUCT_NAME_TEXTBOX, name);
    }

    public void clickSearchButton() {
        waitForElementClickable(driver, AdminProductPUI.SEARCH_BUTTON);
        clickToElement(driver, AdminProductPUI.SEARCH_BUTTON);
        waitAllLoadingIconInvisibleAdmin(driver);
    }

    public boolean isDataRowDisplayed(String productName, String sku, String price) {
        waitForElementVisible(driver, AdminProductPUI.DYNAMIC_DATA_ROW, productName, sku, price);
        return isElementDisplayed(driver, AdminProductPUI.DYNAMIC_DATA_ROW, productName, sku, price);
    }

    public void selectCategoryDropdown(String categoryName) {
        waitForElementClickable(driver, AdminProductPUI.CATEGORY_DROPDOWN);
        selectItemInDropdown(driver, AdminProductPUI.CATEGORY_DROPDOWN, categoryName);
    }

    public void checkOnSearchSubcategoriesCheckbox() {
        waitForElementClickable(driver, AdminProductPUI.SEARCH_SUBCATEGORIES_CHECKBOX);
        checkToCheckbox(driver, AdminProductPUI.SEARCH_SUBCATEGORIES_CHECKBOX);
    }

    public boolean isDataEmptyInTable() {
        waitForElementVisible(driver, AdminProductPUI.DATA_EMPTY_TABLE);
        return isElementDisplayed(driver, AdminProductPUI.DATA_EMPTY_TABLE);
    }

    public void uncheckOnSearchSubcategoriesCheckbox() {
        waitForElementClickable(driver, AdminProductPUI.SEARCH_SUBCATEGORIES_CHECKBOX);
        uncheckToCheckbox(driver, AdminProductPUI.SEARCH_SUBCATEGORIES_CHECKBOX);
    }

    public void selectManufacturerDropdown(String manufacturer) {
        waitForElementClickable(driver, AdminProductPUI.MANUFACTURER_DROPDOWN);
        selectItemInDropdown(driver, AdminProductPUI.MANUFACTURER_DROPDOWN, manufacturer);
    }

    public void enterGoDirectlyToProductSKUTextbox(String name) {
        waitForElementVisible(driver, AdminProductPUI.GO_DIRECTLY_PRODUCT_SKU_TEXTBOX);
        sendkeyToElement(driver, AdminProductPUI.GO_DIRECTLY_PRODUCT_SKU_TEXTBOX, name);
        clickToElement(driver, AdminProductPUI.GO_DIRECTLY_PRODUCT_SKU_BUTTON);
    }

    public String getEditProductTitle(){
        waitForElementVisible(driver, AdminProductPUI.EDIT_PRODUCT_TITLE);
        return getTextElement(driver, AdminProductPUI.EDIT_PRODUCT_TITLE);
    }

}
