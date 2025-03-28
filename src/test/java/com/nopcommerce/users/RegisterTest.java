package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.login.UserRegisterPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class RegisterTest extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);

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
        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), "phulo@gmail", nopCommerceData.getCompany(), nopCommerceData.getPassword());

        verifyEquals(registerPage.getEmailValidationMessage(), "Wrong email");
    }

    @Test
    public void Register_03_Valid_Data(){
        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), nopCommerceData.getPassword());

        verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void Register_04_Exist_Email(){
        homePage = registerPage.clickToLogoutLink();
        registerPage = homePage.openRegisterPage();

        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), nopCommerceData.getPassword());

        verifyEquals(registerPage.getRegisterErrorMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Characters(){
        registerPage.createAnAccount(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), "12345");

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
