package com.merricklabs.wyatt.pages

import com.fasterxml.jackson.databind.ObjectMapper
import com.merricklabs.wyatt.models.verizon.VerizonBill
import org.koin.core.inject
import org.openqa.selenium.By

class BillPage : WyattPage() {

    override val pageLoadHook = {
        driver.currentUrl.contains(BILL_URL)
    }

    private val mapper by inject<ObjectMapper>()

    fun goto() = driver.get(BILL_URL)

    fun fetchBill(): VerizonBill {
        val pageSource = driver.findElement(By.cssSelector("pre")).text
        return mapper.readValue(pageSource, VerizonBill::class.java)
    }

    private companion object {
        const val BILL_URL = "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=July+02,+2019"
    }
}