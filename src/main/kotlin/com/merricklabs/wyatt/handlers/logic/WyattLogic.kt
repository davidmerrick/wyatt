package com.merricklabs.wyatt.handlers.logic

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.external.verizon.VerizonClient
import com.merricklabs.wyatt.pages.LoginPage
import mu.KotlinLogging
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.WebDriver

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    private val config by inject<WyattConfig>()
    private val wyattS3Client by inject<WyattS3Client>()
    private val driverFactory by inject<WebDriverFactory>()
    private val loginPage by inject<LoginPage>()
    private val verizonClient by inject<VerizonClient>()
    private val mapper by inject<ObjectMapper>()

    private val driver: WebDriver

    init {
        driver = driverFactory.createWebDriver(null, null)
    }

    fun handleRequest() {
        log.info("Spinning up Chrome")
        loginPage.goto()
        loginPage.enterUsername(config.verizon.username)
        loginPage.enterPassword(config.verizon.password)
        loginPage.submit()
        Thread.sleep(2000)

        val bill = verizonClient.fetchBill(driver.manage().cookies)
        wyattS3Client.s3.putObject(
                config.s3BucketName,
                "bill",
                mapper.writeValueAsString(bill)
        )
    }
}