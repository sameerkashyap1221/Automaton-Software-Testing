package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderCompletionTests extends BaseTests {

    @BeforeMethod
    public void prepareOrder() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();
        page.locator("#checkout").click();

        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        page.locator("#finish").click();
        page.locator(".complete-header").waitFor();
    }

    @Test
    public void orderConfirmationTest() {
        Assert.assertTrue(page.locator(".complete-header").isVisible());
        System.out.println("Order Confirmation: PASSED");
    }

    @Test
    public void confirmationMessageTest() {
        Assert.assertTrue(
            page.locator(".complete-header").innerText()
                .contains("Thank you for your order")
        );
        System.out.println("Confirmation Message: PASSED");
    }

    @Test
    public void orderDispatchMessageTest() {
        Assert.assertTrue(page.locator(".complete-text").isVisible());
        System.out.println("Dispatch Message: PASSED");
    }

    @Test
    public void backHomeButtonTest() {
        Assert.assertTrue(page.locator("#back-to-products").isVisible());
        System.out.println("Back Home Button: PASSED");
    }

    @Test
    public void returnToProductsTest() {
        page.locator("#back-to-products").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
        System.out.println("Return to Products: PASSED");
    }
}