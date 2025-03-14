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
import pageUIs.users.UserRecentlyViewedProductsPUI;
import utilities.NopCommerceData;

public class TM_06_WishlistCompareRecentView extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        Assert.assertTrue(homePage.isMyAccountDisplayed(driver));
        homePage.openHeaderMenuBarPage(driver,"Computers", "Desktops");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle("Lenovo IdeaCentre");
    }


    @Test
    public void TC_01_Add_To_Wishlist(){
        productPage.clickOnAddToWishlistButton();

        verifyTrue(productPage.isTextbarNotificationDisplay(driver, "The product has been added to your wishlist"));
        productPage.closeTheBarNotification(driver);

        wishlistPage = productPage.openWishlistPage();

        verifyTrue(wishlistPage.isProductDisplay(driver,"Lenovo IdeaCentre"));

        wishlistSharePage = wishlistPage.clickOnWishlistSharingLink();

        verifyEquals(wishlistSharePage.getShareWishlistPageTitle(), "Wishlist of " + nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName());
    }

    @Test
    public void TC_02_Add_Product_To_Cart_From_Wishlist_Page(){
        productPage = wishlistSharePage.clickToProductName("Lenovo IdeaCentre");
        wishlistPage = productPage.openWishlistPage();

        shoppingCartPage = wishlistPage.addProductToCart("Lenovo IdeaCentre");

        verifyEquals(shoppingCartPage.getWishlistQuantity(), "0");
        verifyEquals(shoppingCartPage.getShoppingCartQuantity(), "1");
        verifyTrue(shoppingCartPage.isProductDisplay(driver,"Lenovo IdeaCentre"));
    }

    @Test
    public void TC_03_Remove_Product_In_Wishlist_Page(){
        shoppingCartPage.openHeaderMenuBarPage(driver,"Computers", "Desktops");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle("Lenovo IdeaCentre");

        productPage.clickOnAddToWishlistButton();
        productPage.closeTheBarNotification(driver);

        wishlistPage = productPage.openWishlistPage();
        wishlistPage.removeProductByName("Lenovo IdeaCentre");

        verifyEquals(wishlistPage.getNoDataText(), "The wishlist is empty!");
        verifyTrue(wishlistPage.isProductUndisplay(driver,"Lenovo IdeaCentre"));
    }

    @Test
    public void TC_04_Add_Product_To_Compare(){
        wishlistPage.openHeaderMenuBarPage(driver,"Computers", "Desktops");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productListPage.clickCompareProductButtonByProductName("Lenovo IdeaCentre");
        productListPage.clickCompareProductButtonByProductName("Build your own computer");

        verifyTrue(productListPage.isTextbarNotificationDisplay(driver, "The product has been added to your product comparison"));

        compareProductsPage = productListPage.clickOnCompareLink();

        verifyTrue(compareProductsPage.isDataColumnDisplayed("Lenovo IdeaCentre", "$500.00"));
        verifyTrue(compareProductsPage.isDataColumnDisplayed("Build your own computer", "$1,200.00"));
        verifyTrue(compareProductsPage.isRemoveButtonDisplayed("Lenovo IdeaCentre"));
        verifyTrue(compareProductsPage.isRemoveButtonDisplayed("Build your own computer"));
    }

    @Test
    public void TC_05_Recently_Viewed_Products(){
        homePage.openHeaderMenuBarPage(driver,"Computers", "Notebooks");
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productListPage.openProductByTitle("Apple MacBook Pro");
        productPage.clickOnBreadcrumb("Notebooks");

        productListPage.openProductByTitle("Asus Laptop");
        productPage.clickOnBreadcrumb("Notebooks");

        productListPage.openProductByTitle("HP Envy 15.6-Inch Sleekbook");
        productPage.clickOnBreadcrumb("Notebooks");

        productListPage.openProductByTitle("HP Spectre XT Pro UltraBook");
        productPage.clickOnBreadcrumb("Notebooks");

        productListPage.openProductByTitle("Lenovo Thinkpad Carbon Laptop");

        recentlyViewedProductsPage = productPage.openRecentlyViewedProductsPage();

        verifyTrue(recentlyViewedProductsPage.isProductNameDisplayed("HP Envy 15.6-Inch Sleekbook"));
        verifyTrue(recentlyViewedProductsPage.isProductNameDisplayed("HP Spectre XT Pro UltraBook"));
        verifyTrue(recentlyViewedProductsPage.isProductNameDisplayed("Lenovo Thinkpad Carbon Laptop"));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePO homePage;
    private UserProductListPO productListPage;
    private UserProductPO productPage;
    private UserWishlistPO wishlistPage;
    private UserWishlistSharePO wishlistSharePage;
    private UserShoppingCartPO shoppingCartPage;
    private UserCompareProductsPO compareProductsPage;
    private UserRecentlyViewedProductsPO recentlyViewedProductsPage;
    private NopCommerceData nopCommerceData;
}
