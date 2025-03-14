package com.nopcommerce.users;

import com.nopcommerce.common.Login;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.users.*;
import utilities.NopCommerceData;

public class TM_04_SearchTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        Assert.assertTrue(homePage.isMyAccountDisplayed(driver));
        searchPage = homePage.openSearchPage();
    }

    @Test
    public void Search_01_Empty_Data(){
        searchPage.enterToSearchKeywordTextbox("");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getWarningValidation(), "Search term minimum length is 3 characters");
    }

    @Test
    public void Search_02_Data_Not_Exist(){
        searchPage.enterToSearchKeywordTextbox("Macbook Pro 2050");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getNoResultValidation(), nopCommerceData.getSearchTest().getNoResultValidation());
    }

    @Test
    public void Search_03_Product_With_Related_Name(){
        searchPage.enterToSearchKeywordTextbox("Lenovo");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 2);
        verifyTrue(searchPage.isProductDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));
        verifyTrue(searchPage.isProductDisplayed(nopCommerceData.getSearchTest().getLenovoThinkpadResult()));

    }

    @Test
    public void Search_04_Product_With_Absolute_Name(){
        searchPage.enterToSearchKeywordTextbox("ThinkPad X1 Carbon");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed(nopCommerceData.getSearchTest().getLenovoThinkpadResult()));
    }

    @Test
    public void Search_05_Advanced_Search_With_Parent_Categories(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.uncheckCategoryCheckbox();

        verifyEquals(searchPage.getNoResultValidation(), nopCommerceData.getSearchTest().getNoResultValidation());
    }

    @Test
    public void Search_06_Advanced_Search_With_Sub_Categories(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.checkOnCategoryCheckbox();
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed(nopCommerceData.getSearchTest().getAppleResult()));
    }

    @Test
    public void Search_07_Advanced_Search_With_Incorrect_Manufacturer(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManuFacturerDropdown("HP");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getNoResultValidation(), nopCommerceData.getSearchTest().getNoResultValidation());
    }

    @Test
    public void Search_08_Advanced_Search_With_Correct_Manufacturer(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManuFacturerDropdown("Apple");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductDisplayed(nopCommerceData.getSearchTest().getAppleResult()));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserSearchPO searchPage;
    private NopCommerceData nopCommerceData;
}
