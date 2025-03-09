package com.nopcommerce.users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterTest {

    @BeforeClass
    public void beforeClass(){
        driver = new EdgeDriver();
        driver.get();


    }

    @Test
    public void Register_01_EmptyData(){
        registerPage.clickTheRegisterButton();

        Assert.assertTrue(isFirstNameValidationDisplayed());
        Assert.assertTrue(isLastNameValidationDisplayed);
        Assert.assertTrue(isEmailValidationDisplayed);
        Assert.assertTrue(isPasswordValidationDisplayed);
    }

    @AfterClass
    public void afterClass(){

    }

    WebDriver driver;
}
