package pageUIs.admin.product;

public class AdminProductPUI {
    public static final String PRODUCT_NAME_TEXTBOX = "Xpath=//input[@id='SearchProductName']";
    public static final String SEARCH_BUTTON = "Xpath=//button[@id='search-products']";
    public static final String DYNAMIC_DATA_ROW = "Xpath=//table[@id='products-grid']//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
    public static final String CATEGORY_DROPDOWN = "Xpath=//select[@id='SearchCategoryId']";
    public static final String SEARCH_SUBCATEGORIES_CHECKBOX = "Xpath=//input[@id='SearchIncludeSubCategories']";
    public static final String DATA_EMPTY_TABLE = "Xpath=//td[@class='dt-empty']";
    public static final String MANUFACTURER_DROPDOWN = "Xpath=//select[@id='SearchManufacturerId']";
    public static final String GO_DIRECTLY_PRODUCT_SKU_TEXTBOX = "Xpath=//input[@id='GoDirectlyToSku']";
    public static final String GO_DIRECTLY_PRODUCT_SKU_BUTTON = "Xpath=//button[@id='go-to-product-by-sku']";
    public static final String EDIT_PRODUCT_TITLE = "Xpath=//form[@id='product-form']//h1";
}
