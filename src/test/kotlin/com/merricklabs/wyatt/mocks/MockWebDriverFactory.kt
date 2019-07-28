package com.merricklabs.wyatt.mocks

import com.codeborne.selenide.Config
import com.codeborne.selenide.webdriver.WebDriverFactory
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class MockWebDriverFactory : WebDriverFactory() {

    private val driver by lazy {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-mac")
        ChromeDriver(chromeOptions)
    }

    private val chromeOptions: ChromeOptions
        get() {
            val options = ChromeOptions()
            options.addArguments("--disable-gpu")
            options.addArguments("--headless")
            options.addArguments("--window-size=1366,768")
            options.addArguments("--single-process")
            options.addArguments("--no-sandbox")
            return options
        }

    override fun createWebDriver(config: Config?, proxy: Proxy?): WebDriver? {
        return driver
    }

}