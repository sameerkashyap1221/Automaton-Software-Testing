package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTests {

    @BeforeMethod
    public void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".product_sort_container").waitFor();
    }

    // Test 1: Add one product
    @Test
    public void addOneProductTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();

        Assert.assertEquals(
            page.locator(".shopping_cart_badge").innerText(), "1"
        );

        System.out.println("Add One Product: PASSED");
    }

    // Test 2: Add multiple products
    @Test
    public void addMultipleProductsTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator("#add-to-cart-sauce-labs-bike-light").click();

        Assert.assertEquals(
            page.locator(".shopping_cart_badge").innerText(), "2"
        );

        System.out.println("Add Multiple Products: PASSED");
    }

    // Test 3: Verify cart badge
    @Test
    public void cartBadgeCountTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();

        Assert.assertTrue(
            page.locator(".shopping_cart_badge").isVisible()
        );

        System.out.println("Cart Badge Count: PASSED");
    }

    // Test 4: Open shopping cart
    @Test
    public void openCartTest() {
        page.locator(".shopping_cart_link").click();

        Assert.assertTrue(
            page.url().contains("cart.html")
        );

        System.out.println("Open Cart: PASSED");
    }

    // Test 5: Verify product name
    @Test
    public void verifyProductNameTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator(".shopping_cart_link").click();

        Assert.assertEquals(
            page.locator(".inventory_item_name").innerText(),
            "Sauce Labs Backpack"
        );

        System.out.println("Product Name: PASSED");
    }

    // Test 6: Verify product price
    @Test
    public void verifyProductPriceTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator(".shopping_cart_link").click();

        Assert.assertEquals(
            page.locator(".inventory_item_price").innerText(),
            "$29.99"
        );

        System.out.println("Product Price: PASSED");
    }

    // Test 7: Verify quantity
    @Test
    public void verifyProductQuantityTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator(".shopping_cart_link").click();

        Assert.assertEquals(
            page.locator(".cart_quantity").innerText(),
            "1"
        );

        System.out.println("Product Quantity: PASSED");
    }

    // Test 8: Remove product
    @Test
    public void removeProductTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator(".shopping_cart_link").click();
        page.locator("#remove-sauce-labs-backpack").click();

        Assert.assertEquals(
            page.locator(".cart_item").count(), 0
        );

        System.out.println("Remove Product: PASSED");
    }

    // Test 9: Verify badge after removal
    @Test
    public void cartBadgeAfterRemovalTest() {
        page.locator("#add-to-cart-sauce-labs-backpack").click();
        page.locator("#remove-sauce-labs-backpack").click();

        Assert.assertEquals(
            page.locator(".shopping_cart_badge").count(), 0
        );

        System.out.println("Badge After Removal: PASSED");
    }

    // Test 10: Verify empty cart
    @Test
    public void emptyCartTest() {
        page.locator(".shopping_cart_link").click();

        Assert.assertEquals(
            page.locator(".cart_item").count(), 0
        );

        System.out.println("Empty Cart: PASSED");
    }
}
