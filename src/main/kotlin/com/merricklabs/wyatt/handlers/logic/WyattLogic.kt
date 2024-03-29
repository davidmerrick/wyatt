package com.merricklabs.wyatt.handlers.logic

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.pages.BillPage
import com.merricklabs.wyatt.pages.LoginPage
import com.merricklabs.wyatt.pages.SecurityQuestionPage
import mu.KotlinLogging
import org.awaitility.Awaitility.await
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

private val log = KotlinLogging.logger {}

class WyattLogic : KoinComponent {
    private val config by inject<WyattConfig>()
    private val wyattS3Client by inject<WyattS3Client>()
    private val driverFactory by inject<WebDriverFactory>()
    private val loginPage by inject<LoginPage>()
    private val securityPage by inject<SecurityQuestionPage>()
    private val billPage by inject<BillPage>()

    private val driver: WebDriver

    init {
        driver = driverFactory.createWebDriver(null, null)
    }

    fun handleRequest() {
        log.info("Spinning up Chrome")
        try {
            loginToVerizon()
        } catch (e: Exception) {
            log.error("There was a problem logging into Verizon. Saving screenshot.", e)
            saveScreenshot()
            throw e
        }

        billPage.goto()
        billPage.load()
        val bill = billPage.fetchBill()
        log.info("Success: Fetched bill. Bill length: ${bill.length}")

        wyattS3Client.uploadBill(bill)
    }

    private fun saveScreenshot() {
        driver as TakesScreenshot
        val screenshot = driver.getScreenshotAs(OutputType.FILE)
        wyattS3Client.uploadScreenshot(screenshot)
    }

    private fun loginToVerizon() {
        loginPage.goto()
        loginPage.load()
        loginPage.enterUsername(config.verizon.username)
        loginPage.enterPassword(config.verizon.password)
        loginPage.submit()

        // Verizon will sometimes throw up a "we don't recognize this device" page
        try {
            securityPage.load()
            securityPage.enterSecurityAnswer(config.verizon.securityAnswer)
            securityPage.submit()
        } catch (e: Exception) {
            // It's probably fine. Continue and confirm we're logged in
            log.error { e }
        }

        await().atMost(10, TimeUnit.SECONDS).until {
            loginPage.isLoggedIn()
        }
    }
}