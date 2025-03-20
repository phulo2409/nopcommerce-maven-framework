package pageUIs.admin;

public class AdminEditCustomerPUI {
    public static final String EMAIL_TEXTBOX = "Xpath=//input[@id='Email']";
    public static final String FIRST_NAME_TEXTBOX = "Xpath=//input[@id='FirstName']";
    public static final String LAST_NAME_TEXTBOX = "Xpath=//input[@id='LastName']";
    public static final String COMPANY_TEXTBOX = "Xpath=//input[@id='Company']";
    public static final String ADMIN_COMMENT_AREA = "Xpath=//textarea[@id='AdminComment']";
    public static final String SAVE_BUTTON = "Xpath=//button[@name='save']";
    public static final String ADDRESS_TAB = "Xpath=//div[@id='customer-address']";
    public static final String ADD_NEW_ADDRESS_BUTTON = "Xpath=//div[@id='customer-address']//button[normalize-space()='Add new address']";
    public static final String ADDRESS_DATA_ROW_TABLE = "Xpath=//table[@id='customer-addresses-grid']//td[text()='%s']" +
            "/following-sibling::td[text()='%s']" +
            "/following-sibling::td[text()='%s']" +
            "/following-sibling::td[text()='%s']" +
            "/following-sibling::td[text()='%s']" +
            "/following-sibling::td/div";

    public static final String DYNAMIC_EDIT_BUTTON_BY_NAME = "Xpath=//table[@id='customer-addresses-grid']//td[text()='%s']/following-sibling::td//a[text()='Edit']";
    public static final String DYNAMIC_DELETE_BUTTON_BY_NAME = "Xpath=//table[@id='customer-addresses-grid']//td[text()='%s']/following-sibling::td//a[text()='Delete']";
    public static final String NO_DATA_MESSAGE_TABLE = "Xpath=//table[@id='customer-addresses-grid']//td[text()='No data available in table']";
}
