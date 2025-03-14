package pageObjects.users;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserProductPUI;
import pageUIs.users.UserRecentlyViewedProductsPUI;

public class UserProductPO extends BasePage {
    private WebDriver driver;

    public UserProductPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click to Add Your Review link")
    public void clickAddYourReviewLink() {
        waitForElementClickable(driver, UserProductPUI.ADD_YOUR_REVIEW_LINK);
        clickToElement(driver, UserProductPUI.ADD_YOUR_REVIEW_LINK);
    }

    @Step("Enter to Review Title textbox with {0}")
    public void enterToReviewTitleTextbox(String value) {
        waitForElementVisible(driver, UserProductPUI.REVIEW_TITLE_TEXTBOX);
        sendkeyToElement(driver, UserProductPUI.REVIEW_TITLE_TEXTBOX, value);
    }

    @Step("Enter to Review Text textbox with {0}")
    public void enterToReviewTextTextbox(String value) {
        waitForElementVisible(driver, UserProductPUI.REVIEW_TEXT_TEXTAREA);
        sendkeyToElement(driver, UserProductPUI.REVIEW_TEXT_TEXTAREA, value);
    }

    @Step("Check on Rating Radiobutton with {0}")
    public void checkOnRatingRadio(String value) {
        waitForElementClickable(driver, UserProductPUI.RATING_RADIOBUTTON, value);
        clickToElement(driver, UserProductPUI.RATING_RADIOBUTTON, value);
    }

    @Step("Click Submit Review button")
    public void clickSubmitReviewButton() {
        waitForElementClickable(driver, UserProductPUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver, UserProductPUI.SUBMIT_REVIEW_BUTTON);
    }

    public void clickOnAddToWishlistButton() {
        waitForElementClickable(driver, UserProductPUI.ADD_TO_WISHLIST_BUTT0N);
        clickToElement(driver, UserProductPUI.ADD_TO_WISHLIST_BUTT0N);
    }

    public UserWishlistPO openWishlistPage() {
        waitForElementClickable(driver, UserProductPUI.WISHLIST_MENU_ITEM);
        clickToElement(driver, UserProductPUI.WISHLIST_MENU_ITEM);
        return PageGenerator.getPageGenerator().getWishList(driver);
    }

    public UserRecentlyViewedProductsPO openRecentlyViewedProductsPage(){
        waitForElementClickable(driver, UserProductPUI.RECENTLY_VIEWED_PRODUCTS_LINK);
        clickToElement(driver, UserProductPUI.RECENTLY_VIEWED_PRODUCTS_LINK);
        return PageGenerator.getPageGenerator().getRecentlyViewedProducts(driver);
    }

    public UserProductListPO clickOnBreadcrumb(String item){
        waitForElementClickable(driver, UserProductPUI.DYNAMIC_BREADCRUMB_ITEM, item);
        clickToElement(driver, UserProductPUI.DYNAMIC_BREADCRUMB_ITEM, item);
        return PageGenerator.getPageGenerator().getUserProductList(driver);
    }
}
