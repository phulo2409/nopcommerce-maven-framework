package factoryEnvironment;

import common.BrowserList;
import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

public class LocalFactory implements EnvironmentFactory{
    private WebDriver driver;
    private String browserName;

    public LocalFactory(String browserName){
        this.browserName = browserName;
    }

    @Override
    public WebDriver createDriver(){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser) {
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            case HFIREFOX:
                driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
                break;
            case HCHROME:
                driver = new HeadlessChromeDriverManager().getBrowserDriver();
                break;
            case HEDGE:
                driver = new HeadlessEdgeDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupportedException(browserName);
        }
        return driver;
    }
}
