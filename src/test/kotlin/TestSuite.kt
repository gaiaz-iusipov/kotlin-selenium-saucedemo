import io.github.bonigarcia.seljup.SeleniumJupiter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver
import page.LoginPage

@ExtendWith(SeleniumJupiter::class)
class TestSuite {
    @TestTemplate
    fun `Add items to a cart`(driver: WebDriver) {
        driver.get("https://www.saucedemo.com/")

        val items = hashMapOf(
            "Sauce Labs Backpack" to "\$29.99",
            "Sauce Labs Fleece Jacket" to "\$49.99"
        )

        val inventoryPage = LoginPage(driver)
            .login("standard_user", "secret_sauce")

        for (item in items) {
            inventoryPage.addToCart(item.key)
        }

        val gotItems = inventoryPage
            .goToCart()
            .getItems()

        Assertions.assertEquals(items, gotItems)
    }
}
