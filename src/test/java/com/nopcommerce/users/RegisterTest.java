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
import pageObjects.users.UserRegisterPO;
import utilities.FakerConfig;

public class RegisterTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url){
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();

        fakerConfig = FakerConfig.getFaker();
        firstName = fakerConfig.getFirstName();
        lastName = fakerConfig.getLastName();
        email = fakerConfig.getEmailAddress();
        company = fakerConfig.getCompany();
        password = fakerConfig.getPassword();
    }

    @Test
    public void Register_01_Empty_Data(){
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameValidationMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameValidationMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailValidationMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordValidationMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email(){
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox("phulo@gmail");
        registerPage.enterToCompanyTextbox(company);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getEmailValidationMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Valid_Data(){
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(company);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void Register_04_Exist_Email(){
        homePage = registerPage.clickToLogoutLink();
        registerPage = homePage.openRegisterPage();

        registerPage.enterToFirstNameTextbox("Hoang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox("Hoang Nguyen Corp");
        registerPage.enterToPasswordTextbox("123456789");
        registerPage.enterToConfirmPasswordTextbox("123456789");
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getRegisterErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Characters(){
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(company);
        registerPage.enterToPasswordTextbox("12345");
        registerPage.enterToConfirmPasswordTextbox("12345");
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getPasswordValidationMessage(), "Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");
    }

    @Test
    public void Register_06_Password_Does_Not_Match_Confirm_Password(){
        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(company);
        registerPage.enterToPasswordTextbox("123456789");
        registerPage.enterToConfirmPasswordTextbox("987654321");
        registerPage.clickTheRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordValidationMessage(), "The password and confirmation password do not match.");
    }




    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    WebDriver driver;
    UserHomePO homePage;
    UserRegisterPO registerPage;
    FakerConfig fakerConfig;
    String firstName, lastName, email, company, password;
}
