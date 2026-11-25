package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends BaseTests {

    @BeforeMethod
    public void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        page.locator(".inventory_list").waitFor();
    }

    @Test
    public void menuButtonTest() {
        Assert.assertTrue(page.locator("#react-burger-menu-btn").isVisible());
    }

    @Test
    public void openMenuTest() {
        page.locator("#react-burger-menu-btn").click();
        Assert.assertTrue(page.locator("#logout_sidebar_link").isVisible());
    }

    @Test
    public void logoutButtonTest() {
        page.locator("#react-burger-menu-btn").click();
        Assert.assertTrue(page.locator("#logout_sidebar_link").isVisible());
    }

    @Test
    public void successfulLogoutTest() {
        page.locator("#react-burger-menu-btn").click();
        page.locator("#logout_sidebar_link").click();
        Assert.assertTrue(page.locator("#login-button").isVisible());
    }

    @Test
    public void loginPageAfterLogoutTest() {
        page.locator("#react-burger-menu-btn").click();
        page.locator("#logout_sidebar_link").click();
        Assert.assertTrue(page.url().equals("https://www.saucedemo.com/"));
    }
}