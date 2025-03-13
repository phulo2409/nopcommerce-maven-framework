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
import pageObjects.users.UserHomePO;
import pageObjects.users.UserProductListPO;
import utilities.NopCommerceData;

public class TM_05_SortDisplayPagingTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        nopCommerceData = NopCommerceData.getNopCommerceData();
        homePage.openHeaderMenuBarPage(driver,"Computers", "Notebooks");
        productListPO = PageGenerator.getPageGenerator().getUserProductList(driver);
    }

    @Test
    public void TC_01_Sort_Name_A_To_Z(){
        productListPO.selectSortByDropdown("Name: A to Z");
        verifyTrue(productListPO.isNameSortAscending());
    }

    @Test
    public void TC_02_Sort_Name_Z_To_A(){
        productListPO.selectSortByDropdown("Name: Z to A");
        verifyTrue(productListPO.isNameSortDescending());
    }

    @Test
    public void TC_03_Sort_Price_Low_To_High(){
        productListPO.selectSortByDropdown("Price: Low to High");
        verifyTrue(productListPO.isPriceSortAscending());
    }

    @Test
    public void TC_04_Sort_Price_High_To_Low(){
        productListPO.selectSortByDropdown("Price: High to Low");
        verifyTrue(productListPO.isPriceSortDescending());
    }

    @Test
    public void TC_05_Display_With_3_Product_Per_Page(){
        productListPO.selectDisplayPerPageDropdown("3");

        verifyTrue(productListPO.getTotalProductDisplayed() <= 3);
        verifyEquals(productListPO.getCurrentPage(), "1");
        verifyTrue(productListPO.isNextPageIconDisplayed());

        productListPO.clickOnTheNextPage();
        verifyEquals(productListPO.getCurrentPage(), "2");
        verifyTrue(productListPO.isPreviousPageIconDisplayed());
    }

    @Test
    public void TC_06_Display_With_6_Product_Per_Page(){
        productListPO.selectDisplayPerPageDropdown("6");
        verifyTrue(productListPO.getTotalProductDisplayed() <= 6);
        verifyTrue(productListPO.isPagingUnDisplayed());
    }

    @Test
    public void TC_07_Display_With_9_Product_Per_Page(){
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
    private NopCommerceData nopCommerceData;
}
