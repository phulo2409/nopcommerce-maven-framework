package com.nopcommerce.common;

import common.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.PageGenerator;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserRegisterPO;
import pageObjects.users.UserSearchPO;
import utilities.FakerConfig;

import java.util.Set;

public class Login extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeTest
    public void beforeTest(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        fakerConfig = FakerConfig.getFaker();
        fakerConfig = FakerConfig.getFaker();
        firstName = fakerConfig.getFirstName();
        lastName = fakerConfig.getLastName();
        email = fakerConfig.getEmailAddress();
        company = fakerConfig.getCompany();
        password = fakerConfig.getPassword();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(firstName, lastName, email, company, password);
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(email, password);

        nopCommerceCookies = homePage.getAllCookies(driver);

        closeBrowserDriver();
    }

    WebDriver driver;
    UserHomePO homePage;
    UserRegisterPO registerPage;
    UserLoginPO loginPage;
    UserSearchPO searchPage;
    FakerConfig fakerConfig, faker;
    String firstName, lastName, email, company, password;
    public static Set<Cookie> nopCommerceCookies;
}
