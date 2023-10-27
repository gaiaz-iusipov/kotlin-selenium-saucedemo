package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class InventoryPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "title")
    lateinit var title: WebElement

    @FindBy(className = "inventory_item")
    lateinit var items: MutableList<WebElement>

    @FindBy(className = "shopping_cart_link")
    lateinit var cartLink: WebElement

    init {
        PageFactory.initElements(driver, this)
        Assertions.assertEquals("Products", title.text)
    }

    fun addToCart(name: String): InventoryPage {
        var targetItem: WebElement? = null
        for (item in items) {
            val titleElements = item.findElements(By.className("inventory_item_name"))
            if (titleElements.first.text == name) {
                targetItem = item
                break
            }
        }

        targetItem!!.findElement(By.className("btn_inventory")).click()
        return this
    }

    fun goToCart(): CartPage {
        cartLink.click()
        return CartPage(driver)
    }
}