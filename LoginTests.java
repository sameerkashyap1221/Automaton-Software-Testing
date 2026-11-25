package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {

    @Test
    public void validLoginTest() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        Assert.assertTrue(
            page.url().contains("inventory.html"),
            "Valid login failed"
        );

        System.out.println("Valid Login: PASSED");
    }

    @Test
    public void invalidLoginTest() {
        page.locator("#user-name").fill("wrong_user");
        page.locator("#password").fill("wrong_password");
        page.locator("#login-button").click();

        Assert.assertTrue(
            page.locator("[data-test='error']").isVisible(),
            "Error message was not displayed"
        );

        System.out.println("Invalid Login: PASSED");
    }

    @Test
    public void emptyLoginTest() {
        page.locator("#login-button").click();

        Assert.assertTrue(
            page.locator("[data-test='error']").isVisible(),
            "Empty login validation failed"
        );

        System.out.println("Empty Login: PASSED");
    }
}
