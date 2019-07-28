package com.merricklabs.wyatt.util

import com.codeborne.selenide.Config
import com.codeborne.selenide.webdriver.WebDriverFactory
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class LambdaWebDriverFactory : WebDriverFactory() {

    private val lambdaChromeOptions: ChromeOptions
        get() {
            val options = ChromeOptions()
            options.setBinary(getLibLocation("chrome"))
            options.addArguments("--disable-gpu")
            options.addArguments("--headless")
            options.addArguments("--window-size=1366,768")
            options.addArguments("--single-process")
            options.addArguments("--no-sandbox")
            options.addArguments("--user-data-dir=/tmp/user-data")
            options.addArguments("--data-path=/tmp/data-path")
            options.addArguments("--homedir=/tmp")
            options.addArguments("--disk-cache-dir=/tmp/cache-dir")
            return options
        }

    init {
        System.setProperty("webdriver.chrome.driver", getLibLocation("chromedriver"))
    }

    private fun getLibLocation(lib: String): String {
        return String.format("%s/lib/%s", System.getenv("LAMBDA_TASK_ROOT"), lib)
    }

    override fun createWebDriver(config: Config?, proxy: Proxy?): WebDriver? {
        return ChromeDriver(lambdaChromeOptions)
    }
}