package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class CartPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "title")
    lateinit var title: WebElement

    @FindBy(className = "cart_item")
    lateinit var items: MutableList<WebElement>

    init {
        PageFactory.initElements(driver, this)
        Assertions.assertEquals("Your Cart", title.text)
    }

    fun getItems(): Map<String, String> {
        val data = HashMap<String, String>(items.size)
        for (item in items) {
            val name = item.findElement(By.className("inventory_item_name")).text
            val price = item.findElement(By.className("inventory_item_price")).text

            data[name] = price
        }
        return data
    }
}