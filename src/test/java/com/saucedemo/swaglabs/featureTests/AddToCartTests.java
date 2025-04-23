package com.saucedemo.swaglabs.featureTests;

import com.saucedemo.swaglabs.basetest.BaseTest;
import com.saucedemo.swaglabs.pages.ProductsPage;
import org.testng.annotations.Test;

import static com.saucedemo.swaglabs.basetest.DriverManager.getThreadDriver;

public class AddToCartTests extends BaseTest {

    @Test(groups = "validLogin")
    public void ATC_01_VerifyAddItemToCart() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductAddedToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1");
    }

    @Test(groups = "validLogin")
    public void ATC_02_VerifyAddTwoItemsToCart() {
        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductAddedToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1")
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .verifyProductAddedToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("2");
    }

    @Test(groups = "validLogin")
    public void ATC_03_VerifyRemoveItemFromCartFromProductsPage() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductAddedToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1")
                .removeSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductRemovedFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationNotVisibleOnCart();
    }

    @Test(groups = "validLogin")
    public void ATC_04_VerifyAddTwoItemsToCartAndRemoveOneItemFromCartFromProductsPage() {

        new ProductsPage(getThreadDriver())
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .addSpecificProductToCart(PRODUCTS_DATA.getTestData("Item3.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("2")
                .removeSpecificProductFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyProductRemovedFromCart(PRODUCTS_DATA.getTestData("Item2.Name"))
                .verifyNotificationVisibleOnCart()
                .verifyNotificationTxtOnCartIcon("1");
    }
}
