package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.users.*;

public class PageGenerator {

    public static PageGenerator getPageGenerator(){
        return new PageGenerator();
    }

    public UserHomePO getUserHomePage(WebDriver driver){
        return new UserHomePO(driver);
    }

    public UserRegisterPO getUserRegisterPage(WebDriver driver){
        return new UserRegisterPO(driver);
    }

    public UserLoginPO getUserLoginPage(WebDriver driver){
        return new UserLoginPO(driver);
    }

    public UserCustomerInfoPO getUserCustomerInfoPage(WebDriver driver){
        return new UserCustomerInfoPO(driver);
    }

    public UserAddressPO getUserAddressPage(WebDriver driver){
        return new UserAddressPO(driver);
    }

    public UserChangePasswordPO getUserChangePassword(WebDriver driver){
        return new UserChangePasswordPO(driver);
    }

    public UserProductListPO getUserProductList(WebDriver driver){
        return new UserProductListPO(driver);
    }

    public UserProductPO getUserProduct(WebDriver driver){
        return new UserProductPO(driver);
    }

    public UserMyProductReviewsPO getUserMyProductReviews(WebDriver driver){
        return new UserMyProductReviewsPO(driver);
    }




}
