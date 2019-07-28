package com.merricklabs.wyatt.pages

import org.openqa.selenium.By
import java.time.LocalDateTime

class BillPage : WyattPage() {

    private val billUrl by lazy {
        val month = LocalDateTime.now().month.name.toLowerCase()
        "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=$month+02,+2019"
    }

    override val pageLoadHook = {
        driver.currentUrl.contains(billUrl)
    }

    fun goto() = driver.get(billUrl)

    fun fetchBill(): String {
        return driver.findElement(By.cssSelector("pre")).text
    }
}