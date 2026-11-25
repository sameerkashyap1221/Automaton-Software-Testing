package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutOverviewTests extends BaseTests {

    @BeforeMethod
    public void prepareCheckoutOverview() {
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

        page.locator(".summary_info").waitFor();
    }

    @Test
    public void overviewPageTest() {
        Assert.assertTrue(page.locator(".summary_info").isVisible());
        System.out.println("Overview Page: PASSED");
    }

    @Test
    public void productNameTest() {
        Assert.assertTrue(page.locator(".inventory_item_name").isVisible());
        System.out.println("Product Name: PASSED");
    }

    @Test
    public void productPriceTest() {
        Assert.assertTrue(page.locator(".inventory_item_price").isVisible());
        System.out.println("Product Price: PASSED");
    }

    @Test
    public void paymentInformationTest() {
        Assert.assertTrue(page.locator(".summary_value_label").first().isVisible());
        System.out.println("Payment Information: PASSED");
    }

    @Test
    public void shippingInformationTest() {
        Assert.assertTrue(page.locator(".summary_value_label").nth(1).isVisible());
        System.out.println("Shipping Information: PASSED");
    }

    @Test
    public void itemTotalTest() {
        Assert.assertTrue(page.locator(".summary_subtotal_label").isVisible());
        System.out.println("Item Total: PASSED");
    }

    @Test
    public void finalTotalTest() {
        Assert.assertTrue(page.locator(".summary_total_label").isVisible());
        System.out.println("Final Total: PASSED");
    }
}