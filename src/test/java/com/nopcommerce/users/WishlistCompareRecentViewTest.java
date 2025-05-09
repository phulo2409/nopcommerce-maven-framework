package com.nopcommerce.users;

import com.nopcommerce.common.Login;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.users.dashboard.UserHomePO;
import pageObjects.users.product.UserCompareProductsPO;
import pageObjects.users.product.UserProductListPO;
import pageObjects.users.product.UserProductPO;
import pageObjects.users.product.UserRecentlyViewedProductsPO;
import pageObjects.users.shoppingCart.UserShoppingCartPO;
import pageObjects.users.wishlist.UserWishlistPO;
import pageObjects.users.wishlist.UserWishlistSharePO;
import utilities.NopCommerceData;

public class WishlistCompareRecentViewTest extends BaseTest {

    @Parameters({"env", "browser", "url", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String env, String browserName, String url, @Optional("windows") String osName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        driver = getBrowserDriver(env, browserName, url, osName, ipAddress, portNumber);
        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        homePage.waitForMyAccountDisplay();
        homePage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getDesktopsBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle(nopCommerceData.getProductItem().getLenovoItem());
    }


    @Test
    public void Wishlist_ShoppingCart_01_Add_To_Wishlist(){
        productPage.clickOnAddToWishlistButton();

        verifyTrue(productPage.isSuccessBarNotificationDisplayed(driver, "The product has been added to your wishlist"));
        productPage.closeTheBarNotification(driver);

        wishlistPage = productPage.openWishlistPage();

        verifyTrue(wishlistPage.isProductNameDisplay(driver,nopCommerceData.getProductItem().getLenovoItem()));

        wishlistSharePage = wishlistPage.clickOnWishlistSharingLink();

        verifyEquals(wishlistSharePage.getShareWishlistPageTitle(), "Wishlist of " + nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName());
    }

    @Test
    public void Wishlist_ShoppingCart_02_Add_Product_To_Cart_From_Wishlist_Page(){
        productPage = wishlistSharePage.clickOnProductName(nopCommerceData.getProductItem().getLenovoItem());
        wishlistPage = productPage.openWishlistPage();

        shoppingCartPage = wishlistPage.addProductToCart(nopCommerceData.getProductItem().getLenovoItem());

        verifyEquals(shoppingCartPage.getWishlistQuantity(), "0");
        verifyEquals(shoppingCartPage.getShoppingCartQuantity(), "1");
        verifyTrue(shoppingCartPage.isProductNameDisplay(driver,nopCommerceData.getProductItem().getLenovoItem()));
    }

    @Test
    public void Wishlist_ShoppingCart_03_Remove_Product_In_Wishlist_Page(){
        shoppingCartPage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getDesktopsBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle(nopCommerceData.getProductItem().getLenovoItem());

        productPage.clickOnAddToWishlistButton();
        productPage.closeTheBarNotification(driver);

        wishlistPage = productPage.openWishlistPage();
        wishlistPage.removeProductByName(nopCommerceData.getProductItem().getLenovoItem());

        verifyEquals(wishlistPage.getNoDataText(), "The wishlist is empty!");
        verifyTrue(wishlistPage.isProductUndisplay(driver,nopCommerceData.getProductItem().getLenovoItem()));
    }

    @Test
    public void Wishlist_ShoppingCart_04_Add_Product_To_Compare(){
        wishlistPage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getDesktopsBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productListPage.clickCompareProductButtonByProductName(nopCommerceData.getProductItem().getLenovoItem());
        productListPage.clickCompareProductButtonByProductName(nopCommerceData.getProductItem().getBuildProduct());

        verifyTrue(productListPage.isSuccessBarNotificationDisplayed(driver, "The product has been added to your product comparison"));

        compareProductsPage = productListPage.clickOnCompareLink();

        verifyTrue(compareProductsPage.isDataColumnDisplayed(nopCommerceData.getProductItem().getLenovoItem(), "$500.00"));
        verifyTrue(compareProductsPage.isDataColumnDisplayed(nopCommerceData.getProductItem().getBuildProduct(), "$1,200.00"));
        verifyTrue(compareProductsPage.isRemoveButtonDisplayed(nopCommerceData.getProductItem().getLenovoItem()));
        verifyTrue(compareProductsPage.isRemoveButtonDisplayed(nopCommerceData.getProductItem().getBuildProduct()));
    }

    @Test
    public void Wishlist_ShoppingCart_05_Recently_Viewed_Products(){
        homePage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getNotebookBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

        productListPage.openProductByTitle("Apple MacBook Pro");
        productPage.clickOnBreadcrumb(nopCommerceData.getProductItem().getNotebookBreadcrumb());

        productListPage.openProductByTitle("Asus Laptop");
        productPage.clickOnBreadcrumb(nopCommerceData.getProductItem().getNotebookBreadcrumb());

        productListPage.openProductByTitle("HP Envy 15.6-Inch Sleekbook");
        productPage.clickOnBreadcrumb(nopCommerceData.getProductItem().getNotebookBreadcrumb());

        productListPage.openProductByTitle("HP Spectre XT Pro UltraBook");
        productPage.clickOnBreadcrumb(nopCommerceData.getProductItem().getNotebookBreadcrumb());

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
