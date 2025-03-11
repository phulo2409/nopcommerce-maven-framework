package pageObjects.users;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserProductListPUI;

public class UserProductListPO extends BasePage {
    private WebDriver driver;

    public UserProductListPO(WebDriver driver){
        this.driver = driver;
    }

    public UserProductPO selectProductByTitle(String name) {
        waitForElementClickable(driver, UserProductListPUI.PRODUCT_TITLE, name);
        clickToElement(driver, UserProductListPUI.PRODUCT_TITLE, name);
        return PageGenerator.getPageGenerator().getUserProduct(driver);
    }
}
