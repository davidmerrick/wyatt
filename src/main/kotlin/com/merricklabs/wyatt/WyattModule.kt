package com.merricklabs.wyatt

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.external.aws.WyattS3ClientImpl
import com.merricklabs.wyatt.external.verizon.VerizonClient
import com.merricklabs.wyatt.handlers.logic.WyattLogic
import com.merricklabs.wyatt.pages.LoginPage
import com.merricklabs.wyatt.pages.SecurityQuestionPage
import com.merricklabs.wyatt.util.LambdaWebDriverFactory
import com.merricklabs.wyatt.util.WyattObjectMapper
import okhttp3.OkHttpClient
import org.koin.dsl.module

val WyattModule = module {
    single { LoginPage() }
    single { SecurityQuestionPage() }
    single { OkHttpClient() }
    single { VerizonClient() }
    single { WyattObjectMapper() }
    single { WyattLogic() }
    single { WyattS3ClientImpl() as WyattS3Client }
    single { WyattConfig() }
    single { LambdaWebDriverFactory() as WebDriverFactory }
}