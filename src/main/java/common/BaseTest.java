package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    protected final Logger log;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String url){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Microsoft/Edge/User Data/");
                edgeOptions.addArguments("--profile-directory=Profile 1");
                driver = new EdgeDriver(edgeOptions);
                break;
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Google/Chrome/User Data/");
                chromeOptions.addArguments("--profile-directory=Profile 10");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }

    protected boolean verifyTrue(boolean condition){
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("------------------------- PASSED -------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("------------------------- FAILED -------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition){
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("------------------------- PASSED -------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("------------------------- FAILED -------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected){
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("------------------------- PASSED -------------------------");
        } catch (Throwable e) {
            status = false;
            log.info("------------------------- FAILED -------------------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

}
