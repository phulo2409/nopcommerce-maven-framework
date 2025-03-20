package pageObjects.admin;

import org.openqa.selenium.WebDriver;

public class AdminEditAddressPO extends  AdminAddNewAddressPO{
    private WebDriver driver;

    public AdminEditAddressPO(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
