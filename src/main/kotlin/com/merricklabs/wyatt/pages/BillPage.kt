package com.merricklabs.wyatt.pages

import org.openqa.selenium.By

class BillPage : WyattPage() {

    override val pageLoadHook = {
        driver.currentUrl.contains(BILL_URL)
    }

    fun goto() = driver.get(BILL_URL)

    fun fetchBill(): String {
        return driver.findElement(By.cssSelector("pre")).text
    }

    private companion object {
        const val BILL_URL = "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=July+02,+2019"
    }
}