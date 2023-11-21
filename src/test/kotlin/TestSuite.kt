import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import page.LoginPage

class TestSuite : AbstractTestSuite() {
    private companion object {
        @JvmStatic
        fun getAddItemsToCartArgs() = listOf(
            Arguments.of(
                "standard_user", "secret_sauce", hashMapOf(
                    "Sauce Labs Onesie" to "\$7.99",
                )
            ),
            Arguments.of(
                "standard_user", "secret_sauce", hashMapOf(
                    "Sauce Labs Backpack" to "\$29.99",
                    "Sauce Labs Fleece Jacket" to "\$49.99"
                )
            )
        )
    }

    @ParameterizedTest
    @MethodSource("getAddItemsToCartArgs")
    fun `Add items to a cart`(userName: String, password: String, items: Map<String, String>) {
        driver["https://www.saucedemo.com/"]

        val inventoryPage = LoginPage(driver)
            .login(userName, password)

        for (item in items) {
            inventoryPage.addToCart(item.key)
        }

        val gotItems = inventoryPage
            .goToCart()
            .getItems()

        Assertions.assertEquals(items, gotItems)
    }
}
