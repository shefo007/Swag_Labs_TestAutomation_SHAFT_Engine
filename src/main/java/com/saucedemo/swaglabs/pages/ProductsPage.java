package com.saucedemo.swaglabs.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class ProductsPage {

    private final SHAFT.GUI.WebDriver driver;
    private final static By productsList = By.className("inventory_list");
    private final static By burgerMenuBtn = By.id("react-burger-menu-btn");
    private final static By logoutLink = By.id("logout_sidebar_link");
    private final static By cartIcon = By.cssSelector(".shopping_cart_link");
    private final static By cartIconNotification = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage verifyRedirectedToProductsPage(String endPoint) {
        driver.browser().assertThat().url().contains(endPoint);
        return this;
    }

    public ProductsPage verifyProductsListVisible() {
        driver.element().assertThat(productsList).isVisible();
        return this;
    }

    @Step("Click on Burger Menu Icon")
    public ProductsPage clickBurgerMenuBtn() {
        driver.element().click(burgerMenuBtn);
        return this;
    }

    @Step("Click on Logout")
    public ProductsPage clickLogout() {
        driver.element().click(logoutLink);
        return this;
    }

    @Step("Click on CartPage Icon")
    public ProductsPage clickCartIcon() {
        driver.element().click(cartIcon);
        return this;
    }

    @Step("Adding specific Product to cart: {0}")
    public ProductsPage addSpecificProductToCart(String productName) {
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.element().click(addToCartBtn);
        return this;
    }

    @Step("Remove specific Product from cart: {0}")
    public ProductsPage removeSpecificProductFromCart(String productName) {
        By removeBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.element().click(removeBtn);
        return this;
    }

    @Step("Check specific product added to cart: {0}")
    public ProductsPage verifyProductAddedToCart(String productName) {
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.element().assertThat(addToCartBtn).text().isEqualTo("Remove");
        return this;
    }

    @Step("Check specific product removed from cart: {0}")
    public ProductsPage verifyProductRemovedFromCart(String productName) {
        By addToCartBtn = By.xpath("//div[.='" + productName + "']/ancestor::div[@class='inventory_item']//button");
        driver.element().assertThat(addToCartBtn).text().isEqualTo("Add to cart");
        return this;
    }

    public ProductsPage verifyNotificationTxtOnCartIcon(String text) {
        driver.element().assertThat(cartIconNotification).text().isEqualTo(text);
        return this;
    }

    public ProductsPage verifyNotificationVisibleOnCart() {
        driver.element().assertThat(cartIconNotification).isVisible();
        return this;
    }

    public ProductsPage verifyNotificationNotVisibleOnCart() {
        driver.element().assertThat(cartIconNotification).doesNotExist();
        return this;
    }
}
