package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.*;
import utilities.FakerConfig;

public class MyAccountTest extends BaseTest {

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
        cityName = fakerConfig.getCityName();
        address = fakerConfig.getStreetAddress();
        zipcode = fakerConfig.getZipcode();
        phoneNumber = fakerConfig.getPhoneNumber();
        country = "Vietnam";
        state = "Hồ Chí Minh";

        editFirstName = fakerConfig.getFirstName();
        editLastName = fakerConfig.getLastName();
        editEmail = "new_"+email;
        editCompany = fakerConfig.getCompany();
        editPassword = "new_"+password;

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(firstName, lastName, email, company, password);
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(email, password);
        System.out.println("Account:" + email + " / " + password);
        customerInfoPage = homePage.openCustomerInfoPage(driver);
    }

    @Test
    public void MyAccount_01_Customer_Info(){
        customerInfoPage.checkOnMaleRadio();
        customerInfoPage.enterToFirstNameTextbox(editFirstName);
        customerInfoPage.enterToLastNameTextbox(editLastName);
        customerInfoPage.enterToEmailTextbox(editEmail);
        customerInfoPage.enterToCompanyTextbox(editCompany);
        customerInfoPage.clickTheSaveButton();

        Assert.assertTrue(customerInfoPage.isMaleRadioSelected());
        Assert.assertEquals(customerInfoPage.getValueFirstNameTextbox(), editFirstName);
        Assert.assertEquals(customerInfoPage.getValueLastNameTextbox(), editLastName);
        Assert.assertEquals(customerInfoPage.getValueEmailTextbox(), editEmail);
        Assert.assertEquals(customerInfoPage.getValueCompanyTextbox(), editCompany);
    }

    @Test
    public void MyAccount_02_Address(){
        customerInfoPage.openSidebarLinkByPageNames("Addresses");
        addressPage = PageGenerator.getPageGenerator().getUserAddressPage(driver);

        addressPage.addNewAddress(firstName, lastName, email, company, country, state, cityName, address, zipcode, phoneNumber);

        Assert.assertTrue(addressPage.isSuccessBarNotificationDisplayed(driver));
        addressPage.clickTheEditButton();

        Assert.assertEquals(addressPage.getValueFirstNameTextbox(), firstName);
        Assert.assertEquals(addressPage.getValueLastNameTextbox(), lastName);
        Assert.assertEquals(addressPage.getValueEmailTextbox(), email);
        Assert.assertEquals(addressPage.getValueCompanyTextbox(), company);
        Assert.assertEquals(addressPage.getSelectedItemCountryDropdown(), country);
        Assert.assertEquals(addressPage.getSelectedItemStateDropdown(), state);
        Assert.assertEquals(addressPage.getValueCityTextbox(), cityName);
        Assert.assertEquals(addressPage.getValueAddress1Textbox(), address);
        Assert.assertEquals(addressPage.getValueAddress2Textbox(), address);
        Assert.assertEquals(addressPage.getValueZipTextbox(), zipcode);
        Assert.assertEquals(addressPage.getValuePhoneNumberTextbox(), phoneNumber);
        Assert.assertEquals(addressPage.getValueFaxNumberTextbox(), phoneNumber);
    }

    @Test
    public void MyAccount_03_Change_Password() {
        addressPage.openSidebarLinkByPageNames("Change password");
        changePasswordPage = PageGenerator.getPageGenerator().getUserChangePassword(driver);

        changePasswordPage.changePassword(password, editPassword);
        Assert.assertTrue(changePasswordPage.isSuccessBarNotificationDisplayed(driver));

        changePasswordPage.closeTheBarNotification(driver);
        homePage = changePasswordPage.clickToLogoutLink();

        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(editEmail, editPassword);
        Assert.assertTrue(homePage.isMyAccountDisplayed(driver));

    }

    @Test
    public void MyAccount_04_My_Product_Reviews() {
        homePage.openHeaderMenuBarPage(driver,"Computers", "Desktops");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productPage = productListPage.selectProductByTitle("Build your own computer");

        productPage.clickAddYourReviewLink();
        productPage.enterToReviewTitleTextbox("Good");
        productPage.enterToReviewTextTextbox("Good\nUseful");
        productPage.checkOnRatingRadio("5");
        productPage.clickSubmitReviewButton();
        productPage.closeTheBarNotification(driver);

        customerInfoPage = productPage.openCustomerInfoPage(driver);

        customerInfoPage.openSidebarLinkByPageNames("My product reviews");
        myProductReviewsPage = PageGenerator.getPageGenerator().getUserMyProductReviews(driver);
        Assert.assertTrue(myProductReviewsPage.isTheReviewDisplayed("Good"));
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    WebDriver driver;
    UserHomePO homePage;
    UserRegisterPO registerPage;
    UserLoginPO loginPage;
    UserCustomerInfoPO customerInfoPage;
    UserAddressPO addressPage;
    UserChangePasswordPO changePasswordPage;
    UserProductListPO productListPage;
    UserProductPO productPage;
    UserMyProductReviewsPO myProductReviewsPage;
    FakerConfig fakerConfig, faker;
    String firstName, lastName, email, company, password, cityName, address, zipcode, phoneNumber, country, state,
            editFirstName, editLastName, editEmail, editCompany, editPassword;
}
