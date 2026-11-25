package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTests {

    private void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        page.locator(".inventory_list").waitFor();
    }

    private void addProductAndOpenCart() {
        login();
        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();
    }

    private void openCheckoutOverview() {
        addProductAndOpenCart();
        page.locator("#checkout").click();
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();
    }

    @Test
    public void loginToInventoryTest() {
        login();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    @Test
    public void productDetailsNavigationTest() {
        login();
        page.locator(".inventory_item_name").first().click();
        Assert.assertTrue(page.locator(".inventory_details_name").isVisible());
    }

    @Test
    public void backToProductsTest() {
        login();
        page.locator(".inventory_item_name").first().click();
        page.locator("#back-to-products").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    @Test
    public void inventoryToCartTest() {
        login();
        page.locator(".shopping_cart_link").click();
        Assert.assertTrue(page.url().contains("cart.html"));
    }

    @Test
    public void cartToInventoryTest() {
        addProductAndOpenCart();
        page.locator("#continue-shopping").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    @Test
    public void cartToCheckoutTest() {
        addProductAndOpenCart();
        page.locator("#checkout").click();
        Assert.assertTrue(page.url().contains("checkout-step-one.html"));
    }

    @Test
    public void checkoutToOverviewTest() {
        openCheckoutOverview();
        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    @Test
    public void overviewToCompletionTest() {
        openCheckoutOverview();
        page.locator("#finish").click();
        Assert.assertTrue(page.url().contains("checkout-complete.html"));
    }

    @Test
    public void completionToInventoryTest() {
        openCheckoutOverview();
        page.locator("#finish").click();
        page.locator("#back-to-products").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    @Test
    public void logoutToLoginTest() {
        login();
        page.locator("#react-burger-menu-btn").click();
        page.locator("#logout_sidebar_link").click();
        Assert.assertTrue(page.locator("#login-button").isVisible());
    }
}
