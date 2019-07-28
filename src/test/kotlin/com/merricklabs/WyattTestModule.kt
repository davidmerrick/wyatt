package com.merricklabs

import com.codeborne.selenide.webdriver.WebDriverFactory
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.mocks.MockWebDriverFactory
import com.merricklabs.wyatt.mocks.MockWyattS3Client
import org.koin.dsl.module

val WyattTestModule = module {
    single(override = true) { MockWyattS3Client() as WyattS3Client }
    single(override = true) { MockWebDriverFactory() as WebDriverFactory }
}