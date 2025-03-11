package pageObjects.users;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserHomePUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open the Register Page")
    public UserRegisterPO openRegisterPage(){
        waitForElementClickable(driver, UserHomePUI.REGISTER_LINK);
        clickToElement(driver, UserHomePUI.REGISTER_LINK);
        return PageGenerator.getPageGenerator().getUserRegisterPage(driver);
    }

    @Step("Open the Login Page")
    public UserLoginPO openLoginPage(){
        waitForElementClickable(driver, UserHomePUI.LOGIN_LINK);
        clickToElement(driver, UserHomePUI.LOGIN_LINK);
        return PageGenerator.getPageGenerator().getUserLoginPage(driver);
    }

    @Step("Verify: Check 'Welcome to our store' displayed")
    public boolean isWelcomeTitleDisplayed() {
        waitForElementVisible(driver, UserHomePUI.WELCOME_TITLE);
        return isElementDisplayed(driver, UserHomePUI.WELCOME_TITLE);
    }
}
