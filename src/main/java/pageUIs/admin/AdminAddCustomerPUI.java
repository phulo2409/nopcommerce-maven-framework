package pageUIs.admin;

public class AdminAddCustomerPUI {
    public static final String EMAIL_TEXTBOX = "Xpath=//input[@id='Email']";
    public static final String PASSWORD_TEXTBOX = "Xpath=//input[@id='Password']";
    public static final String FIRST_NAME_TEXTBOX = "Xpath=//input[@id='FirstName']";
    public static final String LAST_NAME_TEXTBOX = "Xpath=//input[@id='LastName']";
    public static final String MALE_GENDER_RADIO = "Xpath=//input[@id='Gender_Male']";
    public static final String FEMALE_GENDER_RADIO = "Xpath=//input[@id='Gender_Female']";
    public static final String COMPANY_TEXTBOX = "Xpath=//input[@id='Company']";


    public static final String ACTIVE_CHECKBOX = "Xpath=//input[@id='Active']";
    public static final String ADMIN_COMMENT_AREA = "Xpath=//textarea[@id='AdminComment']";

    public static final String SAVE_AND_CONTINUE_EDIT_BUTTON = "Xpath=//button[@name='save-continue']";
    public static final String SUCCESS_MESSAGE = "Xpath=//div[contains(@class, 'alert-success')]";

    public static final String SELECTED_CUSTOMER_ROLE = "Xpath=//li[@class='select2-selection__choice']/span";
    public static final String CUSTOMER_ROLE_SELECT = "Xpath=//select[@id='SelectedCustomerRoleIds']/following-sibling::span";
    public static final String CUSTOMER_ROLE_ITEM = "Xpath=//ul[@id='select2-SelectedCustomerRoleIds-results']//li[text()='%s']";

    public static final String BACK_LINK = "Xpath=//h1[@class='float-left']//a";

}
