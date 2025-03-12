package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.*;
import utilities.FakerConfig;

public class TM_04_SearchTest extends BaseTest {

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

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        registerPage = homePage.openRegisterPage();
        registerPage.createAnAccount(firstName, lastName, email, company, password);
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginUserAccount(email, password);
        searchPage = homePage.openSearchPage();

    }

    @Test
    public void Search_01_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getWarningValidation(), "Search term minimum length is 3 characters");
    }

    @Test
    public void Search_02_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Macbook Pro 2050");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getNoResultValidation(), "No products were found that matched your criteria.");
    }

    @Test
    public void Search_03_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Lenovo");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 2);
        verifyTrue(searchPage.isProductDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));
        verifyTrue(searchPage.isProductDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));

    }

    @Test
    public void Search_04_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("ThinkPad X1 Carbon");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void Search_05_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Apple Macbook Pro");
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown("Computers");
        searchPage.uncheckCategoryCheckbox();

        verifyEquals(searchPage.getNoResultValidation(), "No products were found that matched your criteria.");
    }

    @Test
    public void Search_06_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Apple Macbook Pro");
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown("Computers");
        searchPage.checkOnCategoryCheckbox();
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed("Apple Macbook Pro 13-inch"));
    }

    @Test
    public void Search_07_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Apple Macbook Pro");
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown("Computers");
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManuFacturerDropdown("HP");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getNoResultValidation(), "No products were found that matched your criteria.");
    }

    @Test
    public void Search_08_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("Apple Macbook Pro");
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown("Computers");
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManuFacturerDropdown("Apple");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed("Apple MacBook Pro 13-inch"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    WebDriver driver;
    UserHomePO homePage;
    UserRegisterPO registerPage;
    UserLoginPO loginPage;
    UserSearchPO searchPage;
    FakerConfig fakerConfig, faker;
    String firstName, lastName, email, company, password;

}
