package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class InventoryPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "title")
    private lateinit var title: WebElement

    @FindBy(className = "inventory_list")
    private lateinit var inventoryList: WebElement

    @FindBy(className = "shopping_cart_link")
    private lateinit var cartLink: WebElement

    private val inventoryButtonLocator = By.className("btn_inventory")

    init {
        PageFactory.initElements(driver, this)
        Assertions.assertEquals("Products", title.text)
    }

    fun addToCart(name: String): InventoryPage {
        getInventoryItem(name).findElement(inventoryButtonLocator).click()
        return this
    }

    fun goToCart(): CartPage {
        cartLink.click()
        return CartPage(driver)
    }

    private fun getInventoryItem(name: String): WebElement {
        val inventoryItemXpath = ".//div[text()='$name']/ancestor::div[@class='inventory_item']"
        return inventoryList.findElement(By.xpath(inventoryItemXpath))
    }
}