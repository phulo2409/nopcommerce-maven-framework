package com.nopcommerce.users;

import com.nopcommerce.common.Login;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.dashboard.UserSearchPO;
import utilities.NopCommerceData;

public class SearchTest extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        homePage.waitForMyAccountDisplay();
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
        verifyTrue(searchPage.isProductItemTitleDisplayed("Lenovo IdeaCentre 600 All-in-One PC"));
        verifyTrue(searchPage.isProductItemTitleDisplayed(nopCommerceData.getSearchTest().getLenovoThinkpadResult()));

    }

    @Test
    public void Search_04_Product_With_Absolute_Name(){
        searchPage.enterToSearchKeywordTextbox("ThinkPad X1 Carbon");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductItemTitleDisplayed(nopCommerceData.getSearchTest().getLenovoThinkpadResult()));
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
        verifyTrue(searchPage.isProductItemTitleDisplayed(nopCommerceData.getSearchTest().getAppleResult()));
    }

    @Test
    public void Search_07_Advanced_Search_With_Incorrect_Manufacturer(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManufacturerDropdown("HP");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getNoResultValidation(), nopCommerceData.getSearchTest().getNoResultValidation());
    }

    @Test
    public void Search_08_Advanced_Search_With_Correct_Manufacturer(){
        searchPage.enterToSearchKeywordTextbox(nopCommerceData.getSearchTest().getAppleSearch());
        searchPage.checkOnAdvancedSearchCheckbox();
        searchPage.selectItemInCategoryDropDown(nopCommerceData.getSearchTest().getCategorySelect());
        searchPage.checkOnCategoryCheckbox();
        searchPage.selectItemInManufacturerDropdown("Apple");
        searchPage.clickTheSearchButton();

        verifyEquals(searchPage.getTotalProductDisplayed(), 1);
        verifyTrue(searchPage.isProductItemTitleDisplayed(nopCommerceData.getSearchTest().getAppleResult()));
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
