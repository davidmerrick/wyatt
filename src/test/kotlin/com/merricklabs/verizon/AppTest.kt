package com.merricklabs.verizon

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import kotlin.test.Test

class AppTest {
    @Test
    fun `Test login`() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-mac")
        val browser = ChromeDriver()
        browser.get(LOGIN_URL)
        Thread.sleep(5000)
        enterUsername(browser, "foo")
        enterPassword(browser, "bar")
        Thread.sleep(5000)
        browser.close()
    }

    private fun enterUsername(browser: WebDriver, username: String) {
        val usernameSelector = By.cssSelector("input#$USERNAME_FIELD_ID")
        WebDriverWait(browser, 5)
                .until(ExpectedConditions.elementToBeClickable(usernameSelector))
        val field = browser.findElement(usernameSelector)
        field.click()
        field.sendKeys(username)
    }

    private fun enterPassword(browser: WebDriver, password: String) {
        val usernameSelector = By.cssSelector("input#$PASSWORD_FIELD_ID")
        WebDriverWait(browser, 5)
                .until(ExpectedConditions.elementToBeClickable(usernameSelector))
        val field = browser.findElement(usernameSelector)
        field.click()
        field.sendKeys(password)
    }
}
