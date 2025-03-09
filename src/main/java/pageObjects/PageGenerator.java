package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.users.UserHomePO;
import pageObjects.users.UserRegisterPO;

public class PageGenerator {

    public static PageGenerator getPageGenerator(){
        return new PageGenerator();
    }

    public static UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }


}
