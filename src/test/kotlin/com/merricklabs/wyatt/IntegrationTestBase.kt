package com.merricklabs.wyatt

import com.merricklabs.wyatt.config.DEFAULT_REGION
import com.merricklabs.wyatt.config.WyattConfig
import com.nhaarman.mockitokotlin2.given
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

const val DEFAULT_BUCKET_NAME = "wyatt-bills"

open class IntegrationTestBase : KoinTest {

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(WyattModule)
        }

        declareMock<WyattConfig> {
            given { this.s3BucketName }.willReturn(DEFAULT_BUCKET_NAME)
            given { this.s3Endpoint }.willReturn("http://localhost:4563")
            given { this.awsRegion }.willReturn(DEFAULT_REGION)
        }
    }

    @AfterMethod
    protected fun afterMethod() {
        stopKoin()
    }
}