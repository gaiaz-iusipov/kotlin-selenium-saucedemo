package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class CartPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "title")
    private lateinit var title: WebElement

    @FindBy(className = "cart_item")
    private lateinit var items: MutableList<WebElement>

    private val inventoryItemNameLocator = By.className("inventory_item_name")
    private val inventoryItemPriceLocator = By.className("inventory_item_price")

    init {
        PageFactory.initElements(driver, this)
        Assertions.assertEquals("Your Cart", title.text)
    }

    fun getItems(): Map<String, String> {
        val data = HashMap<String, String>(items.size)
        for (item in items) {
            val name = item.findElement(inventoryItemNameLocator).text
            val price = item.findElement(inventoryItemPriceLocator).text

            data[name] = price
        }

        return data
    }
}