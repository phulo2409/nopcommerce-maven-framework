package pageUIs.admin.customer;

public class AdminCustomerPUI {
    public static final String ADD_NEW_BUTTON = "Xpath=//a[@class='btn btn-primary']";

    public static final String SELECTED_CUSTOMER_ROLE = "Xpath=//li[@class='select2-selection__choice']/span";
    public static final String CUSTOMER_ROLE_SELECT = "Xpath=//select[@id='SelectedCustomerRoleIds']/following-sibling::span";
    public static final String CUSTOMER_ROLE_ITEM = "Xpath=//ul[@id='select2-SelectedCustomerRoleIds-results']//li[text()='%s']";

    public static final String SEARCH_BUTTON = "Xpath=//button[@id='search-customers']";

    public static final String DYNAMIC_DATA_ROW = "Xpath=//table[@id='customers-grid']//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
    public static final String DYNAMIC_EDIT_BUTTON_BY_NAME = "Xpath=//table[@id='customers-grid']//td[text()='%s']/following-sibling::td[@class='button-column']//a";

    public static final String EMAIL_TEXTBOX = "Xpath=//input[@id='SearchEmail']";
    public static final String FIRST_NAME_TEXTBOX = "Xpath=//input[@id='SearchFirstName']";
    public static final String LAST_NAME_TEXTBOX = "Xpath=//input[@id='SearchLastName']";
    public static final String COMPANY_TEXTBOX = "Xpath=//input[@id='SearchCompany']";




}
