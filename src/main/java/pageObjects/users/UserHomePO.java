package pageObjects.users;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserHomePUI;

import java.util.Set;

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

    @Step("Open Search Page")
    public UserSearchPO openSearchPage(){
        waitForElementClickable(driver, UserHomePUI.SEARCH_FOOTER_LINK);
        clickToElement(driver, UserHomePUI.SEARCH_FOOTER_LINK);
        return PageGenerator.getPageGenerator().getUserSearch(driver);
    }

    @Step("Get all cookies")
    public Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    @Step("Set cookies")
    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
    }

    @Step("Refresh current page")
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
}
