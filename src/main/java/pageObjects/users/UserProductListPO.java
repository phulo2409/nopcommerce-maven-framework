package pageObjects.users;

import common.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.users.UserProductListPUI;

public class UserProductListPO extends BasePage {
    private WebDriver driver;

    public UserProductListPO(WebDriver driver){
        this.driver = driver;
    }

    @Step("Select product with {0} title ")
    public UserProductPO selectProductByTitle(String name) {
        waitForElementClickable(driver, UserProductListPUI.PRODUCT_TITLE, name);
        clickToElement(driver, UserProductListPUI.PRODUCT_TITLE, name);
        return PageGenerator.getPageGenerator().getUserProduct(driver);
    }
}
