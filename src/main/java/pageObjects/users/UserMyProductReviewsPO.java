package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserMyProductReviewsPUI;

public class UserMyProductReviewsPO extends BasePage {
    private WebDriver driver;

    public UserMyProductReviewsPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isTheReviewDisplayed(String productTitle) {
        waitForElementVisible(driver, UserMyProductReviewsPUI.PRODUCT_REVIEW_TITLE, productTitle);
        return isElementDisplayed(driver, UserMyProductReviewsPUI.PRODUCT_REVIEW_TITLE, productTitle);
    }
}
