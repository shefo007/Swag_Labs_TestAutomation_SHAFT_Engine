package com.saucedemo.swaglabs.pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class LoginPage {

    private final SHAFT.GUI.WebDriver driver;
    private final static By usernameFieldTxt = By.id("user-name");
    private final static By passwordFieldTxt = By.id("password");
    private final static By loginBtn = By.id("login-button");
    private final static By errorMessage = By.cssSelector("h3[data-test='error']");
    private final static By loginContainer = By.id("login_button_container");

    public LoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    @Step("Entering username: {0}")
    public LoginPage typeUsername(String username) {
        driver.element().clear(usernameFieldTxt).type(usernameFieldTxt, username);
        return this;
    }

    @Step("Entering password: {0}")
    public LoginPage typePassword(String password) {
        driver.element().clear(passwordFieldTxt).type(passwordFieldTxt, password);
        return this;
    }

    @Step("Clicking Login Button")
    public LoginPage clickLogin() {
        driver.element().click(loginBtn);
        return this;
    }

    @Step("Press Enter Key")
    public LoginPage pressEnterKeyValidCredentials() {

        return this;
    }


    public LoginPage verifyErrorMessage(String text) {
        driver.element().assertThat(errorMessage).text().isEqualTo(text);
        return this;
    }

    public LoginPage checkPasswordMasked() {
        driver.element().assertThat(passwordFieldTxt)
                .attribute("type").isEqualTo("password");
        return this;
    }

    public LoginPage verifyLoginFormVisibility() {
        driver.element().assertThat(loginContainer).isVisible();
        return this;
    }
}
