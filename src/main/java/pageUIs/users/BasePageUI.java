package pageUIs.users;

public class BasePageUI {
    public static final String HEADER_MENU_FIRST_LEVEL = "Xpath=//div[@class='header-menu']//a[normalize-space()='%s']";
    public static final String HEADER_MENU_SECOND_LEVEL = "Xpath=//div[@class='header-menu']//a[normalize-space()='%s']/parent::li//ul//a[normalize-space()='%s']";
    public static final String LOGOUT_LINK = "XPath=//a[@class='ico-logout']";
    public static final String MY_ACCOUNT_LINK = "XPath=//a[@class='ico-account']";
    public final static String BAR_NOTIFICATION = "Xpath=//div[@class='bar-notification success' and normalize-space()='%s']";
    public final static String CLOSE_BAR_BUTTON = "Xpath=//div[@class='bar-notification success']/span";
    public final static String AJAX_ICON = "Xpath=//div[contains(@class, 'ajax')]";
    public final static String DYNAMIC_PRODUCT_NAME = "Xpath=//a[@class='product-name' and text()='%s']";
}
