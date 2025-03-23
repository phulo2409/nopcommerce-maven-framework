package com.nopcommerce.admin;

import common.BaseTest;
import common.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.admin.customer.*;
import pageObjects.admin.dashboard.AdminDashboardPO;
import pageObjects.admin.login.AdminLoginPO;
import pageObjects.admin.product.AdminProductPO;
import utilities.FakerConfig;
import utilities.NopCommerceData;

public class AdminTest extends BaseTest {

    @Parameters({"browser", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browser, String url){
        driver = getBrowserDriver(browser, url);
        nopCommerceData = NopCommerceData.getNopCommerceData();

        fakerConfig = FakerConfig.getFaker();
        email = fakerConfig.getEmailAddress();
        address = fakerConfig.getStreetAddress();
        phoneNumber = fakerConfig.getPhoneNumber();

        loginPage = PageGenerator.getPageGenerator().getAdminLogin(driver);
        dashboardPage = loginPage.loginAdminAccount(GlobalConstants.getGlobalConstants().getAdminEmail(), GlobalConstants.getGlobalConstants().getAdminPassword());
    }

    @Test
    public void Admin_01_Search_With_Product_Name(){
        dashboardPage.clickOnPageInSidebar(driver, "Catalog", "Products");
        productPage = PageGenerator.getPageGenerator().getAdminProduct(driver);

        productPage.enterProductNameTextbox(nopCommerceData.getProductItem().getLenovoItem());
        
        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed(nopCommerceData.getProductItem().getLenovoItem(), nopCommerceData.getAdminServer().getSkuProduct(), nopCommerceData.getAdminServer().getProductPrice500()));
        
        
    }

