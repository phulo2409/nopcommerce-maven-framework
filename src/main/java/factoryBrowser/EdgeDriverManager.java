package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.apache.commons.lang.SystemUtils.IS_OS_LINUX;

public class EdgeDriverManager implements BrowserFactory{

    @Override
    public WebDriver getBrowserDriver() {
        if (IS_OS_LINUX){
            throw new BrowserNotSupportedException("Edge is not supported on " + System.getProperty("os.name"));
        }

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Microsoft/Edge/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        return new EdgeDriver(options);
    }
}
