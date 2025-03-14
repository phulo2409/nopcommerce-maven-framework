package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserRegisterPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class TM_01_RegisterTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url){
        driver = getBrowserDriver(browser, url);

        nopCommerceData = NopCommerceData.getNopCommerceData();
        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
    }

    @Test
    public void Register_01_Empty_Data(){
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getFirstNameValidationMessage(), "First name is required.");
        verifyEquals(registerPage.getLastNameValidationMessage(), "Last name is required.");
        verifyEquals(registerPage.getEmailValidationMessage(), "Email is required.");
        verifyEquals(registerPage.getConfirmPasswordValidationMessage(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email(){
        registerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        registerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        registerPage.enterToEmailTextbox("phulo@gmail");
        registerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        registerPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        registerPage.enterToConfirmPasswordTextbox(nopCommerceData.getPassword());
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getEmailValidationMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Valid_Data(){
        registerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        registerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        registerPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        registerPage.enterToConfirmPasswordTextbox(nopCommerceData.getPassword());
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void Register_04_Exist_Email(){
        homePage = registerPage.clickToLogoutLink();
        registerPage = homePage.openRegisterPage();

        registerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        registerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        registerPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        registerPage.enterToConfirmPasswordTextbox(nopCommerceData.getPassword());
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getRegisterErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Characters(){
        registerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        registerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        registerPage.enterToPasswordTextbox("12345");
        registerPage.enterToConfirmPasswordTextbox("12345");
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getPasswordValidationMessage(), "Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");
    }

    @Test
    public void Register_06_Password_Does_Not_Match_Confirm_Password(){
        registerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        registerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        registerPage.enterToEmailTextbox(email);
        registerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        registerPage.enterToPasswordTextbox("123456789");
        registerPage.enterToConfirmPasswordTextbox("987654321");
        registerPage.clickTheRegisterButton();

        verifyEquals(registerPage.getConfirmPasswordValidationMessage(), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private FakerConfig fakerConfig;
    private NopCommerceData nopCommerceData;
    private String email;
}
