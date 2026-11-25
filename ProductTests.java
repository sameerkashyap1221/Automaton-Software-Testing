package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTests extends BaseTests {

    @BeforeMethod
    public void login() {

        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        // Wait until inventory page loads
        page.locator(".product_sort_container").waitFor();

        // Diagnostic information
        System.out.println("Page URL: " + page.url());
        System.out.println("Page Title: " + page.title());

        System.out.println("Inventory items: "
                + page.locator(".inventory_item").count());

        System.out.println("Inventory list: "
                + page.locator(".inventory_list").count());

        System.out.println("Product names: "
                + page.locator(".inventory_item_name").count());

        System.out.println("Product prices: "
                + page.locator(".inventory_item_price").count());
    }

    // Test 1: Inventory page
    @Test
    public void inventoryPageTest() {

        Assert.assertTrue(
                page.locator(".inventory_list").isVisible(),
                "Inventory page is not visible"
        );

        System.out.println("Inventory Page: PASSED");
    }

    // Test 2: Product count
    @Test
    public void productCountTest() {

        Assert.assertEquals(
                page.locator(".inventory_item").count(),
                6,
                "Product count should be 6"
        );

        System.out.println("Product Count: PASSED");
    }

    // Test 3: Product names
    @Test
    public void productNamesTest() {

        Assert.assertEquals(
                page.locator(".inventory_item_name").count(),
                6,
                "Six product names should be displayed"
        );

        Assert.assertTrue(
                page.locator(".inventory_item_name").first().isVisible()
        );

        System.out.println("Product Names: PASSED");
    }

    // Test 4: Product prices
    @Test
    public void productPricesTest() {

        Assert.assertEquals(
                page.locator(".inventory_item_price").count(),
                6,
                "Six product prices should be displayed"
        );

        System.out.println("Product Prices: PASSED");
    }

    // Test 5: Product images
    @Test
    public void productImagesTest() {

        Assert.assertEquals(
                page.locator(".inventory_item_img img").count(),
                6,
                "Six product images should be displayed"
        );

        System.out.println("Product Images: PASSED");
    }

    // Test 6: Add to cart buttons
    @Test
    public void addToCartButtonsTest() {

        Assert.assertEquals(
                page.locator(".inventory_item button").count(),
                6,
                "Six Add to Cart buttons should be displayed"
        );

        System.out.println("Add To Cart Buttons: PASSED");
    }

    // Test 7: Sort products A-Z
    @Test
    public void sortNameAToZTest() {

        page.locator(".product_sort_container").selectOption("az");

        String firstProduct =
                page.locator(".inventory_item_name")
                        .first().innerText();

        Assert.assertEquals(
                firstProduct,
                "Sauce Labs Backpack"
        );

        System.out.println("Sorting A-Z: PASSED");
    }

    // Test 8: Sort products Z-A
    @Test
    public void sortNameZToATest() {

        page.locator(".product_sort_container").selectOption("za");

        String firstProduct =
                page.locator(".inventory_item_name")
                        .first().innerText();

        Assert.assertEquals(
                firstProduct,
                "Test.allTheThings() T-Shirt (Red)"
        );

        System.out.println("Sorting Z-A: PASSED");
    }

    // Test 9: Sort price low to high
    @Test
    public void sortPriceLowToHighTest() {

        page.locator(".product_sort_container").selectOption("lohi");

        String firstPrice =
                page.locator(".inventory_item_price")
                        .first().innerText();

        Assert.assertEquals(firstPrice, "$7.99");

        System.out.println("Price Low-High: PASSED");
    }

    // Test 10: Sort price high to low
    @Test
    public void sortPriceHighToLowTest() {

        page.locator(".product_sort_container").selectOption("hilo");

        String firstPrice =
                page.locator(".inventory_item_price")
                        .first().innerText();

        Assert.assertEquals(firstPrice, "$49.99");

        System.out.println("Price High-Low: PASSED");
    }
}
