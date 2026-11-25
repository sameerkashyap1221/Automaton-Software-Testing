package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutValidationTests extends BaseTests {

    @BeforeMethod
    public void loginAndOpenCheckout() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".inventory_list").waitFor();

        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();
        page.locator("#checkout").click();

        page.locator("#first-name").waitFor();
    }

    // 1. First name containing numbers
    @Test
    public void firstNameWithNumbersTest() {
        page.locator("#first-name").fill("Aryan123");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 2. Last name containing numbers
    @Test
    public void lastNameWithNumbersTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar123");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 3. First name with special characters
    @Test
    public void firstNameSpecialCharactersTest() {
        page.locator("#first-name").fill("@Aryan!");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 4. Last name with special characters
    @Test
    public void lastNameSpecialCharactersTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Ku@mar!");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 5. Postal code containing letters
    @Test
    public void postalCodeWithLettersTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("ABC123");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 6. Postal code with special characters
    @Test
    public void postalCodeSpecialCharactersTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("@#$%");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 7. Long first name
    @Test
    public void longFirstNameTest() {
        page.locator("#first-name").fill("A".repeat(100));
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 8. Long last name
    @Test
    public void longLastNameTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("K".repeat(100));
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 9. Long postal code
    @Test
    public void longPostalCodeTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("123456789012345");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
    }

    // 10. Valid customer information
    @Test
    public void validCheckoutInformationTest() {
        page.locator("#first-name").fill("Aryan");
        page.locator("#last-name").fill("Kumar");
        page.locator("#postal-code").fill("141001");
        page.locator("#continue").click();

        Assert.assertTrue(page.url().contains("checkout-step-two.html"));
        Assert.assertTrue(page.locator(".summary_info").isVisible());
    }
}
