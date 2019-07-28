package com.merricklabs.wyatt.handlers.logic

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.WebDriver

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    private val config by inject<WyattConfig>()
    private val wyattS3Client by inject<WyattS3Client>()
    private val driverFactory by inject<WebDriverFactory>()
    private val driver: WebDriver

    init {
        driver = driverFactory.createWebDriver(null, null)
    }


    fun handleRequest() {
        log.info("Spinning up Chrome")
        driver.get("https://www.google.com")
        log.info("url: ${driver.currentUrl}")
//        wyattS3Client.s3.putObject(config.s3BucketName, "foo", "banana")
    }
}