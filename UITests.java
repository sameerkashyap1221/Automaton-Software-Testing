package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UITests extends BaseTests {

    @BeforeMethod
    public void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        page.locator(".inventory_list").waitFor();
    }

    // 1. Application logo visibility
    @Test
    public void applicationLogoTest() {
        Assert.assertTrue(page.locator(".app_logo").isVisible());
    }

    // 2. Products page title
    @Test
    public void productsTitleTest() {
        Assert.assertEquals(page.locator(".title").innerText(), "Products");
    }

    // 3. Product list visibility
    @Test
    public void productListVisibilityTest() {
        Assert.assertTrue(page.locator(".inventory_list").isVisible());
    }

    // 4. Product count
    @Test
    public void productCountTest() {
        Assert.assertEquals(page.locator(".inventory_item").count(), 6);
    }

    // 5. Product names visibility
    @Test
    public void productNamesTest() {
        Assert.assertEquals(page.locator(".inventory_item_name").count(), 6);
    }

    // 6. Product prices visibility
    @Test
    public void productPricesTest() {
        Assert.assertEquals(page.locator(".inventory_item_price").count(), 6);
    }

    // 7. Product images visibility
    @Test
    public void productImagesTest() {
        Assert.assertEquals(
            page.locator(".inventory_item_img img").count(), 6
        );
    }

    // 8. Add to cart buttons visibility
    @Test
    public void addToCartButtonsTest() {
        Assert.assertEquals(
            page.locator(".inventory_item button").count(), 6
        );
    }

    // 9. Shopping cart icon visibility
    @Test
    public void shoppingCartIconTest() {
        Assert.assertTrue(
            page.locator(".shopping_cart_link").isVisible()
        );
    }

    // 10. Menu button visibility
    @Test
    public void menuButtonTest() {
        Assert.assertTrue(
            page.locator("#react-burger-menu-btn").isVisible()
        );
    }

    // 11. Sorting dropdown visibility
    @Test
    public void sortingDropdownTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container").isVisible()
        );
    }

    // 12. Footer visibility
    @Test
    public void footerVisibilityTest() {
        page.locator("footer").scrollIntoViewIfNeeded();
        Assert.assertTrue(page.locator("footer").isVisible());
    }

    // 13. Product description visibility
    @Test
    public void productDescriptionTest() {
        Assert.assertTrue(
            page.locator(".inventory_item_desc").first().isVisible()
        );
    }

    // 14. Cart badge after adding product
    @Test
    public void cartBadgeTest() {
        page.locator(".inventory_item button").first().click();

        Assert.assertTrue(
            page.locator(".shopping_cart_badge").isVisible()
        );
    }

    // 15. Checkout button visibility
    @Test
    public void checkoutButtonTest() {
        page.locator(".inventory_item button").first().click();

        page.locator(".shopping_cart_link").click();

        page.locator("#checkout").waitFor();

        Assert.assertTrue(
            page.locator("#checkout").isVisible(),
            "Checkout button is not visible"
        );
    }
    
    // 16. Back to products navigation
    @Test
    public void backToProductsTest() {
        page.locator(".inventory_item_name").first().click();
        page.locator("#back-to-products").click();

        Assert.assertTrue(page.url().contains("inventory.html"));
    }
}
