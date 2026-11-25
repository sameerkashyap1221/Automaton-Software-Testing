package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingTests extends BaseTests {

    @BeforeMethod
    public void login() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".inventory_list").waitFor();
    }

    // Helper method to get product names
    private List<String> getProductNames() {
        return page.locator(".inventory_item_name").allInnerTexts();
    }

    // Helper method to get product prices
    private List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();

        for (String price :
                page.locator(".inventory_item_price").allInnerTexts()) {
            prices.add(Double.parseDouble(price.replace("$", "")));
        }

        return prices;
    }

    // 1. Verify sorting dropdown
    @Test
    public void sortingDropdownTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container").isVisible()
        );
    }

    // 2. Verify A-Z option
    @Test
    public void sortAZOptionTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container option[value='az']").count() == 1
        );
    }

    // 3. Verify Z-A option
    @Test
    public void sortZAOptionTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container option[value='za']").count() == 1
        );
    }

    // 4. Verify low-high option
    @Test
    public void sortLowHighOptionTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container option[value='lohi']").count() == 1
        );
    }

    // 5. Verify high-low option
    @Test
    public void sortHighLowOptionTest() {
        Assert.assertTrue(
            page.locator(".product_sort_container option[value='hilo']").count() == 1
        );
    }

    // 6. Sort products A-Z
    @Test
    public void sortProductsAZTest() {
        page.locator(".product_sort_container").selectOption("az");

        List<String> names = getProductNames();
        List<String> expected = new ArrayList<>(names);

        Collections.sort(expected);

        Assert.assertEquals(names, expected);
    }

    // 7. Sort products Z-A
    @Test
    public void sortProductsZATest() {
        page.locator(".product_sort_container").selectOption("za");

        List<String> names = getProductNames();
        List<String> expected = new ArrayList<>(names);

        expected.sort(Collections.reverseOrder());

        Assert.assertEquals(names, expected);
    }

    // 8. Sort prices low-high
    @Test
    public void sortPricesLowHighTest() {
        page.locator(".product_sort_container").selectOption("lohi");

        List<Double> prices = getProductPrices();
        List<Double> expected = new ArrayList<>(prices);

        Collections.sort(expected);

        Assert.assertEquals(prices, expected);
    }

    // 9. Sort prices high-low
    @Test
    public void sortPricesHighLowTest() {
        page.locator(".product_sort_container").selectOption("hilo");

        List<Double> prices = getProductPrices();
        List<Double> expected = new ArrayList<>(prices);

        expected.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, expected);
    }

    // 10. Verify first product after A-Z
    @Test
    public void firstProductAZTest() {
        page.locator(".product_sort_container").selectOption("az");

        Assert.assertEquals(
            page.locator(".inventory_item_name").first().innerText(),
            "Sauce Labs Backpack"
        );
    }

    // 11. Verify last product after A-Z
    @Test
    public void lastProductAZTest() {
        page.locator(".product_sort_container").selectOption("az");

        Assert.assertEquals(
            page.locator(".inventory_item_name").last().innerText(),
            "Test.allTheThings() T-Shirt (Red)"
        );
    }

    // 12. Verify first product after Z-A
    @Test
    public void firstProductZATest() {
        page.locator(".product_sort_container").selectOption("za");

        Assert.assertEquals(
            page.locator(".inventory_item_name").first().innerText(),
            "Test.allTheThings() T-Shirt (Red)"
        );
    }

    // 13. Verify last product after Z-A
    @Test
    public void lastProductZATest() {
        page.locator(".product_sort_container").selectOption("za");

        Assert.assertEquals(
            page.locator(".inventory_item_name").last().innerText(),
            "Sauce Labs Backpack"
        );
    }

    // 14. Verify selected sorting option
    @Test
    public void selectedSortingOptionTest() {
        page.locator(".product_sort_container").selectOption("lohi");

        Assert.assertEquals(
            page.locator(".product_sort_container").inputValue(),
            "lohi"
        );
    }

    // 15. Verify product count remains unchanged after sorting
    @Test
    public void productCountAfterSortingTest() {
        page.locator(".product_sort_container").selectOption("hilo");

        Assert.assertEquals(
            page.locator(".inventory_item").count(), 6
        );
    }
}
