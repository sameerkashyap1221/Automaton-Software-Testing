
package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartNavigationTests extends BaseTests {

    private void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        page.locator(".inventory_list").waitFor();
    }

    private void openCart() {
        login();
        page.locator(".shopping_cart_link").click();
        page.locator(".cart_list").waitFor();
    }

    @Test
    public void cartPageTitleTest() {
        openCart();
        Assert.assertTrue(page.title().contains("Swag Labs"));
    }

    @Test
    public void cartHeadingTest() {
        openCart();
        Assert.assertEquals(
            page.locator(".title").innerText(), "Your Cart"
        );
    }

    @Test
    public void cartListVisibleTest() {
        openCart();
        Assert.assertTrue(page.locator(".cart_list").isVisible());
    }

    @Test
    public void checkoutButtonVisibleTest() {
        openCart();
        Assert.assertTrue(page.locator("#checkout").isVisible());
    }

    @Test
    public void continueShoppingButtonVisibleTest() {
        openCart();
        Assert.assertTrue(page.locator("#continue-shopping").isVisible());
    }

    @Test
    public void cartBadgeHiddenWhenEmptyTest() {
        openCart();
        Assert.assertEquals(
            page.locator(".shopping_cart_badge").count(), 0
        );
    }

    @Test
    public void cartRemainsEmptyTest() {
        openCart();
        Assert.assertEquals(
            page.locator(".cart_item").count(), 0
        );
    }

    @Test
    public void continueShoppingReturnsToInventoryTest() {
        openCart();
        page.locator("#continue-shopping").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    @Test
    public void cartUrlTest() {
        openCart();
        Assert.assertTrue(page.url().contains("cart.html"));
    }
}
