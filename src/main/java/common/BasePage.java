package common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.PageGenerator;
import pageObjects.users.UserCustomerInfoPO;
import pageUIs.users.BasePageUI;
import pageUIs.users.UserHomePUI;
import pageUIs.users.UserSideBarPUI;

import java.time.Duration;
import java.util.List;

public class BasePage {
private WebDriver driver;

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

    private List<WebElement> getListElement(WebDriver driver, String locator){
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

    protected void selectItemInDropdown(WebDriver driver, String locator, String textItem){
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
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

    protected boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver, locator).isSelected();
    }

    protected String getTextElement(WebDriver driver, String locator){
        return getElement(driver, locator).getText();
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

    protected void waitForElementSelected(WebDriver driver, String locator){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
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

    protected void sleepInSecond(long timeInSeconds){
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Verify: Success Bar Notification is displayed")
    public boolean isSuccessBarNotificationDisplayed(WebDriver driver) {
        waitForElementVisible(driver, UserSideBarPUI.SUCCESS_BAR_NOTIFICATION);
        return isElementDisplayed(driver, UserSideBarPUI.SUCCESS_BAR_NOTIFICATION);
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
        sleepInSecond(2);
    }
}
