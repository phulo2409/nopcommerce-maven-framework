package com.nopcommerce.common;

import common.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.login.UserLoginPO;
import pageObjects.users.login.UserRegisterPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

import java.util.Set;

public class Login extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeTest
    public void beforeTest(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), nopCommerceData.getPassword());
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(email, nopCommerceData.getPassword());

        nopCommerceCookies = homePage.getAllCookies(driver);
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private FakerConfig fakerConfig, faker;
    private NopCommerceData nopCommerceData;
    private String email;
    public static Set<Cookie> nopCommerceCookies;
}
