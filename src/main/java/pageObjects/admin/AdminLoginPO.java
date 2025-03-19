package pageObjects.admin;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.admin.AdminLoginPUI;

public class AdminLoginPO extends BasePage {
    private WebDriver driver;

    public AdminLoginPO(WebDriver driver){
        this.driver = driver;
    }

    public AdminDashboardPO loginAdminAccount(String email, String password){
        waitForElementVisible(driver, AdminLoginPUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPUI.EMAIL_TEXTBOX, email);
        sendkeyToElement(driver, AdminLoginPUI.PASSWORD_TEXTBOX, password);
        clickToElement(driver, AdminLoginPUI.LOGIN_BUTTON);
        waitAllLoadingIconInvisibleAdmin(driver);
        return PageGenerator.getPageGenerator().getAdminDashboard(driver);
    }
}
