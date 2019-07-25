package com.merricklabs.verizon

import com.merricklabs.verizon.handlers.external.VerizonClient
import com.merricklabs.verizon.handlers.util.ChromeWebService
import com.merricklabs.verizon.pages.LoginPage
import okhttp3.OkHttpClient
import org.koin.dsl.module

val BillFetcherModule = module {
    single { LoginPage() }
    single { ChromeWebService() }
    single { OkHttpClient() }
    single { VerizonClient() }
}