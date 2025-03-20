package com.nopcommerce.admin;

import common.BaseTest;
import common.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.admin.*;
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
        dashboardPage = loginPage.loginAdminAccount(GlobalConstants.ADMIN_EMAIL, GlobalConstants.ADMIN_PASSWORD);
    }

    @Test
    public void TC_01(){
        dashboardPage.clickOnPageInSidebar(driver, "Catalog", "Products");
        productPage = PageGenerator.getPageGenerator().getAdminProduct(driver);

        productPage.enterProductNameTextbox("Lenovo IdeaCentre");
        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed("Lenovo IdeaCentre", "LE_IC_600", "$500.00"));
    }

    @Test
    public void TC_02(){
        productPage.enterProductNameTextbox("Lenovo IdeaCentre");
        productPage.selectCategoryDropdown("Computers");
        productPage.uncheckOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataEmptyInTable());
    }

    @Test
    public void TC_03(){
        productPage.enterProductNameTextbox("Lenovo IdeaCentre");
        productPage.selectCategoryDropdown("Computers");
        productPage.checkOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed("Lenovo IdeaCentre", "LE_IC_600", "$500.00"));
    }

    @Test
    public void TC_04(){
        productPage.enterProductNameTextbox("Lenovo IdeaCentre");
        productPage.selectCategoryDropdown("Computers >> Desktops");
        productPage.uncheckOnSearchSubcategoriesCheckbox();

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataRowDisplayed("Lenovo IdeaCentre", "LE_IC_600", "$500.00"));
    }

    @Test
    public void TC_05(){
        productPage.enterProductNameTextbox("Lenovo IdeaCentre");
        productPage.selectCategoryDropdown("All");
        productPage.uncheckOnSearchSubcategoriesCheckbox();
        productPage.selectManufacturerDropdown("Apple");

        productPage.clickSearchButton();

        verifyTrue(productPage.isDataEmptyInTable());
    }

    @Test
    public void TC_06(){
        productPage.enterGoDirectlyToProductSKUTextbox("LE_IC_600");

        verifyTrue(productPage.getEditProductTitle().contains("Edit product details - Lenovo IdeaCentre"));
    }

    @Test
    public void TC_07(){
        productPage.clickOnPageInSidebar(driver, "Customers", "Customers");
        customerPage = PageGenerator.getPageGenerator().getAdminCustomer(driver);

        addCustomerPage = customerPage.clickOnAddNewButton();

        addCustomerPage.enterToEmailTextbox(email);
        addCustomerPage.enterToPasswordTextbox(nopCommerceData.getPassword());
        addCustomerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        addCustomerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        addCustomerPage.checkOnGenderRadio(nopCommerceData.getGender());
        addCustomerPage.enterToCompanyTextbox(nopCommerceData.getCompany());
        addCustomerPage.selectCustomerRolesDropdown("Guests");
        addCustomerPage.checkOnActiveCheckbox();
        addCustomerPage.enterToAdminComment("Add new comment");
        addCustomerPage.clickOnSaveAndContinueEditButton();

        verifyTrue(addCustomerPage.getSuccessMessage().contains("The new customer has been added successfully."));

        customerPage = addCustomerPage.clickOnBackLink();
        customerPage.selectCustomerRolesDropdown("Guests");
        customerPage.clickOnSearchButton();

        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), "Guests", nopCommerceData.getCompany());
    }

    @Test
    public void TC_08(){
        customerPage.enterToEmailTextbox(email);
        customerPage.selectCustomerRolesDropdown("Guests");

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), "Guests", nopCommerceData.getCompany());
    }

    @Test
    public void TC_09(){
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.selectCustomerRolesDropdown("Guests");

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), "Guests", nopCommerceData.getCompany());
    }

    @Test
    public void TC_10(){
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown("Guests");

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), "Guests", nopCommerceData.getCompany());
    }

    @Test
    public void TC_11(){
        customerPage.enterToEmailTextbox(email);
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown("Guests");

        customerPage.clickOnSearchButton();
        customerPage.isDataRowDisplayed(nopCommerceData.getFirstName() + " " + nopCommerceData.getLastName(), "Guests", nopCommerceData.getCompany());
    }

    @Test
    public void TC_12(){
        customerPage.enterToEmailTextbox(email);
        customerPage.enterToFirstNameTextbox(nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox(nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox(nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown("Guests");

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
        customerPage.selectCustomerRolesDropdown("Guests");
        customerPage.clickOnSearchButton();

        customerPage.isDataRowDisplayed("Edit_" + nopCommerceData.getFirstName() + " Edit_" + nopCommerceData.getLastName(), "Guests", "Edit_" + nopCommerceData.getCompany());
    }

    @Test
    public void TC_13(){
        customerPage.enterToEmailTextbox("edit_" + email);
        customerPage.enterToFirstNameTextbox("Edit_" + nopCommerceData.getFirstName());
        customerPage.enterToLastNameTextbox("Edit_" + nopCommerceData.getLastName());
        customerPage.enterToComapnyTextbox("Edit_" + nopCommerceData.getCompany());
        customerPage.selectCustomerRolesDropdown("Guests");
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
    public void TC_14() {
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
    public void TC_15() {
        editCustomerPage.clickOnDeleteButtonInAddressTable("Edit_" + nopCommerceData.getFirstName());

        verifyTrue(editCustomerPage.noDataInTable());
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
