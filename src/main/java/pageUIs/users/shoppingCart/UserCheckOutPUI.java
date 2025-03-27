package pageUIs.users.shoppingCart;

public class UserCheckOutPUI {
    public final static String DYNAMIC_FIRST_NAME_TEXTBOX = "Xpath=//input[@id='%sNewAddress_FirstName']";
    public final static String DYNAMIC_LAST_NAME_TEXTBOX = "Xpath=//input[@id='%sNewAddress_LastName']";
    public final static String DYNAMIC_EMAIL_TEXTBOX = "Xpath=//input[@id='%sNewAddress_Email']";
    public final static String DYNAMIC_COMPANY_TEXTBOX = "Xpath=//input[@id='%sNewAddress_Company']";
    public final static String DYNAMIC_CITY_TEXTBOX = "Xpath=//input[@id='%sNewAddress_City']";
    public final static String DYNAMIC_ADDRESS1_TEXTBOX = "Xpath=//input[@id='%sNewAddress_Address1']";
    public final static String DYNAMIC_ADDRESS2_TEXTBOX = "Xpath=//input[@id='%sNewAddress_Address2']";
    public final static String DYNAMIC_ZIP_TEXTBOX = "Xpath=//input[@id='%sNewAddress_ZipPostalCode']";
    public final static String DYNAMIC_PHONE_NUMBER_TEXTBOX = "Xpath=//input[@id='%sNewAddress_PhoneNumber']";
    public final static String DYNAMIC_FAX_NUMBER_TEXTBOX = "Xpath=//input[@id='%sNewAddress_FaxNumber']";
    public final static String DYNAMIC_COUNTRY_TEXTBOX = "Xpath=//select[@id='%sNewAddress_CountryId']";
    public final static String DYNAMIC_STATE_TEXTBOX = "Xpath=//select[@id='%sNewAddress_StateProvinceId']";

    public final static String DYNAMIC_CONTINUE_BUTTON = "Xpath=//div[@id='%s-buttons-container']//button[contains(@class, 'new-address-next-step-button')]";
    public final static String SHIPPING_METHOD_CONTINUE_BUTTON = "Xpath=//div[@id='checkout-step-shipping-method']//button[contains(@class, 'shipping-method-next-step-button')]";
    public final static String PAYMENT_METHOD_CONTINUE_BUTTON = "Xpath=//div[@id='payment-method-buttons-container']//button";
    public final static String PAYMENT_INFORMATION_CONTINUE_BUTTON = "Xpath=//div[@id='payment-info-buttons-container']//button";
    public final static String CONFIRM_BUTTON = "Xpath=//div[@id='confirm-order-buttons-container']//button";

    public final static String PAYMENT_INFORMATION_TEXT = "Xpath=//div[@id='checkout-payment-info-load']//td";

    public final static String DYNAMIC_SHIPPING_METHOD_NAME = "Xpath=//div[@class='method-name' and normalize-space()='%s']";
    public final static String DYNAMIC_PAYMENT_METHOD_NAME = "Xpath=//div[@id='checkout-payment-method-load']//label[text()='%s']/preceding-sibling::input";

    public final static String SHIPPING_SAME_ADDRESS_CHECKBOX = "Xpath=//input[@id='ShipToSameAddress']";
    public final static String SHIPPING_ADDRESS_DROPDOWN = "Xpath=//select[@id='shipping-address-select']";

    public final static String DYNAMIC_NAME_TEXT = "Xpath=//div[@class='%s-info']//li[@class='name']";
    public final static String DYNAMIC_EMAIL_TEXT = "Xpath=//div[@class='%s-info']//li[@class='email']";
    public final static String DYNAMIC_PHONE_TEXT = "Xpath=//div[@class='%s-info']//li[@class='phone']";
    public final static String DYNAMIC_FAX_TEXT = "Xpath=//div[@class='%s-info']//li[@class='fax']";
    public final static String DYNAMIC_COMPANY_TEXT = "Xpath=//div[@class='%s-info']//li[@class='company']";
    public final static String DYNAMIC_COUNTRY_TEXT = "Xpath=//div[@class='%s-info']//li[@class='country']";
    public final static String DYNAMIC_STATE_TEXT = "Xpath=//div[@class='%s-info']//li[@class='stateprovince']";
    public final static String DYNAMIC_ADDRESS1_TEXT = "Xpath=//div[@class='%s-info']//li[@class='address1']";
    public final static String DYNAMIC_ADDRESS2_TEXT = "Xpath=//div[@class='%s-info']//li[@class='address2']";
    public final static String DYNAMIC_ZIP_TEXT = "Xpath=//div[@class='%s-info']//li[@class='zippostalcode']";

    public final static String DATA_ROW_TABLE = "Xpath=//a[@class='product-name' and text()='%s']/parent::td/following-sibling::td/span[text()='%s']" +
            "/parent::td/following-sibling::td/span[text()='%s']/parent::td/following-sibling::td/span[text()='%s']";
    public final static String SUB_TOTAL_TEXT = "Xpath=//tr[@class='order-subtotal']//span[@class='value-summary']";
    public final static String SHIPPING_COST_TEXT = "Xpath=//tr[@class='shipping-cost']//span[@class='value-summary']";
    public final static String TAX_COST_TEXT = "Xpath=//tr[@class='tax-value']//span[@class='value-summary']";
    public final static String TOTAL_COST_TEXT = "Xpath=//tr[@class='order-total']//span[@class='value-summary']";

    public final static String SUCCESS_TITLE = "Xpath=//div[contains(@class, 'checkout-page order-completed-page')]//h1";
    public final static String SUCCESS_MESSAGE = "Xpath=//div[@class='section order-completed']//div[@class='title']";
    public final static String ORDER_NUMBER = "Xpath=//div[@class='order-number']";
}
