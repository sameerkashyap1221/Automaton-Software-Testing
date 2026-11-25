
package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdditionalCartTests extends BaseTests {

    @BeforeMethod
    public void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        page.locator(".inventory_list").waitFor();
    }

    // 1. Add all six products to cart
    @Test
    public void addAllProductsTest() {
        page.locator(".inventory_item button").all().forEach(
            button -> button.click()
        );

        Assert.assertEquals(
            page.locator(".shopping_cart_badge").innerText(), "6"
        );
    }

    // 2. Remove all products from cart
    @Test
    public void removeAllProductsTest() {
        // Add all products
        for (int i = 0; i < 6; i++) {
            page.locator(".inventory_item button").nth(i).click();
        }

        // Open cart
        page.locator(".shopping_cart_link").click();

        // Remove products until cart is empty
        while (page.locator(".cart_item").count() > 0) {
            page.locator(".cart_item button").first().click();
        }

        Assert.assertEquals(page.locator(".cart_item").count(), 0);
        Assert.assertFalse(page.locator(".shopping_cart_badge").isVisible());
    }
    
    // 3. Verify cart count after adding three products
    @Test
    public void addThreeProductsTest() {
        for (int i = 0; i < 3; i++) {
            page.locator(".inventory_item button").nth(i).click();
        }

        Assert.assertEquals(
            page.locator(".shopping_cart_badge").innerText(), "3"
        );
    }

    // 4. Continue shopping navigation
    @Test
    public void continueShoppingTest() {
        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();

        page.locator("#continue-shopping").click();

        Assert.assertTrue(page.url().contains("inventory.html"));
    }

    // 5. Verify cart contents remain after sorting
    @Test
    public void cartContentsAfterSortingTest() {
        page.locator(".inventory_item button").first().click();

        String productName =
            page.locator(".inventory_item_name").first().innerText();

        page.locator(".product_sort_container").selectOption("za");
        page.locator(".shopping_cart_link").click();

        Assert.assertEquals(
            page.locator(".inventory_item_name").first().innerText(),
            productName
        );
    }
}