    @Test
    public void Admin_02_Search_With_Product_Name_Parent_Category_Unchecked(){
        productPage.enterProductNameTextbox(nopCommerceData.getProductItem().getLenovoItem());
        productPage.selectCategoryDropdown(nopCommerceData.getSearchTest().getCategorySelect());
        
        productPage.uncheckOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataEmptyInTable());
    }

    @Test
    public void Admin_03_Search_With_Product_Name_Parent_Category_Checked(){
        productPage.enterProductNameTextbox(nopCommerceData.getProductItem().getLenovoItem());
        productPage.selectCategoryDropdown(nopCommerceData.getSearchTest().getCategorySelect());
        productPage.checkOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed(nopCommerceData.getProductItem().getLenovoItem(), nopCommerceData.getAdminServer().getSkuProduct(), nopCommerceData.getAdminServer().getProductPrice500()));
        
    }

    @Test
    public void Admin_04_Search_With_Product_Name_Child_Category(){
        productPage.enterProductNameTextbox(nopCommerceData.getProductItem().getLenovoItem());
        productPage.selectCategoryDropdown("Computers >> Desktops");
        productPage.uncheckOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed(nopCommerceData.getProductItem().getLenovoItem(), nopCommerceData.getAdminServer().getSkuProduct(), nopCommerceData.getAdminServer().getProductPrice500()));
    }

    @Test
    public void Admin_05_Search_With_Product_Name_Manufacturer(){
        productPage.enterProductNameTextbox(nopCommerceData.getProductItem().getLenovoItem());
        productPage.selectCategoryDropdown("All");
        productPage.uncheckOnSearchSubcategoriesCheckbox();
        productPage.selectManufacturerDropdown("Apple");

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataEmptyInTable());
    }

    @Test
    public void Admin_06_Go_Directly_Product_SKU(){
        productPage.enterGoDirectlyToProductSKUTextbox(nopCommerceData.getAdminServer().getSkuProduct());

        verifyTrue(productPage.getEditProductTitle().contains("Edit product details - " + nopCommerceData.getProductItem().getLenovoItem()));
    }

    @Test
    public void Admin_07_Create_New_Customer(){
        productPage.clickOnPageInSidebar(driver, "Customers", "Customers");
        customerPage = PageGenerator.getPageGenerator().getAdminCustomer(driver);

        addCustomerPage = customerPage.clickOnAddNewButton();

        addCustomerPage.enterToEmailTextbox(email);
        addCustomerPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        addCustomerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        addCustomerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        addCustomerPage.checkOnGenderRadio(nopCommerceData.getGender());
        addCustomerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        addCustomerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());
        addCustomerPage.checkOnActiveCheckbox();
        addCustomerPage.enterToAdminComment("Add new comment");
        addCustomerPage.clickOnSaveAndContinueEditButton();

        verifyTrue(addCustomerPage.getSuccessMessage(driver).contains("The new customer has been added successfully."));

        customerPage = addCustomerPage.clickOnBackLink();
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());
        customerPage.clickOnSearchButton();

        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), nopCommerceData.getCompany());
    }

    @Test
    public void Admin_08_Search_Customer_With_Email(){
        customerPage.enterToEmailTextbox(email);
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), nopCommerceData.getCompany());
    }

    @Test
    public void Admin_09_Search_Customer_With_First_Name_And_Last_Name(){
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), nopCommerceData.getCompany());
    }

    @Test
    public void Admin_10_Search_Customer_With_Comapny(){
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), nopCommerceData.getCompany());
    }

    @Test
    public void Admin_11_Search_Customer_With_Full_Data(){
        customerPage.enterToEmailTextbox(email);
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), nopCommerceData.getCompany());
    }

    @Test
    public void Admin_12_Edit_Customer(){
        customerPage.enterToEmailTextbox(email);
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());

        customerPage.clickOnSearchButton();

        editCustomerPage = customerPage.clickOnEditButton(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName());

        editCustomerPage.enterToEmailTextbox("edit_" + email);
        editCustomerPage.enterToFirstNameTextbox("Edit_" + nopCommerceData.getFirstName());
        editCustomerPage.enterToLastNameTextbox("Edit_" + nopCommerceData.getLastName());
        editCustomerPage.enterToCompanyTextbox("Edit_" + nopCommerceData.getCompany());
        editCustomerPage.enterToAdminCommentTextArea("Edit Customer");

        customerPage = editCustomerPage.clickSaveButton();
        verifyTrue(customerPage.getSuccessMessage(driver).contains("The customer has been updated successfully."));

        customerPage.enterToEmailTextbox("edit_" + email);
        customerPage.enterToFirstNameTextbox("Edit_" + nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox("Edit_" + nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox("Edit_" + nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());
        customerPage.clickOnSearchButton();

        customerPage.isDataRowDisplayed("Edit_" + nopCommerceData.getFirstName() + " Edit_" + nopCommerceData.getLastName(), nopCommerceData.getAdminServer().getGuestsRole(), "Edit_" + nopCommerceData.getCompany());
    }

    @Test
    public void Admin_13_Add_New_Address_In_Customer_Detail(){
        customerPage.enterToEmailTextbox("edit_" + email);
        customerPage.enterToFirstNameTextbox("Edit_" + nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox("Edit_" + nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox("Edit_" + nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown(nopCommerceData.getAdminServer().getGuestsRole());
        customerPage.clickOnSearchButton();

        editCustomerPage = customerPage.clickOnEditButton("Edit_" + nopCommerceData.getFirstName() + " Edit_" + nopCommerceData.getLastName());

        editCustomerPage.clickOnAddressTab();
        addNewAddressPage = editCustomerPage.clickOnAddNewAddressButton();

        addNewAddressPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        addNewAddressPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        addNewAddressPage.enterToEmailTextbox(email);
        addNewAddressPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        addNewAddressPage.selectCountryDropdown(nopCommerceData.getCountry());
        addNewAddressPage.selectStateDropdown(nopCommerceData.getCity());
        addNewAddressPage.enterToCityTextbox(nopCommerceData.getCity());
        addNewAddressPage.enterToAddress1Textbox(address);
        addNewAddressPage.enterToAddress2Textbox(address);
        addNewAddressPage.enterToZipTextbox(nopCommerceData.getZipPostal());
        addNewAddressPage.enterToPhoneTextbox(phoneNumber);
        addNewAddressPage.enterToFaxTextbox(phoneNumber);
        addNewAddressPage.clickOnSaveButton();

        verifyTrue(addNewAddressPage.getSuccessMessage(driver).contains("The new address has been added successfully."));

        editCustomerPage = addNewAddressPage.clickOnBackLink();

        verifyTrue(editCustomerPage.getTextAddressDataRow(nopCommerceData.getFirstName(), nopCommerceData.getLastName(), email, phoneNumber, phoneNumber)
                .contains(nopCommerceData.getCountry() + "\n" + nopCommerceData.getCity() + "\n" + nopCommerceData.getCity() + "\n" + address + "\n" + address + "\n" + nopCommerceData.getZipPostal()));
    }

    @Test
    public void Admin_14_Edit_New_Address_In_Customer_Detail() {
        editAddressPage = editCustomerPage.clickOnEditButtonInTableByName(nopCommerceData.getFirstName());

        editAddressPage.enterToFirstNameTextbox("Edit_" + nopCommerceData.getFirstName());
        editAddressPage.enterToLastNameTextbox("Edit_" + nopCommerceData.getLastName());
        editAddressPage.enterToCompanyTextbox("Edit_" + nopCommerceData.getCompany());
        editAddressPage.clickOnSaveButton();
        verifyTrue(editAddressPage.getSuccessMessage(driver).contains("The address has been updated successfully."));

        editCustomerPage = editAddressPage.clickOnBackLink();

        verifyTrue(editCustomerPage.getTextAddressDataRow("Edit_" + nopCommerceData.getFirstName(), "Edit_" + nopCommerceData.getLastName(), email, phoneNumber, phoneNumber)
                .contains(nopCommerceData.getCountry() + "\n" + nopCommerceData.getCity() + "\n" + nopCommerceData.getCity() + "\n" + address + "\n" + address + "\n" + nopCommerceData.getZipPostal()));
    }

    @Test
    public void Admin_15_Delete_Address_In_Customer_Detail() {
        editCustomerPage.clickOnDeleteButtonInAddressTable("Edit_" + nopCommerceData.getFirstName());

        verifyTrue(editCustomerPage.getTextMessageNoDataInTable());
        verifyTrue(editCustomerPage.isDataAddressRowUndisplayed("Edit_" + nopCommerceData.getFirstName(), "Edit_" + nopCommerceData.getLastName(), email, phoneNumber, phoneNumber));
    }
    @AfterClass
    public void afterClass(){
        closeBrowserDriver();
    }

    private WebDriver driver;
    private AdminLoginPO loginPage;
    private AdminDashboardPO dashboardPage;
    private AdminProductPO productPage;
    private AdminCustomerPO customerPage;
    private AdminAddCustomerPO addCustomerPage;
    private AdminEditCustomerPO editCustomerPage;
    private AdminAddNewAddressPO addNewAddressPage;
    private AdminEditAddressPO editAddressPage;
    private NopCommerceData nopCommerceData;
    private FakerConfig fakerConfig;
    private String email, address, phoneNumber;
}
