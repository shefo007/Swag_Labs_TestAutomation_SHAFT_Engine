package com.saucedemo.swaglabs.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

public class CartPage {

    private final SHAFT.GUI.WebDriver driver;

    private final static By cartItems = By.xpath("//div[@class='cart_item']");
    private final static By cartIconNotification = By.cssSelector(".shopping_cart_badge");
    private final static By checkoutBtn = By.id("checkout");
    //Item Locators
    private final static By productQuantity = By.className("cart_quantity");
    private final static By productName = By.className("inventory_item_name");
    private final static By productDesc = By.className("inventory_item_desc");
    private final static By productPrice = By.className("inventory_item_price");
    private By productLocator;

    public CartPage(SHAFT.GUI.WebDriver webDriver) {
        this.driver = webDriver;
    }

    public CartPage getSpecificProductFromCart(String productName) {
        productLocator = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='cart_item']");
        return this;
    }

    public CartPage verifyProductDesc(String text) {
        By desc = new ByChained(productLocator, productDesc);
        driver.element().assertThat(desc).text().isEqualTo(text);
        return this;
    }

    public CartPage verifyProductPrice(String text) {
        By price = new ByChained(productLocator, productPrice);
        driver.element().assertThat(price).text().isEqualTo(text);
        return this;
    }

    public CartPage verifyProductQuantity(String text) {
        By quantity = new ByChained(productLocator, productQuantity);
        driver.element().assertThat(quantity).text().isEqualTo(text);
        return this;
    }

    public CartPage verifyProductName(String text) {
        By name = new ByChained(productLocator, productName);
        driver.element().assertThat(name).text().isEqualTo(text);
        return this;
    }


    @Step("Remove specific Product from cart: {0}")
    public CartPage removeSpecificProductFromCartPage(String productName) {
        By removeBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='cart_item']//button");
        driver.element().click(removeBtn);
        return this;
    }

    @Step("Check specific product added to cart: {0}")
    public CartPage verifySpecificProductRemovedFromCartPage(String productName) {
        By productLocator = By.xpath("//div[.='" + productName + "']");
        driver.element().assertThat(productLocator).doesNotExist();
        return this;
    }

    @Step("Check is cart empty")
    public CartPage verifyCartIsEmpty() {
        driver.element().assertThat(cartItems).doesNotExist();
        return this;
    }

    public CartPage verifyNotificationVisibleOnCart() {
        driver.element().assertThat(cartIconNotification).isVisible();
        return this;
    }

    public CartPage verifyNotificationNotVisibleOnCart() {
        driver.element().assertThat(cartIconNotification).doesNotExist();
        return this;
    }

    @Step("Click Checkout Button")
    public CartPage clickCheckoutBtn() {
        driver.element().click(checkoutBtn);
        return this;
    }

    public CartPage verifyRedirectedToCartPage(String endPoint) {
        driver.browser().assertThat().url().contains(endPoint);
        return this;
    }

    public CartPage verifyNotificationTxtOnCartIcon(String text) {
        driver.element().assertThat(cartIconNotification).text().isEqualTo(text);
        return this;
    }

}
