package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserProductPUI;

public class UserProductPO extends BasePage {
    private WebDriver driver;

    public UserProductPO(WebDriver driver){
        this.driver = driver;
    }

    public void clickAddYourReviewLink() {
        waitForElementClickable(driver, UserProductPUI.ADD_YOUR_REVIEW_LINK);
        clickToElement(driver, UserProductPUI.ADD_YOUR_REVIEW_LINK);
    }

    public void enterToReviewTitleTextbox(String value) {
        waitForElementVisible(driver, UserProductPUI.REVIEW_TITLE_TEXTBOX);
        sendkeyToElement(driver, UserProductPUI.REVIEW_TITLE_TEXTBOX, value);
    }

    public void enterToReviewTextTextbox(String value) {
        waitForElementVisible(driver, UserProductPUI.REVIEW_TEXT_TEXTAREA);
        sendkeyToElement(driver, UserProductPUI.REVIEW_TEXT_TEXTAREA, value);
    }

    public void checkOnRatingRadio(String value) {
        waitForElementClickable(driver, UserProductPUI.RATING_RADIOBUTTON, value);
        clickToElement(driver, UserProductPUI.RATING_RADIOBUTTON, value);
    }

    public void clickSubmitReviewButton() {
        waitForElementClickable(driver, UserProductPUI.SUBMIT_REVIEW_BUTTON);
        clickToElement(driver, UserProductPUI.SUBMIT_REVIEW_BUTTON);
    }
}
