package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "login_logo")
    private lateinit var loginLogo: WebElement

    @FindBy(id = "user-name")
    private lateinit var userNameField: WebElement

    @FindBy(id = "password")
    private lateinit var passwordField: WebElement

    @FindBy(id = "login-button")
    private lateinit var loginButton: WebElement

    init {
        PageFactory.initElements(driver, this)
        Assertions.assertEquals("Swag Labs", loginLogo.text)
    }

    fun login(userName: String, password: String): InventoryPage {
        userNameField.sendKeys(userName)
        passwordField.sendKeys(password)
        loginButton.click()

        return InventoryPage(driver)
    }
}
