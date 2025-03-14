package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserRegisterPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class TM_02_LoginTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
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
        loginPage.enterToEmailTextbox("phulo@gmail");
        loginPage.clickTheLogInButton();

        verifyEquals(loginPage.getEmailValidation(), "Wrong email");
    }

    @Test
    public void Login_03_Login_With_Unregistered_Email(){
        loginPage.enterToEmailTextbox("phulo_neverregister@gmail.com");
        loginPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        loginPage.clickTheLogInButton();

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_Login_With_Registered_Email_And_Empty_Password(){

        loginPage.enterToEmailTextbox(email);
        loginPage.clickTheLogInButton();

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_05_Empty_Data(){
        loginPage.enterToEmailTextbox(email);
        loginPage.clickTheLogInButton();

        verifyEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_06_Login_With_Registered_Email_And_Incorrect_Password(){
        loginPage.enterToEmailTextbox(email);
        loginPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        homePage = loginPage.clickTheLogInButton();

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
