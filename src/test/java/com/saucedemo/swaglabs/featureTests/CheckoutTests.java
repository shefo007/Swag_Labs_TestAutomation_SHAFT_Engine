package com.saucedemo.swaglabs.featureTests;

import com.saucedemo.swaglabs.basetest.BaseTest;
import com.saucedemo.swaglabs.pages.CartPage;
import com.saucedemo.swaglabs.pages.CheckoutPage;
import com.saucedemo.swaglabs.pages.ProductsPage;
import org.testng.annotations.Test;

import static com.saucedemo.swaglabs.basetest.DriverManager.getThreadDriver;

public class CheckoutTests extends BaseTest {

    @Test(groups = "validLogin")
    public void TC_01_VerifyCheckoutWithValidData() {

       new ProductsPage(getThreadDriver())
               .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
               .clickCartIcon();
       new CartPage(getThreadDriver())
               .clickCheckoutBtn();
       new CheckoutPage(getThreadDriver())
               .verifyRedirectedToCheckoutPage(CHECKOUT_DATA.getTestData("endPoint"))
               .fillInfo(CHECKOUT_DATA.getTestData("firstName"),
                       CHECKOUT_DATA.getTestData("lastName"),
                       CHECKOUT_DATA.getTestData("zipCode"))
               .clickContinue()
               .clickFinish()
               .verifyOrderCompleted()
               .verifyOrderConfirmationMsg(CHECKOUT_DATA.getTestData("orderConfMsg"));
    }

    @Test(groups = "validLogin")
    public void TC_02_VerifyCheckoutWithEmptyFirstName() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .clickCheckoutBtn();
        new CheckoutPage(getThreadDriver())
                .verifyRedirectedToCheckoutPage(CHECKOUT_DATA.getTestData("endPoint"))
                .fillInfo(CHECKOUT_DATA.getTestData("emptyFirstName"),
                        CHECKOUT_DATA.getTestData("lastName"),
                        CHECKOUT_DATA.getTestData("zipCode"))
                .clickContinue()
                .verifyErrorMsg(CHECKOUT_DATA.getTestData("errFirstNameReq"));
    }

    @Test(groups = "validLogin")
    public void TC_03_VerifyCheckoutWithEmptyLastName() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .clickCheckoutBtn();
        new CheckoutPage(getThreadDriver())
                .verifyRedirectedToCheckoutPage(CHECKOUT_DATA.getTestData("endPoint"))
                .fillInfo(CHECKOUT_DATA.getTestData("firstName"),
                        CHECKOUT_DATA.getTestData("emptyLastName"),
                        CHECKOUT_DATA.getTestData("zipCode"))
                .clickContinue()
                .verifyErrorMsg(CHECKOUT_DATA.getTestData("errLastNameReq"));
    }

    @Test(groups = "validLogin")
    public void TC_04_VerifyCheckoutWithEmptyZipCode() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .clickCheckoutBtn();
        new CheckoutPage(getThreadDriver())
                .verifyRedirectedToCheckoutPage(CHECKOUT_DATA.getTestData("endPoint"))
                .fillInfo(CHECKOUT_DATA.getTestData("firstName"),
                        CHECKOUT_DATA.getTestData("lastName"),
                        CHECKOUT_DATA.getTestData("emptyZipCode"))
                .clickContinue()
                .verifyErrorMsg(CHECKOUT_DATA.getTestData("errZipCodeReq"));
    }
}
