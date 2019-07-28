package com.merricklabs.wyatt.external.verizon

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.merricklabs.wyatt.models.verizon.VerizonBill
import com.merricklabs.wyatt.util.WyattObjectMapper
import org.awaitility.Awaitility.await
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

class VerizonClient : KoinComponent {

    private val mapper by inject<WyattObjectMapper>()
    private val driverFactory by inject<WebDriverFactory>()
    private val driver: WebDriver

    init {
        driver = driverFactory.createWebDriver(null, null)
    }

    fun fetchBill(): VerizonBill {
        driver.get(BILL_URL)
        await().atMost(20, TimeUnit.SECONDS).until {
            driver.currentUrl.contains(BILL_URL)
        }
        val pageSource = driver.findElement(By.cssSelector("pre")).text
        return mapper.readValue(pageSource, VerizonBill::class.java)
    }

    private companion object {
        const val BILL_URL = "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=July+02,+2019"
    }
}