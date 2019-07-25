package com.merricklabs.verizon.handlers.external

import com.google.common.net.HttpHeaders
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.openqa.selenium.Cookie

class VerizonClient : KoinComponent {

    private val okHttpClient by inject<OkHttpClient>()

    fun fetchBillJson(cookies: Set<Cookie>): String {
        val request = Request.Builder()
                .url(BILL_URL)
                .addHeader(HttpHeaders.COOKIE, cookies.joinToString(";"))
                .get()
                .build()
        val response = okHttpClient.newCall(request).execute()
        return response.body()!!.string()
    }

    private companion object {
        const val BILL_URL = "https://myvpostpay.verizonwireless.com/ui/bill/data/ao/digital/details?stmtDate=July+02,+2019"
    }
}