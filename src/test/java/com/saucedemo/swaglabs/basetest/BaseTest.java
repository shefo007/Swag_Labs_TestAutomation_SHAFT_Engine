package com.saucedemo.swaglabs.basetest;

import com.saucedemo.swaglabs.pages.LoginPage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.saucedemo.swaglabs.basetest.DriverManager.*;

public class BaseTest {

    protected static SHAFT.TestData.JSON LOGIN_DATA;
    protected static SHAFT.TestData.JSON PRODUCTS_DATA;
    protected static SHAFT.TestData.JSON CART_DATA;
    protected static SHAFT.TestData.JSON CHECKOUT_DATA;

    @BeforeClass
    public void beforeEveryClass() {
        LOGIN_DATA = new SHAFT.TestData.JSON("LoginJsonData.json");
        PRODUCTS_DATA = new SHAFT.TestData.JSON("ProductsJsonData.json");
        CART_DATA = new SHAFT.TestData.JSON("ShoppingCartData.json");
        CHECKOUT_DATA = new SHAFT.TestData.JSON("CheckoutInfoData.json");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        setThreadDriver();
        getThreadDriver().browser().navigateToURL(SHAFT.Properties.web.baseURL());
    }

    @BeforeMethod(onlyForGroups = "validLogin")
    public void validLogin() {
        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin();
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        quitDriver();
    }

}
