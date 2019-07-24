package com.merricklabs.verizon

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class IntegrationTestBase : KoinTest {

    // Workaround for Mockito in Kotlin. See https://medium.com/@elye.project/befriending-kotlin-and-mockito-1c2e7b0ef791
    protected fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    @BeforeMethod
    protected fun beforeMethod() {
        startKoin {
            modules(BillFetcherModule)
        }
    }

    @AfterMethod
    protected fun afterMethod() {
        stopKoin()
    }
}