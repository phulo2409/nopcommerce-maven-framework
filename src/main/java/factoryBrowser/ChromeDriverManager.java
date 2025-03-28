package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements BrowserFactory{

    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Admin/AppData/Local/Google/Chrome/User Data/");
        options.addArguments("--profile-directory=Profile 10");
        return new ChromeDriver(options);
    }
}
