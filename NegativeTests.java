package tests;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTests {

    // Reusable login validation
    private void checkLoginError(String username, String password) {
        page.locator("#user-name").fill(username);
        page.locator("#password").fill(password);
        page.locator("#login-button").click();

        Assert.assertTrue(
            page.locator("[data-test='error']").isVisible(),
            "Login error message was not displayed"
        );
    }

    // 1. Invalid username
    @Test
    public void invalidUsernameTest() {
        checkLoginError("wrong_user", "secret_sauce");
    }

    // 2. Invalid password
    @Test
    public void invalidPasswordTest() {
        checkLoginError("standard_user", "wrong_password");
    }

    // 3. Both credentials invalid
    @Test
    public void bothInvalidCredentialsTest() {
        checkLoginError("wrong", "wrong");
    }

    // 4. Empty username
    @Test
    public void emptyUsernameTest() {
        checkLoginError("", "secret_sauce");
    }

    // 5. Empty password
    @Test
    public void emptyPasswordTest() {
        checkLoginError("standard_user", "");
    }

    // 6. Both fields empty
    @Test
    public void emptyBothFieldsTest() {
        checkLoginError("", "");
    }

    // 7. Locked user
    @Test
    public void lockedUserTest() {
        checkLoginError("locked_out_user", "secret_sauce");
    }

    // 8. Username with incorrect case
    @Test
    public void caseSensitiveUsernameTest() {
        checkLoginError("STANDARD_USER", "secret_sauce");
    }

    // 9. Password with incorrect case
    @Test
    public void caseSensitivePasswordTest() {
        checkLoginError("standard_user", "SECRET_SAUCE");
    }

    // 10. Username containing spaces
    @Test
    public void usernameWithSpacesTest() {
        checkLoginError("standard user", "secret_sauce");
    }

    // Reusable method to open checkout
    private void loginAndOpenCheckout() {
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();

        page.locator(".inventory_list").waitFor();

        page.locator(".inventory_item button").first().click();
        page.locator(".shopping_cart_link").click();
        page.locator("#checkout").click();

        page.locator("#first-name").waitFor();
    }

    // Reusable checkout validation
    private void checkCheckoutError(
            String firstName,
            String lastName,
            String postalCode) {

        loginAndOpenCheckout();

        page.locator("#first-name").fill(firstName);
        page.locator("#last-name").fill(lastName);
        page.locator("#postal-code").fill(postalCode);

        page.locator("#continue").click();

        Assert.assertTrue(
            page.locator("[data-test='error']").isVisible(),
            "Checkout validation error was not displayed"
        );
    }

    // 11. Empty first name
    @Test
    public void emptyFirstNameTest() {
        checkCheckoutError("", "Kumar", "141001");
    }

    // 12. Empty last name
    @Test
    public void emptyLastNameTest() {
        checkCheckoutError("Aryan", "", "141001");
    }

    // 13. Empty postal code
    @Test
    public void emptyPostalCodeTest() {
        checkCheckoutError("Aryan", "Kumar", "");
    }

    // 14. All checkout fields empty
    @Test
    public void allCheckoutFieldsEmptyTest() {
        checkCheckoutError("", "", "");
    }

    // 15. First and last names empty
    @Test
    public void firstAndLastNameEmptyTest() {
        checkCheckoutError("", "", "141001");
    }

    // 16. First name and postal code empty
    @Test
    public void firstNameAndPostalCodeEmptyTest() {
        checkCheckoutError("", "Kumar", "");
    }

    // 17. Last name and postal code empty
    @Test
    public void lastNameAndPostalCodeEmptyTest() {
        checkCheckoutError("Aryan", "", "");
    }

    // 18. Only first name provided
    @Test
    public void onlyFirstNameProvidedTest() {
        checkCheckoutError("Aryan", "", "");
    }

    // 19. Only last name provided
    @Test
    public void onlyLastNameProvidedTest() {
        checkCheckoutError("", "Kumar", "");
    }

    // 20. Only postal code provided
    @Test
    public void onlyPostalCodeProvidedTest() {
        checkCheckoutError("", "", "141001");
    }
}
