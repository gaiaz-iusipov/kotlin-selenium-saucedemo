import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver

abstract class AbstractTestSuite {
    protected lateinit var driver: WebDriver
    private lateinit var wdm: WebDriverManager

    @BeforeEach
    fun setup() {
        wdm = WebDriverManager.chromedriver()
        driver = wdm.create()
    }

    @AfterEach
    fun teardown() {
        wdm.quit()
    }
}
