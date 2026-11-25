package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTests {

    @BeforeMethod
    public void loginAndAddProduct() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();
        page.locator("#checkout").click();
    }

    @Test
    public void checkoutPageTest() {
        Assert.assertTrue(page.locator("#first-name").isVisible());
        System.out.println("Checkout Page: PASSED");
    }

    @Test
    public void emptyFirstNameTest() {
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.locator("[data-test='error']").isVisible());
        System.out.println("Empty First Name: PASSED");
    }

    @Test
    public void emptyLastNameTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.locator("[data-test='error']").isVisible());
        System.out.println("Empty Last Name: PASSED");
    }

    @Test
    public void emptyPostalCodeTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#continue").click();

        Assert.assertTrue(page.locator("[data-test='error']").isVisible());
        System.out.println("Empty Postal Code: PASSED");
    }

    @Test
    public void validCustomerInformationTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
        System.out.println("Valid Customer Information: PASSED");
    }
}