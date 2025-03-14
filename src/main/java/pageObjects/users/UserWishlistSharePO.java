package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.BasePageUI;
import pageUIs.users.UserProductPUI;
import pageUIs.users.UserWishlistPUI;
import pageUIs.users.UserWishlistSharePUI;

public class UserWishlistSharePO extends BasePage {
    WebDriver driver;

    public UserWishlistSharePO(WebDriver driver){
        this.driver = driver;
    }

    public String getShareWishlistPageTitle() {
        waitForElementVisible(driver, UserWishlistSharePUI.SHARE_WISHLIST_TITLE);
        return getTextElement(driver, UserWishlistSharePUI.SHARE_WISHLIST_TITLE);
    }

    public UserWishlistPO openWishlistPage() {
        waitForElementClickable(driver, UserWishlistSharePUI.WISHLIST_MENU_ITEM);
        clickToElement(driver, UserWishlistSharePUI.WISHLIST_MENU_ITEM);
        return PageGenerator.getPageGenerator().getWishList(driver);
    }

    public UserProductPO clickToProductName(String productName){
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
        clickToElement(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
        return PageGenerator.getPageGenerator().getUserProduct(driver);
    }



}
