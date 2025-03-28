package common;

import factoryEnvironment.GridFactory;
import factoryEnvironment.LocalFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected final Logger log;
    private Platform platform;

    public WebDriver getDriver() {
        return driver.get();
    }

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String envName, String browserName, String url, String osName, String ipAddress, String portNumber){
        switch (envName){
            case "local":
                driver.set(new LocalFactory(browserName).createDriver());
                break;
            case "grid":
                driver.set(new GridFactory(browserName, osName, ipAddress, portNumber).createDriver());
                break;
            default:
                System.out.println("Run default: " + envName);
                driver.set(new LocalFactory(browserName).createDriver());
                break;
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.getGlobalConstants().getLongTimeout()));
        driver.get().manage().window().maximize();
        driver.get().get(url);
        return driver.get();
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

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.get().manage().deleteAllCookies();
                driver.get().quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
