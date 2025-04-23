package com.saucedemo.swaglabs.featureTests;

import com.saucedemo.swaglabs.basetest.BaseTest;
import com.saucedemo.swaglabs.pages.CartPage;
import com.saucedemo.swaglabs.pages.ProductsPage;
import org.testng.annotations.Test;

import static com.saucedemo.swaglabs.basetest.DriverManager.getThreadDriver;

public class ShoppingCartTests extends BaseTest {

    @Test(groups = "validLogin")
    public void SC_01_VerifyItemAddedToCart() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .verifyRedirectedToCartPage(CART_DATA.getTestData("endPoint"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1")
                .getSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductName(PRODUCTS_DATA.getTestData("Item2.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_02_VerifyTwoItemsAddedToCart() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .verifyRedirectedToCartPage(CART_DATA.getTestData("endPoint"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("2")
                .getSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductName(PRODUCTS_DATA.getTestData("Item2.Name"))
                .getSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .verifyProductName(PRODUCTS_DATA.getTestData("Item3.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_03_VerifyRemoveItemFromCart() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .verifyRedirectedToCartPage(CART_DATA.getTestData("endPoint"))
                .removeSpecificProductFromCartPage(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifySpecificProductRemovedFromCartPage(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyCartIsEmpty()
                .verifyNotificationNotVisibleOnCart();
    }

    @Test(groups = "validLogin")
    public void SC_04_VerifyAddTwoItemsToCartAndRemoveOneItemFromCart() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .verifyRedirectedToCartPage(CART_DATA.getTestData("endPoint"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("2")
                .removeSpecificProductFromCartPage(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifySpecificProductRemovedFromCartPage(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1")
                .getSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .verifyProductName(PRODUCTS_DATA.getTestData("Item3.Name"));
    }

    @Test(groups = "validLogin")
    public void SC_05_VerifyCartIsEmpty() {

        new ProductsPage(getThreadDriver())
                .clickCartIcon();
        new CartPage(getThreadDriver())
                .verifyRedirectedToCartPage(CART_DATA.getTestData("endPoint"))
                .verifyCartIsEmpty()
                .verifyNotificationNotVisibleOnCart();
    }

}
