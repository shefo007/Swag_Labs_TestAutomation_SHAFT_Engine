package com.saucedemo.swaglabs.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {

    private final SHAFT.GUI.WebDriver driver;

    private final static By firstNameField = By.id("first-name");
    private final static By lastNameField = By.id("last-name");
    private final static By postalField = By.id("postal-code");
    private final static By continueBtn = By.id("continue");
    private final static By finishBtn = By.id("finish");
    private final static By orderConformation = By.id("checkout_complete_container");
    private final static By orderConformationMSG = By.cssSelector(".complete-header");
    private final static By errorMsg = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(SHAFT.GUI.WebDriver webDriver) {
        this.driver = webDriver;
    }

    public CheckoutPage verifyRedirectedToCheckoutPage(String endPoint) {
        driver.browser().assertThat().url().contains(endPoint);
        return this;
    }

    public CheckoutPage verifyOrderCompleted() {
        driver.element().assertThat(orderConformation).isVisible();
        return this;
    }

    public CheckoutPage verifyOrderConfirmationMsg(String text) {
        driver.element().assertThat(orderConformationMSG).text().isEqualTo(text);
        return this;
    }

    public CheckoutPage verifyErrorMsg(String text) {
        driver.element().assertThat(errorMsg).text().isEqualTo(text);
        return this;
    }

    @Step("Enter Checkout Info")
    public CheckoutPage fillInfo(String firstName, String lastName, String postalCode) {
        driver.element().type(firstNameField, firstName);
        driver.element().type(lastNameField, lastName);
        driver.element().type(postalField, postalCode);
        return this;
    }

    @Step("Click continue button")
    public CheckoutPage clickContinue() {
        driver.element().click(continueBtn);
        return this;
    }

    @Step("Click finish button")
    public CheckoutPage clickFinish() {
        driver.element().click(finishBtn);
        return this;
    }

}
