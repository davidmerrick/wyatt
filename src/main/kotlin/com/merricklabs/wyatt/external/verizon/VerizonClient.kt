package com.merricklabs.wyatt.external.verizon

import com.google.common.net.HttpHeaders
import com.merricklabs.wyatt.models.verizon.VerizonBill
import com.merricklabs.wyatt.util.WyattObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.Cookie

class VerizonClient : KoinComponent {

    private val okHttpClient by inject<OkHttpClient>()
    private val mapper by inject<WyattObjectMapper>()


    fun fetchBill(cookies: Set<Cookie>): VerizonBill {
        val request = Request.Builder()
                .url(BILL_URL)
                .addHeader(HttpHeaders.COOKIE, cookies.joinToString(";"))
                .get()
                .build()
        val response = okHttpClient.newCall(request).execute()
        return mapper.readValue(response.body()!!.string(), VerizonBill::class.java)
    }

    private companion object {
        const val BILL_URL = "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=July+02,+2019"
    }
}