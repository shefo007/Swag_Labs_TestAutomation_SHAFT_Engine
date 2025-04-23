package com.saucedemo.swaglabs.featureTests;

import com.saucedemo.swaglabs.basetest.BaseTest;
import com.saucedemo.swaglabs.pages.LoginPage;
import com.saucedemo.swaglabs.pages.ProductsPage;
import org.testng.annotations.Test;

import static com.saucedemo.swaglabs.basetest.DriverManager.getThreadDriver;

public class LoginTests extends BaseTest {


    @Test
    public void LG_01_VerifyLoginWithValidUsernameAndPassword() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin();
        new ProductsPage(getThreadDriver())
                .verifyRedirectedToProductsPage(PRODUCTS_DATA.getTestData("endPoint"))
                .verifyProductsListVisible();
    }

    @Test
    public void LG_02_VerifyLoginWithAnInvalidUsername() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("inValidUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin()
                .verifyErrorMessage(LOGIN_DATA.getTestData("invalidUsernameOrPassword"));
    }

    @Test
    public void LG_03_VerifyLoginWithAnInvalidPassword() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("invalidPassword"))
                .clickLogin()
                .verifyErrorMessage(LOGIN_DATA.getTestData("invalidUsernameOrPassword"));
    }

    @Test
    public void LG_04_VerifyLoginWithBlankUsernameField() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("emptyUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin()
                .verifyErrorMessage(LOGIN_DATA.getTestData("usernameRequired"));
    }

    @Test
    public void LG_05_VerifyLoginWithBlankPasswordField() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("emptyPassword"))
                .clickLogin()
                .verifyErrorMessage(LOGIN_DATA.getTestData("passwordRequired"));
    }

    @Test
    public void LG_06_VerifyPasswordIsMasked() {

        new LoginPage(getThreadDriver())
                .typePassword(LOGIN_DATA.getTestData("emptyPassword"))
                .checkPasswordMasked();
    }

//    @Test
    public void LG_07VerifyLoginWithValidCredentialsAndPressEnterKey() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin();

        new ProductsPage(getThreadDriver())
                .verifyRedirectedToProductsPage(PRODUCTS_DATA.getTestData("endPoint"))
                .verifyProductsListVisible();
    }

//    @Test
    public void LG_08_VerifyLoginWithInvalidCredentialsAndPressEnterKey() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("inValidUsername"))
                .typePassword(LOGIN_DATA.getTestData("invalidPassword"))
                .clickLogin()
                .verifyErrorMessage(LOGIN_DATA.getTestData("invalidUsernameOrPassword"));
    }

    @Test
    public void LG_09_VerifyUserLogout() {

        new LoginPage(getThreadDriver())
                .typeUsername(LOGIN_DATA.getTestData("validUsername"))
                .typePassword(LOGIN_DATA.getTestData("validPassword"))
                .clickLogin();
        new ProductsPage(getThreadDriver())
                .verifyRedirectedToProductsPage(PRODUCTS_DATA.getTestData("endPoint"))
                .verifyProductsListVisible()
                .clickBurgerMenuBtn()
                .clickLogout();
        new LoginPage(getThreadDriver())
                .verifyLoginFormVisibility();
    }

}
