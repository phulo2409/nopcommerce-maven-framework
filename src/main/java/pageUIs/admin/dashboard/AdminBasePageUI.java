package pageUIs.admin.dashboard;

public class AdminBasePageUI {
    public static final String DYNAMIC_NAV_LINK = "Xpath=//div[@class='sidebar']//li[contains(@class, 'has-treeview')]//a[normalize-space()='%s']";
    public static final String DYNAMIC_NAV_LINK_OPENING = "Xpath=//div[@class='sidebar']//i[contains(@class, 'fa-dot-circle')]/following-sibling::p[normalize-space()='%s']";
    public static final String AJAX_LOADING = "Xpath=//div[@id='ajaxBusy']";
    public static final String SUCCESS_MESSAGE = "Xpath=//div[contains(@class, 'alert-success')]";
}
