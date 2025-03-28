package com.nopcommerce.users;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.product.UserProductListPO;
import utilities.NopCommerceData;

public class SortDisplayPagingTest extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);
        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        homePage.openHeaderMenuBarPage(driver,"Computers", "Notebooks");
        productListPO = PageGenerator.getPageGenerator().getUserProductList(driver);
    }

    @Test
    public void Sort_01_Sort_Name_A_To_Z(){
        productListPO.selectSortByDropdown("Name: A to Z");
        verifyTrue(productListPO.isNameSortAscending());
    }

    @Test
    public void Sort_02_Sort_Name_Z_To_A(){
        productListPO.selectSortByDropdown("Name: Z to A");
        verifyTrue(productListPO.isNameSortDescending());
    }

    @Test
    public void Sort_03_Sort_Price_Low_To_High(){
        productListPO.selectSortByDropdown("Price: Low to High");
        verifyTrue(productListPO.isPriceSortAscending());
    }

    @Test
    public void Sort_04_Sort_Price_High_To_Low(){
        productListPO.selectSortByDropdown("Price: High to Low");
        verifyTrue(productListPO.isPriceSortDescending());
    }

    @Test
    public void Sort_05_Display_With_3_Product_Per_Page(){
        productListPO.selectDisplayPerPageDropdown("3");

        verifyTrue(productListPO.getTotalProductDisplayed() <= 3);
        verifyEquals(productListPO.getCurrentPage(), "1");
        verifyTrue(productListPO.isNextPageIconDisplayed());

        productListPO.clickOnTheNextPage();
        verifyEquals(productListPO.getCurrentPage(), "2");
        verifyTrue(productListPO.isPreviousPageIconDisplayed());
    }

    @Test
    public void Sort_06_Display_With_6_Product_Per_Page(){
        productListPO.selectDisplayPerPageDropdown("6");
        verifyTrue(productListPO.getTotalProductDisplayed() <= 6);
        verifyTrue(productListPO.isPagingUnDisplayed());
    }

    @Test
    public void Sort_07_Display_With_9_Product_Per_Page(){
        productListPO.selectDisplayPerPageDropdown("9");
        verifyTrue(productListPO.getTotalProductDisplayed() <= 9);
        verifyTrue(productListPO.isPagingUnDisplayed());
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserProductListPO productListPO;
}
