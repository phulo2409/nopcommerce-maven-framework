package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.users.UserRecentlyViewedProductsPUI;

public class UserRecentlyViewedProductsPO extends BasePage {
    private WebDriver driver;

    public UserRecentlyViewedProductsPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isProductNameDisplayed(String productName){
        waitForElementVisible(driver, UserRecentlyViewedProductsPUI.PRODUCT_NAME, productName);
        return isElementDisplayed(driver, UserRecentlyViewedProductsPUI.PRODUCT_NAME, productName);
    }

}
