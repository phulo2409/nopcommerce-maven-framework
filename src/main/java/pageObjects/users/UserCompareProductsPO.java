package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.users.UserCompareProductsPUI;

public class UserCompareProductsPO extends BasePage {
    private WebDriver driver;

    public UserCompareProductsPO(WebDriver driver){
        this.driver = driver;
    }

    public String getColumnIndexByProductName(String productName) {
        int rowSize = getListElement(driver, UserCompareProductsPUI.PRODUCT_NAME_LINK).size();
        String columnIndex = null;
        for (int i = 1; i <= rowSize; i++) {
            String elementIndex = String.valueOf(i+1);
            if (getTextElement(driver, UserCompareProductsPUI.DYNAMIC_PRODUCT_NAME_LINK, elementIndex).equals(productName)) {
                columnIndex = elementIndex;
            }
        }
        return columnIndex;
    }

    public boolean isDataColumnDisplayed(String productName, String price){
        String columnProductIndex = getColumnIndexByProductName(productName);
        waitForElementVisible(driver, UserCompareProductsPUI.DYNAMIC_DATA_COLUMN, columnProductIndex, productName, columnProductIndex, price);
        return isElementDisplayed(driver, UserCompareProductsPUI.DYNAMIC_DATA_COLUMN, columnProductIndex, productName, columnProductIndex, price);
    }

    public boolean isRemoveButtonDisplayed(String productName){
        String columnProductIndex = getColumnIndexByProductName(productName);
        waitForElementVisible(driver, UserCompareProductsPUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, columnProductIndex, productName, columnProductIndex);
        return isElementDisplayed(driver, UserCompareProductsPUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, columnProductIndex, productName, columnProductIndex);
    }
}
