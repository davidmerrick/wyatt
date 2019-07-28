package com.merricklabs.wyatt.pages

import com.codeborne.selenide.webdriver.WebDriverFactory
import org.awaitility.Awaitility.await
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

abstract class WyattPage : KoinComponent {
    protected val driverFactory by inject<WebDriverFactory>()
    protected val driver: WebDriver

    init {
        driver = driverFactory.createWebDriver(null, null)!!
    }

    abstract val pageLoadHook: () -> Boolean

    fun load() {
        await().atMost(10, TimeUnit.SECONDS).until {
            try {
                pageLoadHook.invoke()
            } catch (e: Exception) {
                false
            }
        }
    }
}