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
import pageObjects.users.product.UserProductListPO;
import pageObjects.users.product.UserProductPO;
import pageObjects.users.shoppingCart.UserCheckOutPO;
import pageObjects.users.shoppingCart.UserShoppingCartPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class OrderTest extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGenerator.getPageGenerator().getUserHomePage(driver);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();
        address = fakerConfig.getStreetAddress();
        phoneNumber = fakerConfig.getPhoneNumber();

        homePage.setCookies(driver, Login.nopCommerceCookies);
        homePage.refreshCurrentPage(driver);
        homePage.waitForMyAccountDisplay();
        homePage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getDesktopsBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);

    }

    @Test
    public void Order_01_Add_Product_To_Cart(){
        productPage = productListPage.openProductByTitle(nopCommerceData.getProductItem().getBuildProduct());

        productPage.selectBuildYourOwnComputerToCart("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]", "8GB [+$60.00]", "400 GB [+$100.00]", "Vista Premium [+$60.00]", "yes", "yes", "yes", "1");
        verifyTrue(productPage.isSuccessBarNotificationDisplayed(driver, "The product has been added to your shopping cart"));
        productPage.closeTheBarNotification(driver);

        productPage.moveMouseOnShoppingCartMenuItem(driver);

        verifyEquals(productPage.getCountProductOnFlyoutCart(driver), "There are 1 item(s) in your cart.");

        verifyEquals(productPage.getProductNameInCartMenuItem(driver), nopCommerceData.getProductItem().getBuildProduct());
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("8GB [+$60.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("400 GB [+$100.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Vista Premium [+$60.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Microsoft Office [+$50.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Acrobat Reader [+$10.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Total Commander [+$5.00]"));

        verifyTrue(productPage.getTotalOnFlyoutCart(driver).contains("$1,500.00"));
    }

    @Test
    public void Order_02_Edit_Product_In_Shopping_Cart(){
        shoppingCartPage = productPage.openShoppingCartPage();

        productPage = shoppingCartPage.clickOnEditButton(nopCommerceData.getProductItem().getBuildProduct());

        productPage.selectBuildYourOwnComputerToCart("2.2 GHz Intel Pentium Dual-Core E2200", "4GB [+$20.00]", "320 GB", "Vista Home [+$50.00]", "yes", "no", "no", "2");

        verifyEquals(productPage.getProductTotalPrice(), "$1,320.00");
        verifyTrue(productPage.isSuccessBarNotificationDisplayed(driver, "The product has been added to your shopping cart"));
        productPage.closeTheBarNotification(driver);

        productPage.moveMouseOnShoppingCartMenuItem(driver);
        verifyEquals(productPage.getCountProductOnFlyoutCart(driver), "There are 2 item(s) in your cart.");
        verifyEquals(productPage.getProductNameInCartMenuItem(driver), nopCommerceData.getProductItem().getBuildProduct());
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("2.2 GHz Intel Pentium Dual-Core E2200"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("4GB [+$20.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("320 GB"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Vista Home [+$50.00]"));
        verifyTrue(productPage.getTextProductAttributeOnFlyoutCart(driver).contains("Microsoft Office [+$50.00]"));
        verifyTrue(productPage.getTotalOnFlyoutCart(driver).contains("$2,640.00"));
    }

    @Test
    public void Order_03_Remove_From_Cart(){
        shoppingCartPage = productPage.openShoppingCartPage();

        shoppingCartPage.removeProductByProductName(nopCommerceData.getProductItem().getBuildProduct());

        verifyEquals(shoppingCartPage.getNoDataText(), "Your Shopping Cart is empty!");
        verifyTrue(shoppingCartPage.isProductUndisplay(driver, nopCommerceData.getProductItem().getBuildProduct()));
    }

    @Test
    public void Order_04_Update_Shopping_Cart(){
        shoppingCartPage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getDesktopsBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle(nopCommerceData.getProductItem().getLenovoItem());

        productPage.clickAddToCartButton();
        productPage.closeTheBarNotification(driver);

        shoppingCartPage = productPage.openShoppingCartPage();

        shoppingCartPage.enterProductQuantityByProductName(nopCommerceData.getProductItem().getLenovoItem(),"5");
        verifyEquals(shoppingCartPage.getProductTotalPrice(nopCommerceData.getProductItem().getLenovoItem()), "$2,500.00");
        shoppingCartPage.removeProductByProductName(nopCommerceData.getProductItem().getLenovoItem());


    }

    @Test
    public void Order_05_Check_Out(){
        shoppingCartPage.openHeaderMenuBarPage(driver,nopCommerceData.getProductItem().getComputerBreadcrumb(), nopCommerceData.getProductItem().getNotebookBreadcrumb());
        productListPage = PageGenerator.getPageGenerator().getUserProductList(driver);
        productPage = productListPage.openProductByTitle(nopCommerceData.getSearchTest().getAppleSearch());

        productPage.clickAddToCartButton();
        productPage.closeTheBarNotification(driver);

        shoppingCartPage = productPage.openShoppingCartPage();

        shoppingCartPage.selectGiftWrappingDropdown("No");
        shoppingCartPage.clickOnEstimateShippingButton();

        shoppingCartPage.configEstimateShippingPopup(nopCommerceData.getCountry(),nopCommerceData.getCity(),nopCommerceData.getZipPostal(),"Ground");

        shoppingCartPage.checkOnTermOfServiceCheckbox();
        checkOutPage = shoppingCartPage.clickOnCheckOutButton();

        checkOutPage.uncheckOnShipOnSameAddressCheckbox();
        checkOutPage.addNewAddressBillingShipping("Billing", nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email,
                nopCommerceData.getCompany(), nopCommerceData.getCountry(), nopCommerceData.getCity(), nopCommerceData.getCity(), address, address, nopCommerceData.getZipPostal(), phoneNumber, phoneNumber);

        checkOutPage.selectShippingAddressDropdown("New Address");
        checkOutPage.addNewAddressBillingShipping("Shipping", nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email,
                nopCommerceData.getCompany(), nopCommerceData.getCountry(), nopCommerceData.getCity(), nopCommerceData.getCity(), address, address, nopCommerceData.getZipPostal(), phoneNumber, phoneNumber);

        checkOutPage.selectShippingMethod("Ground ($0.00)");
        checkOutPage.selectPaymentMethod("Check / Money Order");

        verifyTrue(checkOutPage.getPaymentInformation().contains("COMPANY NAME\nyour address here,\nNew York, NY 10001\nUSA"));
        checkOutPage.clickContinueButtonInPaymentInformation();

        verifyEquals(checkOutPage.getDynamicNameText("billing"), nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName());
        verifyEquals(checkOutPage.getDynamicEmailText("billing"), "Email: " + email);
        verifyEquals(checkOutPage.getDynamicPhoneText("billing"), "Phone: " + phoneNumber);
        verifyEquals(checkOutPage.getDynamicFaxText("billing"), "Fax: " + phoneNumber);
        verifyEquals(checkOutPage.getDynamicCompanyText("billing"), nopCommerceData.getCompany());
        verifyEquals(checkOutPage.getDynamicCountryText("billing"), nopCommerceData.getCountry());
        verifyEquals(checkOutPage.getDynamicStateText("billing"), nopCommerceData.getCity());
        verifyEquals(checkOutPage.getDynamicAddress1Text("billing"), address);
        verifyEquals(checkOutPage.getDynamicAddress2Text("billing"), address);
        verifyEquals(checkOutPage.getDynamicZipText("billing"), nopCommerceData.getZipPostal());

        verifyEquals(checkOutPage.getDynamicNameText("shipping"), nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName());
        verifyEquals(checkOutPage.getDynamicEmailText("shipping"), "Email: " + email);
        verifyEquals(checkOutPage.getDynamicPhoneText("shipping"), "Phone: " + phoneNumber);
        verifyEquals(checkOutPage.getDynamicFaxText("shipping"), "Fax: " + phoneNumber);
        verifyEquals(checkOutPage.getDynamicCompanyText("shipping"), nopCommerceData.getCompany());
        verifyEquals(checkOutPage.getDynamicCountryText("shipping"), nopCommerceData.getCountry());
        verifyEquals(checkOutPage.getDynamicStateText("shipping"), nopCommerceData.getCity());
        verifyEquals(checkOutPage.getDynamicAddress1Text("shipping"), address);
        verifyEquals(checkOutPage.getDynamicAddress2Text("shipping"), address);
        verifyEquals(checkOutPage.getDynamicZipText("shipping"), nopCommerceData.getZipPostal());

        verifyTrue(checkOutPage.isDataRowDisplayed(nopCommerceData.getSearchTest().getAppleSearch(), "$1,800.00", "2", "$3,600.00"));
        verifyEquals(checkOutPage.getSubTotalPriceText(), "$3,600.00");
        verifyEquals(checkOutPage.getShippingPriceText(), "$0.00");
        verifyEquals(checkOutPage.getTaxPriceText(), "$0.00");
        verifyEquals(checkOutPage.getTotalPriceText(), "$3,600.00");

        checkOutPage.clickConfirmButton();

        verifyEquals(checkOutPage.getSuccessfulTitle(), "Thank you");
        verifyEquals(checkOutPage.getSuccessfulMessage(), "Your order has been successfully processed!");
        verifyTrue(checkOutPage.getOrderNumber().contains("ORDER NUMBER"));
    }

    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }
    private WebDriver driver;
    private UserHomePO homePage;
    private UserProductListPO productListPage;
    private UserProductPO productPage;
    private UserShoppingCartPO shoppingCartPage;
    private UserCheckOutPO checkOutPage;
    private NopCommerceData nopCommerceData;
    private FakerConfig fakerConfig;
    private String email, address, phoneNumber;
}
