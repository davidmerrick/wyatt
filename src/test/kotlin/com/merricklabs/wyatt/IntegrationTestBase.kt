package com.merricklabs.wyatt

import com.merricklabs.WyattTestModule
import com.merricklabs.wyatt.config.DEFAULT_BUCKET_NAME
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.nhaarman.mockitokotlin2.given
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

const val INTEGRATION_GROUP = "integration"

open class IntegrationTestBase : KoinTest {

    private val s3Client by inject<WyattS3Client>()

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(listOf(WyattModule, WyattTestModule))
        }

        declareMock<WyattConfig> {
            given { this.s3BucketName }.willReturn(DEFAULT_BUCKET_NAME)
        }

        s3Client.createBucket()
    }

    @AfterMethod
    protected fun afterMethod() {
        s3Client.deleteBucket()
        stopKoin()
    }
}