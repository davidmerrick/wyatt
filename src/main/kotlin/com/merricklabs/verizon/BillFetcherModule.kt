package com.merricklabs.verizon

import com.merricklabs.verizon.handlers.util.ChromeWebService
import com.merricklabs.verizon.pages.LoginPage
import org.koin.dsl.module

val BillFetcherModule = module {
    single { LoginPage() }
    single { ChromeWebService() }
}