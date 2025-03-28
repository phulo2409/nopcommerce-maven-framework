package factoryEnvironment;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory implements EnvironmentFactory{
    private WebDriver driver;
    private Platform platform;
    private String browserName;
    private String ipAddress;
    private String portNumber;
    private String osName;

    public GridFactory(String browserName, String osName, String ipAddress, String portNumber){
        this.browserName = browserName;
        this.osName = osName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    @Override
    public WebDriver createDriver() {
        Capabilities capability = null;

        if (osName.toLowerCase().contains("windows")) {
            platform = Platform.WINDOWS;
        } else if (osName.toLowerCase().contains("mac")){
            platform = Platform.MAC;
        } else {
            platform = Platform.LINUX;
        }

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.addArguments("--user-data-dir=C:/Users/phulo/AppData/Local/Microsoft/Edge/User Data/");
                eOptions.addArguments("--profile-directory=Profile 1");
                eOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
