package com.merricklabs.wyatt

import com.merricklabs.WyattTestModule
import com.merricklabs.wyatt.config.DEFAULT_REGION
import com.merricklabs.wyatt.config.WyattConfig
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.external.aws.forceDeleteBucket
import com.nhaarman.mockitokotlin2.given
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.mock.declareMock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

const val DEFAULT_BUCKET_NAME = "wyatt-bills"

open class IntegrationTestBase : KoinTest {

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(listOf(WyattModule, WyattTestModule))
        }

        declareMock<WyattConfig> {
            given { this.s3BucketName }.willReturn(DEFAULT_BUCKET_NAME)
            given { this.s3Endpoint }.willReturn("http://localhost:4572")
            given { this.awsRegion }.willReturn(DEFAULT_REGION)
        }

        val s3Factory = get<WyattS3Client>()
        s3Factory.s3.createBucket(DEFAULT_BUCKET_NAME)
    }

    @AfterMethod
    protected fun afterMethod() {
        val wyattS3Client = get<WyattS3Client>()
        wyattS3Client.s3.forceDeleteBucket(DEFAULT_BUCKET_NAME)
        stopKoin()
    }
}