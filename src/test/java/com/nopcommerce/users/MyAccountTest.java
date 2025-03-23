package com.nopcommerce.users;

import com.nopcommerce.common.Login;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.login.UserLoginPO;
import pageObjects.users.login.UserRegisterPO;
import pageObjects.users.myAccount.UserAddressPO;
import pageObjects.users.myAccount.UserChangePasswordPO;
import pageObjects.users.myAccount.UserCustomerInfoPO;
import pageObjects.users.myAccount.UserMyProductReviewsPO;
import pageObjects.users.product.UserProductListPO;
import pageObjects.users.product.UserProductPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class MyAccountTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();
        cityName = fakerConfig.getCityName();
        address = fakerConfig.getStreetAddress();
        zipcode = fakerConfig.getZipcode();
        phoneNumber = fakerConfig.getPhoneNumber();
        country = "Vietnam";
        state = "Hồ Chí Minh";
        editEmail = "new_"+email;

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        homePage.waitForMyAccountDisplay();
        customerInfoPage = homePage.openCustomerInfoPage(driver);
    }

    @Test
    public void MyAccount_01_Customer_Info(){
        customerInfoPage.updateAccoutInfomation(nopCommerceData.getGender(), "New " + nopCommerceData.getFirstName(), "New " + nopCommerceData.getLastName(), editEmail, "New " + nopCommerceData.getCompany());

        verifyTrue(customerInfoPage.isGenderRadioSelected(nopCommerceData.getGender()));
        verifyEquals(customerInfoPage.getValueFirstNameTextbox(), "New " + nopCommerceData.getFirstName());
        verifyEquals(customerInfoPage.getValueLastNameTextbox(), "New " + nopCommerceData.getLastName());
        verifyEquals(customerInfoPage.getValueEmailTextbox(), editEmail);
        verifyEquals(customerInfoPage.getValueCompanyTextbox(), "New " + nopCommerceData.getCompany());
    }

    @Test
    public void MyAccount_02_Address(){
        customerInfoPage.openSidebarLinkByPageNames("Addresses");
        addressPage = PageGenerator.getPageGenerator().getUserAddressPage(driver);

        addressPage.addNewAddress(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, nopCommerceData.getCompany(), country, state, cityName, address, zipcode, phoneNumber);

        verifyTrue(addressPage.isSuccessBarNotificationDisplayed(driver, "The new address has been added successfully."));
        addressPage.clickTheEditButton();

        verifyEquals(addressPage.getValueFirstNameTextbox(), nopCommerceData.getFirstName());
        verifyEquals(addressPage.getValueLastNameTextbox(), nopCommerceData.getLastName());
        verifyEquals(addressPage.getValueEmailTextbox(), email);
        verifyEquals(addressPage.getValueCompanyTextbox(), nopCommerceData.getCompany());
        verifyEquals(addressPage.getSelectedItemCountryDropdown(), country);
        verifyEquals(addressPage.getSelectedItemStateDropdown(), state);
        verifyEquals(addressPage.getValueCityTextbox(), cityName);
        verifyEquals(addressPage.getValueAddress1Textbox(), address);
        verifyEquals(addressPage.getValueAddress2Textbox(), address);
        verifyEquals(addressPage.getValueZipTextbox(), zipcode);
        verifyEquals(addressPage.getValuePhoneNumberTextbox(), phoneNumber);
        verifyEquals(addressPage.getValueFaxNumberTextbox(), phoneNumber);
    }

    @Test
    public void MyAccount_03_Change_Password() {
        addressPage.openSidebarLinkByPageNames("Change password");
        changePasswordPage = PageGenerator.getPageGenerator().getUserChangePassword(driver);

        changePasswordPage.changePassword(nopCommerceData.getPassword(), "new_" + nopCommerceData.getPassword());
        verifyTrue(changePasswordPage.isSuccessBarNotificationDisplayed(driver, "Password was changed"));

        changePasswordPage.closeTheBarNotification(driver);
        homePage = changePasswordPage.clickToLogoutLink();

        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(editEmail, "new_" + nopCommerceData.getPassword());
        verifyTrue(homePage.isMyAccountDisplayed(driver));

    }

    @Test
    public void MyAccount_04_My_Product_Reviews() {
        homePage.openHeaderMenuBarPage(driver,"Computers", "Desktops");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productPage = productListPage.openProductByTitle("Build your own computer");

        productPage.clickAddYourReviewLink();
        productPage.enterToReviewTitleTextbox("Good");
        productPage.enterToReviewTextTextbox("Good\nUseful");
        productPage.checkOnRatingRadio("5");
        productPage.clickSubmitReviewButton();
        productPage.closeTheBarNotification(driver);

        customerInfoPage = productPage.openCustomerInfoPage(driver);

        customerInfoPage.openSidebarLinkByPageNames("My product reviews");
        myProductReviewsPage = PageGenerator.getPageGenerator().getUserMyProductReviews(driver);
        verifyTrue(myProductReviewsPage.isTheReviewDisplayed("Good"));
    }
    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private UserAddressPO addressPage;
    private UserChangePasswordPO changePasswordPage;
    private UserProductListPO productListPage;
    private UserProductPO productPage;
    private UserMyProductReviewsPO myProductReviewsPage;
    private FakerConfig fakerConfig;
    private NopCommerceData nopCommerceData;
    private String email, cityName, address, zipcode, phoneNumber, country, state,editEmail;
}
