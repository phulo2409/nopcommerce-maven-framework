package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.users.*;
import pageUIs.users.UserRecentlyViewedProductsPUI;

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

    public UserSearchPO getUserSearch(WebDriver driver){
        return new UserSearchPO(driver);
    }

    public UserWishlistPO getWishList(WebDriver driver){
        return new UserWishlistPO(driver);
    }

    public UserWishlistSharePO getWishListShare(WebDriver driver){
        return new UserWishlistSharePO(driver);
    }

    public UserShoppingCartPO getShoppingCart(WebDriver driver){
        return new UserShoppingCartPO(driver);
    }

    public UserCompareProductsPO getCompareProduct(WebDriver driver){
        return new UserCompareProductsPO(driver);
    }

    public UserRecentlyViewedProductsPO getRecentlyViewedProducts(WebDriver driver){
        return new UserRecentlyViewedProductsPO(driver);
    }
}
