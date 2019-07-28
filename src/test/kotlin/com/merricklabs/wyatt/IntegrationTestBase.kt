package com.merricklabs.wyatt

import com.merricklabs.WyattTestModule
import com.merricklabs.wyatt.config.DEFAULT_BUCKET_NAME
import com.merricklabs.wyatt.external.aws.WyattS3Client
import com.merricklabs.wyatt.external.aws.forceDeleteBucket
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

const val INTEGRATION_GROUP = "integration"

open class IntegrationTestBase : KoinTest {

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(listOf(WyattModule, WyattTestModule))
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