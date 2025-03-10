package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserLoginPO;
import pageObjects.users.UserRegisterPO;
import utilities.FakerConfig;

public class LoginTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        fakerConfig = FakerConfig.getFaker();

        fakerConfig = FakerConfig.getFaker();
        firstName = fakerConfig.getFirstName();
        lastName = fakerConfig.getLastName();
        email = fakerConfig.getEmailAddress();
        company = fakerConfig.getCompany();
        password = fakerConfig.getPassword();

        homePage = PageGenerator.getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(firstName, lastName, email, company, password);
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();

    }

    @Test
    public void Login_01_Empty_Data(){
        loginPage.clickTheLogInButton();

        Assert.assertEquals(loginPage.getEmailValidation(), "Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email(){
        loginPage.enterToEmailTextbox("phulo@gmail");
        loginPage.clickTheLogInButton();

        Assert.assertEquals(loginPage.getEmailValidation(), "Wrong email");
    }

    @Test
    public void Login_03_Login_With_Unregistered_Email(){
        loginPage.enterToEmailTextbox("phulo_neverregister@gmail.com");
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickTheLogInButton();

        Assert.assertEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_Login_With_Registered_Email_And_Empty_Password(){

        loginPage.enterToEmailTextbox(email);
        loginPage.clickTheLogInButton();

        Assert.assertEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_05_Empty_Data(){

        loginPage.enterToEmailTextbox(email);
        loginPage.clickTheLogInButton();

        Assert.assertEquals(loginPage.getLoginValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_06_Login_With_Registered_Email_And_Incorrect_Password(){

        loginPage.enterToEmailTextbox(email);
        loginPage.enterToPasswordTextbox(password);
        homePage = loginPage.clickTheLogInButton();

        Assert.assertTrue(homePage.isWelcomeTitleDisplayed());
        Assert.assertTrue(homePage.isMyAccountDisplayed());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    WebDriver driver;
    UserHomePO homePage;
    UserRegisterPO registerPage;
    UserLoginPO loginPage;
    FakerConfig fakerConfig;
    String firstName, lastName, email, company, password;
}
