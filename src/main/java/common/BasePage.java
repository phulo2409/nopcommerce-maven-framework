package common;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.PageGenerator;
import pageObjects.users.myAccount.UserCustomerInfoPO;
import pageUIs.admin.AdminAddCustomerPUI;
import pageUIs.admin.AdminBasePageUI;
import pageUIs.users.dashboard.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
private WebDriver driver;

//    public BasePage(WebDriver driver){
//        this.driver = driver;
//    }

    protected void openPageUrl(WebDriver driver, String url){
        driver.get(url);
    }

    private By getByLocator(String prefixLocator){
        By by = null;
        if (prefixLocator.toUpperCase().startsWith("ID")){
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toUpperCase().startsWith("CLASS")){
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toUpperCase().startsWith("NAME")){
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toUpperCase().startsWith("TAGNAME")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toUpperCase().startsWith("CSS")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toUpperCase().startsWith("XPATH")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!!!!");
        }
        return by;
    }

    private String castParameter(String locator, String... restParameter){
        return String.format(locator, (Object[]) restParameter);
    }

    private WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    protected void clickToElement(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }

    protected void clickToElement(WebDriver driver, String locator, String... restParameter){
        getElement(driver, castParameter(locator, restParameter)).click();
    }

    protected void moveToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    protected void moveToElement(WebDriver driver, String locator, String... restParameter){
        new Actions(driver).moveToElement(getElement(driver, castParameter(locator, restParameter))).perform();
    }

    protected void clickToElementActions(WebDriver driver, String locator, String... restParameter){
        new Actions(driver).click(getElement(driver, castParameter(locator, restParameter))).perform();
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value){
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value, String... restParameter){
        getElement(driver, castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(value);
    }

    protected void sendkeyToElementWithKeysChord(WebDriver driver, String locator, String value, String... restParameter){
        getElement(driver, castParameter(locator, restParameter)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        sleepInSecond(1);
        getElement(driver, castParameter(locator, restParameter)).sendKeys(value);
    }

    protected void pressKeyToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getElement(driver, locator), keys).perform();
    }

    protected void pressKeyToElement(WebDriver driver, String locator, Keys keys, String... restParameter){
        new Actions(driver).sendKeys(getElement(driver, castParameter(locator, restParameter)), keys).perform();
    }

    protected void checkToRadioButton(WebDriver driver, String locator){
        if(!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    protected void checkToRadioButton(WebDriver driver, String locator, String... restParameter){
        if(!getElement(driver, castParameter(locator, restParameter)).isSelected()){
            getElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    protected void checkToCheckbox(WebDriver driver, String locator){
        if(!getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    protected void checkToCheckbox(WebDriver driver, String locator, String... restParameter){
        if(!getElement(driver, castParameter(locator, restParameter)).isSelected()){
            getElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    protected void uncheckToCheckbox(WebDriver driver, String locator){
        if(getElement(driver, locator).isSelected()){
            getElement(driver, locator).click();
        }
    }

    protected void uncheckToCheckbox(WebDriver driver, String locator, String... restParameter){
        if(getElement(driver, castParameter(locator, restParameter)).isSelected()){
            getElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    public void overideGlobalTimeout(WebDriver driver, long timeInSecond){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String textItem){
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... restParameter){
        new Select(getElement(driver, castParameter(locator, restParameter))).selectByVisibleText(textItem);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locator){
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator, String... restParameter){
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator){
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locator);
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0){// Case 3 - Verify Confirm Email textbox is not displayed
            return true;
        } else if (elements.size() > 0 && elements.get(0).isDisplayed()){ // Case 2 - Verify Confirm Email textbox is not displayed
            return true;
        } else { // Case 1 - Verify Confirm Email textbox is displayed
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restParameter){
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, castParameter(locator, restParameter));
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0){// Case 3 - Verify Confirm Email textbox is not displayed
            return true;
        } else if (elements.size() > 0 && elements.get(0).isDisplayed()){ // Case 2 - Verify Confirm Email textbox is not displayed
            return true;
        } else { // Case 1 - Verify Confirm Email textbox is displayed
            return false;
        }
    }



    protected boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }

    protected boolean isElementSelected(WebDriver driver, String locator, String... restParameter){
        return getElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    protected String getTextElement(WebDriver driver, String locator){
        return getElement(driver, locator).getText();
    }

    protected String getTextElement(WebDriver driver, String locator, String... restParameter){
        return getElement(driver, castParameter(locator, restParameter)).getText();
    }

    protected String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getElement(driver, locator).getAttribute(attributeName);
    }

    protected String getTextboxValue(WebDriver driver, String locator){
        return getElementAttribute(driver, locator, "value");
    }

    protected int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }

    protected void waitForElementVisible(WebDriver driver, String locator){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " isn't visible after the timeout waiting period is over");
        }
    }

    protected void waitForElementVisible(WebDriver driver, String locator, String... restParameter){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + castParameter(locator, restParameter) + " isn't visible after the timeout waiting period is over");
        }
    }

    protected void waitForElementInvisible(WebDriver driver, String locator){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " isn't invisible after the timeout waiting period is over");
        }
    }

    protected void waitForElementInvisible(WebDriver driver, String locator, String... restParameter){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " isn't invisible after the timeout waiting period is over");
        }
    }

    protected void waitForElementSelected(WebDriver driver, String locator){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " can't be selected after the timeout waiting period is over");
        }
    }

    protected void waitForElementSelected(WebDriver driver, String locator, String... restParameter){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restParameter))));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " can't be selected after the timeout waiting period is over");
        }
    }

    protected void waitForElementClickable(WebDriver driver, String locator){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + locator + " isn't clickable after the timeout waiting period is over");
        }
    }

    protected void waitForElementClickable(WebDriver driver, String locator, String... restParameter){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
        } catch (Throwable error){
            error.printStackTrace();
            Assert.fail("The " + castParameter(locator, restParameter) + " isn't clickable after the timeout waiting period is over");
        }
    }

    public void sleepInSecond(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected boolean waitForListElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    @Step("Open {0} Page from header menubar")
    public void openHeaderMenuBarPage(String menuItem){
        waitForElementClickable(driver, BasePageUI.HEADER_MENU_FIRST_LEVEL, menuItem);
        clickToElement(driver, BasePageUI.HEADER_MENU_FIRST_LEVEL, menuItem);
    }

    @Step("Open {0} -> {1} Page from header menubar")
    public void openHeaderMenuBarPage(WebDriver driver, String menuItem, String menuItem2){
        waitForElementClickable(driver, BasePageUI.HEADER_MENU_FIRST_LEVEL, menuItem);
        moveToElement(driver, BasePageUI.HEADER_MENU_FIRST_LEVEL, menuItem);
        waitForElementClickable(driver, BasePageUI.HEADER_MENU_SECOND_LEVEL, menuItem, menuItem2);
        clickToElement(driver, BasePageUI.HEADER_MENU_FIRST_LEVEL, menuItem2);
    }

    @Step("Open Customer Info Page")
    public UserCustomerInfoPO openCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getPageGenerator().getUserCustomerInfoPage(driver);
    }

    @Step("Verify: Check 'My Account' displayed")
    public boolean isMyAccountDisplayed(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, BasePageUI.MY_ACCOUNT_LINK);
    }

    @Step("Click to close Bar Notification button")
    public void closeTheBarNotification(WebDriver driver){
        waitForElementClickable(driver, BasePageUI.CLOSE_BAR_BUTTON);
        clickToElement(driver, BasePageUI.CLOSE_BAR_BUTTON);
        waitForElementInvisible(driver, BasePageUI.CLOSE_BAR_BUTTON);
    }

    @Step("Verify: Success Bar Notification is displayed")
    public boolean isSuccessBarNotificationDisplayed(WebDriver driver, String text){
        waitForElementVisible(driver, BasePageUI.BAR_NOTIFICATION, text);
        return isElementDisplayed(driver, BasePageUI.BAR_NOTIFICATION, text);
    }

    @Step("Verify: Product name is displayed")
    public boolean isProductNameDisplay(WebDriver driver, String productName) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
        return isElementDisplayed(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
    }

    @Step("Verify: Product name is undisplayed")
    public boolean isProductUndisplay(WebDriver driver, String productName) {
        waitForElementInvisible(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
        return isElementUndisplayed(driver, BasePageUI.DYNAMIC_PRODUCT_NAME, productName);
    }

    @Step("Wait for all loading icon are invisible")
    public boolean waitAllLoadingIconInvisible(WebDriver driver){
        return waitForListElementInvisible(driver, BasePageUI.AJAX_ICON);
    }

    @Step("Verify: Get product name in Shopping Cart menu item")
    public String getProductNameInCartMenuItem(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.PRODUCT_NAME_FLYOUT_CART);
        return getTextElement(driver, BasePageUI.PRODUCT_NAME_FLYOUT_CART);
    }

    @Step("Verify: Get text of product attribute on flyout cart")
    public String getTextProductAttributeOnFlyoutCart(WebDriver driver){
        waitForElementVisible(driver, BasePageUI.PRODUCT_ATTRIBUTE_FLYOUT_CART);
        return getTextElement(driver, BasePageUI.PRODUCT_ATTRIBUTE_FLYOUT_CART);
    }

    @Step("Verify: Get total price on flyout cart")
    public String getTotalOnFlyoutCart(WebDriver driver){
        waitForElementVisible(driver, BasePageUI.TOTAL_FLYOUT_CART);
        return getTextElement(driver, BasePageUI.TOTAL_FLYOUT_CART);
    }

    @Step("Verify: Get text of counting product on flyout cart")
    public String getCountProductOnFlyoutCart(WebDriver driver){
        waitForElementVisible(driver, BasePageUI.COUNT_PRODUCT_FLYOUT_CART);
        return getTextElement(driver, BasePageUI.COUNT_PRODUCT_FLYOUT_CART);
    }

    @Step("Verify: Move mouse on Shopping Cart menu item")
    public void moveMouseOnShoppingCartMenuItem(WebDriver driver){
        waitForElementVisible(driver, BasePageUI.SHOPPING_CART_LINK);
        moveToElement(driver, BasePageUI.SHOPPING_CART_LINK);
    }

    @Step("Get all cookies")
    public Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    @Step("Set cookies")
    public void setCookies(WebDriver driver, Set<Cookie> cookies){

        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
    }

    public void clickOnPageInSidebar(WebDriver driver, String pageName, String pageName2){
        waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_NAV_LINK, pageName);
        clickToElement(driver, AdminBasePageUI.DYNAMIC_NAV_LINK, pageName);
        waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_NAV_LINK_OPENING, pageName2);
        clickToElement(driver, AdminBasePageUI.DYNAMIC_NAV_LINK_OPENING, pageName2);
    }

    @Step("Wait for all loading icon are invisible")
    public boolean waitAllLoadingIconInvisibleAdmin(WebDriver driver){
        return waitForListElementInvisible(driver, AdminBasePageUI.AJAX_LOADING);
    }

    public String getSuccessMessage(WebDriver driver) {
        waitForElementVisible(driver, AdminBasePageUI.SUCCESS_MESSAGE);
        return getTextElement(driver, AdminBasePageUI.SUCCESS_MESSAGE);
    }
}
