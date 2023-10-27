package page

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginPage(driver: WebDriver) : AbstractPage(driver) {
    @FindBy(className = "login_logo")
    lateinit var loginLogo: WebElement

    @FindBy(id = "user-name")
    lateinit var userNameField: WebElement

    @FindBy(id = "password")
    lateinit var passwordField: WebElement

    @FindBy(id = "login-button")
    lateinit var loginButton: WebElement

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
