package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
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
}
