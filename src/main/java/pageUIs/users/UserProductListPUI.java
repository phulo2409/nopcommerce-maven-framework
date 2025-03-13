package pageUIs.users;

public class UserProductListPUI {
    public final static String PRODUCT_NAME_DYNAMIC = "Xpath=//h2[@class='product-title' and normalize-space()='%s']/parent::div/preceding-sibling::div";
    public final static String PRODUCT_NAME = "Xpath=//div[@class='product-item']//h2[@class='product-title']/a";
    public final static String PRODUCT_PRICE = "Xpath=//div[@class='product-item']//span[contains(@class, 'actual-price')]";
    public final static String PRODUCT_ITEM = "Xpath=//div[@class='product-item']";
    public final static String SORT_BY_DROPDOWN = "Xpath=//select[@id='products-orderby']";
    public final static String DISPLAY_PER_PAGE_DROPDOWN = "Xpath=//select[@id='products-pagesize']";
    public final static String NEXT_PAGE_BUTTON = "Xpath=//div[@class='pager']//li[@class='next-page']";
    public final static String PREVIOUS_PAGE_BUTTON = "Xpath=//div[@class='pager']//li[@class='previous-page']";
    public final static String CURRENT_PAGE_BUTTON = "Xpath=//div[@class='pager']//li[@class='current-page']";
    public final static String PAGING = "Xpath=//div[@class='pager']";
}
