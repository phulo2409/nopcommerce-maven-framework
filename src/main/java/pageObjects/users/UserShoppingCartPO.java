package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserShoppingCartPUI;
import pageUIs.users.UserWishlistPUI;

public class UserShoppingCartPO extends BasePage {
    private WebDriver driver;

    public UserShoppingCartPO(WebDriver driver){
        this.driver = driver;
    }

    public String getWishlistQuantity() {
        waitForElementVisible(driver, UserShoppingCartPUI.WISHLIST_QUATITY_MENU_ITEM);
        return getTextElement(driver, UserShoppingCartPUI.WISHLIST_QUATITY_MENU_ITEM).replace("(", "").replace(")", "");
    }

    public String getShoppingCartQuantity() {
        waitForElementVisible(driver, UserShoppingCartPUI.SHOPPING_CART_QUANTITY_MENU_ITEM);
        return getTextElement(driver, UserShoppingCartPUI.SHOPPING_CART_QUANTITY_MENU_ITEM).replace("(", "").replace(")", "");
    }

    public UserProductPO clickOnEditButton(String productName) {
        waitForElementClickable(driver, UserShoppingCartPUI.DYNAMIC_EDIT_LINK, productName);
        clickToElement(driver, UserShoppingCartPUI.DYNAMIC_EDIT_LINK, productName);
        return PageGenerator.getPageGenerator().getUserProduct(driver);
    }

    public String getNoDataText() {
        waitForElementVisible(driver, UserShoppingCartPUI.NO_DATA_MESSAGE);
        return getTextElement(driver, UserShoppingCartPUI.NO_DATA_MESSAGE);
    }

    public void removeProduct(String productName) {
        waitForElementClickable(driver, UserShoppingCartPUI.DYNAMIC_REMOVE_BUTTON, productName);
        clickToElement(driver, UserShoppingCartPUI.DYNAMIC_REMOVE_BUTTON, productName);
    }


    public void updateProductQuantity(String productName, String number) {
        waitForElementVisible(driver, UserShoppingCartPUI.DYNAMIC_PRODUCT_QUANTITY, productName);
        sendkeyToElementWithKeysChord(driver, UserShoppingCartPUI.DYNAMIC_PRODUCT_QUANTITY, number, productName);
        pressKeyToElement(driver, UserShoppingCartPUI.DYNAMIC_PRODUCT_QUANTITY, Keys.ENTER, productName);
        waitAllLoadingIconInvisible(driver);
    }

    public String getProductTotalPrice(String productName) {
        waitForElementVisible(driver, UserShoppingCartPUI.DYNAMIC_PRODUCT_TOTAL_PRICE_TABLE, productName);
        return getTextElement(driver, UserShoppingCartPUI.DYNAMIC_PRODUCT_TOTAL_PRICE_TABLE, productName);
    }

    public void selectGiftWrapping(String option) {
        waitForElementClickable(driver, UserShoppingCartPUI.GIFT_WRAPPING_DROPDOWN);
        selectItemInDropdown(driver, UserShoppingCartPUI.GIFT_WRAPPING_DROPDOWN, option);
    }

    public void clickOnEstimateShippingButton() {
        waitForElementClickable(driver, UserShoppingCartPUI.ESTIMATE_SHIPPING_BUTTON);
        clickToElement(driver, UserShoppingCartPUI.ESTIMATE_SHIPPING_BUTTON);
    }

    public void configEstimateShippingPopup(String country, String state, String zip, String shippingMethod) {
        waitForElementClickable(driver, UserShoppingCartPUI.COUNTRY_POPUP_DROPDOWN);
        selectItemInDropdown(driver, UserShoppingCartPUI.COUNTRY_POPUP_DROPDOWN, country);
        selectItemInDropdown(driver, UserShoppingCartPUI.STATE_POPUP_DROPDOWN, state);
        sendkeyToElement(driver, UserShoppingCartPUI.ZIP_POSTAL_POPUP_TEXTBOX, zip);
        waitForElementInvisible(driver, UserShoppingCartPUI.POPUP_LOADING_ICON);
        checkToRadioButton(driver, UserShoppingCartPUI.DYNAMIC_SHIPPING_METHOD_POPUP_RADIO, shippingMethod);
        clickToElement(driver, UserShoppingCartPUI.APPLY_BUTTON);
        waitAllLoadingIconInvisible(driver);
    }

    public void checkOnTermOfServiceCheckbox() {
        waitForElementClickable(driver, UserShoppingCartPUI.TERM_OF_SERVICE_CHECKBOX);
        checkToCheckbox(driver, UserShoppingCartPUI.TERM_OF_SERVICE_CHECKBOX);
    }

    public UserCheckOutPO clickOnCheckOutButton() {
        waitForElementClickable(driver, UserShoppingCartPUI.CHECK_OUT_BUTTON);
        clickToElement(driver, UserShoppingCartPUI.CHECK_OUT_BUTTON);
        return PageGenerator.getPageGenerator().getCheckOut(driver);
    }
}
