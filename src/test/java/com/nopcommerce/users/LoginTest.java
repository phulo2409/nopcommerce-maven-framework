package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.login.UserLoginPO;
import pageObjects.users.login.UserRegisterPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class LoginTest extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);
        nopCommerceData = NopCommerceData.getNopCommerceData();
        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), nopCommerceData.getPassword());
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();

    }

    @Test
    public void Login_01_Empty_Data(){
        loginPage.clickTheLogInButton();

        verifyEquals(loginPage.getEmailValidation(), "Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email(){
        loginPage.loginUserAccount("phulo@gmail", "");

        verifyEquals(loginPage.getEmailValidation(), "Wrong email");
    }

    @Test
    public void Login_03_Login_With_Unregistered_Email(){
        loginPage.loginUserAccount("phulo_neverregister@gmail.com", nopCommerceData.getPassword());

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_Login_With_Registered_Email_And_Empty_Password(){
        loginPage.loginUserAccount(email, "");

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_05_Empty_Data(){
        loginPage.loginUserAccount(email, "notrightpassword");

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_06_Login_With_Registered_Email_And_Incorrect_Password(){
        homePage = loginPage.loginUserAccount(email, nopCommerceData.getPassword());

        verifyTrue(homePage.isWelcomeTitleDisplayed());
        verifyTrue(homePage.isMyAccountDisplayed(driver));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private FakerConfig fakerConfig;
    private NopCommerceData nopCommerceData;
    private String email;
}
