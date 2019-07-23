package com.merricklabs.verizon

import com.merricklabs.verizon.pages.LoginPage
import org.openqa.selenium.chrome.ChromeDriver
import kotlin.test.Test

class AppTest {

    @Test
    fun `Test login`() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-mac")
        val browser = ChromeDriver()
        val loginPage = LoginPage(browser)
        loginPage.goto()
        loginPage.enterUsername(System.getenv("USERNAME"))
        loginPage.enterPassword(System.getenv("PASSWORD"))
        loginPage.submit()

        Thread.sleep(5000)
        browser.close()
    }
}
