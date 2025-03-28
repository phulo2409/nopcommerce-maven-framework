package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class HeadlessEdgeDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("-headless");
        options.addArguments("window-size=1920x1080");
        return new EdgeDriver(options);
    }
}
