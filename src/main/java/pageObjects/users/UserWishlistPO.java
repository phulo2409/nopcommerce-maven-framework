package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserWishlistPUI;

public class UserWishlistPO extends BasePage {
    private WebDriver driver;

    public UserWishlistPO(WebDriver driver){
        this.driver = driver;
    }

    public UserWishlistSharePO clickOnWishlistSharingLink() {
        waitForElementClickable(driver, UserWishlistPUI.SHARE_LINK);
        clickToElement(driver, UserWishlistPUI.SHARE_LINK);
        return PageGenerator.getPageGenerator().getWishListShare(driver);
    }

    public void clickOnAddToCartButtonOnTable(String productName){
        waitForElementClickable(driver, UserWishlistPUI.DYNAMIC_ADD_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserWishlistPUI.DYNAMIC_ADD_BUTTON_BY_PRODUCT_NAME, productName);
    }

    public void clickAddToCartButton(){
        waitForElementClickable(driver, UserWishlistPUI.ADD_TO_CART_BUTTON);
        clickToElement(driver, UserWishlistPUI.ADD_TO_CART_BUTTON);
    }





    public UserShoppingCartPO openShoppingCartPage() {
        waitForElementClickable(driver, UserWishlistPUI.SHOPPING_CART_MENU_ITEM);
        return PageGenerator.getPageGenerator().getShoppingCart(driver);
    }

    public void removeProductByName(String productName) {
        waitForElementClickable(driver, UserWishlistPUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(driver, UserWishlistPUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName );
    }

    public UserShoppingCartPO addProductToCart(String productName){
        clickOnAddToCartButtonOnTable(productName);
        clickAddToCartButton();
        return PageGenerator.getPageGenerator().getShoppingCart(driver);
    }

    public String getNoDataText() {
        waitForElementVisible(driver, UserWishlistPUI.NO_DATA_MESSAGE);
        return getTextElement(driver, UserWishlistPUI.NO_DATA_MESSAGE);
    }
}
